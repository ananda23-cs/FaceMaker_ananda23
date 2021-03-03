package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * MainActivity
 * runs the facemaker app with all the widgets for face customization
 *
 * @author Aashish Anand
 * @version 2/22/2021
 */

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener{
    //instance variables
    protected Face face;
    private int checkedButton;
    FaceSurfaceView myFace;
    Button randomFace;
    Spinner hairStyleChanger;
    ArrayAdapter<CharSequence> hairStyles;
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    RadioGroup faceParts;

    //sets up the app with the appropriate widgets
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        //draws a face object in a canvas
        myFace = (FaceSurfaceView)findViewById(R.id.faceMaker);
        face = new Face();
        myFace.setFace(face);

        //random face button
        randomFace = (Button)findViewById(R.id.randomFace);
        randomFace.setOnClickListener(this);

        //Spinner to change hair styles
        hairStyleChanger = (Spinner)findViewById(R.id.hairStyleChanger);
        hairStyles = ArrayAdapter.createFromResource(this, R.array.hairstyles,
                android.R.layout.simple_spinner_item);
        hairStyles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairStyleChanger.setSelection(0);
        hairStyleChanger.setAdapter(hairStyles);
        hairStyleChanger.setOnItemSelectedListener(this);

        //Radio group setup
        faceParts = (RadioGroup)findViewById(R.id.faceParts);
        faceParts.setOnCheckedChangeListener(this);

        //setup RGB seekbars and listeners
        redSeekBar = (SeekBar)findViewById(R.id.redSeekBar);
        redSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar = (SeekBar)findViewById(R.id.blueSeekBar);
        blueSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar = (SeekBar)findViewById(R.id.greenSeekBar);
        greenSeekBar.setOnSeekBarChangeListener(this);
    }

    //sets up listener for the hair style spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        face.setHairStyle(position);
        myFace.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {} //No action taken--ignored

    /**
     * sets up radiogroup listener
     *
     * @param group RadioGroup widget
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = faceParts.findViewById(checkedId);
        int index = faceParts.indexOfChild(rb);
        switch(index){
            case 0: //hair button checked
                redSeekBar.setProgress(this.face.getHairColor(0));
                greenSeekBar.setProgress(this.face.getHairColor(1));
                blueSeekBar.setProgress(this.face.getHairColor(2));
                checkedButton = 0; //shows hair radio button is checked
                break;
            case 1: //eye button checked
                redSeekBar.setProgress(this.face.getEyeColor(0));
                greenSeekBar.setProgress(this.face.getEyeColor(1));
                blueSeekBar.setProgress(this.face.getEyeColor(2));
                checkedButton = 1; //shows eyes radio button is checked
                break;
            case 2: //skin button checked
                redSeekBar.setProgress(this.face.getSkinColor(0));
                greenSeekBar.setProgress(this.face.getSkinColor(1));
                blueSeekBar.setProgress(this.face.getSkinColor(2));
                checkedButton = 2; //shows skin radio button is checked
                break;
        }
    }

    /**
     * sets up the seekbar listener for any progress change
     *
     * @param seekBar seekbar widget
     * @param progress current progress level of the seekbar
     * @param fromUser if changes are made by the user
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.redSeekBar: //red seekbar
                switch(checkedButton){
                    case 0:
                        face.setHairColor(progress,0);
                        break;
                    case 1:
                        face.setEyeColor(progress,0);
                        break;
                    case 2:
                        face.setSkinColor(progress,0);
                        break;
                }
                break;
            case R.id.greenSeekBar: //green seekbar
                switch(checkedButton){
                    case 0:
                        face.setHairColor(progress,1);
                        break;
                    case 1:
                        face.setEyeColor(progress,1);
                        break;
                    case 2:
                        face.setSkinColor(progress,1);
                        break;
                }
                break;
            case R.id.blueSeekBar: //blue seekbar
                switch(checkedButton){
                    case 0:
                        face.setHairColor(progress,2);
                        break;
                    case 1:
                        face.setEyeColor(progress,2);
                        break;
                    case 2:
                        face.setSkinColor(progress,2);
                        break;
                }
                break;
        }
        myFace.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Don't care
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Don't care
    }

    //sets up onClick listener for the random face button
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.randomFace) {
            face.randomize();
            myFace.invalidate();
        }
    }

}