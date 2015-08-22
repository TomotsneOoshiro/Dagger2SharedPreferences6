package com.sarltokyo.dagger2sharedpreferences6.module;

import android.content.Context;
import android.content.SharedPreferences;
import com.sarltokyo.dagger2sharedpreferences6.App;
import com.sarltokyo.dagger2sharedpreferences6.app.MyFragment;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by osabe on 15/08/22.
 */
@Module
public class DebugSharedPrefsModule {
    private final boolean mockMode;

    // テスト用
    public final static String CONFIG_MAME = "test_" + MyFragment.CONFIG_MAME;

    public DebugSharedPrefsModule(boolean provideMocks) {
        mockMode = provideMocks;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        if (mockMode) {
//            return mock(SharedPreferences.class);
            // mockではなく、テスト用プリファレンスファイルを使用
            return App.getInstance()
                    .getSharedPreferences(CONFIG_MAME, Context.MODE_PRIVATE);
        } else {
            return App.getInstance()
                    .getSharedPreferences(MyFragment.CONFIG_MAME, Context.MODE_PRIVATE);
        }
    }
}
