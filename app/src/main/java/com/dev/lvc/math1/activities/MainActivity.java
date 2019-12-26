package com.dev.lvc.math1.activities;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.fragments.HistoryFragment;
import com.dev.lvc.math1.fragments.ListOfPracticeFragment;
import com.dev.lvc.math1.fragments.PracticeFragment;
import com.dev.lvc.math1.fragments.ListOfTestsFragment;
import com.dev.lvc.math1.fragments.PracticeQuestionFragment;

import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import android.view.MenuItem;

import com.dev.lvc.math1.fragments.TestsQuestionFragment;
import com.dev.lvc.math1.models.History;
import com.dev.lvc.math1.utils.SqliteUtils;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private ImageView imgNav;

    private RelativeLayout layoutLuyenTap;

    private RelativeLayout layoutKiemTra, layoutHistory;

    public SQLiteDatabase historySqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        historySqlite = SqliteUtils.readDataBaseFromAssets("historyy.db", this);
        initID();
        initView();
        deleteSQLBefore10Days();
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

        layoutKiemTra.setOnClickListener(v -> showListOfTests());
        layoutLuyenTap.setOnClickListener(v -> showPracFragment("Các phần luyện tập"));
        layoutHistory.setOnClickListener(v -> showHistoryFragment());


    }

    private void deleteSQLBefore10Days() {
        ArrayList<History> histories = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Cursor cursor = historySqlite.rawQuery("select * from History ", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                History history = new History();
                history.setAddTime(cursor.getString(5));
                history.setId(cursor.getInt(0));
                histories.add(history);
            } while (cursor.moveToNext());
            cursor.close();
        }
        long addTime = 0;
        long endTime = 0;
        if (histories.size() > 0)
            for (int index = 0; index < histories.size(); index++) {
                try {
                    if (histories.get(index).getAddTime()!=null){
                        Date date = dateFormat.parse(histories.get(index).getAddTime());
                        Date dateNow = Calendar.getInstance().getTime();
                       endTime= getDaysDifference(date,dateNow);

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.e("cuong", "deleteSQLBefore10Days: " + endTime);
                if (endTime == 10) {
                    SQLiteStatement stmt = historySqlite.compileStatement("DELETE FROM History WHERE id == '" + histories.get(index).getId() + "'");
                    stmt.executeUpdateDelete();

                }
            }

    }
    public static int getDaysDifference(Date fromDate,Date toDate)
    {
        if(fromDate==null||toDate==null)
            return 0;

        return (int)( (toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
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


    public void showBaiLamFragment(String id) {
        if (getSupportFragmentManager().findFragmentByTag(TestsQuestionFragment.class.getName()) == null) {
            TestsQuestionFragment fragment = new TestsQuestionFragment();
            fragment.setId(id);
            addFragment(fragment, TestsQuestionFragment.class.getName());
        }
    }

    private void showHistoryFragment() {
        if (getSupportFragmentManager().findFragmentByTag(HistoryFragment.class.getName()) == null) {
            HistoryFragment historyFragment = new HistoryFragment();
            addFragment(historyFragment, HistoryFragment.class.getName());
        }
    }

    public void showListOfPracticeFragment(String id, String title, String folder, String nameImage) {
        if (getSupportFragmentManager().findFragmentByTag(ListOfPracticeFragment.class.getName()) == null) {
            ListOfPracticeFragment fragment = new ListOfPracticeFragment();
            fragment.setId(id);
            fragment.setTitleToolBar(title);
            fragment.setFolder(folder);
            fragment.setNameImage(nameImage);
            addFragment(fragment, ListOfPracticeFragment.class.getName());
        }

    }

    public void showPracFragment(String title) {
        if (getSupportFragmentManager().findFragmentByTag(PracticeFragment.class.getName()) == null) {
            PracticeFragment fragment = new PracticeFragment();
            fragment.setTitle(title);
            addFragment(fragment, PracticeFragment.class.getName());
        }
    }

    public void showListOfTests() {
        if (getSupportFragmentManager().findFragmentByTag(ListOfTestsFragment.class.getName()) == null) {
            ListOfTestsFragment fragment = new ListOfTestsFragment();
            addFragment(fragment, ListOfTestsFragment.class.getName());
        }
    }

    public void showPracticeQuestionFragment(String id, String practiceId) {
        if (getSupportFragmentManager().findFragmentByTag(PracticeQuestionFragment.class.getName()) == null) {
            PracticeQuestionFragment fragment = new PracticeQuestionFragment();
            fragment.setId(id);
            fragment.setPracticeId(practiceId);
            addFragment(fragment, PracticeQuestionFragment.class.getName());
        }
    }

    private void addFragment(@NonNull Fragment fragment, @NonNull String fragmentTags) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(fragmentTags)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.enter, R.anim.exit)
                .add(R.id.drawer_layout, fragment, fragmentTags)
                .commitAllowingStateLoss();
    }

    public static void hideKeyBoard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void setUserInterface(View view) {

        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyBoard(MainActivity.this);
                    return false;
                }
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setUserInterface(innerView);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_practice) {
            showPracFragment("Các phần luyện tập");
        } else if (id == R.id.nav_tests) {
            showListOfTests();
        } else if (id == R.id.nav_history) {
            showHistoryFragment();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        historySqlite.close();
    }
}
