package com.ouman.luoliluoli;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HotFragment.OnFragmentInteractionListener, ArticleFragment.OnFragmentInteractionListener ,
SearchFragment.OnFragmentInteractionListener, AroundFragment.OnFragmentInteractionListener{

    FabSpeedDial mainFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_news_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hot");
        getSupportActionBar().setElevation(6);


        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.main_appbar_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(8);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HotFragment()).commit();
        }

        //get the fab in main
        FabSpeedDial mainFab = (FabSpeedDial) findViewById(R.id.fab_main);
        if (mainFab == null){
            System.out.println("If main fab is null, this will show.");
        }

        //实现drawer布局
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //设置drawer里面的圆形图片,这貌似是添加原型图片最简单的办法
                ImageView imageViewPortrait = (ImageView) findViewById(R.id.navImageView);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.portrait);
                RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                dr.setCircular(true);
                imageViewPortrait.setImageDrawable(dr);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
            }
            @Override
            public void onDrawerClosed(View drawerView) {
            }
            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //设置默认的选中nav item
        navigationView.setCheckedItem(R.id.nav_hot);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.app_bar_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_hot) {


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HotFragment()).commit();
        } else if (id == R.id.nav_tech) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ArticleFragment()).commit();
        } else if (id == R.id.nav_find) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SearchFragment()).commit();
        } else if (id == R.id.nav_around) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AroundFragment()).commit();

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            MainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //在每次关闭drawer时都使得menu失效，目的是方便后面修改menuitem
        invalidateOptionsMenu();
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    //实现fragment监听方法
    public void onFragmentInteraction(Uri uri){

    }

    //implement a method let other fragment get fab in this activity
    public FabSpeedDial getMainFab(){
        System.out.println("Return main fab.");
        return mainFab;
    }

    public void showFabMain(){
        mainFab.show();
    }
    public void hideFabMain(){
        mainFab.hide();
    }
}
