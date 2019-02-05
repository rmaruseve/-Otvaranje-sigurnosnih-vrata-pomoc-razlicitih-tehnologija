package com.example.core;


import android.graphics.drawable.Drawable;

import com.example.core.api.service.ApiInterface;

public interface Module {

    String getModuleName();

    Drawable getModuleIcon();

    void ApiModule(ApiInterface apiInterface);
}
