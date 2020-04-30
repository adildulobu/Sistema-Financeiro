package com.firedevz.sistemadegestaofinanceira.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firedevz.sistemadegestaofinanceira.R;
import com.firedevz.sistemadegestaofinanceira.sql.DatabaseHelper;

public class ActivityFundos extends AppCompatActivity {

    private TextView mTextMessage;
    private Button btnOutrasContas;
    private TextView txSaldoCaixa;
    private ListView lstMovimentos;

    DatabaseHelper db = new DatabaseHelper(this);

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
        setContentView(R.layout.activity_fundos);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btnOutrasContas = (Button) findViewById(R.id.btnOutrasContas);
        txSaldoCaixa = (TextView) findViewById(R.id.txSaldoCaixa);
        lstMovimentos = (ListView) findViewById(R.id.lstMovimentos);

        txSaldoCaixa.setText(db.valorCaixa()+".00 MZN");

    }

}
