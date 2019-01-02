package com.inso.watch.phonelib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.inso.watch.commonlib.utils.L;

import java.util.Date;

/**
 * Comment:
 *                      |  OFFHOOK   接听
 * 来电：IDLE -> RINGING
 *                      \  IDLE  挂断/未接
 * <p>
 * <p>
 * <p>
 * 去电：IDLE
 * Author: ftc300
 * Date: 2018/11/2
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */
public class PhoneCallReceiver extends BroadcastReceiver {

    //The receiver will be recreated whenever android feels like it.  We need a static variable to remember data between instantiations
    static PhoneCallStartEndDetector mListener;
    Context mContext;
    final String ACTION_OUTGOING = "android.intent.action.NEW_OUTGOING_CALL";
    final String PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
//        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//        L.d("PhoneStateReceiver onReceive state: " + state);
//        mContext = context;
//        if (mListener == null) {
//            mListener = new PhoneCallStartEndDetector();
//        }
        //We listen to two intents.  The new outgoing call only tells us of an outgoing call.  We use it to get the number.
//        if (action.equals(ACTION_OUTGOING)) {
//            mListener.setOutgoingNumber(intent.getExtras().getString(PHONE_NUMBER));
//            return;
//        }

//        //The other intent tells us the phone state changed.  Here we set a mListener to deal with it
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null!=telephony)
        telephony.listen(mListener, PhoneStateListener.LISTEN_CALL_STATE);
    }


}


