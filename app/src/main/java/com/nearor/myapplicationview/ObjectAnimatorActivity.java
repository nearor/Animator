package com.nearor.myapplicationview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ObjectAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //简单的方式
//    public void rotateyAnimRun(View v){
//        ObjectAnimator
//                .ofFloat(v, "rotationX", 0.0f, 360.0f)
//                .setDuration(500)
//                .start();
//
//    }


    //一个ObjectAnimator实例实现多种动画,并且没有setter，getter方法
    public void rotateyAnimRun(final View v){
        ObjectAnimator ani = ObjectAnimator
                .ofFloat(v,"zhk",1.0F,0.0F)
                .setDuration(500);
        ani.start();
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               float cval = (float) animation.getAnimatedValue();
                v.setScaleX(cval);
                v.setScaleY(cval);
                v.setAlpha(cval);
            }
        });

    }

}
