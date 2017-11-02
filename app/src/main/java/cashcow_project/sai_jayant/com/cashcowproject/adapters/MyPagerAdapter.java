package cashcow_project.sai_jayant.com.cashcowproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.google.gson.JsonArray;

import cashcow_project.sai_jayant.com.cashcowproject.frgments.FirstFragment;
import cashcow_project.sai_jayant.com.cashcowproject.frgments.SecondFragment;

/**
 * Created by macbookpro on 01/11/17.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    JsonArray a,b;



    public MyPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return FirstFragment.newInstance(0, "one");
            case 1: // Fragment # 0 - This will show FirstFragment different title

                return FirstFragment.newInstance(1, "two");
            case 2: // Fragment # 1 - This will show SecondFragment
                return FirstFragment.newInstance(2, "three");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}