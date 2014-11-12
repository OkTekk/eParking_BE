package be.laurent_fournier.eparkingbe_v3;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Lowlow on 11/11/2014.
 * Version ${VERSION}.
 *
 * 1st serious project of mine ^_^
 * eParking BE tend to be useful to lazy Belgian people
 * It's function is to ease the use of 'SMS paied parking'
 * in all the country.
 * There is already one -maked by one of the two existing
 * plateforms- but it take 0.30€ by transaction !
 *
 * All comments: http://www.laurent-fournier.be/wp/forums/topic/dev-perso-eparking-be/
 */

public class MainActivity extends ActionBarActivity {
    private EditText idAuto2 = null, idZone2 = null;
    private RadioGroup contactGrp;
    private TextView infoShow = null, infoEtat = null;

    private SmsBuild buildingRequest = null;

    /***********************************************************************************
     * First, create/define the instance call and every usable objects from the layout *
     ***********************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idAuto2 = (EditText)findViewById(R.id.idAuto2);
        idAuto2.addTextChangedListener(textWatcher);

        idZone2 = (EditText)findViewById(R.id.idZone2);
        idZone2.addTextChangedListener(textWatcher);

        contactGrp = (RadioGroup)findViewById(R.id.contactNums);

        Button startButton = (Button)findViewById(R.id.parkStart);
        startButton.setOnClickListener(startWatcher);

        Button stopButton = (Button)findViewById(R.id.parkStop);
        stopButton.setOnClickListener(stopWatcher);

        infoShow = (TextView)findViewById(R.id.infoShow);
        infoEtat = (TextView)findViewById(R.id.infoEtat);
    }

    /***********************************************************
     * Listen on texts.                                        *
     * Actually i just use it for cleaning writing fields      *
     * Soon (...) all listeners like this one will be placed   *
     * in a dedicated class (ListenersMain.java)               *
     ***********************************************************/
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            infoShow.setText("");
            infoEtat.setText(""); }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    /***************************************************************
     * Listen on Start & Stop buttons's clicks                     *
     * This one and the Stop one will be in ListenersMain.java     *
     ***************************************************************
     * Start: Make verifs to be sure every needed field is filled  *
     *        if so, concatenate values required for buildSms()    *
     * Stop: Actually just do the minimum xD                       *
     ***************************************************************/
    private View.OnClickListener startWatcher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String strAuto = idAuto2.getText().toString();
            String strZone = idZone2.getText().toString();

            if(contactGrp.getCheckedRadioButtonId() == R.id.contactLabel1 || contactGrp.getCheckedRadioButtonId() == R.id.contactLabel2) {

                if(strAuto.length() == 0 && strZone.length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.noValues, Toast.LENGTH_SHORT).show(); }

                else if(strAuto.length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.noIdAuto, Toast.LENGTH_SHORT).show(); }

                else if(strZone.length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.noIdZone, Toast.LENGTH_SHORT).show(); }

                else {
//                    buildSms(strZone + " " + strAuto); }
                    buildingRequest = new SmsBuild(strZone + " " + strAuto); }}

            else { Toast.makeText(MainActivity.this, R.string.noContact, Toast.LENGTH_SHORT).show(); }
        }
    };

    /* See above, startWatcher, for this comment */
    private View.OnClickListener stopWatcher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            buildSms("Q");
            buildingRequest = new SmsBuild("Q");
        }
    };

    /****************************************************
     * Extracted to an independant class: SmsBuild.java *
     ****************************************************
    public void buildSms(String message) {
        String strContact = null;

        switch (contactGrp.getCheckedRadioButtonId()) {
            case R.id.contactLabel1:    strContact = getString(R.string.contactNum1); break;
            case R.id.contactLabel2:    strContact = getString(R.string.contactNum2); break;
            case R.id.contactLabel3:    Toast.makeText(MainActivity.this, R.string.noContact, Toast.LENGTH_SHORT).show(); break;   // To-do
            default:                    Toast.makeText(MainActivity.this, R.string.badNum, Toast.LENGTH_LONG).show(); break;
        }
        sendSms(strContact, message);
        infoShow.setText(String.format(getString(R.string.infoShow2), message, strContact));
    }
     */

    /****************************************************
     * Extracted to an independant class: SmsSend.java  *
     ****************************************************
    public void sendSms(String contact, String message) {
        PendingIntent sendPi = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0);
        PendingIntent receivePi = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);

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
     */

    /* To-do */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu; this adds items to the action bar if it is present. */
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /* To-do */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle action bar item clicks here. The action bar will
           automatically handle clicks on the Home/Up button, so long
           as you specify a parent activity in AndroidManifest.xml. */
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
