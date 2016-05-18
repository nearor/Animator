package com.nearor.myapplicationview;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.GridView;

public class LayoutAnimationsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private ViewGroup viewGroup ;
    private GridLayout mGridLayout;
    private CheckBox mAppear,mChangeAppear,mDisAppear,mChangeDisAppear;
    private LayoutTransition layoutTransition;
    private int mVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animations);

        viewGroup = (ViewGroup) findViewById(R.id.id_container);

        mAppear = (CheckBox) findViewById(R.id.id_appear);
        mChangeAppear = (CheckBox) findViewById(R.id.id_change_appear);
        mDisAppear = (CheckBox) findViewById(R.id.id_disappear);
        mChangeDisAppear = (CheckBox) findViewById(R.id.id_change_disappear);

        mAppear.setOnCheckedChangeListener(this);
        mChangeDisAppear.setOnCheckedChangeListener(this);
        mDisAppear.setOnCheckedChangeListener(this);
        mChangeAppear.setOnCheckedChangeListener(this);

        //创建一个gridLayout
        mGridLayout = new GridLayout(this);
        //设置每列五个按钮
        mGridLayout.setColumnCount(5);
        //添加到布局中
        viewGroup.addView(mGridLayout);
        //默认动画全部开启
        layoutTransition = new LayoutTransition();
        mGridLayout.setLayoutTransition(layoutTransition);


    }


    public void addBtn(View view)
    {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        mGridLayout.addView(button,Math.min(1,mGridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGridLayout.removeView(button);
            }
        });
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(
                LayoutTransition.APPEARING,
                mAppear.isChecked() ? layoutTransition.getAnimator(LayoutTransition.APPEARING) : null
        );

        //当然了动画支持自定义，还支持设置时间，比如我们修改下，添加的动画为:
//        layoutTransition.setAnimator(
//                LayoutTransition.APPEARING,
//                mAppear.isChecked() ? ObjectAnimator.ofFloat(this,"scaleX",0,1) : null
//        );





        layoutTransition.setAnimator(
                LayoutTransition.CHANGE_APPEARING,
                mChangeAppear.isChecked() ? layoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING) : null
        );

        layoutTransition.setAnimator(
                LayoutTransition.CHANGE_DISAPPEARING,
                mChangeDisAppear.isChecked() ? layoutTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING) : null
        );

        layoutTransition.setAnimator(
                LayoutTransition.DISAPPEARING,
                mDisAppear.isChecked() ? layoutTransition.getAnimator(LayoutTransition.DISAPPEARING) : null
        );

        mGridLayout.setLayoutTransition(layoutTransition);

    }
}
