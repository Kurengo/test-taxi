package com.example.test_task.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.test_task.R;
import com.example.test_task.ui.fragments.CitiesFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCitiesScreen();
        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.sort_by_city:
                if (isCurrentCitiesFragment() && getFragment(CitiesFragment.TAG) != null) {
                    CitiesFragment fragment = (CitiesFragment) getFragment(CitiesFragment.TAG);
                    fragment.sortByCity();
                }
                break;
            case R.id.sort_by_id:
                if (isCurrentCitiesFragment() && getFragment(CitiesFragment.TAG) != null) {
                    CitiesFragment fragment = (CitiesFragment) getFragment(CitiesFragment.TAG);
                    fragment.sortById();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Fragment getFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    private boolean isCurrentCitiesFragment() {
        boolean isCitiesFragment = false;
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(CitiesFragment.TAG);
        if (fragment != null && fragment.isVisible()) {
            isCitiesFragment = true;
        }
        return isCitiesFragment;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    public void showCitiesScreen() {
        CitiesFragment fragment = CitiesFragment.getInstance();
        showFragment(fragment, CitiesFragment.TAG);
    }

    private void showFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.contentFrame, fragment, tag)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }
}