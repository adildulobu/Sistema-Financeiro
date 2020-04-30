package com.firedevz.sistemadegestaofinanceira.sql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.firedevz.sistemadegestaofinanceira.modelo.Clientes;
import com.firedevz.sistemadegestaofinanceira.modelo.Produtos;
import com.firedevz.sistemadegestaofinanceira.modelo.Rendimento;
import com.firedevz.sistemadegestaofinanceira.modelo.Usuario;
import com.firedevz.sistemadegestaofinanceira.modelo.Venda;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_gestaofinance";

    // Table Names
    private static final String TABELA_USUARIO = "tb_usuario";
    private static final String TABELA_CONTAS = "tb_contas";
    private static final String TABELA_PRODUTO = "tb_produto";
    private static final String TABELA_RENDIMENTO = "tb_rendimento";
    private static final String TABELA_VENDAS = "tb_vendas";
    private static final String TABELA_CLIENTES = "tb_clientes";

    //************************************************************************************************

    // TABELA USUARIO - Nome de colunas
    private static String COLUNA_ID_USUARIO = "id_usuario";
    private static String COLUNA_NOME_USUARIO = "nome";
    private static String COLUNA_TELEFONE_USUARIO = "telefone";
    private static String COLUNA_EMAIL_USUARIO = "email";
    private static String COLUNA_ENDERECO_USUARIO = "endereço";
    private static String COLUNA_SENHA_USUARIO = "senha";

    // TABELA PRODUTOS - Nome de colunas
    private static String COLUNA_ID_PRODUTO = "id_produto";
    private static String COLUNA_NOME_PRODUTO = "Descrição";
    private static String COLUNA_DATA_PRODUTO= "dia_entrada";
    private static String COLUNA_CATEGORIA_PRODUTO= "categoria";
    private static String COLUNA_VALIDADE_PRODUTO= "prazo";
    private static String COLUNA_PRECO_COMPRA_PRODUTO = "valor_compra";
    private static String COLUNA_PRECO_VENDA_PRODUTO = "preço";
    private static String COLUNA_QUANTIDADE_PRODUTO= "quantidade";
    private static String COLUNA_UNIDADE_PRODUTO= "unidade";
    private static String COLUNA_ESTOQUEMINIMO_PRODUTO= "estoque_minimo";


    // TABELA RENDIMENTO - Nome de colunas
    private static String COLUNA_ID_RENDIMENTO = "id_rendimento";
    private static String COLUNA_DESCRICAO_RENDIMENTO = "descricao";
    private static String COLUNA_VALOR_RENDIMETNO = "preco";
    private static String COLUNA_TIPO_RENDIMENTO = "tipo_de_rendimento";
    private static String COLUNA_DATA_RENDIMENO = "data_rendimento";

    // TABELA CONTAS - Nome de colunas
    private static String COLUNA_ID_CONTA = "id_conta";
    private static String COLUNA_NOME_CONTA = "conta";
    private static String COLUNA_TIPO_CONTA= "tipo_conta";
    private static String COLUNA_SALDO_INICIAL_CONTA= "saldo_inicial";
    private static String COLUNA_SALDO_ACTUAL= "saldo_actual";

    // TABELA VENDAS - Nome de colunas
    private static String COLUNA_ID_VENDA = "id_vendas";
    private static String COLUNA_QUANTIDADE_PRODUTO_VENDA = "quantidade_produtos";
    private static String COLUNA_DESCONTO_VENDA= "desconto_venda";
    private static String COLUNA_PRECO_TOTAL_VENDA= "preco_total";
    private static String COLUNA_TIPO_VENDA= "tipo_venda";

    // TABELA CLIENTES - Nome de colunas
    private static String COLUNA_ID_CLIENTE = "id_clientes";
    private static String COLUNA_NOME_CLIENTE = "nome_cliente";
    private static String COLUNA_TELEFONE_CLIENTE= "contacto_cliente";
    private static String COLUNA_EMAIL_CLIENTE= "email_cliente";
    private static String COLUNA_MORADA_CLIENTE= "morada_cliente";

    //*********************CRIACAO DE TABELAS*********************************

    //CRIAR TABELA USUARIO
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABELA_USUARIO + "("
            + COLUNA_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_NOME_USUARIO + " VARCHAR,"
            + COLUNA_TELEFONE_USUARIO + " VARCHAR, " + COLUNA_EMAIL_USUARIO + " VARCHAR, " + COLUNA_ENDERECO_USUARIO + " VARCHAR, " + COLUNA_SENHA_USUARIO + " VARCHAR" + ")";

    //CRIAR TABELA VENDAS
    private String CREATE_SALES_TABLE = "CREATE TABLE " + TABELA_VENDAS + "("
            + COLUNA_ID_VENDA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUNA_ID_PRODUTO + " REFERENCES tb_produto (id_produto) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL, "
            + COLUNA_QUANTIDADE_PRODUTO_VENDA + " INTEGER NOT NULL, " + COLUNA_TIPO_VENDA + " VARCHAR NOT NULL, " + COLUNA_DESCONTO_VENDA + " REAL, " + COLUNA_PRECO_TOTAL_VENDA + " REAL NOT NULL, "
            + COLUNA_ID_USUARIO + " INTEGER REFERENCES " +TABELA_USUARIO +  "("+COLUNA_ID_USUARIO+") ON DELETE CASCADE ON UPDATE CASCADE, "
            + COLUNA_ID_CLIENTE + " INTEGER REFERENCES "+ TABELA_CLIENTES + "("+COLUNA_ID_CLIENTE+") ON DELETE CASCADE ON UPDATE CASCADE" + ")";

    //CRIAR TABELA CLIENTES
    private String CREATE_CLIENTES_TABLE = "CREATE TABLE " + TABELA_CLIENTES + "("
            + COLUNA_ID_CLIENTE  + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUNA_NOME_CLIENTE + " VARCHAR NOT NULL, " + COLUNA_TELEFONE_CLIENTE + "INTEGER NOT NULL, "
            + COLUNA_EMAIL_CLIENTE + " VARCHAR, " + COLUNA_MORADA_CLIENTE + " VARCHAR" + ")";



    //CRIAR TABELA CONTAS
   private String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABELA_CONTAS + "("
            + COLUNA_ID_CONTA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUNA_NOME_CONTA + "VARCHAR NOT NULL, " + COLUNA_TIPO_CONTA + " VARCHAR NOT NULL, " + COLUNA_SALDO_INICIAL_CONTA + " REAL NOT NULL, "
            + COLUNA_SALDO_ACTUAL + " REAL NOT NULL, " + COLUNA_ID_USUARIO + " INTEGER REFERENCES "+ TABELA_USUARIO+ "("+COLUNA_ID_USUARIO +") ON DELETE CASCADE ON UPDATE CASCADE" + ")";


    //CRIAR TABELA PRODUTOS
    private String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABELA_PRODUTO + "("
            + COLUNA_ID_PRODUTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_NOME_PRODUTO + " VARCHAR, " + COLUNA_DATA_PRODUTO + " DATETIME, " + COLUNA_CATEGORIA_PRODUTO + " VARCHAR, " + COLUNA_VALIDADE_PRODUTO + " DATETIME, "
            + COLUNA_PRECO_COMPRA_PRODUTO + " REAL, " + COLUNA_PRECO_VENDA_PRODUTO + " REAL, " + COLUNA_QUANTIDADE_PRODUTO + " REAL, " + COLUNA_UNIDADE_PRODUTO + " VARCHAR, " + COLUNA_ESTOQUEMINIMO_PRODUTO + " INTEGER" + ")";

    //CRIAR TABELA RENDIMENTOS
    private String CREATE_RENDIMENTO_TABLE = "CREATE TABLE " + TABELA_RENDIMENTO + "("
            + COLUNA_ID_RENDIMENTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_DESCRICAO_RENDIMENTO + " VARCHAR, " + COLUNA_VALOR_RENDIMETNO + " REAL,"
            + COLUNA_TIPO_RENDIMENTO + " REAL, " + COLUNA_DATA_RENDIMENO + " DATETIME" + ")";

    //**********************************************************************************************


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_RENDIMENTO_TABLE);
        db.execSQL(CREATE_ACCOUNT_TABLE);
        db.execSQL(CREATE_SALES_TABLE);
        db.execSQL(CREATE_CLIENTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_RENDIMENTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CONTAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_VENDAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CLIENTES);

        // create new tables
        onCreate(db);

    }


    //*****************************CRUD****************************************************


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

        Cursor cursor = db.query(TABELA_USUARIO, new String[]{COLUNA_ID_USUARIO, COLUNA_NOME_USUARIO, COLUNA_TELEFONE_USUARIO, COLUNA_EMAIL_USUARIO, COLUNA_ENDERECO_USUARIO, COLUNA_SENHA_USUARIO}, COLUNA_ID_USUARIO +"="+verficainsert, null, null, null, null);
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


    //Metodo Adiciona Cliente
    public boolean addCliente(Clientes clientes) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_CLIENTE, clientes.getNome());
        values.put(COLUNA_TELEFONE_CLIENTE, clientes.getTelefone());
        values.put(COLUNA_EMAIL_CLIENTE, clientes.getEmail());
        values.put(COLUNA_MORADA_CLIENTE, clientes.getMorada());
        long verficainsert = db.insert(TABELA_CLIENTES, null, values);

        Cursor cursor = db.query(TABELA_USUARIO, new String[]{COLUNA_ID_CLIENTE, COLUNA_NOME_CLIENTE, COLUNA_TELEFONE_CLIENTE, COLUNA_EMAIL_CLIENTE, COLUNA_MORADA_CLIENTE}, COLUNA_ID_CLIENTE +"="+verficainsert, null, null, null, null);
        cursor.moveToFirst();
        Clientes clientes1= new Clientes();
        clientes1.setId(cursor.getInt(0));
        clientes1.setNome(cursor.getString(1));
        clientes1.setTelefone(cursor.getString(2));
        clientes1.setEmail(cursor.getString(3));
        clientes1.setMorada(cursor.getString(4));
        cursor.close();
        db.close();
        if(verficainsert != -1){
            return true;
        }

        return false;
    }


    //Metodo Adiciona ADD produtos
    public boolean addProduto(Produtos produtos) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_PRODUTO, produtos.getNome());
        values.put(COLUNA_DATA_PRODUTO, produtos.getDataEntrada());
        values.put(COLUNA_CATEGORIA_PRODUTO, produtos.getCategoria());
        values.put(COLUNA_VALIDADE_PRODUTO,produtos.getPrazo()) ;
        values.put(COLUNA_PRECO_COMPRA_PRODUTO, produtos.getPrecoCompra());
        values.put(COLUNA_PRECO_VENDA_PRODUTO, produtos.getPreco());
        values.put(COLUNA_QUANTIDADE_PRODUTO, produtos.getQuantidade());
        values.put(COLUNA_UNIDADE_PRODUTO, produtos.getUnidade());
        values.put(COLUNA_ESTOQUEMINIMO_PRODUTO, produtos.getEstoqueMinimo());
        long verficainsert = db.insert(TABELA_PRODUTO, null, values);

        Cursor cursor = db.query(TABELA_PRODUTO, new String[]{COLUNA_ID_PRODUTO, COLUNA_NOME_PRODUTO,COLUNA_DATA_PRODUTO, COLUNA_CATEGORIA_PRODUTO,COLUNA_VALIDADE_PRODUTO,COLUNA_PRECO_COMPRA_PRODUTO, COLUNA_PRECO_VENDA_PRODUTO, COLUNA_QUANTIDADE_PRODUTO, COLUNA_UNIDADE_PRODUTO,COLUNA_ESTOQUEMINIMO_PRODUTO}, COLUNA_ID_PRODUTO +"="+verficainsert, null, null, null, null);

        cursor.moveToFirst();
        Produtos produtos1= new Produtos();
        produtos1.setId(cursor.getInt(0));
        produtos1.setNome(cursor.getString(1));
        produtos1.setDataEntrada(cursor.getString(2));
        produtos1.setCategoria(cursor.getString(3));
        produtos1.setPrazo(cursor.getString(4));
        produtos1.setPrecoCompra(cursor.getInt(5));
        produtos1.setPreco(cursor.getInt(6));
        produtos1.setQuantidade(cursor.getInt(7));
        produtos1.setUnidade(cursor.getString(8));
        produtos1.setEstoqueMinimo(cursor.getInt(9));
        cursor.close();
        db.close();
        if(verficainsert != -1){
            return true;
        }

        return false;
    }


    //Metodo Adiciona vendas
    public boolean addVenda(Venda venda) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_PRODUTO, venda.getIdProduto());
        values.put(COLUNA_QUANTIDADE_PRODUTO_VENDA, venda.getQuantidade());
        values.put(COLUNA_TIPO_VENDA,venda.getTipoVenda()) ;
        values.put(COLUNA_DESCONTO_VENDA, venda.getDesconto());
        values.put(COLUNA_PRECO_TOTAL_VENDA, venda.getPrecoTotal());
        //values.put(COLUNA_ID_USUARIO, venda.getQuantidade());
        values.put(COLUNA_ID_CLIENTE, venda.getIdCliente());

        long verficainsert = db.insert(TABELA_PRODUTO, null, values);

        Cursor cursor = db.query(TABELA_PRODUTO, new String[]{COLUNA_ID_PRODUTO, COLUNA_NOME_PRODUTO,COLUNA_DATA_PRODUTO, COLUNA_CATEGORIA_PRODUTO,COLUNA_VALIDADE_PRODUTO,COLUNA_PRECO_COMPRA_PRODUTO, COLUNA_PRECO_VENDA_PRODUTO, COLUNA_QUANTIDADE_PRODUTO, COLUNA_UNIDADE_PRODUTO,COLUNA_ESTOQUEMINIMO_PRODUTO}, COLUNA_ID_PRODUTO +"="+verficainsert, null, null, null, null);

        cursor.moveToFirst();
        Venda venda1= new Venda();
        venda1.setIdVenda(cursor.getInt(0));
        venda1.setIdProduto(cursor.getInt(1));
        venda1.setQuantidade(Float.parseFloat(cursor.getString(1)));
        venda1.setTipoVenda(cursor.getString(2));
        venda1.setDesconto(Float.parseFloat(cursor.getString(3)));
        venda1.setPrecoTotal(Float.parseFloat(cursor.getString(4)));
        venda1.setIdCliente(cursor.getInt(5));
        cursor.close();
        db.close();
        if(verficainsert != -1){
            return true;
        }

        return false;
    }

    //Dinheiro Investido
    public int dinheiroInvestido(){
        String sqlQuery = "SELECT SUM(("+COLUNA_PRECO_COMPRA_PRODUTO+")*("+COLUNA_QUANTIDADE_PRODUTO+")) FROM "+TABELA_PRODUTO;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery,null);

        int total=0;

        cursor.moveToFirst();

        if (cursor.getCount()>0) {
            total = cursor.getInt(0);
        }
        return total;
    }

    //Dinheiro do caixa
    public int valorCaixa(){
        String sqlQuery = "SELECT SUM(("+COLUNA_PRECO_TOTAL_VENDA+")) FROM "+TABELA_VENDAS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery,null);

        int total=0;

        cursor.moveToFirst();

        if (cursor.getCount()>0) {
            total = cursor.getInt(0);
        }
        return total;
    }


    //Metodo Add Rendimentos
    public boolean addRendimetno(Rendimento rendimento) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_DESCRICAO_RENDIMENTO, rendimento.getDescricao());
        values.put(COLUNA_VALOR_RENDIMETNO, rendimento.getValor());
        values.put(COLUNA_TIPO_RENDIMENTO,rendimento.getTipo()) ;
        values.put(COLUNA_DATA_RENDIMENO,rendimento.getData()) ;
        long verficainsert = db.insert(TABELA_RENDIMENTO, null, values);

        Cursor cursor = db.query(TABELA_RENDIMENTO, new String[]{COLUNA_ID_RENDIMENTO,COLUNA_DESCRICAO_RENDIMENTO, COLUNA_VALOR_RENDIMETNO, COLUNA_TIPO_RENDIMENTO,COLUNA_DATA_RENDIMENO}, COLUNA_ID_RENDIMENTO+"="+verficainsert, null, null, null, null);

        cursor.moveToFirst();
        Rendimento rendimento1= new Rendimento();
        rendimento1.setDescricao(cursor.getString(0));
        rendimento1.setValor(cursor.getDouble(1));
        rendimento1.setTipo(cursor.getString(2));
        rendimento1.setData(cursor.getString(3));
        cursor.close();
        db.close();
        if(verficainsert != -1){
            return true;
        }

        return false;
    }

    //***********************************************************************************************

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


    //Metodo Verifica Cliente
    public boolean verificaCliente(String telefone) {
        String[] columns = {
                COLUNA_ID_CLIENTE
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUNA_TELEFONE_CLIENTE + " = ?";
        String[] selectionArgs = {telefone};

        Cursor cursor = db.query(TABELA_CLIENTES, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    //Metodo Verifica Produto
    public boolean verificaProduto(String nomeProduto) {
        String[] columns = {
                COLUNA_ID_PRODUTO
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUNA_NOME_PRODUTO + " = ?";
        String[] selectionArgs = {nomeProduto};

        Cursor cursor = db.query(TABELA_PRODUTO, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    //**********************************************************************************************

    //Metodo apagar usuario
    public void apagarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_USUARIO, COLUNA_TELEFONE_USUARIO + " = ?", new String[]{String.valueOf(usuario.getTelefone())});
        db.close();
    }


    //Metodo apagar produto
    public void apagarProduto(Produtos produtos) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_PRODUTO, COLUNA_ID_PRODUTO + " = ?", new String[]{String.valueOf(produtos.getId())});
        db.close();
    }

    //Metodo apagar rendimeto
    public void apagarRendimento(Rendimento rendimento) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_RENDIMENTO, COLUNA_DESCRICAO_RENDIMENTO + " = ?", new String[]{String.valueOf(rendimento.getDescricao())});
        db.close();
    }

    //Metodo Seleciona Usuario
    public Usuario selecionarUsuario(int telefone) {
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

    //Metodo Seleciona produto
    public Produtos selecionarProduto(String nome) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_PRODUTO, new String[]{COLUNA_ID_PRODUTO, COLUNA_NOME_PRODUTO, COLUNA_DATA_PRODUTO,COLUNA_CATEGORIA_PRODUTO,COLUNA_VALIDADE_PRODUTO,COLUNA_PRECO_COMPRA_PRODUTO, COLUNA_PRECO_VENDA_PRODUTO, COLUNA_QUANTIDADE_PRODUTO, COLUNA_UNIDADE_PRODUTO,COLUNA_ESTOQUEMINIMO_PRODUTO}, COLUNA_ID_PRODUTO + "  = ?",
                new String[]{String.valueOf(nome)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Produtos produtos = new Produtos(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4), Float.parseFloat(cursor.getString(5)), Float.parseFloat(cursor.getString(6)),Integer.parseInt(cursor.getString(7)), cursor.getString(8), Integer.parseInt(cursor.getString(9)));

        return produtos;
    }

    //Metodo Seleciona Rendimento
    public Rendimento selecionarRendimento(String descricao) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_RENDIMENTO, new String[]{COLUNA_ID_PRODUTO,COLUNA_DESCRICAO_RENDIMENTO, COLUNA_VALOR_RENDIMETNO, COLUNA_TIPO_RENDIMENTO}, COLUNA_DESCRICAO_RENDIMENTO + "  = ?",
                new String[]{String.valueOf(descricao)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Rendimento rendimento = new Rendimento(cursor.getInt(0),cursor.getString(1),
                Double.parseDouble(cursor.getString(2)), cursor.getString(3),cursor.getString(4));

        return rendimento;
    }


    //Metodo Actualiza Usuario
    public void actualizaCliente(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_USUARIO, usuario.getNome());
        values.put(COLUNA_EMAIL_USUARIO, usuario.getEmail());
        values.put(COLUNA_ENDERECO_USUARIO, usuario.getEndereco());
        values.put(COLUNA_SENHA_USUARIO, usuario.getSenha());


        db.update(TABELA_USUARIO, values, COLUNA_TELEFONE_USUARIO + " = ?", new String[]{String.valueOf(usuario.getTelefone())});

    }

    //Metodo Actualiza Produto
    public void actualizaProduto(Produtos produtos) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_DATA_PRODUTO, produtos.getDataEntrada());
        values.put(COLUNA_CATEGORIA_PRODUTO, produtos.getCategoria());
        values.put(COLUNA_VALIDADE_PRODUTO,produtos.getPrazo()) ;
        values.put(COLUNA_PRECO_COMPRA_PRODUTO, produtos.getPrecoCompra());
        values.put(COLUNA_PRECO_VENDA_PRODUTO, produtos.getPreco());
        values.put(COLUNA_QUANTIDADE_PRODUTO, produtos.getQuantidade());
        values.put(COLUNA_UNIDADE_PRODUTO, produtos.getUnidade());
        values.put(COLUNA_ESTOQUEMINIMO_PRODUTO, produtos.getEstoqueMinimo());

        db.update(TABELA_PRODUTO, values, COLUNA_NOME_PRODUTO + " = ?", new String[]{String.valueOf(produtos.getNome())});

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


    //Metodo Lista Produtos
    public List<Produtos> listaTodosProdutos(String categoria) {
        List<Produtos> listaProdutos = new ArrayList<Produtos>();
        String query;


        if(categoria == "todos" ) {

            query = "SELECT * FROM " + TABELA_PRODUTO;
        }else {
            query = "SELECT * FROM "+TABELA_PRODUTO+ " WHERE "+COLUNA_CATEGORIA_PRODUTO+" = "+categoria+"";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Produtos produtos= new Produtos();
                produtos.setId(c.getInt(0));
                produtos.setNome(c.getString(1));
                produtos.setDataEntrada(c.getString(2));
                produtos.setCategoria(c.getString(3));
                produtos.setPrazo(c.getString(4));
                produtos.setPrecoCompra(Float.parseFloat(c.getString(5)));
                produtos.setPreco(Float.parseFloat(c.getString(6)));
                produtos.setQuantidade(c.getInt(7));
                produtos.setUnidade(c.getString(8));
                produtos.setEstoqueMinimo(c.getInt(9));

                listaProdutos.add(produtos);
            } while ((c.moveToNext()));

        }
        return listaProdutos;

    }


    ///Lista todos os rendimentos
    public List<Rendimento> listaTodosRendimentos(){
        List<Rendimento> listaRendimento = new ArrayList<Rendimento>();

        String query = "SELECT * FROM " + TABELA_RENDIMENTO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                Rendimento rendimento = new Rendimento();
                rendimento.setIdDescricao(c.getInt(0));
                rendimento.setDescricao(c.getString(1));
                rendimento.setValor(Double.parseDouble(c.getString(2)));
                rendimento.setTipo(c.getString(3));
                rendimento.setData(c.getString(4));

                listaRendimento.add(rendimento);
            }while ((c.moveToNext()));

        }
        return listaRendimento;

    }


//    public List<filtroProdutosCategoria> (String categoria){
//        String sqlQuery = "SELECT * FROM "+TABELA_PRODUTO+ " WHERE "+COLUNA_CATEGORIA_PRODUTO+" ="+categoria;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(sqlQuery,null);
//
//
//        cursor.moveToFirst();
//
//        if (cursor.getCount()>0) {
//            total = cursor.getInt(0);
//        }
//        return total;
//    }



}
