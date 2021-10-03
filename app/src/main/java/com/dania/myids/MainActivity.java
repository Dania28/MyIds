package com.dania.myids;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    int CODE_WRITE_SETTINGS_PERMISSION = 100;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location my_location, cllgGate1, cllgGate2, cllgGate3, cllgLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Common.brightness = Settings.System.getInt(getApplicationContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean permission = Settings.System.canWrite(getApplicationContext());
            if (permission){
                changeBrightness();
            }else {
                permission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
            }
            if (permission) {

            }  else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                startActivityForResult(intent, CODE_WRITE_SETTINGS_PERMISSION);
            }
        }

        my_location = new Location("my_location");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                my_location = task.getResult();
            }
        });

        cllgGate1 = new Location("cllgGate1");
        cllgGate2 = new Location("cllgGate2");
        cllgGate3 = new Location("cllgGate3");
        cllgLibrary = new Location("cllgLibrary");

        cllgGate3.setLatitude(28.718139);
        cllgGate3.setLongitude(77.066829);

        cllgLibrary.setLatitude(28.71995436122248);
        cllgLibrary.setLongitude(77.06648682889761);

        cllgGate1.setLatitude(28.719494);
        cllgGate1.setLongitude(77.065318);


        cllgGate2.setLatitude(28.720366);
        cllgGate2.setLongitude(77.066146);


        float a = my_location.distanceTo(cllgGate3);

        if (a>1000.0){
            Intent i = new Intent(getApplicationContext(),Ids.class);
            i.putExtra("value",4);
            startActivity(i);
            finish();
        }else {
            ArrayList<Float> diff = new ArrayList<>();
            diff.add(a);
            diff.add(my_location.distanceTo(cllgLibrary));
            diff.add(my_location.distanceTo(cllgGate1));
            diff.add(my_location.distanceTo(cllgGate2));
            float min = Collections.max(diff);
            if(diff.indexOf(min)==1){
                Intent i = new Intent(getApplicationContext(),Ids.class);
                i.putExtra("value",2);
                startActivity(i);
                finish();
            }else {
                Intent i = new Intent(getApplicationContext(),Ids.class);
                i.putExtra("value",0);
                startActivity(i);
                finish();
            }

        }
    }

    private void changeBrightness() {
        Settings.System.putInt(getApplicationContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_WRITE_SETTINGS_PERMISSION && Settings.System.canWrite(this)){
            changeBrightness();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_WRITE_SETTINGS_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            changeBrightness();
        }
    }

}