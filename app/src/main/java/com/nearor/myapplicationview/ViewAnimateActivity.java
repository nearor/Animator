package com.nearor.myapplicationview;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class ViewAnimateActivity extends AppCompatActivity {

    protected static final String TAG = "ViewAnimateActivity";

    private ImageView mBlueBall;
    private float mScreenHeight;//屏幕的高度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animate);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);//获取屏幕分辨率
        mScreenHeight = outMetrics.heightPixels;

        mBlueBall = (ImageView) findViewById(R.id.id_ball);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void viewAnim(View view){
        // need API12
        mBlueBall.animate()
                .alpha(0)
                .y(mScreenHeight / 2).setDuration(2000)
                //need API12
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                })
                //need API16
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mBlueBall.setY(0);
                                mBlueBall.setAlpha(1.0f);
                            }
                        });
                    }
                })
                .start();


    }

    public void propertyValuesHolder(View view){

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha",1f,
                0f,1f);

        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y",0,
                mScreenHeight / 2,0);

        ObjectAnimator.ofPropertyValuesHolder(mBlueBall,pvhX,pvhY).setDuration(2000).start();
    }

}
