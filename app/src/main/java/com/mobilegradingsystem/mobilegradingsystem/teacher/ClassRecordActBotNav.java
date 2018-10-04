package com.mobilegradingsystem.mobilegradingsystem.teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mobilegradingsystem.mobilegradingsystem.R;
import com.mobilegradingsystem.mobilegradingsystem.teacher.fragment.ClassProfileBotBNav.AnnouncementTeacherFragement;
import com.mobilegradingsystem.mobilegradingsystem.teacher.fragment.ClassProfileBotBNav.DashboardClassTeacherFragement;
import com.mobilegradingsystem.mobilegradingsystem.teacher.fragment.ClassProfileBotBNav.ViewStudentsTeacherFragement;
import com.mobilegradingsystem.mobilegradingsystem.teacher.fragmentClassRecord.ClassAttendanceFragement;
import com.mobilegradingsystem.mobilegradingsystem.teacher.fragmentClassRecord.ClassGradeFragement;
import com.mobilegradingsystem.mobilegradingsystem.teacher.fragmentClassRecord.ClassParticipationFragement;
import com.mobilegradingsystem.mobilegradingsystem.teacher.fragmentClassRecord.ClassProjectsFragement;
import com.mobilegradingsystem.mobilegradingsystem.teacher.fragmentClassRecord.ClassQuizzesLongTestFragement;
import com.mobilegradingsystem.mobilegradingsystem.viewsAdapter.ViewPagerAdapter;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class ClassRecordActBotNav extends AppCompatActivity {

    SlidingRootNav slidingRootNav;
    ClassParticipationFragement classParticipationFragement;
    ClassProjectsFragement classProjectsFragement;
    ClassQuizzesLongTestFragement classQuizzesLongTestFragement;
    ClassGradeFragement classGradeFragement;
    ClassAttendanceFragement classAttendanceFragement;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_record_act_bot_nav);
        viewPager = (ViewPager) findViewById(R.id.viewpager) ;
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withDragDistance(200)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withRootViewScale(1f)
                .withRootViewYTranslation(4)
                .withMenuLayout(R.layout.root_nav_class_record)
                .withSavedState(savedInstanceState)
                .withContentClickableWhenMenuOpened(true)
                .inject();

        findViewById(R.id.toggleMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidingRootNav.isMenuOpened()){
                    slidingRootNav.closeMenu();
                }else {
                    slidingRootNav.openMenu();
                }
            }
        });
        setupViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        classGradeFragement = new ClassGradeFragement();
        classProjectsFragement = new ClassProjectsFragement();
        classQuizzesLongTestFragement = new ClassQuizzesLongTestFragement();
        classParticipationFragement = new ClassParticipationFragement();
        classAttendanceFragement = new ClassAttendanceFragement();

        adapter.addFragment(classAttendanceFragement);
        adapter.addFragment(classParticipationFragement);
        adapter.addFragment(classProjectsFragement);
        adapter.addFragment(classQuizzesLongTestFragement);
        adapter.addFragment(classGradeFragement);

        viewPager.setAdapter(adapter);
    }

}
