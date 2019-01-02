package com.inso;

import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Comment:
 * Author: ftc300
 * Date: 2019/1/2
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */

public class ChModule extends ReactContextBaseJavaModule {

    public ChModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    void switch2NewAct(){
        Intent intent=new Intent(getReactApplicationContext(),ChAct.class);
        intent.putExtra("data","SwitchToChenAct");
        getReactApplicationContext().startActivity(intent);
    }


    @Override
    public String getName() {
        return "ChModule";
    }
}
