package com.example.annawang.meetyou.view;

import android.content.Context;
import android.util.SparseArray;

import com.example.annawang.meetyou.view.render.DayNightLoadingRenderer;
import com.example.annawang.meetyou.view.render.ElectricFanLoadingRenderer;

import java.lang.reflect.Constructor;

public final class LoadingRendererFactory {
    private static final SparseArray<Class<? extends LoadingRenderer>> LOADING_RENDERERS = new SparseArray<>();

    static {
        LOADING_RENDERERS.put(0, DayNightLoadingRenderer.class);
        LOADING_RENDERERS.put(1, ElectricFanLoadingRenderer.class);
    }

    private LoadingRendererFactory() {
    }

    public static LoadingRenderer createLoadingRenderer(Context context, int loadingRendererId) throws Exception {
        Class<?> loadingRendererClazz = LOADING_RENDERERS.get(loadingRendererId);
        Constructor<?>[] constructors = loadingRendererClazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes != null
                    && parameterTypes.length == 1
                    && parameterTypes[0].equals(Context.class)) {
                constructor.setAccessible(true);
                return (LoadingRenderer) constructor.newInstance(context);
            }
        }

        throw new InstantiationException();
    }
}
