package com.nearor.myapplicationview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class AnimatorSetActivity extends AppCompatActivity {

    private ImageView mBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);

        mBall = (ImageView) findViewById(R.id.id_ball);

    }


    public void togetherRun(View view){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBall,"scaleX",1.0f,2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBall,"scaleY",1.0f,2f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(anim1,anim2);//两个动画同时执行
        animatorSet.start();

    }

    public void playWithAfter(View view)
    {
        float cx = mBall.getX();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBall, "scaleX",
                1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBall, "scaleY",
                1.0f, 2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBall,
                "x",  cx ,  0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBall,
                "x", cx);

        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim4).after(anim3);
        animSet.setDuration(1000);
        animSet.start();
    }



}
