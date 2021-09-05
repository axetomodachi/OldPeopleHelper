package com.example.oldpeoplehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveContacts extends AppCompatActivity {

    private Button next;
    private EditText pno_e,name_e;
    private String pno,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_contacts);
        //Initialize
        pno_e=findViewById(R.id.phone_S);
        name_e=findViewById(R.id.name_s);
        next=findViewById(R.id.next_btn);
        //On click
        next.setOnClickListener(view -> {
            pno=pno_e.getText().toString();
            name=name_e.getText().toString();
            OpenSaver(pno,name);
        });
    }
    void OpenSaver(String pno, String Name)
    {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        Pattern p = Pattern.compile("^\\d{10}$");//phone number regex
        Matcher m=p.matcher(pno);
        if(!m.matches())
        {
            pno_e.setError("Wrong number");
            return;
        }
        if(contactExists(getApplicationContext(),pno))
        {
            pno_e.setError("Already exists");
            return;
        }
        if(name.isEmpty())
        {
            name_e.setError("Name is empty");
            return;
        }
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, pno);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, Name);
        name_e.setText("");
        pno_e.setText("");
        startActivity(intent);
    }
    public boolean contactExists(Context context, String number)
    {
        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = { ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME };
        Cursor cur = context.getContentResolver().query(lookupUri,mPhoneNumberProjection, null, null, null);
        try {
            if (cur.moveToFirst()) {
                cur.close();
                return true;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return false;
    }
}