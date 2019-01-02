package com.inso.watch.phonelib;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.inso.watch.commonlib.utils.L;

import java.util.Date;

import static android.telephony.TelephonyManager.CALL_STATE_IDLE;
import static android.telephony.TelephonyManager.CALL_STATE_OFFHOOK;
import static android.telephony.TelephonyManager.CALL_STATE_RINGING;

/**
 * Comment:
 * Author: ftc300
 * Date: 2018/11/2
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */

//Deals with actual events
public class PhoneCallStartEndDetector extends PhoneStateListener {
    private int lastState = CALL_STATE_IDLE;
    private Date callStartTime;
    private boolean isIncoming;
    private String savedNumber;  //because the passed incoming is only valid in ringing
    private IHandleCallEvent callEvent;

    public void setCallEvent(IHandleCallEvent arg_callEvent) {
        callEvent = arg_callEvent;
    }

    //The outgoing number is only sent via a separate intent, so we need to store it out of band
    public void setOutgoingNumber(String number) {
        savedNumber = number;
    }

    //Incoming call-  goes from IDLE to RINGING when it rings, to OFFHOOK when it's answered, to IDLE when its hung up
    //Outgoing call-  goes from IDLE to OFFHOOK when it dials out, to IDLE when hung up
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        if (state == CALL_STATE_RINGING) {
            L.d("state = RINGING");
        } else if (state == CALL_STATE_OFFHOOK) {
            L.d("state = OFFHOOK");
        } else if (state == CALL_STATE_IDLE) {
            L.d("state = IDLE");
        }else {
            L.d("state = ??");
        }
        if (lastState == state) {
            //No change, debounce extras
            return;
        }
        switch (state) {
            case CALL_STATE_RINGING:
                isIncoming = true;
                callStartTime = new Date();
                savedNumber = incomingNumber;
                if (null != callEvent) {
                    callEvent.onIncomingCallStarted(incomingNumber, callStartTime);
                }
                break;
            case CALL_STATE_OFFHOOK:
                //Transition of ringing->offhook are pickups of incoming calls.  Nothing donw on them
                if (lastState != CALL_STATE_RINGING) {
                    isIncoming = false;
                    callStartTime = new Date();
                    if (null != callEvent) {
                        callEvent.onOutgoingCallStarted(savedNumber, callStartTime);
                    }
                }
                break;
            case CALL_STATE_IDLE:
                //Went to idle-  this is the end of a call.  What type depends on previous state(s)
                if (lastState == CALL_STATE_RINGING) {
                    //Ring but no pickup-  a miss
                    if (null != callEvent) {
                        callEvent.onMissedCall(savedNumber, callStartTime);
                    }
                } else if (isIncoming) {
                    if (null != callEvent) {
                        callEvent.onIncomingCallEnded(savedNumber, callStartTime, new Date());
                    }
                } else {
                    if (null != callEvent) {
                        callEvent.onOutgoingCallEnded(savedNumber, callStartTime, new Date());
                    }
                }
                break;
        }
        lastState = state;
    }

}
