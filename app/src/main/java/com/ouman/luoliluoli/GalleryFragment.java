package com.ouman.luoliluoli;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouman.luoliluoli.galleryfragments.GalleryFragmentPagerAdapter;


public class GalleryFragment extends Fragment {


    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("美图");

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_tab_gallery);
        viewPager.setAdapter(new GalleryFragmentPagerAdapter(getFragmentManager(), view.getContext()));




        //find tab layout in article fragment
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_gallery);
        tabLayout.setupWithViewPager(viewPager);
        //set tab layout fill screen
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        return view;
    }

}
