package com.example.eltonjosedesouza.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eltonjosedesouza on 29/04/15.
 */
public class CustomArrayAdapter extends ArrayAdapter<CidadeEstadoModel.Estado> {
    // declaring our ArrayList of items
    private ArrayList<CidadeEstadoModel.Estado> estadoArrayList;
    private ArrayList<CidadeEstadoModel.Estado> estadoArrayListAll;
    private ArrayList<CidadeEstadoModel.Estado> estadoSugestaoArrayList;
    private int viewResourceId;


    public CustomArrayAdapter(Context context, int textViewResourceId,
                              ArrayList<CidadeEstadoModel.Estado>
                                      estadoArrayList) {
        super(context, textViewResourceId, estadoArrayList);
        this.estadoArrayList = estadoArrayList;
        this.estadoArrayListAll =
                (ArrayList<CidadeEstadoModel.Estado>) estadoArrayList.clone();
        this.estadoSugestaoArrayList =
                new ArrayList<CidadeEstadoModel.Estado>();
        this.viewResourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(viewResourceId, null);

        }

        CidadeEstadoModel.Estado estado = estadoArrayList.get(position);

        if (estado != null) {
            TextView uf = (TextView) v.findViewById(R.id.uf_estado);
            uf.setText(estado.getSigla());
        }

        return v;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }


    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((CidadeEstadoModel.Estado) (resultValue)).getSigla();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                estadoSugestaoArrayList.clear();
                for (CidadeEstadoModel.Estado estado : estadoArrayListAll) {
                    if (estado.getSigla().toLowerCase()
                            .startsWith(constraint.toString().toLowerCase())) {
                        estadoSugestaoArrayList.add(estado);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = estadoSugestaoArrayList;
                filterResults.count = estadoSugestaoArrayList.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            ArrayList<CidadeEstadoModel.Estado> filteredList =
                    (ArrayList<CidadeEstadoModel.Estado>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (CidadeEstadoModel.Estado c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}


