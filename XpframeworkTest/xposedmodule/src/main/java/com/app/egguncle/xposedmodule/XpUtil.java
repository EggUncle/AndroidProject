package com.app.egguncle.xposedmodule;

import android.view.View;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by egguncle on 16.11.28.
 */

public class XpUtil implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if(!lpparam.packageName.equals("com.app.egguncle.xpframeworktest"))
            return;

        XposedBridge.log("Loaded app:"+lpparam.packageName);

        XposedHelpers.findAndHookMethod("com.app.egguncle.xpframeworktest.MainActivity", lpparam.classLoader, "onClick", View.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                XposedBridge.log("HOOK SUCCESS");
            }
        });
    }
}
