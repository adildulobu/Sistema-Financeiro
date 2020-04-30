package com.firedevz.sistemadegestaofinanceira.activity;

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
import com.firedevz.sistemadegestaofinanceira.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class EfectuarVendasActivity extends AppCompatActivity {

    private ListView lstConta,lstProdutos;
    private Button btnPagar,btnFacturar;
    private Spinner spnCategorias;
    private TextView txtconta;
    private int total;
    private DatabaseHelper db = new DatabaseHelper(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;


    activityListaProdutos lista = new activityListaProdutos();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efectuar_vendas);


        btnPagar = (Button) findViewById(R.id.btnPagar);
        btnFacturar = (Button) findViewById(R.id.btnFacturar);
        spnCategorias = (Spinner) findViewById(R.id.spnCategorias);
        lstConta = (ListView) findViewById(R.id.lstConta);
        lstProdutos = (ListView) findViewById(R.id.lstProdutos);
        txtconta = (TextView) findViewById(R.id.txtconta);

       // listaProdutos();

        lstProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) lstProdutos.getItemAtPosition(position);

                Toast.makeText(EfectuarVendasActivity.this, "Adicionado a conta: " + conteudo, Toast.LENGTH_LONG).show();
                String codigo = conteudo.substring(0, conteudo.indexOf(":"));
                Produtos produtos = db.selecionarProduto((codigo));

                String nomePro=produtos.getNome();
                float preco=produtos.getPreco();

                total+=preco;

                listaConta(nomePro,preco);
                txtconta.setText("Total: "+total+".00 MNZ");
            }
        });



    }



    public void listaProdutos(String categoria) {
        List<Produtos> produtos = db.listaTodosProdutos(categoria);

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(EfectuarVendasActivity.this, android.R.layout.simple_list_item_1,arrayList);

        lstProdutos.setAdapter(adapter);

        for(Produtos c: produtos){
            // Log.d("Lista","\nID: " + c.getCodigo() + "Nome: "+c.getNome());
            arrayList.add(c.getId()+ ":                "+c.getNome()+ "             "+c.getPreco()+"MZN");
            adapter.notifyDataSetChanged();

        }
    }

    public void listaConta(String produto,float preco) {
       // List<Produtos> produtos = db.listaTodosProdutos();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(EfectuarVendasActivity.this, android.R.layout.simple_list_item_1,arrayList);

        lstConta.setAdapter(adapter);

      //  for(Produtos c: produtos){
            // Log.d("Lista","\nID: " + c.getCodigo() + "Nome: "+c.getNome());
            arrayList.add(produto+"  "+preco+" MZN");
            adapter.notifyDataSetChanged();

       // }
    }


}
