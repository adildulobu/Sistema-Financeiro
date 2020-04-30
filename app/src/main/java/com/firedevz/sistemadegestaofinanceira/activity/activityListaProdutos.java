package com.firedevz.sistemadegestaofinanceira.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firedevz.sistemadegestaofinanceira.R;
import com.firedevz.sistemadegestaofinanceira.modelo.Produtos;
import com.firedevz.sistemadegestaofinanceira.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class activityListaProdutos extends Activity implements View.OnClickListener   {


    private Button btnApagarProduto,btnAdicionarProduto,btnEditarProdut;
    private ListView lstProdutos;
    private Spinner spnCategoriaPro;


    DatabaseHelper db = new DatabaseHelper(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    private ArrayAdapter<String > adpCategoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        btnApagarProduto = (Button) findViewById(R.id.btnApagarProduto);
        btnAdicionarProduto = (Button) findViewById(R.id.btnAdicionarProduto);
        btnEditarProdut = (Button) findViewById(R.id.btnEditarProdut);
        lstProdutos = (ListView) findViewById(R.id.lstProdutos);
        spnCategoriaPro = (Spinner) findViewById(R.id.spnCategoriaPro);


        adpCategoria = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategoriaPro.setAdapter(adpCategoria);

        adpCategoria.add("todos");
        adpCategoria.add("bebidas Alcoolicas");
        adpCategoria.add("Bebidas nao alcoolicas");
        adpCategoria.add("Mariscos");
        adpCategoria.add("Carnes");
        adpCategoria.add("Vegetais");
        adpCategoria.add("Cereais");
        adpCategoria.add("Doces e salgados");
        adpCategoria.add("Outro");

        String categoria = (String)spnCategoriaPro.getSelectedItem().toString();
        Toast.makeText(activityListaProdutos.this, "Categoria selecionada: "+categoria, Toast.LENGTH_LONG).show();

        listaProdutos(categoria);


        lstProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(view==lstProdutos) {

                    String conteudo = (String) lstProdutos.getItemAtPosition(position);

                    Toast.makeText(activityListaProdutos.this, "Selecionado: " + conteudo, Toast.LENGTH_LONG).show();
                    String codigo = conteudo.substring(0, conteudo.indexOf(":"));

                    Produtos produtos = db.selecionarProduto((codigo));
//                edtCodigo.setText(String.valueOf(cliente.getCodigo()));
//                edtNome.setText(cliente.getNome());
//                edtTelefone.setText(cliente.getTelefone());
//                edtEmail.setText(cliente.getEmail());
                }
            }
        });





    }

    public void listaProdutos(String categor) {
        List<Produtos> produtos = db.listaTodosProdutos(categor);

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(activityListaProdutos.this, android.R.layout.simple_list_item_1,arrayList);

        lstProdutos.setAdapter(adapter);

        for(Produtos c: produtos){
            // Log.d("Lista","\nID: " + c.getCodigo() + "Nome: "+c.getNome());
            arrayList.add(c.getId()+ ":                "+c.getNome()+ "             "+c.getPreco()+"MZN" + "      "+c.getQuantidade());
            adapter.notifyDataSetChanged();

        }
    }


    @Override
    public void onClick(View v) {

        //Apagar
        if(v== btnApagarProduto) {
            Toast.makeText(activityListaProdutos.this, "Selecione um Produto", Toast.LENGTH_LONG).show();
            lstProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedFromList = (lstProdutos.getItemAtPosition(position).toString());
                    String codigo = selectedFromList.substring(0, selectedFromList.indexOf(":"));
                    Produtos produtos = new Produtos();
                    produtos.setId(Integer.parseInt(codigo));
                    db.apagarProduto(produtos);
                    Toast.makeText(activityListaProdutos.this, "Produto Excluido com Sucesso", Toast.LENGTH_LONG).show();
                    String catego = "todos";
                    listaProdutos(catego);
                }
            });
               }


        //Adicionar
        if (v == btnAdicionarProduto) {
            Intent i = new Intent(getApplicationContext(), ActivityAdicionarProdutos.class);
            startActivity(i);

        }



        }










        /////Fim
}
