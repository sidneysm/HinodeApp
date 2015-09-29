package com.example.sidney.hinode.atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.sidney.hinode.DAO.ProdutoDAO;
import com.example.sidney.hinode.ProdutoAdapter;
import com.example.sidney.hinode.R;
import com.example.sidney.hinode.modelos.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelecionarProdutoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv;
    private EditText et;
    private List<Produto> list;
    private ProdutoDAO produtoDAO;
    private ArrayList<HashMap<String, String>> produtos;
    private ArrayList<Long> produtosSelecionados;
    private ArrayList<HashMap<String, String>> produtosEncontrados = new ArrayList<>();
    private String[] from;
    private int[] to;
    private int layoutNativo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_produto);

        lv = (ListView) findViewById(R.id.lvProdutos);
        et = (EditText) findViewById(R.id.etLocalizarProduto);

        produtoDAO = new ProdutoDAO(this);
        list = produtoDAO.buscar();
        produtos = new ArrayList<>();



        for (int i = 0; i < list.size(); i++){
            HashMap<String, String> produto = new HashMap<String, String>();
            produto.put("nome", "Produto: " + list.get(i).getNome());
            produto.put("código", "Código: " + list.get(i).getId());

            produtos.add(produto);
        }

        from = new String[]{"nome", "código"};

        layoutNativo = android.R.layout.simple_list_item_multiple_choice;

        to = new int[]{android.R.id.text1, android.R.id.text2};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, (ArrayList) produtos);

        lv.setAdapter(new SimpleAdapter(this, produtos, layoutNativo, from, to));
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        CarregarEncontrados();

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CarregarEncontrados();
                lv.setAdapter(new SimpleAdapter(SelecionarProdutoActivity.this, produtosEncontrados, layoutNativo, from, to));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lv.setOnItemClickListener(this);
    }

    public void CarregarEncontrados(){
        int textlength = et.getText().length();

        produtosEncontrados.clear();

        for (int i = 0; i < produtos.size(); i++){
            if (textlength <= produtos.get(i).get("nome").length()){
                if (produtos.get(i).get("nome").contains(et.getText().toString())){
                    produtosEncontrados.add(produtos.get(i));
                }

            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();

        produtosSelecionados = new ArrayList<Long>();



        SparseBooleanArray status = lv.getCheckedItemPositions();

        produtosSelecionados.clear();

        HashMap<String, String> nome = (HashMap<String, String>) adapterView.getItemAtPosition(i);

        for (int j = 0; j < status.size(); j++) {
            if (status.valueAt(j)) {
                HashMap<String, String> cod = (HashMap<String, String>) adapterView.getItemAtPosition(j);
                produtosSelecionados.add(Long.parseLong(cod.get("código").replace("Código: ", "").toString()));
            }
        }
        String mensagem = "Você clicou no produto" + nome.get("nome").replace("Produto: ", "");

        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT)
                .show();

        intent.putExtra("nome", nome.get("nome").replace("Produto: ", ""));
        intent.putExtra("id", Long.parseLong(nome.get("código").replace("Código: ", "")));
        intent.putExtra("selecionados", produtosSelecionados);


        setResult(2, intent);
        //finish();
    }
    public void sair(View view){
        finish();
    }
}
