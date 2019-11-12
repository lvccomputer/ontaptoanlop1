package com.dev.lvc.math1.activities;

import android.os.Bundle;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.fragments.HistoryFragment;
import com.dev.lvc.math1.fragments.TestFragment;
import com.dev.lvc.math1.fragments.TestingFragment;
import com.dev.lvc.math1.fragments.PracticeFragment;

import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import android.view.MenuItem;

import com.dev.lvc.math1.models.History;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private ImageView imgNav;

    private RelativeLayout layoutLuyenTap;

    private RelativeLayout layoutKiemTra,layoutHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initID();
        initView();


    }

    private void initID() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        imgNav = findViewById(R.id.imgNav);
        layoutLuyenTap = findViewById(R.id.layoutLuyenTap);
        layoutKiemTra = findViewById(R.id.layoutKiemTra);
        layoutHistory = findViewById(R.id.layoutHistory);
    }

    private void initView() {

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        imgNav.setOnClickListener(this);

        layoutKiemTra.setOnClickListener(v -> showKiemTraFragment());
        layoutLuyenTap.setOnClickListener(v -> showLuyenTapFragment());
        layoutHistory.setOnClickListener(v -> showHistoryFragment());
    }

    protected boolean isExit = false;

    @Override
    public void onBackPressed() {
        int index = getSupportFragmentManager().getBackStackEntryCount();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (index > 0) {
                super.onBackPressed();
            } else {
                if (isExit) {
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Ấn thêm lần nữa để thoát!", Toast.LENGTH_LONG).show();
                    isExit = true;
                    new Handler().postDelayed(() -> isExit = false, 3000);
                }
            }
        }
    }

    private void showLuyenTapFragment() {
        if (getSupportFragmentManager().findFragmentByTag(PracticeFragment.class.getName()) == null) {
            PracticeFragment fragment = new PracticeFragment();
            addFragment(fragment, PracticeFragment.class.getName());
        }
    }

    private void showKiemTraFragment() {
        if (getSupportFragmentManager().findFragmentByTag(TestFragment.class.getName()) == null) {
            TestFragment fragment = new TestFragment();
            addFragment(fragment, TestFragment.class.getName());
        }
    }

    public void showBaiLamFragment() {
        if (getSupportFragmentManager().findFragmentByTag(TestingFragment.class.getName()) == null) {
            TestingFragment fragment = new TestingFragment();
            addFragment(fragment, TestingFragment.class.getName());
        }
    }
    private void showHistoryFragment() {
        if (getSupportFragmentManager().findFragmentByTag(HistoryFragment.class.getName()) == null) {
            HistoryFragment historyFragment = new HistoryFragment();
            addFragment(historyFragment, HistoryFragment.class.getName());
        }
    }
    private void addFragment(@NonNull Fragment fragment, @NonNull String fragmentTags) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(fragmentTags)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_en, R.anim.pop_ex)
                .add(R.id.drawer_layout, fragment, fragmentTags)
                .commitAllowingStateLoss();
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_share) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.imgNav:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.layoutLuyenTap:
                onBackPressed();
                break;
            case R.id.layoutKiemTra:
                onBackPressed();
                break;

        }
    }
}
