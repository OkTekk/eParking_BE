package be.laurent_fournier.eparkingbe_v3;

import android.support.v7.app.ActionBarActivity;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lowlow on 11/11/2014.
 * Version ${VERSION}.
 */

public class SmsBuild extends ActionBarActivity {
    private RadioGroup contactGrp;
    private TextView infoShow = null;
    private SmsSend sendingRequest;

    public SmsBuild(String message) {
        String strContact = null;

        contactGrp = (RadioGroup)findViewById(R.id.contactNums);
        infoShow = (TextView)findViewById(R.id.infoShow);

        switch (contactGrp.getCheckedRadioButtonId()) {
            case R.id.contactLabel1:    strContact = getString(R.string.contactNum1); break;
            case R.id.contactLabel2:    strContact = getString(R.string.contactNum2); break;
            case R.id.contactLabel3:    Toast.makeText(this, R.string.noContact, Toast.LENGTH_SHORT).show(); break;   // To-do
            default:                    Toast.makeText(this, R.string.badNum, Toast.LENGTH_LONG).show(); break;
        }

        sendingRequest = new SmsSend(strContact, message);
        infoShow.setText(String.format(getString(R.string.infoShow2), message, strContact));
    }
}
