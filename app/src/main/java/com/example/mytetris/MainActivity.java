package com.example.mytetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //width and height of the game screen part
    int gWidth,gHeight;
    //game screen controls
    View view;
    //map
    boolean [][] maps;
    //box and box size
    Point[] boxes;
    int boxType;
    int boxSize;
    //initialize the paint of the auxiliary line
    Paint paintLine;
    Paint boxLine;
    Paint mapLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide the action bar on the top
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        initData();
        initBoxes();
        initView();
        initListen();
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

        //initialize the box size
        boxSize = gWidth / maps.length;

    }

    //new boxes
    public void initBoxes(){

        // randomly set a new box
        Random random = new Random();
        boxType = random.nextInt(7);

        switch (boxType){
            //square tetromino
            case 0:
                boxes = new Point[]{new Point(4,0), new Point(5,0), new Point(4,1), new Point(5,1)};
                break;

             //L-tetromino type 1
            case 1:
                boxes = new Point[]{new Point(3,1), new Point(3,0), new Point(4,1), new Point(5,1)};
                break;

            //L-tetromino type 2
            case 2:
                boxes = new Point[]{new Point(5,1), new Point(5,0), new Point(4,1), new Point(3,1)};
                break;

            //skew tetromino type 1
            case 3:
                boxes = new Point[]{new Point(5,0), new Point(4,0), new Point(5,1), new Point(6,1)};
                break;

            //skew tetromino type 2
            case 4:
                boxes = new Point[]{new Point(5,0), new Point(6,0), new Point(5,1), new Point(4,1)};
                break;

            //T-tetromin
            case 5:
                boxes = new Point[]{new Point(5,0), new Point(4,0), new Point(6,0), new Point(5,1)};
                break;

            //straight tetromino
            case 6:
                boxes = new Point[]{new Point(5,0), new Point(4,0), new Point(6,0), new Point(7,0)};
                break;

        }
    }

    //initialize the view
    public void initView(){
        //initialize the paint
        paintLine = new Paint();
        paintLine.setColor(getResources().getColor(R.color.green));
        paintLine.setAntiAlias(true);

        boxLine = new Paint();
        boxLine.setColor(getResources().getColor(R.color.light_yellow));
        boxLine.setAntiAlias(true);

        mapLine = new Paint();
        mapLine.setColor(getResources().getColor(R.color.yellow));
        mapLine.setAntiAlias(true);

        //get the parent container
        FrameLayout gameScreen = findViewById(R.id.gameScreen);
        //instantiate the game part
        view = new View(this){
            //overwrite the game screen part with the auxiliary line
            @Override
            protected void onDraw(Canvas canvas){
                super.onDraw(canvas);

                //draw the pile up maps after adding each types of boxes
                for(int i = 0; i < maps.length; i++){
                    for(int j  = 0; j < maps[i].length; j++){
                        if(maps[i][j] == true){
                            canvas.drawRect(i * boxSize,j * boxSize, i * boxSize + boxSize, j * boxSize + boxSize, mapLine);
                        }
                    }
                }

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
        view.setBackgroundColor(getResources().getColor(R.color.greyGreen));
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

    //initialize the listening and add event to the button
    public void initListen(){
        findViewById(R.id.btn_left).setOnClickListener(this);
        findViewById(R.id.btn_right).setOnClickListener(this);
        findViewById(R.id.btn_rotate).setOnClickListener(this);
        findViewById(R.id.btn_down).setOnClickListener(this);
        findViewById(R.id.btn_quickDown).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
    }

    //click event
    @Override
    public void onClick(View v){
        switch (v.getId()){

            //left
            case R.id.btn_left:
                //Toast.makeText(this,"Clicked Left", Toast.LENGTH_SHORT).show();
                move(-1,0);    //decrease one unit of the x-axis and keep y-axis unchanged when move it to the left
                break;

            //right
            case R.id.btn_right:
                //Toast.makeText(this,"Clicked Right", Toast.LENGTH_SHORT).show();
                move(1,0);    //increase one unit of the x-axis and keep y-axis unchanged when move it to the right
                break;

            //up
            case R.id.btn_rotate:
                //Toast.makeText(this,"Clicked Up", Toast.LENGTH_SHORT).show();
                rotate();
                break;

            //down
            case R.id.btn_down:
                //Toast.makeText(this,"Clicked Down", Toast.LENGTH_SHORT).show();
                moveDown();
                break;

            //quick Downwards
            case R.id.btn_quickDown:
                while(true){
                    if(moveDown()){
                        // if move downwards successfully, then keep go downwards until reach the button
                    }else{
                        //if fails,then break directly
                        break;
                    }
                }

            //start
            case R.id.btn_start:
                //Toast.makeText(this,"Start", Toast.LENGTH_SHORT).show();
                break;

            //pause
            case R.id.btn_pause:
                //Toast.makeText(this,"Paused", Toast.LENGTH_SHORT).show();
                break;

        }

        // call the method to redraw the view after each move
        view.invalidate();
    }

    //Move Downwards and fulfill pile up
    public boolean moveDown(){
        // moving down
        if(move(0,1)){      //if moving success, no need to pile up
            return true;
        }else{                      //if moving fails, pile up
            for(int i = 0; i < boxes.length; i++){
                maps[boxes[i].x][boxes[i].y] = true;
            }
        }
        //After pile up, create new boxes
        initBoxes();
        return false;
    }
    //move
    public boolean move(int x, int y){
        // Log.e("Before: ",boxes[0].x + ":" + boxes[0].y);

        /*check the new x-axis and y-axis whether they are out of the screen
          if it is true; then return false directly;
          if all new boxes are not out of bound, then move the boxes to the new position and return true;
         */
        for (int i = 0; i < boxes.length; i++){
            if(outOfBound( boxes[i].x + x, boxes[i].y + y)) {
                return false;
            }
        }

        //iterate the boxes array and add each box with the offset
        for (int i = 0; i < boxes.length; i++){
            boxes[i].x += x;
            boxes[i].y += y;
        }
        //Log.e("After: ",boxes[0].x + ":" + boxes[0].y);
        return true;
    }

    //rotate
    public boolean rotate(){

        //if it is square tetromino, there is no need to rotate
        if(boxType == 0){
            return false;
        }

        /*check the new x-axis and y-axis whether they are out of the screen
          if it is true; then return false directly;
          if all new boxes are not out of bound, then rotate the boxes and return true;
         */
        for (int i = 0; i < boxes.length; i++){
            if(outOfBound( -boxes[i].y + boxes[0].y + boxes[0].x, boxes[i].x - boxes[0].x + boxes[0].y)) {
                return false;
            }
        }

        //iterate each boxes and make each of them rotate clockwise 90 degrees
        for(int i = 0; i < boxes.length; i++){

            /* use the Euclidean transformation of rotation in Cartesian coordination */
            int newX = -boxes[i].y + boxes[0].y + boxes[0].x;
            int newY = boxes[i].x - boxes[0].x + boxes[0].y;
            boxes[i].x = newX;
            boxes[i].y = newY;
        }
        return true;
    }

    /**
     * check boundary
     * check whether x and y is out of the bound
     *@param x   x-axis
     *@param y   y-axis
     * @return    true---out of bound;  false---not out of bound
     */
    public boolean outOfBound(int x, int y){
        if(x < 0 || y < 0 || x > maps.length - 1 || y > maps[0].length - 1 || maps[x][y] == true){
            return true;
        }
        return false;
    }
}
