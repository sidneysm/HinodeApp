package com.example.sidney.hinode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.sidney.hinode.DAO.ClienteDAO;
import com.example.sidney.hinode.DAO.ProdutoDAO;
import com.example.sidney.hinode.atividades.CadastroClienteActivity;
import com.example.sidney.hinode.atividades.CadastroProdutoActivity;
import com.example.sidney.hinode.atividades.ClienteListActvity;
import com.example.sidney.hinode.modelos.Cliente;
import com.example.sidney.hinode.modelos.Produto;

import java.util.List;

/**
 * Created by Sidney on 14/08/2015.
 */
public class ClienteAdapter extends BaseAdapter {
    private Context context;
    private List<Cliente> list;

    public ClienteAdapter(Context context, List<Cliente> list) {
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

        return list.get(i).getId();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final int auxPosition = i;

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_cliente_list_actvity, null);

        TextView tvNomeCliente = (TextView) layout.findViewById(R.id.clienteNome);
        tvNomeCliente.setText("Nome: " + list.get(i).getNome());

        TextView tvTelefoneCliente = (TextView) layout.findViewById(R.id.clienteTelefone);
        tvTelefoneCliente.setText("Telefone: " + list.get(i).getTelefone());

        Button editarBt = (Button) layout.findViewById(R.id.editarClient);
        editarBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, CadastroClienteActivity.class);
                intent.putExtra("id", list.get(auxPosition).getId());
                intent.putExtra("nome", list.get(auxPosition).getNome());
                intent.putExtra("telefone", list.get(auxPosition).getTelefone());
                context.startActivity(intent);

            }
        });

        Button deletarBt = (Button) layout.findViewById(R.id.deletarCliente);
        deletarBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ClienteDAO bd = new ClienteDAO(context);
                bd.deletar(list.get(auxPosition));

                layout.setVisibility(View.GONE);
            }
        });


        return layout;
    }
}
