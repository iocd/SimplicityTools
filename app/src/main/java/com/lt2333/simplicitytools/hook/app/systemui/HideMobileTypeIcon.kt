package com.lt2333.simplicitytools.hook.app.systemui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.lt2333.simplicitytools.util.XSPUtils
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class HideMobileTypeIcon : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        val classIfExists = XposedHelpers.findClassIfExists(
            "com.android.systemui.statusbar.StatusBarMobileView",
            lpparam.classLoader
        )
        XposedHelpers.findAndHookMethod(
            classIfExists,
            "initViewState",
            "com.android.systemui.statusbar.phone.StatusBarSignalPolicy\$MobileIconState",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    if (XSPUtils.getBoolean("hide_mobile_type_icon", false)) {
                        (XposedHelpers.getObjectField(
                            param.thisObject,
                            "mMobileType"
                        ) as TextView).visibility = View.INVISIBLE

                        (XposedHelpers.getObjectField(
                            param.thisObject,
                            "mMobileTypeImage"
                        ) as ImageView).visibility = View.INVISIBLE

                        (XposedHelpers.getObjectField(
                            param.thisObject,
                            "mMobileTypeSingle"
                        ) as TextView).visibility = View.INVISIBLE
                    }
                }
            })

        XposedHelpers.findAndHookMethod(
            classIfExists,
            "updateState",
            "com.android.systemui.statusbar.phone.StatusBarSignalPolicy\$MobileIconState",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    if (XSPUtils.getBoolean("hide_mobile_type_icon", false)) {
                        (XposedHelpers.getObjectField(
                            param.thisObject,
                            "mMobileType"
                        ) as TextView).visibility = View.INVISIBLE

                        (XposedHelpers.getObjectField(
                            param.thisObject,
                            "mMobileTypeImage"
                        ) as ImageView).visibility = View.INVISIBLE

                        (XposedHelpers.getObjectField(
                            param.thisObject,
                            "mMobileTypeSingle"
                        ) as TextView).visibility = View.INVISIBLE
                    }
                }
            })


    }
}