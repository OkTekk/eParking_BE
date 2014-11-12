package be.laurent_fournier.eparkingbe_v3;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.widget.TextView;

/**
 * Created by Lowlow on 11/11/2014.
 * Version ${VERSION}.
 */
public class SmsSend extends ActionBarActivity {
    private TextView infoEtat = null;

    public SmsSend(String contact, String message) {
        PendingIntent sendPi = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0);
        PendingIntent receivePi = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);

        infoEtat = (TextView)findViewById(R.id.infoEtat);

        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case ActionBarActivity.RESULT_OK:               infoEtat.setText(R.string.smsSend_OK); break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:   infoEtat.setText(R.string.smsSend_ERROR_GENERIC_FAILURE); break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:        infoEtat.setText(R.string.smsSend_ERROR_NO_SERVICE); break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:          infoEtat.setText(R.string.smsSend_ERROR_NULL_PDU); break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:         infoEtat.setText(R.string.smsSend_ERROR_RADIO_OFF); break;
                }
            }
        }, new IntentFilter("SMS_SENT"));

        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case ActionBarActivity.RESULT_OK:               infoEtat.setText(R.string.smsReceive_OK); break;
                    case ActionBarActivity.RESULT_CANCELED:         infoEtat.setText(R.string.smsReceive_CANCELED); break;
                }
            }
        }, new IntentFilter("SMS_DELIVERED"));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(contact, null, message, sendPi, receivePi);
    }
}
