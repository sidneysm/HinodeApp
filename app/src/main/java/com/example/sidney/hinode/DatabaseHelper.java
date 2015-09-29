package com.example.sidney.hinode;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sidney on 07/08/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "HinodeApp";
    private static int VERSAO = 6;

    public DatabaseHelper(Context context){
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PRODUTO (" +
                    "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "codProduto INTEGER NULL," +
                    "nome VARCHAR NULL," +
                    "preco FLOAT NULL," +
                    "categoria VARCHAR NULL," +
                    "descricao VARCHAR NULL" +
                    ");");

        sqLiteDatabase.execSQL("CREATE TABLE CLIENTES (" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR NULL, " +
                "telefone VARCHAR NULL " +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE VENDAS (" +
                "  _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "  CLIENTE_idCliente INTEGER UNSIGNED NOT NULL," +
                "  valor FLOAT NULL," +
                "  data_venda DATE NULL" +
                "  );");
        sqLiteDatabase.execSQL("CREATE INDEX VENDAS_FKIndex1 ON vendas(CLIENTE_idCliente);");
//
        sqLiteDatabase.execSQL("CREATE TABLE VENDAS_ENVOLVE_PRODUTO (" +
                "  VENDAS_idVENDAS INTEGER UNSIGNED NOT NULL," +
                "  PRODUTO_codProduto INTEGER UNSIGNED NOT NULL," +
                "  quantidade INTEGER UNSIGNED NULL," +
                "  PRIMARY KEY(VENDAS_idVENDAS, PRODUTO_codProduto)" +
                ");");
        sqLiteDatabase.execSQL("CREATE INDEX VENDAS_has_PRODUTO_FKIndex1 ON VENDAS_ENVOLVE_PRODUTO(VENDAS_idVENDAS);");
        sqLiteDatabase.execSQL("CREATE INDEX VENDAS_has_PRODUTO_FKIndex2 ON VENDAS_ENVOLVE_PRODUTO(PRODUTO_codProduto);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table PRODUTO");
        sqLiteDatabase.execSQL("drop table CLIENTES");
        sqLiteDatabase.execSQL("drop table if exists VENDAS");
        sqLiteDatabase.execSQL("drop table if exists VENDAS_ENVOLVE_PRODUTO");
        onCreate(sqLiteDatabase);

    }
}
