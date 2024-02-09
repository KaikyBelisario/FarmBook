package com.example.farmbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {

    Context context;

    ArrayList<Farm> list;

    LayoutInflater inflater;

    public Adapter(Context context, ArrayList<Farm> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item,null);
        TextView tvfnome = (TextView) convertView.findViewById(R.id.tvfnome);
        TextView tvfcriador = (TextView) convertView.findViewById(R.id.tvfcriador);
        TextView tvfproducao = (TextView) convertView.findViewById(R.id.tvfproducao);
        TextView tvflink = (TextView) convertView.findViewById(R.id.tvflink);
        tvfnome.setText(list.get(position).getFarm());
        tvfcriador.setText(list.get(position).getCriador());
        tvfproducao.setText(list.get(position).getProducao());
        tvflink.setText(list.get(position).getLink());
        return convertView;
    }
}
