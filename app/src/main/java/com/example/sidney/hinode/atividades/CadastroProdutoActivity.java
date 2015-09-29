package com.example.sidney.hinode.atividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sidney.hinode.modelos.Produto;
import com.example.sidney.hinode.DAO.ProdutoDAO;
import com.example.sidney.hinode.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sidney on 07/08/2015.
 */
public class CadastroProdutoActivity extends AppCompatActivity {

    private Spinner categoria;
    private Produto produto = new Produto();


    private EditText nomeProduto, codProduto, descProduto, precoProduto;
    private Button salvarBt;
    private Button editarBt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_produtos);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.categoria_produto,
                android.R.layout.simple_spinner_item);
        categoria = (Spinner) findViewById(R.id.spinnerCategoria);
        categoria.setAdapter(adapter);

        codProduto = (EditText) findViewById(R.id.etCodigoDoProduto);
        nomeProduto = (EditText) findViewById(R.id.etNomeDoProduto);
        descProduto = (EditText) findViewById(R.id.etDescricao);
        precoProduto = (EditText) findViewById(R.id.etPrecoProduto);

        salvarBt = (Button) findViewById(R.id.bCadastrarProduto);
        editarBt = (Button) findViewById(R.id.bEditarProduto);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                produto.setId(bundle.getLong("id"));
                produto.setNome(bundle.getString("nome"));
                produto.setCodigo(bundle.getString("codigo"));
                produto.setPreco(bundle.getFloat("preco"));
                produto.setCategoria(bundle.getString("categoria"));
                produto.setDescricao(bundle.getString("descrição"));

                codProduto.setText(produto.getCodigo());
                nomeProduto.setText(produto.getNome());
                categoria = (Spinner) findViewById(R.id.spinnerCategoria);
                categoria.setAdapter(adapter);
                categoria.setSelection(pegarCategoria(produto.getCategoria()));
                descProduto.setText(produto.getDescricao());
                precoProduto.setText(String.valueOf(produto.getPreco()));

                salvarBt.setVisibility(View.GONE);
                editarBt.setVisibility(View.VISIBLE);


            }
        }
    }

    public void salvarProduto(View view) {

        produto.setCodigo(codProduto.getText().toString());
        produto.setNome(nomeProduto.getText().toString());
        produto.setPreco(Float.parseFloat(precoProduto.getText().toString()));
        produto.setCategoria(categoria.getSelectedItem().toString());
        produto.setDescricao(descProduto.getText().toString());

        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        produtoDAO.inserir(produto);

        Toast.makeText(this, "Produto inserido com sucesso", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void editarProduto(View view) {

        produto.setNome(nomeProduto.getText().toString());
        produto.setCodigo(codProduto.getText().toString());
        produto.setPreco(Float.parseFloat(precoProduto.getText().toString()));
        produto.setCategoria(categoria.getSelectedItem().toString());
        produto.setDescricao(descProduto.getText().toString());

        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        produtoDAO.atualizar(produto);

        Toast.makeText(this, "Produto \"" + produto.getNome() + "\" atualizado com sucesso.", Toast.LENGTH_LONG).show();

    }

    public void fechar(View view) {
        finish();
    }



    public int pegarCategoria(String categoria) {

        ArrayList<String> cat = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.categoria_produto)));
        return cat.indexOf(categoria);

    }

}
