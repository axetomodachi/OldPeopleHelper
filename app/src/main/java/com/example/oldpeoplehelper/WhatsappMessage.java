package com.example.oldpeoplehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WhatsappMessage extends AppCompatActivity {
    private EditText whatsapp_m;
    private Button whatsapp_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_message);
        //Initialize
        whatsapp_m=findViewById(R.id.wtsp_msg);
        whatsapp_s=findViewById(R.id.wtsp_send);
        //On Click
        whatsapp_s.setOnClickListener(view -> {
            //If message is not empty, then send message else open whatsapp
            if(!whatsapp_m.getText().toString().isEmpty())
                sendMessage(whatsapp_m.getText().toString());
            else
                OpenApp("com.whatsapp");
        });
    }
    private void OpenApp(String packagename)
    {
        //to open whatsapp
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packagename);
        if (launchIntent != null)
        {
            whatsapp_m.setText("");
            startActivity(launchIntent);
        }
        else
        {
            Toast.makeText(
                    this,
                    "Please install whatsapp first.",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
    private void sendMessage(String message)
    {
        //Send Message(Whatsapp)
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(
                    this,
                    "Please install whatsapp first.",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        whatsapp_m.setText("");
        startActivity(intent);
    }
}