package com.example.demotest.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.demotest.ChartColumnFragment;
import com.example.demotest.HomeFragment;
import com.example.demotest.StockFragment;
import com.example.demotest.UserFragment;

public class ViewPagerAdapterTest extends FragmentStateAdapter {

    public ViewPagerAdapterTest(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new HomeFragment();
        } else if (position == 1) {
            return new StockFragment();
        } else if (position == 2) {
            return new ChartColumnFragment();
        } else if (position == 3) {
            return new UserFragment();
        } else {
            return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
