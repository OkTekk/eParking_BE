package be.laurent_fournier.eparkingbe;

import android.app.PendingIntent;
import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class main extends ActionBarActivity {
    private EditText idauto2 = null, idzone2 = null;
    private RadioGroup contact;
    private Button startButton = null, stopButton = null;
    private TextView timer1 = null;

    private String message = null;
    private String strContact = null;

    private final String noIdAuto =     "Veuillez entrer un numéro de plaque\nSans espaces ni traits d'union.";
    private final String noIdZone =     "Veuillez entrer le numéro de zone se situant sur le coté de la borne de parking la plus proche.";
    private final String contactZero =  "Veuillez cocher un des numéros de contact.";
    private final String enterValues =  "Veuillez entrer des valeurs !";
    private final String strConfirm =   "Message envoyé.\nVous allez recevoir un SMS de confirmation...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idauto2 = (EditText) findViewById(R.id.idauto2);
        idauto2.addTextChangedListener(textWatcher);

        idzone2 = (EditText) findViewById(R.id.idzone2);
        idzone2.addTextChangedListener(textWatcher);

        String strAuto = idauto2.getText().toString();
        String strZone = idzone2.getText().toString();
        message = strAuto + " " + strZone;
//        message = message.concat(String.valueOf(idauto2));
//        message = message.concat(" ");
//        message = message.concat(String.valueOf(idzone2));
//        message = message.concat(" ");

        contact = (RadioGroup) findViewById(R.id.contact);

        switch(contact.getCheckedRadioButtonId()) {
            case R.id.numero1:  strContact = "4411"; break;
            case R.id.numero2:  strContact = "4810"; break;
            case R.id.numero3:  strContact = "0495256023"; break;
            case R.id.numero4:  strContact = "0494493051"; break;
            default:            break;
        }
//        if(contact.getCheckedRadioButtonId() == R.id.numero1) {
//            strContact = "4411"; }
//        else if(contact.getCheckedRadioButtonId() == R.id.numero2) {
//            strContact = "4810"; }
//        else { strContact = "0495256023"; }

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(startWatcher);

        stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(stopWatcher);

        timer1 = (TextView) findViewById(R.id.timer1);
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            timer1.setText(R.string.timer2);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    private View.OnClickListener startWatcher = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            String aString = idauto2.getText().toString();
            String zString = idzone2.getText().toString();

            switch(contact.getCheckedRadioButtonId()) {
                case R.id.numero1: case R.id.numero2:
                case R.id.numero3: case R.id.numero4:

                    if(aString.length() == 0 && zString.length() == 0) {
                        Toast.makeText(main.this, enterValues, Toast.LENGTH_SHORT).show(); }
                    else if(aString.length() == 0) {
                        Toast.makeText(main.this, noIdAuto, Toast.LENGTH_SHORT).show(); }
                    else if(zString.length() == 0) {
                        Toast.makeText(main.this, noIdZone, Toast.LENGTH_SHORT).show(); }
                    else {
                        sendSms(strContact, message);
                        timer1.setText(strConfirm); } break;

                default:
                    Toast.makeText(main.this, contactZero, Toast.LENGTH_SHORT).show(); break;
            }
//            if(contact.getCheckedRadioButtonId() == R.id.numero1 || contact.getCheckedRadioButtonId() == R.id.numero2) {
//                if(aString.length() == 0 && zString.length() == 0) {
//                    Toast.makeText(main.this, enterValues, Toast.LENGTH_SHORT).show(); }
//
//                else if(aString.length() == 0) {
//                    Toast.makeText(main.this, noIdAuto, Toast.LENGTH_SHORT).show(); }
//
//                else if(zString.length() == 0) {
//                    Toast.makeText(main.this, noIdZone, Toast.LENGTH_SHORT).show(); }
//
//                else {
//                    sendSms(strContact, message);
//                    timer1.setText("Message envoyé.\nEn attente de confirmation..."); }}
//
//            else { Toast.makeText(main.this, contactZero, Toast.LENGTH_SHORT).show(); }
        }
    };

    private View.OnClickListener stopWatcher = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            sendSms(strContact, "Q");

//            idzone2.getText().clear();
//            idauto2.getText().clear();

//            timer1.setText(R.string.RESULTAT_BAS_ECRAN);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) { return true; }

        return super.onOptionsItemSelected(item);
    }

    public void sendSms(String contact, String message) {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, main.class), 0);
        SmsManager sms = SmsManager.getDefault();

        if(message == "Q") {
            sms.sendTextMessage(contact, null, "Q", pi, null); }

        else { sms.sendTextMessage(contact, null, message, pi, null);}
    }
}
