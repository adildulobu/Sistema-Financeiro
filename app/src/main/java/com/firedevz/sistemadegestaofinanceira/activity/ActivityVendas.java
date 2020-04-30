package com.firedevz.sistemadegestaofinanceira.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firedevz.sistemadegestaofinanceira.R;
import com.firedevz.sistemadegestaofinanceira.activity.EfectuarVendasActivity;

public class ActivityVendas extends AppCompatActivity {

    private Button btnEfectuarVendas,btnVendasPendentes,btnHistoricoVendas,btnRequisicoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);
        inicializaComponentes();
        eventoClikes();

    }

    private void eventoClikes() {

        btnEfectuarVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EfectuarVendasActivity.class);
                startActivity(i);
            }
        });

    }

    private void inicializaComponentes() {
        btnEfectuarVendas=(Button)findViewById(R.id.btnEfectuarVendas);
    }

}
