package com.sunil.demo.test;


import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;
import com.sunil.demo.test.injection.ApplicationComponent;
import com.sunil.demo.test.injection.ApplicationModule;
import com.sunil.demo.test.injection.DaggerApplicationComponent;

import timber.log.Timber;

public class AppController extends Application {

    private final ApplicationComponent applicationComponent = createApplicationComponent();

    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_request);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());//TODO Install a Crashlytics tree in production
        }
    }

    protected ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
