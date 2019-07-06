package com.example.gif_royale;

import android.net.Uri;
import android.os.Bundle;

import com.example.gif_royale.ui.main.Fragment_Create;
import com.example.gif_royale.ui.main.Fragment_Join;
import com.example.gif_royale.ui.main.Fragment_Rivals;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.gif_royale.ui.main.SectionsPagerAdapter;

public class MatchmakingActivity extends AppCompatActivity
        implements Fragment_Create.OnFragmentInteractionListener,
                   Fragment_Join.OnFragmentInteractionListener,
                   Fragment_Rivals.OnFragmentInteractionListener {
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof Fragment_Create) {
            Fragment_Create fragment_create = (Fragment_Create) fragment;
            fragment_create.setOnFragmentInteractionListener(this);
        }
        else if (fragment instanceof Fragment_Join) {
            Fragment_Join fragment_join = (Fragment_Join) fragment;
            fragment_join.setOnFragmentInteractionListener(this);
        }
        else if (fragment instanceof Fragment_Rivals) {
            Fragment_Rivals fragment_rivals = (Fragment_Rivals) fragment;
            fragment_rivals.setOnFragmentInteractionListener(this);
        }
        else
            return;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchmaking);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.TabLayout_Matchmaking);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
}