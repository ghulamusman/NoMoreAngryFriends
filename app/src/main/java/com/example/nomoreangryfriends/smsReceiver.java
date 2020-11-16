package com.example.nomoreangryfriends;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class smsReceiver extends BroadcastReceiver {

    private static final String TAG = smsReceiver.class.getSimpleName();
    public static final String pdu_type = "pdus";

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) { // Receiving and processing data from the text message received using a broadcast receiver.

        getText currentText = new getText();
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String phoneNumber = "";
        String receivedMessage = "";
        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get(pdu_type);

        msgs = new SmsMessage[pdus.length];

        for (int i = 0; i < msgs.length; i++) {
            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
            phoneNumber += msgs[i].getOriginatingAddress();
            receivedMessage += msgs[i].getMessageBody();
        }

        Log.d(TAG, "Received text from: " + phoneNumber);
        Toast.makeText(context, phoneNumber+" :"+receivedMessage, Toast.LENGTH_LONG).show();

        sendText(currentText.getMessageText(context),phoneNumber);  //  Get Reply from the getText class.
    }


    private void sendText(String messageContent, String phoneNumber){     // Method to send a specified text message to a specified number.

        System.out.println(messageContent + " " + phoneNumber);
        Log.d(TAG, "Sent text To: " + phoneNumber);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, messageContent, null, null);

    }
}
