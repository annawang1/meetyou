package com.example.annawang.meetyou.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;

import com.example.annawang.meetyou.R;
import com.example.annawang.meetyou.util.LogUtil;
import com.example.annawang.meetyou.util.PermissionUtil;
import com.example.annawang.meetyou.view.render.ElectricFanLoadingRenderer;
import com.plattysoft.leonids.ParticleSystem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.util.concurrent.TimeUnit;

@EActivity(R.layout.activity_meet)
public class MeetActivity extends AppCompatActivity {

    private ParticleSystem ps;
    private long mStartTimeMills;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.trace();
        mStartTimeMills = System.currentTimeMillis();
        if (!PermissionUtil.checkHavePermission(this)) {
            PermissionUtil.requestPermission(this);
        }
    }

    @AfterViews
    protected void afterView() {
        LogUtil.trace();
        FlashFragment flashFragment = FlashFragment.create();
        flashFragment.setClickToolbar(new FlashFragment.ClickToolbar() {
            @Override
            public void onClickToolbar(final Fragment fragment) {
                if (System.currentTimeMillis() - mStartTimeMills > ElectricFanLoadingRenderer.ANIMATION_DURATION) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pushFragment(fragment);
                        }
                    }, TimeUnit.SECONDS.toMillis(30));
                }

            }
        });
        pushFragment(flashFragment);
    }

    private void pushFragment(Fragment fragment) {
        LogUtil.trace();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flash_container, fragment)
                .commitAllowingStateLoss();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.trace();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ps = new ParticleSystem(this, 100, R.drawable.ic_green_leaf, 800);
                ps.setScaleRange(0.7f, 1.3f);
                ps.setSpeedRange(0.05f, 0.1f);
                ps.setRotationSpeedRange(90, 180);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.emit((int) event.getX(), (int) event.getY(), 40);
                break;
            case MotionEvent.ACTION_MOVE:
                ps.updateEmitPoint((int) event.getX(), (int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
                ps.stopEmitting();
                break;
        }
        return true;
    }

    @Click(R.id.toolbar)
    protected void click() {
        pushFragment(ContentFragment.create());
    }

}
