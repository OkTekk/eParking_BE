package be.laurent_fournier.eparkingbe_v2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lowlow on 20/10/2014.
 */

public class main_activity extends ActionBarActivity {
    private EditText idAuto2 = null, idZone2 = null;
    private RadioGroup contactGrp;
    private Button startButton, stopButton;
    private TextView infoShow = null;

    private String strContact = null, strMessage = null, strAuto = null, strZone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        idAuto2 = (EditText)findViewById(R.id.idAuto2);
        idAuto2.addTextChangedListener(textWatcher);

        idZone2 = (EditText)findViewById(R.id.idZone2);
        idZone2.addTextChangedListener(textWatcher);

        contactGrp = (RadioGroup)findViewById(R.id.contactNums);

        startButton = (Button)findViewById(R.id.parkStart);
        startButton.setOnClickListener(startWatcher);

        stopButton = (Button)findViewById(R.id.parkStop);
        stopButton.setOnClickListener(stopWatcher);

        infoShow = (TextView)findViewById(R.id.infoShow);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            infoShow.setText("");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private OnClickListener startWatcher = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private OnClickListener stopWatcher = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    public void sendSms(String contact, String message) {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, main_activity.class), 0);
        SmsManager sms = SmsManager.getDefault();

        if(message == "Q") {
            sms.sendTextMessage(contact, null, "Q", pi, null); }

        else {sms.sendTextMessage(contact, null, message, pi, null); }
    }
}
