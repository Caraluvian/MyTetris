package com.example.mytetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    //width and height of the game screen part
    int gWidth,gHeight;
    //game screen controls
    View view;
    //map
    boolean [][] maps;
    //box and box size
    Point[] boxes;
    int boxSize;
    //initialize the paint of the auxiliary line
    Paint paintLine;
    Paint boxLine;

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
        //initialize the map 10 * 20
        maps = new boolean [10][20];
        //initialize the box
        boxes = new Point[]{new Point(3,0), new Point(3,1), new Point(4,1), new Point(5,1)};
        //initialize the box size
        boxSize = gWidth / maps.length;

    }

    //initialize the view
    public void initView(){
        //initialize the paint
        paintLine = new Paint();
        paintLine.setColor(0xff666666);
        paintLine.setAntiAlias(true);

        boxLine = new Paint();
        boxLine.setColor(0xff000000);
        boxLine.setAntiAlias(true);

        //get the parent container
        FrameLayout gameScreen = findViewById(R.id.gameScreen);
        //instantiate the game part
        view = new View(this){
            //overwrite the game screen part with the auxiliary line
            @Override
            protected void onDraw(Canvas canvas){
                super.onDraw(canvas);
                //drawing boxes
                for(int i = 0; i < boxes.length; i++){
                    canvas.drawRect(boxes[i].x * boxSize,boxes[i].y * boxSize,boxes[i].x * boxSize + boxSize,boxes[i].y * boxSize + boxSize,boxLine);
                }


                //drawing map auxiliary line
                //maps.length = 10, 10 rows (x axis)
                for(int i = 0; i < maps.length; i++){
                    canvas.drawLine(i * boxSize,0,i * boxSize, view.getHeight(),paintLine);
                }
                //maps[0].length = 20, 20 boxes on each row (y axis)
                for(int j = 0; j < maps[0].length; j++){
                    canvas.drawLine(0, j * boxSize, view.getWidth(), j * boxSize, paintLine);
                }
            }
        };
        //set the size of the game screen
        view.setLayoutParams(new FrameLayout.LayoutParams(gWidth,gHeight));
        //set background color
        view.setBackgroundColor(0x10000000);
        //add it in parent container
        gameScreen.addView(view);
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
