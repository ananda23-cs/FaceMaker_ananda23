package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

/**
 * FaceSurfaceView class
 *
 * creates a surface view for the face object
 *
 * @author Aashish Anand
 * @version 2/26/2021
 */

public class FaceSurfaceView extends SurfaceView {
    //initializes face instance variable
    Face face;

    public FaceSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
    }

    //setter method for a Face object
    public void setFace(Face f){
        this.face = f;
    }

    //draws the face object on the canvas
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.DKGRAY);
        face.draw(canvas);
    }
}
