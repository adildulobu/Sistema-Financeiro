package com.firedevz.sistemadegestaofinanceira.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firedevz.sistemadegestaofinanceira.R;
import com.firedevz.sistemadegestaofinanceira.modelo.Produtos;
import com.firedevz.sistemadegestaofinanceira.sql.DatabaseHelper;

import java.util.ArrayList;

public class ActivityAdicionarProdutos extends Activity implements View.OnClickListener {

    private TextView mTextMessage;

    private EditText edtID, edtNomeProduto, edtData,edtPrazo, edtPreco, edtPrecoVenda,edtQuantidade,edtNotificarEstoque;
    private Button btnAdicionarPro;
    private Spinner spnCategoria,spnUnidade;

    DatabaseHelper db = new DatabaseHelper(this);

    private Produtos produtos;

    private ArrayAdapter<String > adpUnidade;
    private ArrayAdapter<String > adpCategoria;
    ArrayList<String> arrayList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produtos);

        edtNomeProduto = (EditText)findViewById(R.id.edtNomeProduto);
        edtData = (EditText)findViewById(R.id.edtData);
        edtPrazo = (EditText)findViewById(R.id.edtPrazo);
        edtPreco = (EditText)findViewById(R.id.edtPreco);
        edtPrecoVenda = (EditText)findViewById(R.id.edtPrecoVenda);
        edtQuantidade = (EditText)findViewById(R.id.edtQuantidade);
        edtNotificarEstoque = (EditText)findViewById(R.id.edtNotificarEstoque);
       // edtID = (EditText)findViewById(R.id.edtID);

        btnAdicionarPro = (Button) findViewById(R.id.btnAdicionarPro);

        spnCategoria = (Spinner) findViewById(R.id.spnCategoria);
        spnUnidade = (Spinner) findViewById(R.id.spnUnidade);

        btnAdicionarPro.setOnClickListener(this);




        adpCategoria = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategoria.setAdapter(adpCategoria);

        adpCategoria.add("bebidas Alcoolicas");
        adpCategoria.add("Bebidas nao alcoolicas");
        adpCategoria.add("Mariscos");
        adpCategoria.add("Carnes");
        adpCategoria.add("Vegetais");
        adpCategoria.add("Cereais");
        adpCategoria.add("Doces e salgados");
        adpCategoria.add("Outro");



        adpUnidade = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpUnidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnUnidade.setAdapter(adpUnidade);

        adpUnidade.add("uni");
        adpUnidade.add("kg");
        adpUnidade.add("Litros");
        adpUnidade.add("chot");
        adpUnidade.add("doze");
        adpUnidade.add("Caixas");

    }

    @Override
    public void onClick(View v) {

        if(v == btnAdicionarPro) {

            //String codigo = edtID.getText().toString();
            String nome = edtNomeProduto.getText().toString();
            String data = edtData.getText().toString();
            String categoria = (String) spnCategoria.getSelectedItem();
            String prazo = edtPrazo.getText().toString();
            String preco = edtPreco.getText().toString();
            String precoVenda = edtPrecoVenda.getText().toString();
            String quantidade = edtQuantidade.getText().toString();
            String Unidade = (String) spnUnidade.getSelectedItem();
            String estoqueMinimo = edtNotificarEstoque.getText().toString();




            if (validarFormulario()) {
                Toast.makeText(ActivityAdicionarProdutos.this, "Produto adicionado com Sucesso", Toast.LENGTH_LONG).show();
                db.addProduto(new Produtos(nome, data,categoria, prazo, Float.parseFloat(precoVenda), Float.parseFloat(preco), Integer.parseInt(quantidade), Unidade, Integer.parseInt(estoqueMinimo)));
                limpaCampos();


            }else
            {
                // inicio.hideProgressDialog();
                Toast.makeText(ActivityAdicionarProdutos.this, "Preencha Todos os Campos obrigatorios", Toast.LENGTH_LONG).show();

            }


//            } else {
//                //update
//                db.actualizaCliente(new Produtos(Integer.parseInt(codigo),nome, telefone, email));
//                Toast.makeText(ActivityAdicionarProdutos.this, "Produto Actualizado com Sucesso", Toast.LENGTH_LONG).show();
//                limpaCampos();
//            }

//
//            String item = edtNome.getText().toString();
//
//            item += "-" + spnOpcoes.getSelectedItem();
//
//            adpDados.add(item);
//        }
//        else
//        if(v == btnRemover){
//            if(adpDados.getCount() >0){
//                String item = adpDados.getItem(0);
//                adpDados.remove(item);
//            }
        }else{
            // inicio.hideProgressDialog();
            Toast.makeText(ActivityAdicionarProdutos.this, "Não é possivel Registar um produto existente! ", Toast.LENGTH_LONG).show();
        }

    }

    public void limpaCampos() {

       // edtID.setText("");
        edtNomeProduto.setText("");
        edtData.setText("");
        edtPrazo.setText("");
        edtPreco.setText("");
        edtPrecoVenda.setText("");
        edtQuantidade.setText("");
        edtNotificarEstoque.setText("");

        edtNomeProduto.requestFocus();
    }

    private boolean validarFormulario() {
        boolean valid = true;

        String quant = edtQuantidade.getText().toString();
        if (TextUtils.isEmpty(quant)) {
            edtQuantidade.setError("Campo Obrigatório.");
            valid = false;
        } else {
            edtQuantidade.setError(null);
        }

//        String data = edtData.getText().toString();
//        if (TextUtils.isEmpty(data)) {
//            edtData.setError("Campo Obrigatório.");
//            valid = false;
//        } else {
//            edtData.setError(null);
//        }

//        String prazo = edtPrazo.getText().toString();
//        if (TextUtils.isEmpty(prazo)) {
//            edtPrazo.setError("Campo Obrigatório.");
//            valid = false;
//        } else {
//            edtPrazo.setError(null);
//        }

        String preco = edtPreco.getText().toString();
        if (TextUtils.isEmpty(preco)) {
            edtPreco.setError("Campo Obrigatório..");
            valid = false;
        } else {
            edtPreco.setError(null);
        }

        String nomeProduto = edtNomeProduto.getText().toString();
        if (TextUtils.isEmpty(nomeProduto)) {
            edtNomeProduto.setError("Campo Obrigatório..");
            valid = false;
        } else {
            edtNomeProduto.setError(null);
        }
        String precoVenda = edtPrecoVenda.getText().toString();
        if (TextUtils.isEmpty(precoVenda)) {
            edtPrecoVenda.setError("Campo Obrigatório..");
            valid = false;
        } else {
            edtPrecoVenda.setError(null);
        }


        return valid;
    }
}
