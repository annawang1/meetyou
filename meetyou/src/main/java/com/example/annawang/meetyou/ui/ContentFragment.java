package com.example.annawang.meetyou.ui;

import android.media.MediaPlayer;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.example.annawang.meetyou.R;
import com.example.annawang.meetyou.util.LogUtil;
import com.example.annawang.meetyou.widget.LoadingView;
import com.plattysoft.leonids.ParticleSystem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_content)
public class ContentFragment extends Fragment {

    @ViewById(R.id.day_night_view)
    protected LoadingView mDayNightView;

    @ViewById(R.id.content_layout)
    protected View mContentLayout;

    private List<ParticleSystem> mParticleSystems = new ArrayList<>();

    private MediaPlayer mMediaPlayer;

    private int[] mFlowersResList = {R.drawable.ic_filter_vintage_blue_100_18dp
            , R.drawable.ic_filter_vintage_red_100_18dp
            , R.drawable.ic_filter_vintage_cyan_100_18dp
            , R.drawable.ic_filter_vintage_green_100_18dp
            , R.drawable.ic_filter_vintage_lime_100_18dp
            , R.drawable.ic_filter_vintage_orange_100_18dp
            , R.drawable.ic_filter_vintage_purple_100_18dp
            , R.drawable.ic_filter_vintage_teal_100_18dp};

    public static ContentFragment create() {
        LogUtil.trace();
        return ContentFragment_.builder().build();
    }

    @AfterViews
    protected void afterView() {
        LogUtil.trace();
        startPlay();
    }

    private void startPlay() {
        try {
            mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.content_background_yellow);
            mMediaPlayer.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            LogUtil.error(e);
        }
    }

    @Touch(R.id.day_night_view)
    protected boolean touch(MotionEvent event) {
        LogUtil.trace();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mParticleSystems.clear();
                for (@DrawableRes int drawableRes : mFlowersResList) {
                    ParticleSystem particleSystem = generateParticleSystem(drawableRes);
                    mParticleSystems.add(particleSystem);
                    particleSystem.emit((int) event.getX(), (int) event.getY(), 70);
                }
                break;
            case MotionEvent.ACTION_UP:
                for (ParticleSystem particleSystem : mParticleSystems) {
                    particleSystem.stopEmitting();
                }
                break;
        }
        return true;
    }

    private ParticleSystem generateParticleSystem(@DrawableRes int drawableRes) {
        LogUtil.trace();
        ParticleSystem ps = new ParticleSystem(getActivity(), 100, drawableRes, 800);
        ps.setScaleRange(0.7f, 1.3f);
        ps.setSpeedRange(0.1f, 0.25f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new AccelerateInterpolator());
        return ps;
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlay();
    }

    private void stopPlay() {
        if (null != mMediaPlayer) {
            mMediaPlayer.release();
        }
    }
}
