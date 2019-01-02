package com.inso.watch.phonelib;

import java.util.Date;

/**
 * Comment:
 * Author: ftc300
 * Date: 2018/11/2
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */

public interface IHandleCallEvent {
    //Derived classes should override these to respond to specific events of interest
    void onIncomingCallStarted(String number, Date start);

    void onOutgoingCallStarted(String number, Date start);

    void onIncomingCallEnded(String number, Date start, Date end);

    void onOutgoingCallEnded(String number, Date start, Date end);

    void onMissedCall(String number, Date start);

}
