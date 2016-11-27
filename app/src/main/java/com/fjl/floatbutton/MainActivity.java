package com.fjl.floatbutton;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fjl.floatbutton.widget.FabTagLayout;
import com.fjl.floatbutton.widget.FloatingActionButtonPlus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private List<String> mList;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.FabPlus)
    FloatingActionButtonPlus mFloatingActionButtonPlus;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        events();
    }
    private void events() {
        mFloatingActionButtonPlus.setOnItemClickListener(new FloatingActionButtonPlus.OnItemClickListener() {
            @Override
            public void onItemClick(FabTagLayout tagView, int position) {
                snaShow(mCoordinatorLayout,"Click btn" + position);
            }
        });
    }
    private void init() {
        mList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            mList.add("this is item" + i);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new MyAdapter(this, mList));
    }

    private void snaShow(View view,String text){
        Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show();
    }
}
