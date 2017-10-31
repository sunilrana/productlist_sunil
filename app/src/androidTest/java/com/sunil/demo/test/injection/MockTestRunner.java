package com.sunil.demo.test.injection;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.sunil.demo.test.MockProductApplication;



public class MockTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MockProductApplication.class.getName(), context);
    }
}
