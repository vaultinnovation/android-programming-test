package com.vaultinnovation.androidcodetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private LayoutInflater inflater=null;
    private Context mContext;
    private ArrayList<String> arrayTexts;

    public class ViewHolder{
        TextView tv_title;

        public ViewHolder(View view) {
            tv_title = (TextView) view.findViewById(R.id.item_text);
        }
    }

    public ListAdapter(Context context) {
        arrayTexts = new ArrayList<>();
        arrayTexts.addAll(TextManager.sharedInstance().arrayTexts);
        mContext = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return arrayTexts.size();
    }

    @Override
    public String getItem(int position) {
        return arrayTexts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        String url = null;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mListener.tappedItemCell(position);
            }
        });

        String strText = arrayTexts.get(position);
        holder.tv_title.setText(strText);

        return convertView;
    }

    private OnTextListener mListener;
    public void setOnTextListener(OnTextListener listener)
    {
        mListener = listener;
    }

    public interface OnTextListener{
        void tappedItemCell(int postion);
    }
}
