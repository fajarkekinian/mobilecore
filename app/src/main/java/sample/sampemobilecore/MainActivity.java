package sample.sampemobilecore;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ironsource.mobilcore.AdUnitEventListener;
import com.ironsource.mobilcore.MobileCore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileCore.setAdUnitEventListener(new AdUnitEventListener() {
            @Override
            public void onAdUnitEvent(MobileCore.AD_UNITS adUnit, EVENT_TYPE eventType,
                                      MobileCore.AD_UNIT_TRIGGER... trigger) {
                if(adUnit == MobileCore.AD_UNITS.INTERSTITIAL &&
                        eventType == AdUnitEventListener.EVENT_TYPE.AD_UNIT_INIT_SUCCEEDED) {
                    MobileCore.loadAdUnit(MobileCore.AD_UNITS.INTERSTITIAL,
                            MobileCore.AD_UNIT_TRIGGER.MAIN_MENU);
                }
                else if(adUnit == MobileCore.AD_UNITS.INTERSTITIAL &&
                        eventType == AdUnitEventListener.EVENT_TYPE.AD_UNIT_READY) {
                    for(MobileCore.AD_UNIT_TRIGGER myTrigger:trigger){
                        if(myTrigger.equals(MobileCore.AD_UNIT_TRIGGER.MAIN_MENU)){
                            MobileCore.showInterstitial(MainActivity.this,
                                    MobileCore.AD_UNIT_TRIGGER.MAIN_MENU, null);
                        }
                    }
                }
            }
        });
        MobileCore.init(this, "1REKG6VMNP1RDDMI3Q9GNSWCJ8777", MobileCore.LOG_TYPE.DEBUG,
                MobileCore.AD_UNITS.INTERSTITIAL);

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
