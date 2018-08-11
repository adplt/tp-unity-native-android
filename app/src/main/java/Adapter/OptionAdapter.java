package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tpapp.www.tpapp.R;

import java.util.List;

import Data.SettingItem;

public class OptionAdapter extends ArrayAdapter{

    private String [] option;
    private LayoutInflater li;

    public OptionAdapter(Context context, int resource, String[] option){
        super(context, resource, option);
        this.option=option;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = li.inflate(R.layout.interface_listview_option, null);

            TextView tv = (TextView) convertView.findViewById(R.id.title);
            tv.setText(option[position]);
        }

        return convertView;
    }

}