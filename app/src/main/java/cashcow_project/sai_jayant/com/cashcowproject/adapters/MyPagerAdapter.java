package cashcow_project.sai_jayant.com.cashcowproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cashcow_project.sai_jayant.com.cashcowproject.frgments.FirstFragment;
import cashcow_project.sai_jayant.com.cashcowproject.frgments.SecondFragment;

/**
 * Created by macbookpro on 01/11/17.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    String[] StringData;//Make field for fetching the data

    public MyPagerAdapter(FragmentManager fragmentManager, String[] data) {
        super(fragmentManager);
        StringData = data;
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
                return FirstFragment.newInstance(0, StringData[0]);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return FirstFragment.newInstance(1, StringData[1]);
            case 2: // Fragment # 1 - This will show SecondFragment
                return SecondFragment.newInstance(2, StringData[2]);
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