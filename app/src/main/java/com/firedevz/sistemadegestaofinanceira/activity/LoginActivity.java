package com.firedevz.sistemadegestaofinanceira.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firedevz.sistemadegestaofinanceira.MenuPrincipal;
import com.firedevz.sistemadegestaofinanceira.R;
import com.firedevz.sistemadegestaofinanceira.ajuda.Validacoes;
import com.firedevz.sistemadegestaofinanceira.sql.BaseDados;
import com.firedevz.sistemadegestaofinanceira.sql.DatabaseHelper;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private final AppCompatActivity activity = LoginActivity.this;

    private EditText edtTelefoneLogin,edtSenhaLogin;
    private Button btnGuardarSenha,btnEntrar,btnFacebook,btnGmail;
    private TextView tv5,tvCriarConta;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutSenha;
    private NestedScrollView nestedScrollView;

    private Validacoes validacoes;
    private DatabaseHelper db;

    InicioActivity inicio = new InicioActivity();


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializaComponentes();
        eventoClikes();
        inicializaObjectos();
    }

    private void inicializaObjectos() {
        db = new DatabaseHelper(activity);
        validacoes = new Validacoes(activity);
    }

    private void eventoClikes() {
        //Criar Conta
        tvCriarConta.setOnClickListener(this);

        //Repor senha
        tv5.setOnClickListener(this);

        //Login usando facebook
        btnFacebook.setOnClickListener(this);

        //login usando gmail
        btnGmail.setOnClickListener(this);

        //login
        btnEntrar.setOnClickListener(this);
        }


    private void inicializaComponentes() {
        edtTelefoneLogin=(EditText)findViewById(R.id.edtTelefoneLogin);
        edtSenhaLogin=(EditText)findViewById(R.id.edtSenhaLogin);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnGuardarSenha=(Button)findViewById(R.id.btnGuardarSenha);
        btnFacebook=(Button)findViewById(R.id.btnFacebook);
        btnGmail=(Button)findViewById(R.id.btnGmail);
        tv5=(TextView)findViewById(R.id.tv5);
        tvCriarConta=(TextView)findViewById(R.id.tvCriarConta);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                verifyFromSQLite();
                break;
            case R.id.tvCriarConta:
                Intent it = new Intent(getApplicationContext(),RegistarActivity.class);
                startActivity(it);
                break;
        }
    }

    private void verifyFromSQLite(){

        if(db.verificaUsuario(edtTelefoneLogin.getText().toString().trim()
            , edtSenhaLogin.getText().toString().trim())){
            Intent it = new Intent(getApplicationContext(),MenuPrincipal.class);
            startActivity(it);
        }else {
        //    Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show;
            Toast.makeText(LoginActivity.this, "Celular ou Senha Incorrectos, verifique e tente novamente", Toast.LENGTH_LONG).show();
            emptyInputEdt();
        }
    }

    private void emptyInputEdt(){
        edtTelefoneLogin.setText(null);
        edtSenhaLogin.setText(null);
    }


}
