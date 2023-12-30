package dnamobile.ca.a2dgamejavadroid;

import static dnamobile.ca.a2dgamejavadroid.Dpad.DOWN;
import static dnamobile.ca.a2dgamejavadroid.Dpad.LEFT;
import static dnamobile.ca.a2dgamejavadroid.Dpad.RIGHT;
import static dnamobile.ca.a2dgamejavadroid.Dpad.UP;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import dnamobile.ca.a2dgamejavadroid.Entities.Player;
import dnamobile.ca.a2dgamejavadroid.World.TileManager;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    public GameLoop gameLoop;
    public Paint textPaint = new Paint();

    public int tileSize = 0;
    public int maxTilesX = 0;
    public int maxTilesY = 0;
    public int cameraWidth,cameraHeight = 0;
    public int buttonPressed = 0;
    public int buttonReleased = 0;
    public boolean checkbuttonpressed = false;

    public Player player;
    public TileManager tileManager;


    public GameView(Context context) {
        super(context);
        setupGameView();
    }


    public void update(){
        player.update();
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        tileManager.draw(canvas);
        player.draw(canvas);
        canvas.drawText(String.valueOf(buttonPressed) + " " + String.valueOf(buttonReleased),50,50,textPaint);
    }

    public void setupGameView(){
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameLoop = new GameLoop(this,surfaceHolder);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(24);
        tileSize = 100;
        cameraWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        cameraHeight = getContext().getResources().getDisplayMetrics().heightPixels + getNavbarHeight();
        maxTilesX = 50;
        maxTilesY = 50;
        player = new Player(this);
        tileManager = new TileManager(this);

    }





    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)){
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this,surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public int getNavbarHeight(){
        Resources resources = getContext().getResources();
        @SuppressLint("InternalInsetResource")
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

}
