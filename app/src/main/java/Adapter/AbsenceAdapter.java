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

public class AbsenceAdapter extends ArrayAdapter<Absence>{

    private List<Absence> a_list;
    private LayoutInflater li;

    public AbsenceAdapter(Context context, int resource, List<Data.Absence> a_list){
        super(context, resource, a_list);

        this.a_list=a_list;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=li.inflate(R.layout.interface_listview_absence_history,null);

            TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
            TextView tv_2 = (TextView) convertView.findViewById(R.id.note);

            tv_1.setText(a_list.get(position).getAbsenceIn() + " - " + a_list.get(position).getAbsenceOut());
            tv_2.setText(a_list.get(position).getInfoSupport());
        }

        return convertView;
    }

}

