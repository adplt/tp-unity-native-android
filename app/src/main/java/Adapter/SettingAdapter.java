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

public class SettingAdapter extends ArrayAdapter<Data.SettingItem>{

    private List<SettingItem> si;
    private LayoutInflater li;

    public SettingAdapter(Context context, int resource, List<SettingItem> si){
        super(context, resource, si);
        this.si = si;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = li.inflate(R.layout.interface_listview_setting, null);

            TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
            TextView tv_2 = (TextView) convertView.findViewById(R.id.note);

            tv_1.setText(si.get(position).getTittle());
            tv_2.setText(si.get(position).getNote());
        }

        return convertView;
    }

}
