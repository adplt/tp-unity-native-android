package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tpapp.www.tpapp.R;

import java.util.List;

import Data.CandidateStudent;

public class HistoryAdapter extends ArrayAdapter {

    private List<CandidateStudent> list_fuh;
    private LayoutInflater li;
    private TextView tv_1,tv_2;

    public HistoryAdapter(Context context, int resource, List<CandidateStudent> list_fuh){
        super(context, resource, list_fuh);
        this.list_fuh=list_fuh;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView=li.inflate(R.layout.interface_listview_absence_history,null);

            tv_1 = (TextView) convertView.findViewById(R.id.title);
            tv_2 = (TextView) convertView.findViewById(R.id.note);

            tv_1.setText(list_fuh.get(position).getName());
            tv_2.setText(list_fuh.get(position).getDateFollowUp());
        }

        return convertView;
    }
}
