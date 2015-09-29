package com.example.sidney.hinode.atividades;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sidney.hinode.DAO.ProdutoDAO;
import com.example.sidney.hinode.DAO.VendasDAO;
import com.example.sidney.hinode.DAO.VendasEnvolveProdutoDAO;
import com.example.sidney.hinode.R;
import com.example.sidney.hinode.modelos.Produto;
import com.example.sidney.hinode.modelos.Venda;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class EfetuarVendasActivity extends AppCompatActivity implements Button.OnClickListener {
    public static final int CONSTANTE_TELA_CLIENTES = 1;
    public static final int CONSTANTE_TELA_PRODUTOS = 2;

    private EditText etCliente, etDataVenda, precoVenda;
    private EditText etProduto;
    private ImageButton btBuscarData;
    private long idCliente;
    private ArrayList<Long> idProdutos = new ArrayList<Long>();
    private long idProd;


    private Button botao;

    private int dia_x, mes_x, ano_x;

    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efetuar_vendas);

        botao = (Button) findViewById(R.id.bAdcionarVenda);
        precoVenda = (EditText) findViewById(R.id.precoVenda);


        etCliente = (EditText) findViewById(R.id.etBuscarCliente);
        etProduto = (EditText) findViewById(R.id.etBuscarProduto);

        final Calendar calendar = Calendar.getInstance();
        dia_x = calendar.get(Calendar.DAY_OF_MONTH);
        mes_x = calendar.get(Calendar.MONTH);
        ano_x = calendar.get(Calendar.YEAR);

        showDialogOnButtonClick();
        etDataVenda = (EditText) findViewById(R.id.etDataVenda);

    }

    public void showDialogOnButtonClick() {
        btBuscarData = (ImageButton) findViewById(R.id.btBuscarData);

        btBuscarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickarListner, ano_x, mes_x, dia_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickarListner =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                    ano_x = ano;
                    mes_x = mes+1;
                    dia_x = dia;
                    Toast.makeText(EfetuarVendasActivity.this,dia_x+ "/"+mes_x+"/" + ano, Toast.LENGTH_SHORT).show();
                    etDataVenda.setText(dia_x + "/" + mes_x + "/" + ano_x);
                }
            };




    public void buscarProduto(View view) {
        startActivityForResult(new Intent(this, SelecionarProdutoActivity.class), CONSTANTE_TELA_PRODUTOS);
    }

    public void buscarCliente(View view) {
        startActivityForResult(new Intent(this, SelicionarClienteActivity.class), CONSTANTE_TELA_CLIENTES);
    }


    protected void onActivityResult(int codigoTela, int resuldade, Intent intent) {
        if (codigoTela == CONSTANTE_TELA_CLIENTES) {
            Bundle params = intent.getExtras();
            etCliente.setText(params.getString("nome").replace("Cliente: ", ""));
            idCliente = params.getLong("id");

        } else if (codigoTela == CONSTANTE_TELA_PRODUTOS) {
            Bundle params = intent.getExtras();
            if (params != null) {
                etProduto.setText(params.getString("nome"));
                idProdutos.add(params.getLong("id"));
                idProd = params.getLong("id");

                ArrayList<Integer> selecteds = params.getIntegerArrayList("selecionados");

                StringBuilder message = new StringBuilder();
                message.append("Produtos selecionados:\n");

                for (int i = 0; i < selecteds.size(); i++) {

                    idProdutos.add(selecteds.get(i).longValue());
                    message.append(idProdutos.get(i) + "\n");
                }

                // Exibe o nome dos estados selecionados
                Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();

            }
        }

    }

    public void salvarVenda(View view){
        VendasDAO vendasDAO = new VendasDAO(this);
        VendasEnvolveProdutoDAO vendasEnvolveProdutoDAO = new VendasEnvolveProdutoDAO(this);
        ProdutoDAO produtoDAO = new ProdutoDAO(this);


        Venda venda = new Venda();
        venda.setIdCliente(idCliente);
        venda.setData(Date.valueOf(ano_x + "-" + mes_x + "-" + dia_x));
        venda.setValor(produtoDAO.buscar(idProd).getPreco());

        long idVenda = vendasDAO.inserir(venda);

        vendasEnvolveProdutoDAO.inserir(idVenda, idProd, 1);
        Toast.makeText(this, "Venda cadastrada com sucesso", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void onClick(View view) {
    }
}
