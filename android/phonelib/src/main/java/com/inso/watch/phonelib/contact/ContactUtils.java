package com.inso.watch.phonelib.contact;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;

/**
 * Comment:
 * /data/data/com/android.providers.contacts/databases
 * 安卓 判断陌生号码
 * Author: ftc300
 * Date: 2018/11/2
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */

public class ContactUtils {
    public void queryContacts(Context context) {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (null != cursor && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String rawContactId = "";
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Log.v("contactID", id);
                Cursor rawContactCur = cr.query(ContactsContract.RawContacts.CONTENT_URI, null, ContactsContract.RawContacts._ID + "=?", new String[]{id}, null);
                if (null != rawContactCur && rawContactCur.moveToFirst()) {
                    //读取第一条记录的RawContacts._ID列的值
                    rawContactId = rawContactCur.getString(rawContactCur.getColumnIndex(ContactsContract.RawContacts._ID));
                }
                rawContactCur.close();
                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor phoneCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID + "=?",
                            new String[]{rawContactId}, null);
                    phoneCur.close();
                }
            }
            cursor.close();
        }
    }

}
