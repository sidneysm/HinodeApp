package com.example.sidney.hinode.atividades;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sidney.hinode.ClienteAdapter;
import com.example.sidney.hinode.DAO.ClienteDAO;
import com.example.sidney.hinode.R;
import com.example.sidney.hinode.modelos.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteListActvity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cliente_list_actvity);

        ClienteDAO clienteDAO = new ClienteDAO(this);

        List<Cliente> list = clienteDAO.buscar();
        setListAdapter(new ClienteAdapter(this, list));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente_list_actvity, menu);
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
