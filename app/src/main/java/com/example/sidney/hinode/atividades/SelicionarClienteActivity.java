package com.example.sidney.hinode.atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sidney.hinode.DAO.ClienteDAO;
import com.example.sidney.hinode.R;
import com.example.sidney.hinode.modelos.Cliente;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SelicionarClienteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private ListView lv;
    private EditText et;
    private List<Cliente> list;
    private ArrayList<HashMap<String, String>> clientes;
    private ArrayList<HashMap<String, String>> clientesEncontrados = new ArrayList<HashMap<String, String>>();
    private String[] from;
    private int[] to;
    private int layoutNativo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selicionar_cliente);

        lv = (ListView) findViewById(R.id.lvClientes);
        et = (EditText) findViewById(R.id.etLocalizarCliente);

        ClienteDAO clienteDAO = new ClienteDAO(this);
        list = clienteDAO.buscar();
        clientes = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < list.size(); i++) {
            HashMap<String, String> produto = new HashMap<String, String>();
            produto.put("nome", "Cliente: " + list.get(i).getNome());
            produto.put("código", "Código: " + list.get(i).getId());

            clientes.add(produto);
        }

        from = new String[]{"nome", "código"};

        layoutNativo = android.R.layout.two_line_list_item;

        to = new int[]{android.R.id.text1, android.R.id.text2};

        lv.setAdapter(new SimpleAdapter(this, clientes, layoutNativo, from, to));
        CarregarEncontrados();

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CarregarEncontrados();
                lv.setAdapter(new SimpleAdapter(SelicionarClienteActivity.this, clientesEncontrados, layoutNativo, from, to));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lv.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
        Intent intent = new Intent();


        long idCliente = a.getItemIdAtPosition(position);
        HashMap<String, String> nome = (HashMap<String, String>) a.getItemAtPosition(position);


        String mensagem = "Você clicou em um item" + nome.get("código").replace("Código: ", "");

        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT)
                .show();

        intent.putExtra("nome", nome.get("nome").replace("Cliente: ", ""));
        intent.putExtra("id", Long.parseLong(nome.get("código").replace("Código: ", "").toString()));

        setResult(1, intent);
        finish();
    }

    public void selecionaCliente(View view){
        Intent intent = new Intent();

        intent.putExtra("nome", et.getText().toString());

        setResult(1, intent);
        finish();
    }


    public void CarregarEncontrados() {
        int textlength = et.getText().length();

        clientesEncontrados.clear();

        for (int i = 0; i < clientes.size(); i++) {
            if (textlength <= clientes.get(i).get("nome").length()) {
                if (clientes.get(i).get("nome").contains(et.getText().toString())) {
                    clientesEncontrados.add(clientes.get(i));
                }

            }
        }
    }

//    @Override
//    public void onClick(View view) {
//
//    }
}
