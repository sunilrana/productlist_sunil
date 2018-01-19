package com.sunil.demo.test.repository;

import android.content.Context;

import com.sunil.demo.test.utils.Utils;

/**
 * Created by Sunil Rana on 11/10/2017.
 */

public class MockData {

    String mockData;

    public MockData(Context context) {
        this.mockData = Utils.loadJSONFromAsset(context);
    }
}
