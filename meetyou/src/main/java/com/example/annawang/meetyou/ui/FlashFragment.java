package com.example.annawang.meetyou.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.annawang.meetyou.R;
import com.example.annawang.meetyou.util.LogUtil;
import com.example.annawang.meetyou.view.SlideSample;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import su.levenetc.android.textsurface.TextSurface;

@EFragment(R.layout.fragment_flash)
public class FlashFragment extends Fragment {

    @ViewById(R.id.toolbar)
    protected Toolbar mToolbar;

    @ViewById(R.id.text_surface)
    protected TextSurface mTextSurface;

    public static FlashFragment create() {
        return FlashFragment_.builder().build();
    }

    @AfterViews
    protected void afterView() {
        LogUtil.trace();
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        mTextSurface.postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
            }
        }, 1000);

    }

    private void show() {
        LogUtil.trace();
        if (null == mTextSurface) return;
        mTextSurface.reset();
        SlideSample.play(mTextSurface);
    }

    @Click(R.id.toolbar)
    protected void click() {
        LogUtil.trace();
        if (null != getClickToolbar()) {
            getClickToolbar().onClickToolbar(ContentFragment.create());
        }
    }
    
    private ClickToolbar mClickToolbar;

    public ClickToolbar getClickToolbar() {
        return mClickToolbar;
    }

    public void setClickToolbar(ClickToolbar clickToolbar) {
        mClickToolbar = clickToolbar;
    }

    public interface ClickToolbar {
        void onClickToolbar(Fragment fragment);
    }

}
