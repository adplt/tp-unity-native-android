package Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.tpapp.www.tpapp.R;
import java.util.HashMap;
import java.util.List;
import Data.Branch;
import Data.Faculty;

public class ProductAdapter extends BaseExpandableListAdapter{

    private Context c;
    private List<Data.Branch> list_b;
    private HashMap<String,List<Faculty>> list_f;

    public ProductAdapter(Context c, List<Data.Branch> list_b, HashMap<String,List<Faculty>> list_f){
        this.c=c;
        this.list_b=list_b;
        this.list_f=list_f;
    }

    @Override
    public int getGroupCount(){
        return list_b.size();
    }

    @Override
    public int getChildrenCount(int parent){
        return list_f.size();
    }

    @Override
    public Object getGroup(int parent){
        return list_b.get(parent);
    }

    @Override
    public Object getChild(int parent, int child){
        if(list_b.size()!=0){

            Log.e("list_b.size()", String.valueOf(list_b.size()));

            if(list_f.get(String.valueOf(list_b.get(parent).getID())).size()>0 || list_f.get(String.valueOf(list_b.get(parent).getID())).size()<4){
                Log.e("Product Adapter", String.valueOf(list_f.get(String.valueOf(list_b.get(parent).getID())).size()));
                return list_f.get(String.valueOf(list_b.get(parent).getID())).get(child);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public long getGroupId(int parent){
        return parent;
    }

    @Override
    public long getChildId(int parent, int child){
        return child;
    }

    @Override
    public boolean hasStableIds(){
        return true;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convert_view, ViewGroup parent_view) {

        Data.Branch branch_name = (Branch) getGroup(parent);

        if(convert_view == null){
            LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convert_view = li.inflate(R.layout.content_product_parent,parent_view,false);

            TextView tv = (TextView) convert_view.findViewById(R.id.parent);
            tv.setText(branch_name.getBranchName());
            tv.setTypeface(null, Typeface.BOLD);
        }

        return convert_view;
    }

    @Override
    public View getChildView(int parent, int child, boolean last_child, View convert_view, ViewGroup parent_view) {

        Data.Faculty faculty_name = (Data.Faculty) getChild(parent, child);

        if(convert_view == null){
            LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convert_view = li.inflate(R.layout.content_product_child,parent_view,false);

            TextView tv_1 = (TextView) convert_view.findViewById(R.id.child);
            TextView tv_2 = (TextView) convert_view.findViewById(R.id.detail);

            tv_1.setText(faculty_name.getFacultyName());
            tv_2.setText(faculty_name.getNote());
        }

        return convert_view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){
        /*query = query.toLowerCase();

        if(query.isEmpty()){
            list_b.addAll(list_ori);
        }else{
            for(Data.Branch b:list_ori){
                ArrayList<Data.Faculty> list_f = b.getListFaculty();
                ArrayList<Data.Faculty> list_new = new ArrayList<>();

                for(Data.Faculty f:list_f){
                    if(f.getFacultyName().toLowerCase().contains(query)){
                        list_new.add(f);
                    }
                }

                if(list_new.size()>0){
                    Data.Branch new_b = new Data.Branch(b.getBranchName(),list_new);
                    list_b.add(new_b);
                }
            }
        }

        notifyDataSetChanged();*/
    }

}