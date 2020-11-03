package com.example.studentsproject3intentsandlisteners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyBroadcastActivity extends MainMenu {
    private Button buttonSend;
    static TextView textNotification;
    static int n = 1;
    Intent intent; Bundle bundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broadcast);

        findViewById(R.id.button_broadcast).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Your own broadcast
                Log.d ("Send", "Sent a broadcast");
                Intent intent = new Intent("android.intent.action.PULSA_EL_BOTON"); // Entiendo, aqui es donde le pido que haga la accion, la cual
                // el receiver pilla.
                bundle.putString("msg", "Started broadcast service ..." + n++  + " times" );
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        }
        );
        // TODO: Getting button and textview from xml
        // TODO: Attaching onclicklistener to buttons

        intent = new Intent();    bundle = new Bundle();

        IntentFilter filter = new IntentFilter();
        // TODO: Create an IntentFilter and register the receiver
        // The actions to be filtered
        filter.addAction("android.intent.action.PULSA_EL_BOTON"); // Aqui registro ambas, la mia propia que es activada con el boton de mas arriba.
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE"); // esta es cuando la conectividad cambia.
        // Create an instance of myBroadcastReceiver
        MyReceiver myReceiver = new MyReceiver();
        registerReceiver(myReceiver, filter);
    }
}