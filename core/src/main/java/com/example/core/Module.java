package com.example.core;


import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.core.api.service.ApiInterface;

public interface Module {

    String getModuleName(Context context);

    Drawable getModuleIcon(Context context);

    void ApiModule(ApiInterface apiInterface);
}
