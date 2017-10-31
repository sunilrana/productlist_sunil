package com.sunil.demo.test.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.sunil.demo.test.AppController;

/**
 * Created by Sunil Rana on 10/28/2017.
 */


public class ApplicationFactory extends ViewModelProvider.NewInstanceFactory {

    private AppController application;

    public ApplicationFactory(AppController application) {
        this.application = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        T t = super.create(modelClass);
        if (t instanceof ApplicationComponent.Injectable) {
            ((ApplicationComponent.Injectable) t).inject(application.getApplicationComponent());
        }
        return t;
    }
}
