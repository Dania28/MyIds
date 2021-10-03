package com.dania.myids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Ids extends AppCompatActivity {

    int position = 0;
    private ImageView img;
    private Button left, right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ids);
        img = findViewById(R.id.img);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {
            position= extras.getInt("value");
        }
        setId(position);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position!=0){
                    position = position-1;
                    setId(position);
                }else {
                    position = 13;
                    setId(position);
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position!=13){
                    position = position+1;
                    setId(position);
                }else {
                    position = 0;
                    setId(position);
                }
            }
        });

    }

    private void  setId(int i){
        switch (i){
            case 0:
                img.setImageResource(R.drawable.college_front);
                break;
            case 1:
                img.setImageResource(R.drawable.college_back);
                break;
            case 2:
                img.setImageResource(R.drawable.library_front);
                break;
            case 3:
                img.setImageResource(R.drawable.library_back);
                break;
            case 4:
                img.setImageResource(R.drawable.pan_front);
                break;
            case 5:
                img.setImageResource(R.drawable.pan_back);
                break;
            case 6:
                img.setImageResource(R.drawable.aadhar_front);
                break;
            case 7:
                img.setImageResource(R.drawable.aadhar_back);
                break;
            case 8:
                img.setImageResource(R.drawable.voter_front);
                break;
            case 9:
                img.setImageResource(R.drawable.voter_back);
                break;
            case 10:
                img.setImageResource(R.drawable.metro);
                break;
            case 11:
                img.setImageResource(R.drawable.bus_front);
                break;
            case 12:
                img.setImageResource(R.drawable.bus_back);
                break;
            case 13:
                img.setImageResource(R.drawable.fee);
                break;
            default:
                img.setImageResource(R.drawable.college_front);
        }
    }

    @Override
    public void finish() {
        super.finish();
        Settings.System.putInt(getApplicationContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        Settings.System.putInt(getApplicationContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS,Common.brightness);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}