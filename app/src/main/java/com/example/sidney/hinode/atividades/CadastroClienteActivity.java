package com.example.sidney.hinode.atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sidney.hinode.DAO.ClienteDAO;
import com.example.sidney.hinode.R;
import com.example.sidney.hinode.modelos.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText nomeCliente, telefoneCliente;
    private Cliente cliente = new Cliente();
    private Button cadCliente, atualizaCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        nomeCliente = (EditText) findViewById(R.id.etNomeDoCliente);
        telefoneCliente = (EditText) findViewById(R.id.etTelefoneDoCliente);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                cliente.setId(bundle.getLong("id"));
                cliente.setNome(bundle.getString("nome"));
                cliente.setTelefone(bundle.getString("telefone"));

                nomeCliente.setText(cliente.getNome());
                telefoneCliente.setText(cliente.getTelefone());

                cadCliente = (Button) findViewById(R.id.btConfirmarCliente);
                atualizaCliente = (Button) findViewById(R.id.bEditarCliente);

                cadCliente.setVisibility(View.GONE);
                atualizaCliente.setVisibility(View.VISIBLE);
            }
        }

    }

    public void salvarCliente(View view){
        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente.getText().toString());
        cliente.setTelefone(telefoneCliente.getText().toString());

        ClienteDAO clienteDAO = new ClienteDAO(this);
        clienteDAO.inserir(cliente);

        long resultado;

        Toast.makeText(this, "Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void editarCliente(View view){
        cliente.setNome(nomeCliente.getText().toString());
        cliente.setTelefone(telefoneCliente.getText().toString());

        ClienteDAO clienteDAO = new ClienteDAO(this);
        clienteDAO.atalizar(cliente);

        Toast.makeText(this, "Cliente \"" + cliente.getNome() + "\" atualizado com sucesso.", Toast.LENGTH_SHORT).show();

    }

}
