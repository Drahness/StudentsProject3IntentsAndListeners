package com.example.studentsproject3intentsandlisteners;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private final Context context;
    private final List<Item> list;

    public MyAdapter(Context context, String... values) {
        this.context = context;
        list = new ArrayList<>();
        for (String value : values) {
            list.add(new Item(value, false));
        }
    }

    public String[] getChecked() {
        List<Item> checkeds = new ArrayList<>();
        for (Item item : list) {
            if (item.isChecked()) {
                checkeds.add(item);
            }
        }
        String[] arrayof = new String[checkeds.size()];
        for (int i = 0; i < checkeds.size(); i++) {
            arrayof[i] = checkeds.get(i).ItemString;
        }
        return arrayof;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override // Que tonteria de metodo :/
    public long getItemId(int position) {
        return position;
    }

    public boolean isChecked(int position) {
        return list.get(position).checked;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        // reuse views
        ViewHolder viewHolder = new ViewHolder();
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_list_my_vocational, null);

            viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.asignatura_check_box);
            //viewHolder.icon = (ImageView) rowView.findViewById(R.id.rowImageView);
            viewHolder.text = (TextView) rowView.findViewById(R.id.textview_vocational);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.checkBox.setChecked(list.get(position).checked);

        final String itemStr = list.get(position).ItemString;

        viewHolder.checkBox.setTag(position);
        viewHolder.text.setText(itemStr);
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean newState = !list.get(position).isChecked();
                list.get(position).checked = newState;
                Toast.makeText(context.getApplicationContext(),
                        itemStr,
                        Toast.LENGTH_SHORT).show();
                finalViewHolder.checkBox.setChecked(newState);
            }
        });
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean newState = !list.get(position).isChecked();
                list.get(position).checked = newState;
                Toast.makeText(context.getApplicationContext(),
                        itemStr,
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.checkBox.setChecked(isChecked(position));

        return rowView;
    }

    public static class ViewHolder {
        CheckBox checkBox;
        TextView text;
    }

    public class Item {
        boolean checked;
        String ItemString;

        Item(String t, boolean b) {
            ItemString = t;
            checked = b;
        }

        public boolean isChecked() {return checked;}
    }
}
