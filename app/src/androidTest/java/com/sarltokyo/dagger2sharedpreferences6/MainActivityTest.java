package com.sarltokyo.dagger2sharedpreferences6;

import android.app.Instrumentation;
import com.sarltokyo.dagger2sharedpreferences6.app.MainActivity;
import com.sarltokyo.dagger2sharedpreferences6.app.MyFragment;
import com.sarltokyo.dagger2sharedpreferences6.app.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.is;


/**
 * Created by osabe on 15/08/22.
 */
public class MainActivityTest extends InjectedBaseActivityTest {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testPrefValueIsDisplayed() throws Exception {

        getSharedPrefs().edit().putString(MyFragment.KEY_TEXT, "foo").commit();

        getActivity();

        Instrumentation.ActivityMonitor monitor
                = getInstrumentation().addMonitor(MainActivity.class.getName(),
                null, true);

        onView(withId(R.id.prefEt)).check(matches(withText("foo")));

        getInstrumentation().removeMonitor(monitor);
    }

    /**
     * todo
     * 一度、ボタン押下でプリファレンスに書き込んで、Activity#finishで画面を閉じ、
     * 再度、getActivityしても、画面が開かず、Activityでプリファレンスの値の書き込み、
     * 更新がテストできない。
     */
    public void testWritePref() throws Exception {

        getActivity();

        Instrumentation.ActivityMonitor monitor
                = getInstrumentation().addMonitor(MainActivity.class.getName(),
                null, true);

        onView(withId(R.id.prefEt)).perform(typeText("hoge"));
        onView(withId(R.id.btn)).perform(click());

        String actual = getSharedPrefs().getString(MyFragment.KEY_TEXT, "");
        assertThat(actual, is("hoge"));

        getInstrumentation().removeMonitor(monitor);
    }

    public void testUpdatePref() throws Exception {

        getSharedPrefs().edit().putString(MyFragment.KEY_TEXT, "foo").commit();

        getActivity();

        Instrumentation.ActivityMonitor monitor
                = getInstrumentation().addMonitor(MainActivity.class.getName(),
                null, true);

        onView(withId(R.id.prefEt)).check(matches(withText("foo")));
        onView(withId(R.id.prefEt)).perform(clearText()).perform(typeText("boo"));
        onView(withId(R.id.btn)).perform(click());

        String actual = getSharedPrefs().getString(MyFragment.KEY_TEXT, "");
        assertThat(actual, is("boo"));

        getInstrumentation().removeMonitor(monitor);
    }
}
