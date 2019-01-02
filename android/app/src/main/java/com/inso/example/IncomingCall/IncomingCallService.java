package com.inso.example.IncomingCall;

import android.content.Intent;

import com.inso.watch.commonlib.utils.L;
import com.inso.watch.phonelib.PhoneCallService;

import java.util.Date;

/**
 * Comment:
 * Author: ftc300
 * Date: 2018/11/2
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */

public class IncomingCallService extends PhoneCallService {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.d(TAG, "onStartCommand action: " + intent.getAction() + " flags: " + flags + " startId: " + startId);
        registerPhoneStateListener();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onIncomingCallStarted(String number, Date start) {
        L.d("IncomingCallService onIncomingCallStarted starttime is " + start.getTime() + " number is " + number);
        super.onIncomingCallStarted(number, start);
    }

    @Override
    public void onIncomingCallEnded(String number, Date start, Date end) {
        L.d("IncomingCallService onIncomingCallEnded starttime is " + start.getTime() + " number is " + number + "endtime is " + end);
        super.onIncomingCallEnded(number, start, end);
    }

    @Override
    public void onMissedCall(String number, Date start) {
        L.d("IncomingCallService onIncomingCallStarted onMissedCall is " + start.getTime() + " number is " + number );
        super.onMissedCall(number, start);
    }
}
