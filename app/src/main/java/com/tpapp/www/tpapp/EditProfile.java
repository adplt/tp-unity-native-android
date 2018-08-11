package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import Adapter.OptionAdapter;

public class EditProfile extends AppCompatActivity{

    private Session sm;
    private HashMap<String,String>user;
    private RelativeLayout rl_1,rl_2;
    private TextView  tv;
    private com.mikhaellopez.circularimageview.CircularImageView civ;
    private static final String URL_PHOTO_PROFILE = "http://json.tpunity.com/picture/";
    private static final String URL_BACKGROUND_PROFILE = "http://json.tpunity.com/background/";
    private ProgressBar pb_1,pb_2;
    private ImageView iv;
    private ProgressDialog pd;
    private ListView lv;
    private CameraPhoto cp;
    private GalleryPhoto gp;
    private int flag;
    private String photo_profile,background_profile;
    private AlertDialog.Builder adb;

    private static final int CAMERA_REQUEST = 13323;
    private static final int GALLERY_REQUEST = 22131;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_edit_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_9);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        pd = new ProgressDialog(EditProfile.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        tv = (TextView) findViewById(R.id.save_profile_label);
        tv.setTextColor(getResources().getColor(R.color.white_background));

        photo_profile = "";
        background_profile = "";

        sm = new Session(EditProfile.this);

        if(sm.getStatusLogin() == true){
            user = sm.getLogin();

            cp = new CameraPhoto(EditProfile.this);
            gp = new GalleryPhoto(EditProfile.this);

            pb_1 = (ProgressBar) findViewById(R.id.pg_1);
            pb_2 = (ProgressBar) findViewById(R.id.pg_2);
            civ = (com.mikhaellopez.circularimageview.CircularImageView) findViewById(R.id.photo_profile_edit);
            iv = (ImageView) findViewById(R.id.background_edit);

            rl_2 = (RelativeLayout) findViewById(R.id.option_layout_edit_profile);
            lv = (ListView) findViewById(R.id.option_listview_edit_profile);
            String[] option = new String[2];
            option[0] = "Camera";
            option[1] = "Gallery";
            OptionAdapter aa = new OptionAdapter(getBaseContext(),R.layout.interface_listview_option,option);
            lv.setAdapter(aa);
            rl_2.setVisibility(View.GONE);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DialogInterface di = adb.create();
                    di.cancel();

                    String opt = lv.getAdapter().getItem(position).toString();

                    if(opt == "Camera" || opt.equals("Camera")){
                        try{
                            startActivityForResult(cp.takePhotoIntent(), CAMERA_REQUEST);
                            cp.addToGallery();
                        }catch(IOException e){
                            Toast.makeText(getBaseContext(), "Unable to take camera.", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        startActivityForResult(gp.openGalleryIntent(), GALLERY_REQUEST);
                    }
                }
            });

            new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
            ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(EditProfile.this).build();
            ImageLoader.getInstance().init(ilc);
            ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + user.get(Session.PICTURE_LOGIN), civ, new ImageLoadingListener() {

                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    pb_1.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    pb_1.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    pb_1.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    pb_1.setVisibility(View.GONE);
                }

            });

            ImageLoader.getInstance().displayImage(URL_BACKGROUND_PROFILE + user.get(Session.BACKGROUND_LOGIN), iv, new ImageLoadingListener() {

                @Override
                public void onLoadingStarted(String imageUri, View view){
                    pb_2.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason){
                    pb_2.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage){
                    pb_2.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view){
                    pb_2.setVisibility(View.GONE);
                }

            });

            rl_1 = (RelativeLayout) findViewById(R.id.save_profile);
            rl_1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if((photo_profile == "" || photo_profile.equals("")) && (background_profile == "" || background_profile.equals(""))){
                        Toast.makeText(getBaseContext(),"No image selected.",Toast.LENGTH_LONG).show();
                    }else{
                        new AsyncTask<Bitmap,Void,String>(){

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                pd.show();
                            }

                            @Override
                            protected String doInBackground(Bitmap... params) {
                                return "";
                            }

                            @Override
                            protected void onPostExecute(String s){
                                super.onPostExecute(s);
                                s = uploadPhoto();

                                if(s == "" || s.equals("")){
                                    new AlertDialog.Builder(EditProfile.this)
                                            .setTitle("Edit Profile")
                                            .setMessage("Save setting successfully !")
                                            .setIcon(R.drawable.ic_logo_48dp)
                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    startActivity(new Intent(EditProfile.this, ProfileLogin.class));
                                                }
                                            })
                                            .show();
                                }else{
                                    Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();
                                }

                                if(pd.isShowing()){
                                    pd.dismiss();
                                }
                            }

                        }.execute();
                    }
                }
            });

            civ.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    flag = 0;
                    doOption();
                }
            });

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag = 1;
                    doOption();
                }
            });

        }else{
            new AlertDialog.Builder(EditProfile.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(EditProfile.this).execute();
                    }
                })
                .show();
        }
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
            .setMessage("Are you sure want to exit ?")
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(EditProfile.this, ProfileLogin.class));
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){}
            })
            .show();
    }

    public void doOption(){
        rl_2.setVisibility(View.VISIBLE);
        ((ViewGroup)rl_2.getParent()).removeView(rl_2);

        adb = new AlertDialog.Builder(EditProfile.this);
        adb.setView(rl_2);
        adb.setCancelable(true);
        adb.show();
    }

    public String uploadPhoto(){
        String result = "";

        if(photo_profile != "" || !photo_profile.equals("")){
            try{
                Bitmap b = com.kosalgeek.android.photoutil.ImageLoader.init().from(photo_profile).requestSize(512,512).getBitmap();
                String encode = getStringImage(b);

                HashMap<String,String> post_data = new HashMap<>();
                post_data.put("image",encode);

                PostResponseAsyncTask prat = new PostResponseAsyncTask(EditProfile.this, post_data, new AsyncResponse(){
                    @Override
                    public void processFinish(String s){
                        Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();
                    }
                });

                prat.execute("http://json.tpunity.com/upload.php?id="+ user.get(Session.NO_PRM_LOGIN) + "&folder=picture");
                prat.setEachExceptionsHandler(new EachExceptionsHandler() {

                    @Override
                    public void handleIOException(IOException e){
                        Toast.makeText(getBaseContext(),"Can't connect to server.",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleMalformedURLException(MalformedURLException e){
                        Toast.makeText(getBaseContext(),"URL error.",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleProtocolException(ProtocolException e){
                        Toast.makeText(getBaseContext(),"Protocol error.",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleUnsupportedEncodingException(UnsupportedEncodingException e){
                        Toast.makeText(getBaseContext(),"Encoding error.",Toast.LENGTH_LONG).show();
                    }

                });
            }catch(Exception e){
                //result = "Unable to encode photo profile";
                result = e.getMessage();
            }
        }else if(background_profile != "" || !background_profile.equals("")){
            try{
                Bitmap b = com.kosalgeek.android.photoutil.ImageLoader.init().from(background_profile).requestSize(512,512).getBitmap();
                String encode = getStringImage(b);

                HashMap<String,String> post_data = new HashMap<>();
                post_data.put("image",encode);

                PostResponseAsyncTask prat = new PostResponseAsyncTask(EditProfile.this, post_data, new AsyncResponse(){
                    @Override
                    public void processFinish(String s){}
                });

                prat.execute("http://json.tpunity.com/upload.php?id="+ user.get(Session.NO_PRM_LOGIN) + "&folder=background");
                prat.setEachExceptionsHandler(new EachExceptionsHandler() {

                    @Override
                    public void handleIOException(IOException e) {
                        Toast.makeText(getBaseContext(), "Can't connect to server.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleMalformedURLException(MalformedURLException e) {
                        Toast.makeText(getBaseContext(), "URL error.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleProtocolException(ProtocolException e) {
                        Toast.makeText(getBaseContext(), "Protocol error.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                        Toast.makeText(getBaseContext(), "Encoding error.", Toast.LENGTH_LONG).show();
                    }

                });
            }catch(Exception e){
                //result = "Unable to encode background picture";
                result = e.getMessage();
            }
        }

        return result;
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == CAMERA_REQUEST){
                String photo_path = cp.getPhotoPath();

                try{
                    Bitmap b = com.kosalgeek.android.photoutil.ImageLoader.init().from(photo_path).requestSize(512,512).getBitmap();
                    if(flag == 0){
                        civ.setImageBitmap(getRotateBitmap(b,90));
                        photo_profile = photo_path;
                    }else{
                        BitmapDrawable ob = new BitmapDrawable(getResources(), b);
                        iv.setBackground(ob);
                        background_profile = photo_path;
                    }
                }catch(Exception e){
                    Toast.makeText(getBaseContext(),"Unable to load photo.",Toast.LENGTH_LONG).show();
                }
            }else{
                Uri uri = data.getData();
                gp.setPhotoUri(uri);
                String photo_path = gp.getPath();

                try{
                    Bitmap b = com.kosalgeek.android.photoutil.ImageLoader.init().from(photo_path).requestSize(512,512).getBitmap();

                    if(flag == 0){
                        civ.setImageBitmap(b);
                        photo_profile = photo_path;
                    }else{
                        BitmapDrawable ob = new BitmapDrawable(getResources(), b);
                        iv.setBackgroundDrawable(ob);
                        background_profile = photo_path;
                    }
                }catch(Exception e){
                    Toast.makeText(getBaseContext(),"Unable to choose photo.",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public Bitmap getRotateBitmap(Bitmap source, float angle){
        Matrix m = new Matrix();
        m.postRotate(angle);
        Bitmap b = Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),m,true);
        return b;
    }

}