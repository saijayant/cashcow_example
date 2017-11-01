package cashcow_project.sai_jayant.com.cashcowproject.activites;

import android.os.Bundle;

import cashcow_project.sai_jayant.com.cashcowproject.R;

/**
 * Created by macbookpro on 01/11/17.
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }

}
