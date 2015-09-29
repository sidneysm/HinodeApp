package com.example.sidney.hinode.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sidney.hinode.DatabaseHelper;
import com.example.sidney.hinode.modelos.Venda;

/**
 * Created by Sidney on 27/09/2015.
 */
public class VendasEnvolveProdutoDAO {

    private SQLiteDatabase db;

    public VendasEnvolveProdutoDAO(Context context){
        DatabaseHelper auxBd = new DatabaseHelper(context);
        db = auxBd.getWritableDatabase();
    }

    public void inserir(long idVenda, long idProduto, int quantidade){
        ContentValues values = new ContentValues();
        values.put("VENDAS_idVENDAS", idVenda);
        values.put("PRODUTO_codProduto", idProduto);
        values.put("quantidade", quantidade);

        long resultado = db.insert("VENDAS_ENVOLVE_PRODUTO", null, values);
    }

}
