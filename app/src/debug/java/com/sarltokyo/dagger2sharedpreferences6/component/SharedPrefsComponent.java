package com.sarltokyo.dagger2sharedpreferences6.component;

import com.sarltokyo.dagger2sharedpreferences6.InjectedBaseActivityTest;
import com.sarltokyo.dagger2sharedpreferences6.app.MyFragment;
import com.sarltokyo.dagger2sharedpreferences6.module.DebugSharedPrefsModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by osabe on 15/08/22.
 */
@Singleton
@Component(modules = {DebugSharedPrefsModule.class})
public interface SharedPrefsComponent {
    void inject(MyFragment fragment);
    void inject(InjectedBaseActivityTest test);

    public final static class Initializer {
        public static SharedPrefsComponent init(boolean mockMode) {
            return DaggerSharedPrefsComponent.builder()
                    .debugSharedPrefsModule(new DebugSharedPrefsModule(mockMode))
                    .build();
        }
    }
}
