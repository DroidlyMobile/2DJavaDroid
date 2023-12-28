package dnamobile.ca.a2dgamejavadroid;

import static dnamobile.ca.a2dgamejavadroid.Dpad.CENTER;
import static dnamobile.ca.a2dgamejavadroid.Dpad.DOWN;
import static dnamobile.ca.a2dgamejavadroid.Dpad.LEFT;
import static dnamobile.ca.a2dgamejavadroid.Dpad.RIGHT;
import static dnamobile.ca.a2dgamejavadroid.Dpad.UP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    Dpad dpad = new Dpad();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        fullscreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.gameLoop.stopLoop();
    }

    public void fullscreen(){
        View decorView = this.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | Window.FEATURE_NO_TITLE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        decorView.setSystemUiVisibility(uiOptions);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(gameView);
    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }*/

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {

        if (Dpad.isDpadDevice(event)) {
            int press = dpad.getDirectionPressed(event);
            gameView.buttonPressed = press;
            switch (press) {
                case LEFT:
                    // Do something for LEFT direction press
                    gameView.player.currentDirection = "left";
                    return false;
                case RIGHT:
                    // Do something for RIGHT direction press
                    gameView.player.currentDirection = "right";
                    return false;
                case UP:
                    // Do something for UP direction press
                    gameView.player.currentDirection = "up";
                    return false;
                case DOWN:
                    // Do something for UP direction press
                    gameView.player.currentDirection = "down";
                    return false;
                case CENTER:
                    // Do something for UP direction press
                    gameView.player.currentDirection = "bb";
                    return true;
            }
        }
        return super.onGenericMotionEvent(event);
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        gameView.player.currentDirection = "";
        return super.onKeyUp(keyCode, event);
    }

}