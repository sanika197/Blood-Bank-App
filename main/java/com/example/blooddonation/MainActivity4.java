package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import android.os.Bundle;

public class MainActivity4 extends AppCompatActivity {

    Button play;
    VideoView videoView;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        videoView = findViewById(R.id.myvideo);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+ R.raw.newvideo);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        play = findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                videoView.start();
                videoView.setMediaController(mediaController);
                videoView.requestFocus();
            }
        });

    }
}