package be.laurent_fournier.eparkingbe_v3;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Created by Lowlow on 11/11/2014.
 * Version ${VERSION}.
 *
 * 1st serious project of mine ^_^
 * eParking BE tend to be useful to lazy Belgians
 * Its function is to ease the use of 'Parking's SMS payment'
 * in all the country.
 * There is already one -created by one of the two existing
 * plateforms- but it take 0.30€ by transaction
 * in addition to SMS price and time of actual parking !
 *
 * All comments: http://www.laurent-fournier.be/wp/forums/topic/dev-perso-eparking-be/
 **/

public class MainActivity extends ActionBarActivity {
    private Spinner idAuto2 = null, idZone2 = null;
    private RadioGroup contactGrp;
    private TextView infoShow = null, infoEtat = null;

    private DbQueries dbQueries;
    private User user; private Zone zone; private Auto auto;

//    private static final String general_prefs = "GeneralPrefs";


    /* *********************************************************************************
     * First, create/define the instance call and every usable objects from the layout *
     * *********************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* *********************************************
         * TO-DO : 1st launch preferences auto setting *
         * *********************************************
        if(WelcomeCheck.isFirst(MainActivity.this)) { WelcomeSend(true); }*/

        dbQueries = new DbQueries(this);
        dbQueries.open();
        ArrayAdapter<User> arrayUser = new ArrayAdapter<User>(this, android.R.layout.simple_spinner_item, dbQueries.getUsers());
        ArrayAdapter<Zone> arrayZone = new ArrayAdapter<Zone>(this, android.R.layout.simple_spinner_item, dbQueries.getZones());
        ArrayAdapter<Auto> arrayAuto = new ArrayAdapter<Auto>(this, android.R.layout.simple_spinner_item, dbQueries.getAutos());
        dbQueries.close();

        idAuto2 = (Spinner)findViewById(R.id.idAuto2);
        arrayAuto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idAuto2.setAdapter(arrayAuto);
        idAuto2.setOnItemSelectedListener(onAutoSelectedListener);

        idZone2 = (Spinner)findViewById(R.id.idZone2);
        arrayZone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idZone2.setAdapter(arrayZone);
        idZone2.setOnItemSelectedListener(onZoneSelectedListener);

        Button startButton = (Button)findViewById(R.id.parkStart);
        startButton.setOnClickListener(startWatcher);

        Button stopButton = (Button)findViewById(R.id.parkStop);
        stopButton.setOnClickListener(stopWatcher);

        infoShow = (TextView)findViewById(R.id.infoShow);
        infoEtat = (TextView)findViewById(R.id.infoEtat);
    }


    /* *********************************************************************************
     * Listen on texts.                                                                *
     * Actually i just use it to clean the fields                                      *
     * Soon (...) all listeners like this one will be placed                           *
     * in a dedicated class (ListenersMain.java)                                       *
     * *********************************************************************************/
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            infoShow.setText("");
            infoEtat.setText("");
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };


    /* *********************************************************************************
     * Listen on Start & Stop buttons's clicks                                         *
     * Later, they will be in ListenersMain.java                                       *
     * *********************************************************************************/
    private View.OnClickListener startWatcher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           SmsBuild(zone.getZoneCode() + " " + auto.getAutoLicense());
        }
    };

    private View.OnClickListener stopWatcher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SmsBuild("Q");
        }
    };

    /* *********************************************************************************
     * Listen on Spinner selections                                                    *
     * And set last selected into buffer table to recover them  at th next launch      *
     * *********************************************************************************/
    private AdapterView.OnItemSelectedListener onZoneSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            zone = (Zone)parentView.getItemAtPosition(position);

/*            dbQueries.open();
            dbQueries.
            dbQueries.close();*/
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {}
    };

    private AdapterView.OnItemSelectedListener onAutoSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            auto = (Auto)parentView.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {}
    };


    /* *********************************************************************************
     * TO-DO :                                                                         *
     * Extract to an independant class: SmsBuild.java                                  *
     * *********************************************************************************/
    public void SmsBuild(String message) {
        String strContact = null;
        if(zone.getZoneProvider() == 1) { strContact = "4810"; }
        else if(zone.getZoneProvider() == 2) { strContact = "4411"; }
        else { infoShow.setText(String.format(getString(R.string.badNum))); }

        SmsSend(strContact, message);
        infoShow.setText(String.format(getString(R.string.infoShow2), message, strContact));
    }


    /* *********************************************************************************
     * TO-DO :                                                                         *
     * Extract the methods to an independant class                                     *
     * *********************************************************************************/
    public void SmsSend(String contact, String message) {
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


    /* *********************************************************************************
     * TO-DO                                                                           *
     * *********************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu; this adds items to the action bar if it is present. */
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /* *********************************************************************************
     * TO-DO                                                                           *
     * *********************************************************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle action bar item clicks here. The action bar will
           automatically handle clicks on the Home/Up button, so long
           as you specify a parent activity in AndroidManifest.xml. */
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) { return true; }

        return super.onOptionsItemSelected(item);
    }
}
