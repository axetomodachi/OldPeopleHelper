package com.example.oldpeoplehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class YoutubeSearch extends AppCompatActivity {
    private EditText searchTerm;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_search);
        //Initialize
        searchTerm=findViewById(R.id.utube_serach);
        searchBtn=findViewById(R.id.search_btn);
        //OnClick
        searchBtn.setOnClickListener(view ->
        {
            //Open link on youtube based on search
            String link="https://www.youtube.com/results?search_query="+searchTerm.getText().toString().replaceAll(" ","+");
            Uri uri = Uri.parse(link);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            searchTerm.setText("");
            startActivity(intent);
        });
    }
}