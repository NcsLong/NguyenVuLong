package com.example.demotest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.demotest.adapter.ViewPagerAdapterTest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewPager2 viewPager2;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.menu_activity);
            bottomNavigationView = findViewById(R.id.dacda);
            drawerLayout = findViewById(R.id.drawer_layout);
            toolbar = findViewById(R.id.toolbar);
            viewPager2 = findViewById(R.id.viewparger2);
            setupViewPager();

          setSupportActionBar(toolbar);
          getSupportActionBar().setDisplayShowTitleEnabled(false);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.na_view);
        Menu menu = navigationView.getMenu();
        MenuItem itemLogout = menu.findItem(R.id.nav_logout);

        navigationView.setNavigationItemSelectedListener(this);
        // call viewPager


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_menu){
                    viewPager2.setCurrentItem(0);
                } else if (item.getItemId() == R.id.stock_menu) {
                    viewPager2.setCurrentItem(1);
                } else if (item.getItemId() == R.id.chart_column_menu) {
                    viewPager2.setCurrentItem(2);
                } else if (item.getItemId() == R.id.user_menu) {
                    viewPager2.setCurrentItem(3);
                }
                return true;
            }
        });

    }
    private void setupViewPager(){
        ViewPagerAdapterTest viewPagerAdapter = new ViewPagerAdapterTest(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    bottomNavigationView.getMenu().findItem(R.id.main_menu).setChecked(true);
                } else if (position == 1) {
                    bottomNavigationView.getMenu().findItem(R.id.stock_menu).setChecked(true);
                } else if (position == 2) {
                    bottomNavigationView.getMenu().findItem(R.id.chart_column_menu).setChecked(true);
                } else if (position == 3) {
                    bottomNavigationView.getMenu().findItem(R.id.user_menu).setChecked(true);
                } else {
                    bottomNavigationView.getMenu().findItem(R.id.main_menu).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.main_menu) {
            viewPager2.setCurrentItem(0);
        } else if (item.getItemId() == R.id.pulse_menu) {
            viewPager2.setCurrentItem(1);
        } else if (item.getItemId() == R.id.chart_column_menu) {
            viewPager2.setCurrentItem(2);
        } else if (item.getItemId() == R.id.user_menu) {
            viewPager2.setCurrentItem(3);
        } else {
            viewPager2.setCurrentItem(0);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
