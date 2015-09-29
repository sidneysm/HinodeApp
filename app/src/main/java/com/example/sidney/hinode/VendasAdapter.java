package com.example.sidney.hinode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.sidney.hinode.modelos.Cliente;
import com.example.sidney.hinode.modelos.Venda;

import java.util.List;

/**
 * Created by Sidney on 29/09/2015.
 */
public class VendasAdapter extends BaseAdapter{
    private Context context;
    private List<Venda> list;

    public VendasAdapter(Context context, List<Venda> list) {
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
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_vendas_list, null);



        return layout;
    }
}
