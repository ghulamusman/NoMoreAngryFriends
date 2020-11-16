package com.example.nomoreangryfriends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
    private static final String FILE_NAME = "reply.txt";
    private static final String TAG = smsReceiver.class.getSimpleName();

    private EditText replyText;     // for the new reply to be entered.
    private TextView currentReply;  // the text view displays the current reply to be sent.
    private getText currentText;    // loads text from file.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentText = new getText();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkForSmsPermission();
        if(currentText.getMessageText(getApplicationContext()).length() == 0){
            setSaveFile();
        }
        replyText = findViewById(R.id.replyText);
        currentReply = findViewById(R.id.currentText);
        currentReply.setText(currentText.getMessageText(getApplicationContext()));



    }
    private void setSaveFile(){         // Sets the save file with a default reply incase no reply has been specified.
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write("Hey Dude!".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void onResume() {
        super.onResume();
        ComponentName component = new ComponentName(this, smsReceiver.class);
        getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    public void onPause() {
        super.onPause();
        ComponentName component = new ComponentName(this, smsReceiver.class);
        getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


    public void saveReply(View view) {              // saves the reply provided by the user locally to be used the next time a text is sent.
        String textToSave = replyText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(textToSave.getBytes());

            replyText.getText().clear();
            Toast.makeText(this, "Saved",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        currentReply.setText(currentText.getMessageText(getApplicationContext()));
    }

    // Checking for permissions to send and receive texts

    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS},  MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        Toast.makeText(this, "Permission Granted",Toast.LENGTH_LONG).show();

    }



}
