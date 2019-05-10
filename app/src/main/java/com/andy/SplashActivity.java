package com.andy;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-05-10 11:31
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-10 11:31
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class SplashActivity extends AppCompatActivity {

    private ImageView img;
    private ImageView ImgMark;


    ViewPropertyAnimation.Animator animator = view -> {
        view.setAlpha(0f);
        ObjectAnimator objAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        objAnimator.setDuration(2000);
        objAnimator.start();
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        initStatus();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        img = findViewById(R.id.img_id);
        ImgMark = findViewById(R.id.icon_mark);
        ImgMark.post(() -> {
            Glide.with(SplashActivity.this).load(R.drawable.splash)
                    .animate(animator).into(img);
            startAnimate();
        });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    private void initStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decoderView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decoderView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    private void startAnimate() {
        int imgHeight = ImgMark.getHeight() / 5;
        int height = getWindowManager().getDefaultDisplay().getHeight();
        int dy = (height - imgHeight) / 2;
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animatorTranslate = ObjectAnimator.ofFloat(ImgMark, "translationY", 0, dy);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(ImgMark, "ScaleX", 1f, 0.2f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(ImgMark, "ScaleY", 1f, 0.2f);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(ImgMark, "alpha", 1f, 0.5f);
        set.play(animatorTranslate)
                .with(animatorScaleX).with(animatorScaleY).with(animatorAlpha);
        set.setDuration(1200);
        set.setInterpolator(new AccelerateInterpolator());
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                ImgMark.postDelayed(() -> {
                    startActivity(new Intent(SplashActivity.this, AppActivity.class));
                    SplashActivity.this.finish();
                }, 3000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
