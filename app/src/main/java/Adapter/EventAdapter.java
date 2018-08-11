package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.tpapp.www.tpapp.R;

import java.io.FileInputStream;
import java.util.List;
import Data.Event;

public class EventAdapter extends ArrayAdapter<Event> {

    private List<Event> e_list;
    private LayoutInflater li;
    private ProgressBar pb;
    private static final String URL_EVENT_PROFILE = "http://json.tpunity.com/event/";

    public EventAdapter(Context context, int resource, List<Data.Event> e_list){
        super(context, resource, e_list);

        this.e_list=e_list;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder vh = new ViewHolder();

        if(convertView==null){
            convertView = li.inflate(R.layout.interface_listview_event, null);

            vh.civ = (CircularImageView) convertView.findViewById(R.id.ev_picture);
            vh.tv_1 = (TextView) convertView.findViewById(R.id.ev_name);
            vh.tv_2 = (TextView) convertView.findViewById(R.id.ev_address);
            vh.tv_3 = (TextView) convertView.findViewById(R.id.ev_date);
            vh.tv_4 = (TextView) convertView.findViewById(R.id.ev_note);
            pb = (ProgressBar) convertView.findViewById(R.id.pg);

            vh.tv_1.setText(e_list.get(position).getName());
            vh.tv_2.setText(e_list.get(position).getAddress());
            vh.tv_3.setText(e_list.get(position).getStart() + " - " + e_list.get(position).getEnd());
            vh.tv_4.setText(e_list.get(position).getNote());

            /*ImageLoader.getInstance().displayImage(URL_EVENT_PROFILE + e_list.get(position).getEventPicture(), vh.civ, new ImageLoadingListener() {

                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    pb.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    pb.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    pb.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    pb.setVisibility(View.GONE);
                }

            });*/

            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder{
        private TextView tv_1,tv_2,tv_3,tv_4;
        private CircularImageView civ;
    }
}
