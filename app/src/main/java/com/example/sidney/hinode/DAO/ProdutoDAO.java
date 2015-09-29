package com.example.sidney.hinode.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sidney.hinode.DatabaseHelper;
import com.example.sidney.hinode.modelos.Produto;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sidney on 11/08/2015.
 */
public class ProdutoDAO {

    private SQLiteDatabase db;

    public ProdutoDAO(Context context){
        DatabaseHelper auxBd = new DatabaseHelper(context);
        db = auxBd.getWritableDatabase();
    }

    public void inserir(Produto produto){
        ContentValues values = new ContentValues();
        values.put("codProduto", produto.getCodigo());
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        values.put("descricao", produto.getDescricao());
        values.put("categoria", produto.getCategoria());

        long resultado = db.insert("produto", null, values);
    }




    public List<Produto> buscar(){
        List<Produto> list = new ArrayList<Produto>();
        String[] colunas = new String[]{"_id", "codProduto", "nome", "preco", "categoria", "descricao"};

        Cursor cursor = db.query("produto", colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Produto produto = new Produto();
                produto.setId(cursor.getLong(0));
                produto.setCodigo(cursor.getString(1));
                produto.setNome(cursor.getString(2));
                produto.setPreco(cursor.getFloat(3));
                produto.setCategoria(cursor.getString(4));
                produto.setDescricao(cursor.getString(5));
                list.add(produto);
            }while (cursor.moveToNext());
        }

        return(list);
    }

    public Produto buscar(long id){
        Produto produto = new Produto();
        String[] colunas = new String[]{"_id", "codProduto", "nome", "preco", "categoria", "descricao"};
        Cursor cursor = db.query("produto", colunas, "_id = " + id, null, null, null, "nome ASC");
        cursor.moveToFirst();
        
        produto.setId(cursor.getLong(0));
        produto.setCodigo(cursor.getString(1));
        produto.setNome(cursor.getString(2));
        produto.setPreco(cursor.getFloat(3));
        produto.setCategoria(cursor.getString(4));
        produto.setDescricao(cursor.getString(5));

        return produto;
    }

    public void atualizar(Produto produto){
        ContentValues values = new ContentValues();
        values.put("codProduto", produto.getCodigo());
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        values.put("categoria", produto.getCategoria());
        values.put("descricao", produto.getDescricao());

        db.update("produto", values, "_id = ?", new String[]{"" + produto.getId()});
    }

    public void deletar(Produto produto){
        db.delete("produto", "_id = " + produto.getId(), null);
    }

}
