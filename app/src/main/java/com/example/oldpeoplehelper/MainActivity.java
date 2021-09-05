package com.example.oldpeoplehelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 123;
    private Button call_name,call_num,save_num,message,wtsp,utube,phot,internet,news,camera,refresh,fb,ig,email;
    String fb_link,ig_link,email_link;
    ImageButton edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize
        call_name=findViewById(R.id.call_name);
        call_num=findViewById(R.id.call_number);
        save_num=findViewById(R.id.save_number);
        message=findViewById(R.id.msg);
        wtsp=findViewById(R.id.whatsapp);
        utube=findViewById(R.id.Youtube);
        phot=findViewById(R.id.gallery);
        edit=findViewById(R.id.developer_but);
        internet=findViewById(R.id.Internet);
        news=findViewById(R.id.news);
        camera=findViewById(R.id.camera_but);
        refresh=findViewById(R.id.refresh);
        fb=findViewById(R.id.fb);
        ig=findViewById(R.id.ig);
        email=findViewById(R.id.email);
        //codes here

        //Developer Contacts
        ig_link = "https://www.instagram.com/ternary.games";
        fb_link = "https://www.facebook.com/rudra.ojha.395017";
        email_link = "mailto:neonudrafloy@gmail.com";
        //On clicks
            //Instagram
        ig.setOnClickListener(view -> {
            Uri uri = Uri.parse(ig_link);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
            //Email
        email.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(email_link));
            startActivity(intent);
        });
            //FaceBook
        fb.setOnClickListener(view -> {
            Uri uri = Uri.parse(fb_link);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
            //Refresh
        refresh.setOnClickListener(view -> {
            if(isNetworkAvailable())
                internet.setText("Turn Internet Off");
            else
                internet.setText("Turn Internet On");
        });
            //Camera
        camera.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            startActivity(intent);
        });
            //News
        news.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NewsViewer.class);
            startActivity(intent);
        });
            //Network
        internet.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.ACTION_DATA_USAGE_SETTINGS);
            startActivity(intent);
        });
            //EditButton(Only for debugging)
        edit.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),Developer_package_prefs.class);
            startActivity(intent);
        });
            //Call with num.
        call_num.setOnClickListener(view -> OpenDialer());
            //Call with Name
        call_name.setOnClickListener(view ->
        {
            Intent intent= new Intent(Intent.ACTION_VIEW,  ContactsContract.Contacts.CONTENT_URI);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        );
            //Youtube
        utube.setOnClickListener(view ->
        {
            Intent intent = new Intent(getApplicationContext(), YoutubeSearch.class);
            startActivity(intent);
        });
            //Whatsapp
        wtsp.setOnClickListener(view ->
        {
            Intent intent = new Intent(getApplicationContext(), WhatsappMessage.class);
            startActivity(intent);
        });
            //Save Number
        save_num.setOnClickListener(view -> {
            if(hasPermission()) {
                Intent intent = new Intent(getApplicationContext(), SaveContacts.class);
                startActivity(intent);
            }
        });
            //Messaging
        message.setOnClickListener(view -> {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
            smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.setData(Uri.parse("sms:"));
            startActivity(smsIntent);
        });
            //Gallery
        phot.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setType("image/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }



    private boolean hasPermission()
    {
        //Check contact permission
        if (checkSelfPermission(android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CONTACTS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Read Contacts permission");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setMessage("Please enable access to contacts.");
                builder.setOnDismissListener(dialog -> requestPermissions(
                        new String[]
                                {android.Manifest.permission.READ_CONTACTS}
                        , PERMISSIONS_REQUEST_READ_CONTACTS));
                builder.show();
            } else {
                requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS},
                        PERMISSIONS_REQUEST_READ_CONTACTS);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults)
    {//Contact Permission
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(getApplicationContext(), SaveContacts.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "You have disabled a contacts permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(isNetworkAvailable())
            internet.setText("Turn Internet Off");
        else
            internet.setText("Turn Internet On");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(isNetworkAvailable())
            internet.setText("Turn Internet Off");
        else
            internet.setText("Turn Internet On");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isNetworkAvailable())
            internet.setText("Turn Internet Off");
        else
            internet.setText("Turn Internet On");
    }

    private boolean isNetworkAvailable()
    {
        //Check if network is available
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void OpenDialer()
    {
        //Open Dialer
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }

}