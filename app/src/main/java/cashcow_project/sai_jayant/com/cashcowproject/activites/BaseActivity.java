package cashcow_project.sai_jayant.com.cashcowproject.activites;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import cashcow_project.sai_jayant.com.cashcowproject.BuildConfig;
import cashcow_project.sai_jayant.com.cashcowproject.R;
import cashcow_project.sai_jayant.com.cashcowproject.adapters.MyPagerAdapter;
import cashcow_project.sai_jayant.com.cashcowproject.network.RetrofitClient;
import cashcow_project.sai_jayant.com.cashcowproject.network.RetrofitInterface;
import data.Dog;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.NetworkUtil;

/**
 * Created by macbookpro on 01/11/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;
    private MyPagerAdapter adapterViewPager;
    JsonArray ar, br;
    JsonArray jr;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        dialog = new ProgressDialog(this);
        dialog.setMessage("Doing something, please wait.");
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        getDataOneFromServer();
        getDataTwoFromServer();

        //change this if clause according to the view component
        if (getContentViewId() == R.layout.activity_dashboard) {
            ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
            CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
            //here the pager will start the sliding frgments
             showValue();
            adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
            vpPager.setAdapter(adapterViewPager);
            indicator.setViewPager(vpPager);
        }


    }

    private void showValue() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("DATAONE", Context.MODE_PRIVATE);
        String strSavedValue = sharedPreferences.getString("Data_one","");
        Log.d("First base", "onCreate: "+strSavedValue);
    }

    private void getDataOneFromServer() {
        if (NetworkUtil.isNetworkAvailble(this)) {
            dialog.show();
            RetrofitInterface ret = RetrofitClient.getClient().create(RetrofitInterface.class);
            Call<JsonElement> call = ret.getTestOne();
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                    Log.d("String", "onResponse:  response" + response.body());

                    if (response.code() == 200) {

                        JsonObject moviesResponse = null;

                        jr = response.body().getAsJsonArray();
                        Log.d("getDataOne", "onResponse: " + jr.toString());
                        String str = jr.get(0).toString();
                        Log.d("response", "onResponse: " + str);
                        SharedPreferences mPrefs = BaseActivity.this.getSharedPreferences("DATAONE", Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        prefsEditor.putString("Data_one",jr.toString());
                        prefsEditor.commit();


                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.d("response", "onFailure: " + t + "      " + call);
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });
            if (dialog.isShowing()) {
                dialog.dismiss();
        }

        }
    }

    private void getDataTwoFromServer() {
        if (NetworkUtil.isNetworkAvailble(this)) {
            dialog.show();

            RetrofitInterface ret = RetrofitClient.getClient().create(RetrofitInterface.class);
            Call<JsonElement> call = ret.getTestTwo();
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                    Log.d("String", "onResponse:  response" + response.body());

                    if (response.code() == 200) {

                        JsonObject moviesResponse = null;

                        JsonArray jr = response.body().getAsJsonArray();
                        Log.d("response", "onResponse: " + jr);
                        SharedPreferences mPrefs = BaseActivity.this.getSharedPreferences("DATATWO", Context.MODE_PRIVATE);

                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        prefsEditor.putString("Data_two",jr.toString());
                        Log.d("inpref", "onResponse: "+jr.toString());
                        prefsEditor.commit();
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.d("response", "onFailure: " + t + "      " + call);
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
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
