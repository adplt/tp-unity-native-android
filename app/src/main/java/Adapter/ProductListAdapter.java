package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tpapp.www.tpapp.R;

import java.util.List;
import Data.Absence;
import Data.Degree;

public class ProductListAdapter extends ArrayAdapter<Degree>{

    private List<Degree> d_list;
    private LayoutInflater li;
    private String faculty;

    public ProductListAdapter(Context context, int resource, List<Data.Degree> d_list, String faculty){
        super(context, resource, d_list);

        this.d_list=d_list;
        this.faculty = faculty;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=li.inflate(R.layout.interface_listview_product_list,null);

            TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
            TextView tv_2 = (TextView) convertView.findViewById(R.id.note);

            tv_1.setText(d_list.get(position).getDegreeName());
            tv_2.setText("");
        }

        return convertView;
    }

}

