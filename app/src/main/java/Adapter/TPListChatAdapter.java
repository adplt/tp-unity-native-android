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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tpapp.www.tpapp.R;
import java.util.List;
import Data.TeamPromotion;

public class TPListChatAdapter extends ArrayAdapter<TeamPromotion>{

    private List<TeamPromotion> list_tpl_list;
    private LayoutInflater li;
    private String username;
    private ProgressBar pg;
    private static final String URL_PHOTO_PROFILE = "http://json.tpunity.com/picture/";

    public TPListChatAdapter(Context context, int resouce, List<TeamPromotion> list_tpl_list, String username){
        super(context, resouce, list_tpl_list);
        this.list_tpl_list = list_tpl_list;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.username = username;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder vh = new ViewHolder();

        if(convertView == null){
            if(list_tpl_list.get(position).getID() == username || list_tpl_list.get(position).getID().equals(username)){

            }else{
                convertView = li.inflate(R.layout.interface_listview_team_promotion, null);

                vh.tv_1 = (TextView) convertView.findViewById(R.id.tp_name);
                vh.tv_2 = (TextView) convertView.findViewById(R.id.tp_score);
                vh.civ = (CircularImageView) convertView.findViewById(R.id.picture_tp);

                pg = (ProgressBar) convertView.findViewById(R.id.pg_2);

                vh.tv_1.setText(list_tpl_list.get(position).getName());
                vh.tv_2.setVisibility(View.GONE);

                ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + list_tpl_list.get(position).getURLImage(), vh.civ, new ImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        pg.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        pg.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        pg.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        pg.setVisibility(View.GONE);
                    }

                });
                convertView.setTag(vh);
            }
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder{
        private TextView tv_1,tv_2;
        private CircularImageView civ;
    }

}