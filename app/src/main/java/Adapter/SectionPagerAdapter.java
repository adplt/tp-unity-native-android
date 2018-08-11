package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tpapp.www.tpapp.AvailableSchedule;
import com.tpapp.www.tpapp.EventSchedule;
import com.tpapp.www.tpapp.SupportSchedule;

public class SectionPagerAdapter extends FragmentPagerAdapter{

    public SectionPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                return new AvailableSchedule();
            case 1:
                return new SupportSchedule();
            case 2:
                return new EventSchedule();
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Available";
            case 1:
                return "Support";
            case 2:
                return "Event";
        }

        return null;
    }

}
