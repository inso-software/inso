package com.inso.watch.phonelib;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.inso.watch.commonlib.utils.L;

import java.util.Date;

import static com.inso.watch.phonelib.PhoneCallReceiver.mListener;


public class PhoneCallService extends Service implements IHandleCallEvent {

    public static final String TAG = PhoneCallService.class.getSimpleName();
    public static final String ACTION_REGISTER_LISTENER = "action_register_listener";

    @Override
    public void onCreate() {
        super.onCreate();
        L.d(TAG,"PhoneCallService onCreate");
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        L.d("onStartCommand action: " + intent.getAction() + " flags: " + flags + " startId: " + startId);
//        String action = intent.getAction();
//        if (null != action && action.equals(ACTION_REGISTER_LISTENER)) {
//            registerPhoneStateListener();
//        }
//        return super.onStartCommand(intent, flags, startId);
//    }

    protected void registerPhoneStateListener() {
        TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (mListener == null) {
            mListener = new PhoneCallStartEndDetector();
        }
        mListener.setCallEvent(this);
        if (telephony != null) {
            telephony.listen(mListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        L.d(TAG,"onBind action: " + intent.getAction());
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.d(TAG,"onUnbind action: " + intent.getAction());
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        L.d(TAG,"onRebind action: " + intent.getAction());
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        L.d(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onIncomingCallStarted(String number, Date start) {
        L.d(TAG,"onIncomingCallStarted");
    }

    @Override
    public void onOutgoingCallStarted(String number, Date start) {
        L.d(TAG,"onOutgoingCallStarted");
    }

    @Override
    public void onIncomingCallEnded(String number, Date start, Date end) {
        L.d(TAG,"onIncomingCallEnded");
    }

    @Override
    public void onOutgoingCallEnded(String number, Date start, Date end) {
        L.d(TAG,"onOutgoingCallEnded");
    }

    @Override
    public void onMissedCall(String number, Date start) {
        L.d(TAG,"onMissedCall");
    }
}
