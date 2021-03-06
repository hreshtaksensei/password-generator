package com.mindestria.app.passwordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;

public class Password_Activity extends AppCompatActivity {

    // Objects declarations
    Switch alphabeticSwitch = null;
    RadioGroup alphabeticRadioGroup = null;
    Switch numericSwitch = null;
    Switch symbolicSwitch = null;
    TextView passwordLengthTextView = null;
    SeekBar passwordLengthSeekBar = null;
    Button generateButton = null;
    TextView passwordScreen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        // Change Title name of the activity
        getSupportActionBar().setTitle(R.string.app_name_full);
        // Enable back home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Getting objects from view
        alphabeticSwitch = (Switch)findViewById(R.id.alphabeticSwitch);
        alphabeticRadioGroup = (RadioGroup)findViewById(R.id.alphabeticRadioGroup);
        numericSwitch = (Switch)findViewById(R.id.numericSwitch);
        symbolicSwitch = (Switch)findViewById(R.id.symbolicSwitch);
        passwordLengthTextView = (TextView)findViewById(R.id.passwordLengthTextView);
        passwordLengthSeekBar = (SeekBar)findViewById(R.id.passwordLengthSeekBar);
        generateButton = (Button)findViewById(R.id.generateButton);
        passwordScreen = (TextView)findViewById(R.id.passwordScreen);
        // Settings events listener
        passwordLengthTextView.setText(getString(R.string.lbl_password_length)+" ("+passwordLengthSeekBar.getProgress()+")");
        alphabeticSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(alphabeticRadioGroup.getVisibility()== View.GONE){
                    alphabeticRadioGroup.setVisibility(View.VISIBLE);
                } else {
                    alphabeticRadioGroup.setVisibility(View.GONE);
                }
            }
        });
        passwordLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                passwordLengthTextView.setText(getString(R.string.lbl_password_length)+" ("+passwordLengthSeekBar.getProgress()+")");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alphabeticSwitch.isChecked() || numericSwitch.isChecked() || symbolicSwitch.isChecked()){
                    // Variables
                    String alphabeticLowerCase = "abcdefghijklmnopqrstuvwxyz";
                    String alphabeticUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    String numericNumbers = "0123456789";
                    String symbolicCharacters = "@#$&€éèàç=+£!?";
                    StringBuilder passwordCharactersList = new StringBuilder();
                    // Random objects
                    SecureRandom randomNumber = new SecureRandom();
                    StringBuilder passwordGenerated = new StringBuilder(10);
                    // Baking password character list using enable options
                    if(alphabeticSwitch.isChecked() && alphabeticRadioGroup.getCheckedRadioButtonId() == R.id.rad_lowerCase)
                        passwordCharactersList.append(alphabeticLowerCase);
                    if(alphabeticSwitch.isChecked() && alphabeticRadioGroup.getCheckedRadioButtonId() == R.id.rad_upperCase)
                        passwordCharactersList.append(alphabeticUpperCase);
                    if(alphabeticSwitch.isChecked() && alphabeticRadioGroup.getCheckedRadioButtonId() == R.id.rad_lowerUpperCase)
                        passwordCharactersList.append(alphabeticLowerCase+alphabeticUpperCase);
                    if(numericSwitch.isChecked()) passwordCharactersList.append(numericNumbers);
                    if(symbolicSwitch.isChecked()) passwordCharactersList.append(symbolicCharacters);
                    // Generating random password
                    for(int i=0;i<passwordLengthSeekBar.getProgress();i++) {
                        passwordGenerated.append(passwordCharactersList.toString().charAt(randomNumber.nextInt(passwordCharactersList.length())));
                    }
                    // Display password
                    passwordScreen.setText(passwordGenerated.toString());
                } else {
                    Toast.makeText(Password_Activity.this, R.string.dlg_error_check_one, Toast.LENGTH_SHORT).show();
                }
            }
        });
        passwordScreen.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                passwordScreen.setCursorVisible(true);
                ClipboardManager clipboard = (ClipboardManager) Password_Activity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("password", passwordScreen.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(Password_Activity.this, R.string.dlg_password_copied, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
