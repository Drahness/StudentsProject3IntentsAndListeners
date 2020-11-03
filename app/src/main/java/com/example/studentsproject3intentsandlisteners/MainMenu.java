package com.example.studentsproject3intentsandlisteners;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public abstract class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Bundle b = new Bundle();
        Intent intent = new Intent();
        if (id == R.id.home_activity && this.getClass() != MainActivity.class) {
            intent.setClass(MainMenu.this,MainActivity.class);
        }
        else if(id == R.id.broadcast_activity && this.getClass() != MyBroadcastActivity.class) {
            intent.setClass(MainMenu.this,MyBroadcastActivity.class);
        }
        else if(id == R.id.intents_activity && this.getClass() != MyIntentsActivity.class)  {
            intent.setClass(MainMenu.this,MyIntentsActivity.class);
        }
        else if(id == R.id.services_activity && this.getClass() != MyServicesActivity.class) {
            intent.setClass(MainMenu.this,MyServicesActivity.class);
        }
        intent.putExtras(b);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
