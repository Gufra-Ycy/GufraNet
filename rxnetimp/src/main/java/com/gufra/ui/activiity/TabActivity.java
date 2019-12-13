package com.gufra.ui.activiity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.gufra.net.rxnetimp.R;
import com.gufra.ui.fragment.JokeFragment;
import com.gufra.ui.fragment.OneFragment;
import com.gufra.ui.fragment.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {
    private TabLayout mTab;
    private ViewPager mViewPager;
    private List<String> mTitles;
    private List<Fragment>mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initData();
        initView();

    }

    private void initView() {
        mTab = (TabLayout)findViewById(R.id.tab_01);
        mViewPager = (ViewPager)findViewById(R.id.viewpager_01);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragments.size());
        //设置适配

        //已被弃用
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }
        });
        mTab.setupWithViewPager(mViewPager);
    }

    private void initData(){
        mTitles = new ArrayList<String>();
        mTitles.add("One");
        mTitles.add("Joke");
        mTitles.add("Settings");

        mFragments = new ArrayList<Fragment>();
        mFragments.add(new OneFragment());
        mFragments.add(new JokeFragment());
        mFragments.add(new SettingsFragment());
    }
}
