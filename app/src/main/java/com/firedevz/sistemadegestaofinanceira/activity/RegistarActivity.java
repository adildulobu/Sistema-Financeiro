package com.firedevz.sistemadegestaofinanceira.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.firedevz.sistemadegestaofinanceira.MenuPrincipal;
import com.firedevz.sistemadegestaofinanceira.R;
import com.firedevz.sistemadegestaofinanceira.ajuda.Validacoes;
import com.firedevz.sistemadegestaofinanceira.modelo.Usuario;
import com.firedevz.sistemadegestaofinanceira.sql.BaseDados;
import com.firedevz.sistemadegestaofinanceira.sql.DatabaseHelper;


public class RegistarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNomeCompleto, edtEmail, edtCelular,edtEndereco, edtSenha, edtRepeteSenha;
    private Button btnRegistar;

    private DatabaseHelper db;
    private Usuario usuario;

    private  final AppCompatActivity activity = RegistarActivity.this;
    private NestedScrollView nestedScrollView;

    private Validacoes validacoes;

    InicioActivity inicio = new InicioActivity();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        inicializaComponentes();
        eventoClikes();
        inicializaObjectos();
    }

    private void inicializaObjectos() {
        db = new DatabaseHelper(activity);
        usuario = new Usuario();

    }

    private void eventoClikes() {
        btnRegistar.setOnClickListener(this);

    }

    private void inicializaComponentes() {
        edtNomeCompleto = findViewById(R.id.edtNomeCompleto);
        edtEmail = findViewById(R.id.edtEmail);
        edtCelular = findViewById(R.id.edtCelular);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtSenha = findViewById(R.id.edtSenha);
        edtRepeteSenha = findViewById(R.id.edtRepeteSenha);
        btnRegistar = findViewById(R.id.btnRegistar);

    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.btnRegistar:
                postDataToSQLite();
                break;
        }
    }

    private void postDataToSQLite(){
        if(!db.verificaUsuario(edtCelular.getText().toString().trim())){

            usuario.setNome((edtNomeCompleto.getText().toString().trim()));
            usuario.setEmail(edtEmail.getText().toString().trim());
            usuario.setTelefone(edtCelular.getText().toString().trim());
            usuario.setEndereco(edtEndereco.getText().toString().trim());
            usuario.setSenha(edtSenha.getText().toString().trim());


            //inicio.showProgressDialog();
           if(validarFormulario()) {
               //inicio.showProgressDialog();
               Toast.makeText(RegistarActivity.this, "Usuario Registrado com Sucesso", Toast.LENGTH_LONG).show();
               db.addUsuario(usuario);
               //inicio.hideProgressDialog();

               Intent i = new Intent(getApplicationContext(),LoginActivity.class);
               startActivity(i);
            }else
           {
               // inicio.hideProgressDialog();
               Toast.makeText(RegistarActivity.this, "Preencha Todos os Campos obrigatorios!", Toast.LENGTH_LONG).show();

           }

        }
        else{
            // inicio.hideProgressDialog();
            Toast.makeText(RegistarActivity.this, "O numero introduzido ja possui uma conta! ", Toast.LENGTH_LONG).show();
        }
    }

    private void emptyInputEdt() {
        edtNomeCompleto.setText(null);
        edtEmail.setText(null);
        edtCelular.setText(null);
        edtEndereco.setText(null);
        edtSenha.setText(null);
        edtRepeteSenha.setText(null);
    }

    private boolean validarFormulario() {
        boolean valid = true;

//        String email = edtEmail.getText().toString();
//        if (TextUtils.isEmpty(email)) {
//            edtEmail.setError("Campo Obrigatório.");
//            valid = false;
//        } else {
//            edtEmail.setError(null);
//        }

        String senha = edtSenha.getText().toString();
        if (TextUtils.isEmpty(senha)) {
            edtSenha.setError("Campo Obrigatório.");
            valid = false;
        } else {
            edtSenha.setError(null);
        }

        String repeteSenha = edtRepeteSenha.getText().toString();
        if (TextUtils.isEmpty(repeteSenha)) {
            edtRepeteSenha.setError("Campo Obrigatório.");
            valid = false;
        } else {
            edtRepeteSenha.setError(null);
        }

        String celular = edtCelular.getText().toString();
        if (TextUtils.isEmpty(celular)) {
            edtCelular.setError("Campo Obrigatório..");
            valid = false;
        } else {
            edtCelular.setError(null);
        }

        String nomeCompleto = edtNomeCompleto.getText().toString();
        if (TextUtils.isEmpty(nomeCompleto)) {
            edtNomeCompleto.setError("Campo Obrigatório..");
            valid = false;
        } else {
            edtNomeCompleto.setError(null);
        }
        String endereco = edtEndereco.getText().toString();
        if (TextUtils.isEmpty(endereco)) {
            edtEndereco.setError("Campo Obrigatório..");
            valid = false;
        } else {
            edtEndereco.setError(null);
        }


        return valid;
    }
}
