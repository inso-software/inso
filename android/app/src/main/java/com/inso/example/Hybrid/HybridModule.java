package com.inso.example.Hybrid;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Comment:
 * Author: ftc300
 * Date: 2019/1/2
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */

public class HybridModule extends ReactContextBaseJavaModule {
    private PassData data = new PassData(2019,"ftc300");
    private static final String HYBRID_EVENT = "HybridEvent";
    public HybridModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "HybridModule";
    }

    @ReactMethod
    public void getIntentData(Callback callback){
        try{
            Activity currentActivity=getCurrentActivity();
            String result =currentActivity.getIntent().getStringExtra("data");
            if(TextUtils.isEmpty(result)){
                callback.invoke("getIntentData:No data");
            }else{
                callback.invoke(result);
            }
        }catch (Exception e){
            callback.invoke("error");
        }
    }

    @ReactMethod
    public void callNative(String fromRN){
        Toast.makeText(getReactApplicationContext(),"message from RN is " + fromRN,Toast.LENGTH_LONG).show();
    }

    @ReactMethod
    public void callNativeInvokeFuntion(Callback callback){
        data = new PassData(2019,"ftc300 callNativeInvokeFuntion" );
        callback.invoke(data);
    }


    @ReactMethod
    public void callNativeByPromise( Promise promise){
        data = new PassData(2019,"ftc300 callNativeByPromise" );
        promise.resolve(data);
    }

    @ReactMethod
    public void callNativeByEmitter(){
        WritableMap params = Arguments.createMap();
        params.putString("happy", "2019");
        params.putString("newyear", "ftc300");
        getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(HYBRID_EVENT, params);
    }

    //
    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String,Object> constants = new HashMap<>();
        constants.put(HYBRID_EVENT,HYBRID_EVENT);
        return constants;
    }
}
