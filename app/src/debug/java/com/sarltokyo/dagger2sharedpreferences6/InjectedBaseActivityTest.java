package com.sarltokyo.dagger2sharedpreferences6;

import android.content.SharedPreferences;
import android.test.ActivityInstrumentationTestCase2;
import com.sarltokyo.dagger2sharedpreferences6.app.MainActivity;

import javax.inject.Inject;

/**
 * Created by osabe on 15/08/15.
 */
public class InjectedBaseActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    @Inject
    SharedPreferences mockSharedPrefs;

    public InjectedBaseActivityTest(Class activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        App app = (App)getInstrumentation().getTargetContext().getApplicationContext();
        app.setMockMode(true);
        app.component().inject(this);

        getSharedPrefs().edit().clear().commit();
    }

    @Override
    protected void tearDown() throws Exception {
//        getSharedPrefs().edit().clear().commit();

        App.getInstance().setMockMode(false);
    }

    protected SharedPreferences getSharedPrefs() {
        return mockSharedPrefs;
    }
}
