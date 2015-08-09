package com.l904;

import android.content.Intent;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.test.ActivityUnitTestCase;

/**
 * Created by santosh on 8/9/15.
 */
public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ContextThemeWrapper context = new ContextThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        setActivityContext(context);
        startActivity(new Intent(getInstrumentation().getTargetContext(),MainActivity.class),null,null);
    }

    public void testStrt(){
        int i = getStartedActivityIntent().getIntExtra("type",0);
        if(i==0) fail("wrong param");    }
}
