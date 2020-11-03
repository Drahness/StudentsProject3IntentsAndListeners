package com.example.studentsproject3intentsandlisteners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MyServicesActivity extends MainMenu implements View.OnClickListener{
    private Button buttonStart, buttonStop;
    private ImageView androidServices, stoppedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_services);
        //Getting buttons from xml
        buttonStart = findViewById(R.id.start_button);
        buttonStart.setVisibility(View.VISIBLE);
        buttonStop =  findViewById(R.id.stop_button);
        buttonStop.setVisibility(View.INVISIBLE);
        //Attaching onclicklistener to buttons
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        // The project logo
        androidServices = findViewById(R.id.image_service);
        stoppedImage = findViewById(R.id.stopped_image);
        Glide.with(this).asGif().load(R.drawable.giphy).into(androidServices);//.into(R.id.image_service)

    }

    @Override
    public void onClick(View view) {
        if (view == buttonStart) {
// TODO:
// Hide the button start and show the button stop after starting
            // Starting service
            startService(new Intent(this, MyService.class));
            buttonStart.setVisibility(View.INVISIBLE);
            buttonStop.setVisibility(View.VISIBLE);
            stoppedImage.setVisibility(View.INVISIBLE);
            androidServices.setVisibility(View.VISIBLE);
        } else if (view == buttonStop) {
// Hide the button stop and show the button start after stopping
            // TODO:
            // Stopping service
            buttonStart.setVisibility(View.VISIBLE);
            buttonStop.setVisibility(View.INVISIBLE);
            Glide.with(this).asDrawable().load(R.drawable.giphy).into(androidServices);//.into(R.id.image_service)
            stopService(new Intent(this, MyService.class));
            androidServices.setVisibility(View.INVISIBLE);
            stoppedImage.setVisibility(View.VISIBLE);
        }

    }

}