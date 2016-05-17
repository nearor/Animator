package com.nearor.myapplicationview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.PointF;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;


/**
 * ValueAnimator并没有在属性上做操作，你可能会问这样有啥好处？我岂不是还得手动设置？
 * 好处：不需要操作的对象的属性一定要有getter和setter方法，你可以自己根据当前动画的计算值，来操作任何属性
 */
public class valueAnimatorActivity extends AppCompatActivity {

    private static final String TAG = "ANIMATOR";

    private ImageView mBall;

    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
        mBall = (ImageView) findViewById(R.id.value_imag);
        WindowManager wm = this.getWindowManager();
        mHeight = wm.getDefaultDisplay().getHeight();
    }


    //垂直自由落体
    public void verticalRun(View view){
        ValueAnimator animator = ValueAnimator.ofFloat(0,mHeight - mBall.getHeight());
        animator.setTarget(mBall);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBall.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }


    //抛物线
    public void paowuxianRun(View view){
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0,0));
        valueAnimator.setInterpolator(new LinearInterpolator());  //时间差值,线性差值
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {   //TypeEvaluator  类型估值，主要用于设置动画操作属性的值。
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }

        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                mBall.setX(point.x);
                mBall.setY(point.y);

            }
        });

    }

    //淡出且删除
    public void cleanRun(View view){

        ObjectAnimator objectAnimator =  ObjectAnimator.ofFloat(mBall,"alpha",0.5f);

        //仅仅监听结束动作

        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup parent = (ViewGroup) mBall.getParent();
                if(parent != null){
                    parent.removeView(mBall);
                }
            }
        });


//        objectAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                Log.e(TAG,"AnimationStart");
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Log.e(TAG,"AnimationEnd");
//                ViewGroup parent = (ViewGroup) mBall.getParent();
//                if(parent!=null){
//                    parent.removeView(mBall);
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                Log.e(TAG,"AnimationCancel");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                Log.e(TAG,"AnimationRepeat");
//            }
//        });

        objectAnimator.start();

    }



}
