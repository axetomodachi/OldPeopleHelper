package com.example.oldpeoplehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class NewsViewer extends AppCompatActivity {
    private Button nEng,nBng,nHnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_viewer);
        //Initialize
        nEng=findViewById(R.id.eng_news);
        nHnd=findViewById(R.id.hind_news);
        nBng=findViewById(R.id.beng_news);
        //On Clicks
        nEng.setOnClickListener(view -> OpenNews("https://www.bbc.com/news/world_radio_and_tv"));
        nBng.setOnClickListener(view -> OpenNews("https://bengali.abplive.com/live-tv"));
        nHnd.setOnClickListener(view -> OpenNews("https://www.abplive.com/live-tv"));
    }
    private void OpenNews(String link)
    {
        //Simple Open Link
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}