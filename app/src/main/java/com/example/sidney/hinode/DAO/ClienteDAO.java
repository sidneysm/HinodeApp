package com.example.sidney.hinode.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sidney.hinode.DatabaseHelper;
import com.example.sidney.hinode.modelos.Cliente;
import com.example.sidney.hinode.modelos.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidney on 12/08/2015.
 */
public class ClienteDAO  {
    private SQLiteDatabase db;

    public ClienteDAO(Context context) {
        DatabaseHelper auxBd = new DatabaseHelper(context);
        db = auxBd.getWritableDatabase();
    }

    public void inserir(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());

        long resultado = db.insert("clientes", null, values);

    }

    public void atalizar(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());

        db.update("clientes", values, "_id = ?", new String[]{"" + cliente.getId()});
    }

    public void deletar(Cliente cliente){
        db.delete("clientes", "_id = " + cliente.getId(), null);
    }

    public List<Cliente> buscar(){
        List<Cliente> list = new ArrayList<Cliente>();
        String[] colunas = new String[]{"_id", "nome", "telefone"};

        Cursor cursor = db.query("clientes", colunas, null, null, null, null, "nome ASC");

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                Cliente cliente = new Cliente();
                cliente.setId(cursor.getLong(0));
                cliente.setNome(cursor.getString(1));
                cliente.setTelefone(cursor.getString(2));
                list.add(cliente);
            }while (cursor.moveToNext());
        }

        return(list);
    }

}
