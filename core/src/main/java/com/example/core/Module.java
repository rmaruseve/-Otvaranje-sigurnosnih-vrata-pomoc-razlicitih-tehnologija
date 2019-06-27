package com.example.core;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.example.core.api.service.ApiInterface;

public interface Module {

    String getModuleName(Context context);

    Drawable getModuleIcon(Context context);

    Fragment getModuleFragment(Context context);

    void ApiModule(ApiInterface apiInterface);
}
