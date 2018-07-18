package com.vaultinnovation.androidcodetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vaultinnovation.androidcodetest.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.Collections;

public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> items;

    ListAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String item) {
        items.add(item);
        Collections.sort(items, String.CASE_INSENSITIVE_ORDER);
        notifyDataSetChanged();
    }

    public ArrayList<String> getItems() {
        return items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            ListItemBinding binding = ListItemBinding.inflate(inflater, viewGroup, false);
            convertView = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String textItem = getItem(position);
        viewHolder.binding.text.setText(textItem);

        return convertView;
    }

    static class ViewHolder {
        ListItemBinding binding;

        public ViewHolder(ListItemBinding binding) {
            this.binding = binding;
        }
    }
}
