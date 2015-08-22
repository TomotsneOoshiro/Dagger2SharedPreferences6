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
public class SharedPrefsModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return App.getInstance()
                .getSharedPreferences(MyFragment.CONFIG_MAME, Context.MODE_PRIVATE);

    }
}
