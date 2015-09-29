package com.example.sidney.hinode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sidney.hinode.DAO.ProdutoDAO;
import com.example.sidney.hinode.atividades.CadastroProdutoActivity;
import com.example.sidney.hinode.modelos.Produto;

import java.util.List;

/**
 * Created by Sidney on 11/08/2015.
 */
public class ProdutoAdapter extends BaseAdapter {



    private Context context;
    private List<Produto> list;

    public ProdutoAdapter(Context context, List<Produto> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int i) {

        return list.get(i);
    }

    @Override
    public long getItemId(int i) {

        return Long.parseLong(list.get(i).getCodigo());
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int auxPosition = position;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_produto_list, null);

        TextView tvCodigo = (TextView) layout.findViewById(R.id.codProduto);
        tvCodigo.setText("Código: " + list.get(position).getCodigo());

        TextView tvNome = (TextView) layout.findViewById(R.id.nome);
        tvNome.setText("Nome: " + list.get(position).getNome());

        TextView tvPreco = (TextView) layout.findViewById(R.id.preco);
        tvPreco.setText("Preço: " + String.valueOf(list.get(position).getPreco()));

        TextView tvCategoria = (TextView) layout.findViewById(R.id.categoria);
        tvCategoria.setText("Categoria: " + list.get(position).getCategoria());

        TextView tvDescricao = (TextView) layout.findViewById(R.id.descricao);
        tvDescricao.setText("Descrição: " + list.get(position).getDescricao());



        Button editarBt = (Button) layout.findViewById(R.id.editar);
        editarBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View i) {
                Intent intent = new Intent(context, CadastroProdutoActivity.class);
                intent.putExtra("id", list.get(auxPosition).getId());
                intent.putExtra("nome", list.get(auxPosition).getNome());
                intent.putExtra("preco", list.get(auxPosition).getPreco());
                intent.putExtra("categoria", list.get(auxPosition).getCategoria());
                intent.putExtra("descrição", list.get(auxPosition).getDescricao());
                intent.putExtra("codigo", list.get(auxPosition).getCodigo());
                context.startActivity(intent);
            }
        });

        Button deletarBt = (Button) layout.findViewById(R.id.deletar);
        deletarBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View i) {
                ProdutoDAO bd = new ProdutoDAO(context);
                bd.deletar(list.get(auxPosition));

                layout.setVisibility(View.GONE);
            }
        });



        return layout;
    }
}
