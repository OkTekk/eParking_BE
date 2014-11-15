package be.laurent_fournier.eparkingbe_v3;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lowlow on 13/11/2014.
 * Version ${VERSION}.
 */

public class WelcomeCheck {
    private static final String EPARKING_PREFS = "eParking_Prefs";

    public static boolean isFirst(Context context){
        final SharedPreferences prefsReader = context.getSharedPreferences(EPARKING_PREFS, Context.MODE_PRIVATE);
        final boolean first = prefsReader.getBoolean("is_first", true);

        if(first){
            final SharedPreferences.Editor prefsEditor = prefsReader.edit();
            prefsEditor.putBoolean("is_first", false);
            prefsEditor.commit();
        }

        return first;
    }
}
