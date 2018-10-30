package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallSMS extends AppCompatActivity {


    private int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private int MY_PERMISSIONS_REQUEST_SEND_SMS = 2;
    EditText txtNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_sms);

        int Permission_All = 1;

        String[] Permissions = {Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS};
        if (!hasPremission(this, Permissions)){
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }

        txtNumber = findViewById(R.id.textMessage);

        Button btnCall = findViewById(R.id.btnMakeCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialNumber(getString(R.string.phoneNumber));
            }
        });

        Button btnSMS = findViewById(R.id.btnSendText);

        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(txtNumber.getText().toString(), getString(R.string.phoneNumber));
            }
        });
    }

    private void sendSMS(String textSMS, String phoneNumber) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, textSMS, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    private void dialNumber(String phoneNumber) {
        //startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)));

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("tel:"+phoneNumber));
        this.startActivity(intent);
    }

    public static boolean hasPremission(Context context, String... premissions){

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context != null && premissions != null)
        {
            for(String premission:premissions)
            {
                if(ActivityCompat.checkSelfPermission(context, premission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
