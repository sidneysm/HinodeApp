package com.example.sidney.hinode.atividades;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.sidney.hinode.DAO.ProdutoDAO;
import com.example.sidney.hinode.DatabaseHelper;
import com.example.sidney.hinode.modelos.Produto;
import com.example.sidney.hinode.ProdutoAdapter;
import com.example.sidney.hinode.R;

import java.util.List;

public class ProdutoListActivity extends ListActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_produto_list);

        ProdutoDAO produtoDAO = new ProdutoDAO(this);



        List<Produto> list = produtoDAO.buscar();

        setListAdapter(new ProdutoAdapter(this, list));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_produto_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
