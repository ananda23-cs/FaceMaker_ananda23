package com.example.facemaker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.Random;

/**
 * class Face:
 *
 * creates a Face object to be drawn and customized in the app
 *
 * @author Aashish Anand
 * @version 2/22/2021
 */

public class Face {

    //Instance variables
    private int skinColor, eyeColor, hairColor, mouthColor, hairStyle;
    private Paint skinPaint = new Paint();
    private Paint eyePaint = new Paint();
    private Paint hairPaint = new Paint();
    private Paint corneaPaint = new Paint();
    private Paint pupilPaint = new Paint();
    private Paint mouthPaint = new Paint();

    /**
     * constructor for a Face object
     */
    public Face() {
        corneaPaint.setColor(0xFFFFFFFF);
        pupilPaint.setColor(0xFF000000);
        mouthColor = Color.rgb(255,0,0);
        mouthPaint.setColor(mouthColor);
        this.randomize();
    }

    /**
     * Randomizes all the instance variables to its valid values
     *
     * @param
     * @return void
     */
    public void randomize(){
        Random r = new Random();
        int f = r.nextInt(3);
        switch(f) {
            case 0:
                skinColor = Color.rgb(252, 215, 207);
                eyeColor = Color.rgb(108,165,128);
                hairColor = Color.rgb(154,51,0);
                break;
            case 1:
                skinColor = Color.rgb(101, 67, 33);
                eyeColor = Color.rgb(133,171,206);
                hairColor = Color.rgb(170,136,102);
                break;
            case 2:
                skinColor = Color.rgb(233, 132, 86);
                eyeColor = Color.rgb(96,49,1);
                hairColor = Color.rgb(36,28,17);
                break;
        }
        skinPaint.setColor(skinColor);
        eyePaint.setColor(eyeColor);
        hairPaint.setColor(hairColor);
        hairStyle = (int)(Math.random()*4);
    }

    /**
     * Draws the face on a given Canvas
     *
     * @param canvas a canvas for a face object
     * @return void
     */
    public void draw(Canvas canvas) {
        canvas.drawCircle(1000.0f,425.0f, 400.0f, skinPaint);
        drawEyes(canvas);
        drawMouth(canvas);
        drawHair(canvas);
    }

    /**
     * Draws the eyes on the face in the canvas
     *
     * @param canvas a canvas for a face object
     * @return void
     */
    public void drawEyes(Canvas canvas){
        canvas.drawCircle(800.0f, 365.0f,100.0f, corneaPaint);
        canvas.drawCircle(800.0f, 365.0f,70.0f, eyePaint);
        canvas.drawCircle(800.0f, 365.0f,40.0f, pupilPaint);
        canvas.drawCircle(1200.0f, 365.0f,100.0f, corneaPaint);
        canvas.drawCircle(1200.0f, 365.0f,70.0f, eyePaint);
        canvas.drawCircle(1200.0f, 365.0f,40.0f, pupilPaint);
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }

    /**
     * Draws the mouth on the face in the canvas
     *
     * @param canvas a canvas for a face object
     * @return void
     */
    public void drawMouth(Canvas canvas){
        RectF smile = new RectF(900.0f, 400.0f, 1100.0f,700.0f);
        canvas.drawArc(smile,0.0f, 180.0f, true, mouthPaint);
    }

    /**
     * Draws the hair on the face object in the canvas
     *
     * @param canvas a canvas for a face object
     * @return void
     */
    public void drawHair(Canvas canvas){
        switch (hairStyle){
            case 0:
                break;
            case 1:
                canvas.drawArc(600.0f,25.0f,1400.0f,405.0f, 180.0f,
                        180.0f,true, hairPaint);
                break;
            case 2:
                canvas.drawArc(600.0f,25.0f,1400.0f,405.0f,180.0f,
                        180.0f,true, hairPaint);
                canvas.drawArc(580.0f,120.0f,750.0f,830.0f,90.0f,
                        180.0f,true, hairPaint);
                canvas.drawArc(1250.0f,120.0f,1420.0f,830.0f,-90.0f,
                        180.0f,true, hairPaint);
                break;
            case 3:
                canvas.drawRect(700.0f,25.0f,1300.0f,205.0f, hairPaint);
                break;
        }
    }

    /**
     * External Citation:
     * Date 26 February 2021
     * Problem: Could not figure out how to get individual channels of the colors of the face.
     * Resource: https://www.tutorialspoint.com/how-to-get-pixels-rgb-values-of-an
                                                    -image-using-java-opencv-library
     * Solution: Used the example code from the link above.
     */

    /**
     * getter method for the eye color
     *
     * @param channel represents a color channel
     *                0 is Red, 1 is Blue, 2 is Green
     * @return value of the selected channel or 0 if invalid color channel
     */
    public int getEyeColor(int channel) {
        int colorEye = eyePaint.getColor();
        switch(channel){
            case 0:
                return (colorEye >> 16) & 0xFF;
            case 1:
                return (colorEye >> 8) & 0xFF;
            case 2:
                return (colorEye) & 0xFF;
        }
        return 0;
    }

    /**
     * getter method for the hair color
     *
     * @param channel represents a color channel
     *                0 is Red, 1 is Blue, 2 is Green
     * @return value of the selected channel or 0 if invalid color channel
     */
    public int getHairColor(int channel) {
        int colorHair = hairPaint.getColor();
        switch(channel){
            case 0:
                return (colorHair >> 16) & 0xFF;
            case 1:
                return (colorHair >> 8) & 0xFF;
            case 2:
                return (colorHair) & 0xFF;
        }
        return 0;
    }

    /**
     * getter method for the skin color
     *
     * @param channel represents a color channel
     *                0 is Red, 1 is Blue, 2 is Green
     * @return value of the selected channel or 0 if invalid color channel
     */
    public int getSkinColor(int channel) {
        int colorSkin = skinPaint.getColor();
        switch(channel){
            case 0:
                return (colorSkin >> 16) & 0xFF;
            case 1:
                return (colorSkin >> 8) & 0xFF;
            case 2:
                return (colorSkin) & 0xFF;
        }
        return 0;
    }

    /**
     * setter method for the eye color
     *
     * @param channel represents a color channel
     *                0 is Red, 1 is Blue, 2 is Green
     * @param progress progress of the channel seekbars
     * @return
     */
    public void setEyeColor(int progress, int channel) {
        int colorEye = eyePaint.getColor();

        int r = (colorEye >> 16) & 0xFF;
        int g = (colorEye >> 8) & 0xFF;
        int b = (colorEye) & 0xFF;
        switch (channel){
            case 0:
                this.eyePaint.setARGB(255, progress, g, b);
                break;
            case 1:
                this.eyePaint.setARGB(255, r, progress, b);
                break;
            case 2:
                this.eyePaint.setARGB(255, r, g, progress);
                break;
        }
    }

    /**
     * setter method for the hair color
     *
     * @param channel represents a color channel
     *                0 is Red, 1 is Blue, 2 is Green
     * @param progress progress of the channel seekbars
     * @return
     */
    public void setHairColor(int progress, int channel) {
        int colorHair = hairPaint.getColor();
        
        int r = (colorHair >> 16) & 0xFF;
        int g = (colorHair >> 8) & 0xFF;
        int b = (colorHair) & 0xFF;
        switch (channel){
            case 0:
                this.hairPaint.setARGB(255, progress, g, b);
                break;
            case 1:
                this.hairPaint.setARGB(255, r, progress, b);
                break;
            case 2:
                this.hairPaint.setARGB(255, r, g, progress);
                break;
        }
    }

    /**
     * setter method for the skin color
     *
     * @param channel represents a color channel
     *                0 is Red, 1 is Blue, 2 is Green
     * @param progress progress of the channel seekbars
     * @return
     */
    public void setSkinColor(int progress, int channel) {
        int colorSkin = skinPaint.getColor();

        int r = (colorSkin >> 16) & 0xFF;
        int g = (colorSkin >> 8) & 0xFF;
        int b = (colorSkin) & 0xFF;
        switch (channel){
            case 0:
                this.skinPaint.setARGB(255, progress, g, b);
                break;
            case 1:
                this.skinPaint.setARGB(255, r, progress, b);
                break;
            case 2:
                this.skinPaint.setARGB(255, r, g, progress);
                break;
        }
    }
}
