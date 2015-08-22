package com.sarltokyo.dagger2sharedpreferences6;

import android.app.Application;
import com.sarltokyo.dagger2sharedpreferences6.component.SharedPrefsComponent;

/**
 * Created by osabe on 15/08/22.
 */
public class App extends Application {

    private static App sInstance;
    private SharedPrefsComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        component = SharedPrefsComponent.Initializer.init(false);
    }

    public static App getInstance() {
        return sInstance;
    }

    public SharedPrefsComponent component() {
        return component;
    }

    public void setMockMode(boolean useMock) {
        component = SharedPrefsComponent.Initializer.init(useMock);
    }
}
