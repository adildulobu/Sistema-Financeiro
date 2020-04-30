package com.firedevz.sistemadegestaofinanceira.sql;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;


import com.firedevz.sistemadegestaofinanceira.modelo.Produtos;
import com.firedevz.sistemadegestaofinanceira.modelo.Usuario;

import java.util.*;


public class BaseDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static String BANCO_GESTAO = "bd_Sigefi.db";


//************************TABELAS***************************************************************************************************************************************

    //Delaracao Tabela USUARIO
    private static String TABELA_USUARIO = "tb_usuarios";

    private static String COLUNA_ID_USUARIO = "id";
    private static String COLUNA_NOME_USUARIO = "nome";
    private static String COLUNA_TELEFONE_USUARIO = "telefone";
    private static String COLUNA_EMAIL_USUARIO = "email";
    private static String COLUNA_ENDERECO_USUARIO = "endereço";
    private static String COLUNA_SENHA_USUARIO = "senha";

    String[] columns = {
            COLUNA_ID_USUARIO,
            COLUNA_NOME_USUARIO,
            COLUNA_TELEFONE_USUARIO,
            COLUNA_EMAIL_USUARIO,
            COLUNA_ENDERECO_USUARIO,
            COLUNA_SENHA_USUARIO

    };

    //Declaracao tabela produto

    private static String TABELA_PRODUTO = "tb_usuarios";

    private static String COLUNA_ID_PRODUTO = "id";
    private static String COLUNA_NOME_PRODUTO = "Descrição";
    private static String COLUNA_PRECO_COMPRA_PRODUTO = "preço de compra";
    private static String COLUNA_PRECO_VENDA_PRODUTO = "preço";
    private static String COLUNA_VALIDADE_PRODUTO= "prazo";
    private static String COLUNA_QUANTIDADE_PRODUTO = "categoria";

    String[] colunasProduto = {
            COLUNA_ID_PRODUTO,
            COLUNA_NOME_PRODUTO,
            COLUNA_PRECO_COMPRA_PRODUTO,
            COLUNA_PRECO_VENDA_PRODUTO,
            COLUNA_VALIDADE_PRODUTO,
            COLUNA_QUANTIDADE_PRODUTO
    };


    //**************************************************************************************************************************************************************
    //**************************************************************************************************************************************************************

    public BaseDados(Context context) {
        super(context, BANCO_GESTAO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }



    //*****************************************************************************************************************************************************
    //*****************************************************************************************************************************************************

    //CRIAR TABELA USUARIO
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABELA_USUARIO + "("
            + COLUNA_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_NOME_USUARIO + " VARCHAR,"
            + COLUNA_TELEFONE_USUARIO + " VARCHAR, " + COLUNA_EMAIL_USUARIO + " VARCHAR, " + COLUNA_ENDERECO_USUARIO + " VARCHAR, " + COLUNA_SENHA_USUARIO + " VARCHAR" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABELA_USUARIO;





    //CRIAR TABELA PRODUTOS
    private String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABELA_PRODUTO + "("
            + COLUNA_ID_PRODUTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_NOME_PRODUTO + " VARCHAR,"
            + COLUNA_PRECO_COMPRA_PRODUTO + " DOUBLE, " + COLUNA_PRECO_VENDA_PRODUTO + " DOUBLE, " + COLUNA_VALIDADE_PRODUTO + " DATETIME, " + COLUNA_QUANTIDADE_PRODUTO + " INTEGER" + ")";

    private String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + TABELA_PRODUTO;

//    public BaseDados(Context context) {
//        super(context, BANCO_GESTAO, null, VERSAO_BANCO);
//    }



//********************************************************************************************************************************************************************8
    //Metodo Adiciona Usuario
    public boolean addUsuario(Usuario usuario) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put(COLUNA_ID_USUARIO, usuario.getId());

        values.put(COLUNA_NOME_USUARIO, usuario.getNome());
        values.put(COLUNA_TELEFONE_USUARIO, usuario.getTelefone());
        values.put(COLUNA_EMAIL_USUARIO, usuario.getEmail());
        values.put(COLUNA_ENDERECO_USUARIO, usuario.getEndereco());
        values.put(COLUNA_SENHA_USUARIO, usuario.getSenha());
        long verficainsert = db.insert(TABELA_USUARIO, null, values);

        Cursor cursor = db.query(TABELA_USUARIO, columns, COLUNA_ID_USUARIO +"="+verficainsert, null, null, null, null);
        cursor.moveToFirst();
        Usuario usuario1= new Usuario();
        usuario1.setId(cursor.getInt(0));
        usuario1.setNome(cursor.getString(1));
        usuario1.setTelefone(cursor.getString(2));
        usuario1.setEmail(cursor.getString(3));
        usuario1.setEndereco(cursor.getString(4));
        usuario1.setSenha(cursor.getString(5));
        cursor.close();
        db.close();
        if(verficainsert != -1){
            return true;
        }

        return false;
    }



    //********************************************************************************************************************************************************************8
    //Metodo Adiciona ADD produtos
    public boolean addProduto(Produtos produtos) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_PRODUTO, produtos.getNome());
        values.put(COLUNA_PRECO_COMPRA_PRODUTO, produtos.getPreco());
        values.put(COLUNA_PRECO_VENDA_PRODUTO,produtos.getPrecoCompra()) ;
        values.put(COLUNA_VALIDADE_PRODUTO, produtos.getPrazo());
        values.put(COLUNA_QUANTIDADE_PRODUTO, produtos.getQuantidade());
        long verficainsert = db.insert(TABELA_PRODUTO, null, values);

        Cursor cursor = db.query(TABELA_PRODUTO, colunasProduto, COLUNA_ID_PRODUTO +"="+verficainsert, null, null, null, null);
        cursor.moveToFirst();
        Produtos produtos1= new Produtos();
        produtos.setId(cursor.getInt(0));
        produtos.setNome(cursor.getString(1));
        produtos.setPreco(cursor.getInt(2));
        produtos.setPrecoCompra(cursor.getInt(3));
        produtos.setPrazo(cursor.getString(4));
        produtos.setQuantidade(cursor.getInt(5));
        cursor.close();
        db.close();
        if(verficainsert != -1){
            return true;
        }

        return false;
    }
    //*****************************************************************************************************************************************************

    //Metodo Verifica Usuario
    public boolean verificaUsuario(String telefone) {
        String[] columns = {
                COLUNA_ID_USUARIO
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUNA_TELEFONE_USUARIO + " = ?";
        String[] selectionArgs = {telefone};

        Cursor cursor = db.query(TABELA_USUARIO, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    //Metodo Verifica Usuario e Password
    public boolean verificaUsuario(String telefone, String senha) {
        String[] columns = {
                COLUNA_ID_USUARIO
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUNA_TELEFONE_USUARIO + " = ?" + " AND " + COLUNA_SENHA_USUARIO + " =?";
        String[] selectionArgs = {telefone, senha};

        Cursor cursor = db.query(TABELA_USUARIO, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    //Metodo apagar usuario
    void apagarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_USUARIO, COLUNA_TELEFONE_USUARIO + " = ?", new String[]{String.valueOf(usuario.getTelefone())});
        db.close();
    }


    //Metodo Seleciona Usuario

    Usuario selecionarUsuario(int telefone) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_USUARIO, new String[]{COLUNA_ID_USUARIO, COLUNA_NOME_USUARIO, COLUNA_TELEFONE_USUARIO, COLUNA_EMAIL_USUARIO, COLUNA_ENDERECO_USUARIO, COLUNA_SENHA_USUARIO}, COLUNA_TELEFONE_USUARIO + "  = ?",
                new String[]{String.valueOf(telefone)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Usuario usuario1 = new Usuario(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return usuario1;
    }


    //Metodo Actualiza Usuario
    void actualizaCliente(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_USUARIO, usuario.getNome());
        values.put(COLUNA_EMAIL_USUARIO, usuario.getEmail());
        values.put(COLUNA_ENDERECO_USUARIO, usuario.getEndereco());
        values.put(COLUNA_SENHA_USUARIO, usuario.getSenha());


        db.update(TABELA_USUARIO, values, COLUNA_TELEFONE_USUARIO + " = ?", new String[]{String.valueOf(usuario.getTelefone())});

    }

    //Metodo Lista Usuarios
    public List<Usuario> listaTodosUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();

        String query = "SELECT * FROM " + TABELA_USUARIO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                usuario.setTelefone(c.getString(0));
                usuario.setNome(c.getString(1));
                usuario.setEmail(c.getString(2));
                usuario.setEndereco(c.getString(3));

                listaUsuarios.add(usuario);
            } while ((c.moveToNext()));

        }
        return listaUsuarios;

    }


    //**********************************************


}
