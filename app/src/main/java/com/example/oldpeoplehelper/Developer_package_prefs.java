package com.example.oldpeoplehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Developer_package_prefs extends AppCompatActivity {

    //TODO This activity is of no use(/only for debugging)

    private EditText contacts_e,gallery_e,youtube_e,whatsapp_e,camera_e;
    private Button Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_package_prefs);
        contacts_e=findViewById(R.id.contact_e);
        gallery_e=findViewById(R.id.gallery_e);
        youtube_e=findViewById(R.id.utube_e);
        whatsapp_e=findViewById(R.id.wtsp_e);
        camera_e=findViewById(R.id.camera_e);
        Save=findViewById(R.id.save_btn);

        Save.setOnClickListener(view -> {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            SharedPreferences.Editor editor = pref.edit();
            if(!youtube_e.getText().toString().isEmpty())
            editor.putString("youtube_name",youtube_e.getText().toString());
            if(!whatsapp_e.getText().toString().isEmpty())
            editor.putString("whatsapp_name",whatsapp_e.getText().toString());
            if(!gallery_e.getText().toString().isEmpty())
            editor.putString("gallery_name",gallery_e.getText().toString());
            if(!contacts_e.getText().toString().isEmpty())
            editor.putString("contacts_name",contacts_e.getText().toString());
            if(!camera_e.getText().toString().isEmpty())
                editor.putString("camera_name",camera_e.getText().toString());
            editor.commit();
        });
    }
}