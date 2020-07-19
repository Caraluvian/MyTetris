package com.example.mytetris;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    int gWidth,gHeight;
    //game screen controls
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the action bar on the top
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    //initialize the data
    public void initData(){
        //get the width of the screen
        int width = getScreenWidth(this);
        //set the width of the game screen is 2/3 of the screen
        gWidth = width * 2/3;
        //height = width * 2;
        gHeight = gWidth * 2;
    }

    //initialize the view
    public void initView(){
        //get the parent container
        FrameLayout gameScreeen = findViewById(R.id.gameScreen);
        //instantiate the game part
        view = new View(this);
        //set the size of the game screen
        view.setLayoutParams(new FrameLayout.LayoutParams(gWidth,gHeight));
        //set background color
        view.setBackgroundColor(0x10000000);
        //add it in parent container
        gameScreeen.addView(view);
    }
    //get the screen width
    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
