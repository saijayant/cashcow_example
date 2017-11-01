package cashcow_project.sai_jayant.com.cashcowproject.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import cashcow_project.sai_jayant.com.cashcowproject.R;
import cashcow_project.sai_jayant.com.cashcowproject.adapters.MyPagerAdapter;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by macbookpro on 01/11/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;
    private MyPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);


        //change this if clause according to the view component
        if (getContentViewId() == R.layout.activity_dashboard) {
            ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
            //here the pager will start the sliding frgments
            String[] thisIsAStringArray = new String[3];
            //this the data i placed in form of string array.
            thisIsAStringArray[0] = "AAA AAA AAA AAAAA\nAAAA AAAAA AAAAAA";
            thisIsAStringArray[1] = "BBB BBB BBB BBBB\nAAAA AAAAA AAAAAA";
            thisIsAStringArray[2] = "CCC CCC CCC CCCC\nAAAA AAAAA AAAAAA";

            adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), thisIsAStringArray);
            vpPager.setAdapter(adapterViewPager);
            indicator.setViewPager(vpPager);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        navigationView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    Toast.makeText(BaseActivity.this, "home", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BaseActivity.this, HomeActivity.class));
                } else if (itemId == R.id.navigation_dashboard) {
                    Toast.makeText(BaseActivity.this, "dash", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(BaseActivity.this, DashboardActivity.class));
                } else if (itemId == R.id.navigation_notifications) {
                    Toast.makeText(BaseActivity.this, "noti", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(BaseActivity.this, NotificationsActivity.class));
                }
                finish();
            }

        }, 300);

        return true;
    }

    private void updateNavigationBarState() {
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        Menu menu = navigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked) {
                item.setChecked(true);
                break;
            }
        }
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

}
