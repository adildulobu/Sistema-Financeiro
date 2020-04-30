package com.firedevz.sistemadegestaofinanceira.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firedevz.sistemadegestaofinanceira.R;
import com.firedevz.sistemadegestaofinanceira.modelo.Produtos;
import com.firedevz.sistemadegestaofinanceira.modelo.Rendimento;
import com.firedevz.sistemadegestaofinanceira.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class RendimentosActivity extends AppCompatActivity {

    //private TextView mTextMessage;

    EditText edtDescricao,edtValor,edtData;
    Button btnLimpar,btnSalvar,btnCExcluir;
    ListView lstViewRendimentos;
    Spinner spnTipo;

    DatabaseHelper db = new DatabaseHelper(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adpOpcoes;


//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
//            }
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendimentos);
//
//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        edtDescricao = (EditText)findViewById(R.id.edtDescricao);
        edtValor = (EditText)findViewById(R.id.edtValor);
        edtData = (EditText)findViewById(R.id.edtData);
        spnTipo = (Spinner)findViewById(R.id.spnTipo);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnCExcluir = (Button) findViewById(R.id.btnExcluir);
        lstViewRendimentos = (ListView) findViewById(R.id.lstViewRendimentos);

        listaRendimentos();
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });

        lstViewRendimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) lstViewRendimentos.getItemAtPosition(position);

                // Toast.makeText(MainActivity.this, "Select: " + conteudo, Toast.LENGTH_LONG).show();
                String descricao = conteudo.substring(0, conteudo.indexOf("-"));

                Rendimento rendimento = db.selecionarRendimento(descricao);
                edtDescricao.setText(rendimento.getDescricao());
                edtValor.setText((int) Double.parseDouble(String.valueOf(rendimento.getValor())));
                edtData.setText(rendimento.getData());
                spnTipo.getSelectedItem();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String descricao = edtDescricao.getText().toString();
                String valor = edtValor.getText().toString();
                String tipo = spnTipo.getSelectedItem().toString();
                String data = edtData.getText().toString();

                if (descricao.isEmpty() ) {
                    edtDescricao.setError("campo Obrigatorio");
                    edtValor.setError("campo Obrigatorio");

                } else {
                    //insert
                    db.addRendimetno(new Rendimento(descricao, Double.parseDouble(valor), tipo, data));
                    Toast.makeText(RendimentosActivity.this, "Rendimento adicionado com Sucesso", Toast.LENGTH_LONG).show();
                    limpaCampos();
                    listaRendimentos();
                }
//                } else {
//                    //update
//                    db.actualizaCliente(new Cliente(Integer.parseInt(codigo),nome, telefone, email));
//                    Toast.makeText(MainActivity.this, "Cliente Actualizado com Sucesso", Toast.LENGTH_LONG).show();
//                    limpaCampos();
//                    listaClientes();
//                }

            }



        });

        btnCExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao = edtDescricao.getText().toString();

                if(descricao.isEmpty()){
                    Toast.makeText(RendimentosActivity.this, "Selecione uma Rendimento", Toast.LENGTH_LONG).show();
                } else{
                    Rendimento rendimento = new Rendimento();
                    rendimento.setDescricao(descricao);
                    db.apagarRendimento(rendimento);

                    Toast.makeText(RendimentosActivity.this, "Rendimento Excluido com Sucesso", Toast.LENGTH_LONG).show();
                    limpaCampos();
                    listaRendimentos();

                }

            }
        });



        adpOpcoes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adpOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipo.setAdapter(adpOpcoes);

        adpOpcoes.add("Empresariais");
        adpOpcoes.add("Profissionais");
        adpOpcoes.add("Renda");
        adpOpcoes.add("Juro");
        adpOpcoes.add("Lucro");
        adpOpcoes.add("Incrementos Patrimoniais e indemnizações");
        adpOpcoes.add("Pensão");
        adpOpcoes.add("Outro");
        adpOpcoes.add("Outro");


    }





    void limpaCampos() {
        edtData.setText("");
        edtDescricao.setText("");
        edtValor.setText("");
        edtDescricao.requestFocus();
    }

    public void listaRendimentos() {
        List<Rendimento> rendimentos = db.listaTodosRendimentos();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(RendimentosActivity.this, android.R.layout.simple_list_item_1,arrayList);

        lstViewRendimentos.setAdapter(adapter);

        for(Rendimento c: rendimentos){
            // Log.d("Lista","\nID: " + c.getCodigo() + "Nome: "+c.getNome());
            arrayList.add(c.getDescricao()+ "       "+c.getValor()+ "       "+c.getData());
            adapter.notifyDataSetChanged();

        }
    }



}
