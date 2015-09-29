package com.example.sidney.hinode.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sidney.hinode.DatabaseHelper;
import com.example.sidney.hinode.modelos.Produto;
import com.example.sidney.hinode.modelos.Venda;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidney on 25/09/2015.
 */
public class VendasDAO {
    private SQLiteDatabase db;

    public VendasDAO(Context context){
        DatabaseHelper auxBd = new DatabaseHelper(context);
        db = auxBd.getWritableDatabase();
    }

    public long inserir(Venda venda){
        ContentValues values = new ContentValues();
        values.put("CLIENTE_idCliente", venda.getIdCliente());
        values.put("valor", venda.getValor());
        values.put("data_venda", String.valueOf(venda.getData()));

        long resultado = db.insert("vendas", null, values);
        return resultado;
    }

    public List<Venda> buscar(){
        List<Venda> list = new ArrayList<Venda>();
        String[] colunas = new String[]{"_id", "CLIENTE_idCliente", "valor", "data_venda"};

        Cursor cursor = db.query("vendas", colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Venda venda = new Venda();
                venda.setId(cursor.getLong(0));
                venda.setIdCliente(cursor.getLong(1));
                venda.setValor(cursor.getFloat(2));
                venda.setData(Date.valueOf(cursor.getString(3)));
                list.add(venda);
            }while (cursor.moveToNext());
        }

        return(list);
    }
}
