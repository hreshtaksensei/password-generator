package com.mindestria.app.passwordgenerator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; 

public class MainActivity extends AppCompatActivity {

    // Objects declarations
    private Button passwordActivityButton = null;
    private Button anotherActivityButton = null;
    private Button exitButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Creating the savedInstances
        super.onCreate(savedInstanceState);
        // Change Title name of the activity
        getSupportActionBar().setTitle(R.string.app_name_full);
        getSupportActionBar().setLogo(R.mipmap.icon);
        // Setting content to view
        setContentView(R.layout.activity_main);
        // Get view objects
        passwordActivityButton = (Button)findViewById(R.id.passwordActivityButton);
        anotherActivityButton = (Button)findViewById(R.id.anotherActivityButton);
        exitButton = (Button)findViewById(R.id.exitButton);
        // Settings events listener
        passwordActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passwordActivityIntent = new Intent(MainActivity.this, Password_Activity.class);
                startActivity(passwordActivityIntent);
            }
        });
        anotherActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Alert dialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder
                    .setTitle(R.string.exit_dialog_title_text)
                    .setMessage(R.string.exit_dialog_message_text)
                    .setCancelable(false)
                    .setPositiveButton(R.string.exit_dialog_positive_answer_text, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.exit_dialog_negative_answer_text,new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                            dialog.cancel();
                        }
                    } /*,new DialogInterface.OnShowListener(){
                        @Override
                        public void onShow(DialogInterface dialog) {
                            Button positive = this.dialog(DialogInterface.BUTTON_POSITIVE);
                            positive.setFocusable(true);
                            positive.setFocusableInTouchMode(true);
                            positive.requestFocus();
                        }
                    }*/)
                    .create()
                    .show();
            }
        });
    }
}
