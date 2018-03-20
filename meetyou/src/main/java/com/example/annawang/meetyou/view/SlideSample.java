package com.example.annawang.meetyou.view;

import android.graphics.Color;

import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.animations.TransSurface;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Pivot;
import su.levenetc.android.textsurface.contants.Side;
import su.levenetc.android.textsurface.contants.TYPE;

public class SlideSample {

    public static final int DURATION_SLIDE = 1250;
    private static String mColor = "#71CC4F";

    public static void play(TextSurface textSurface) {

        Text textA = TextBuilder.create("凝视你的双眼").setColor(Color.parseColor(mColor)).setPadding(10, 10, 10, 10).build();
        Text textB = TextBuilder.create("我看到了天堂").setColor(Color.parseColor(mColor)).setPadding(10, 10, 10, 10).setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textA).build();
        Text textC = TextBuilder.create("星星在空中闪耀").setColor(Color.parseColor(mColor)).setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textB).build();
        Text textD = TextBuilder.create("你躲在月亮后面").setColor(Color.parseColor(mColor)).setPadding(10, 10, 10, 10).setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textC).build();

        Text textE = TextBuilder.create("在某个角落偷看").setColor(Color.parseColor(mColor)).setPadding(10, 10, 10, 10).setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textD).build();
        Text textF = TextBuilder.create("我找了很久").setColor(Color.parseColor(mColor)).setPadding(10, 10, 10, 10).setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textE).build();
        Text textG = TextBuilder.create("找到你").setColor(Color.parseColor(mColor)).setPadding(10, 10, 10, 10).setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textF).build();
        Text textH = TextBuilder.create("哦").setColor(Color.parseColor(mColor)).setPadding(10, 10, 10, 10).setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textG).build();

        textSurface.play(TYPE.SEQUENTIAL
                , Slide.showFrom(Side.TOP, textA, DURATION_SLIDE)
                , new TransSurface(DURATION_SLIDE, textA, Pivot.CENTER)
                , Slide.showFrom(Side.TOP, textB, DURATION_SLIDE)
                , new TransSurface(DURATION_SLIDE, textB, Pivot.CENTER)
                , Slide.showFrom(Side.TOP, textC, DURATION_SLIDE)
                , new TransSurface(DURATION_SLIDE, textC, Pivot.CENTER)
                , Slide.showFrom(Side.BOTTOM, textD, DURATION_SLIDE)
                , new TransSurface(DURATION_SLIDE, textD, Pivot.CENTER)
                , Slide.showFrom(Side.TOP, textE, DURATION_SLIDE)
                , Slide.showFrom(Side.TOP, textF, DURATION_SLIDE)
                , Slide.showFrom(Side.TOP, textG, DURATION_SLIDE)
                , Slide.showFrom(Side.BOTTOM, textH, DURATION_SLIDE)
                , new TransSurface(DURATION_SLIDE * 3, textE, Pivot.CENTER));
    }
}
