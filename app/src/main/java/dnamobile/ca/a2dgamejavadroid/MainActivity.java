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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    Dpad dpad = new Dpad();
    public ArrayList<String> buttonlist = new ArrayList<>();//Checks what button is pressed sets to list so when corresponding button is released the update method functions porperly
    public ArrayList<String> buttonindexlist = new ArrayList<>();
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getRepeatCount() == 0) {
            gameView.buttonPressed = keyCode;

            //Check buttons when we press them then handle actions

            if (keyCode == 23 && !buttonlist.contains("A BUTTON")){//A
                buttonlist.add("A BUTTON");
                buttonindexlist.add(String.valueOf(keyCode));
            }else
            if (keyCode == 4){//B

            }else
            if (keyCode == 62){//Y

            }else
            if (keyCode == 67){//X

            }
            //IF DPAD
            if (keyCode == 21 || keyCode == 22 || keyCode == 19 || keyCode == 20) {
                buttonindexlist.clear();
                buttonlist.clear();
                if (keyCode == 21
                        && !buttonlist.contains("LEFT BUTTON")
                        && !buttonlist.contains("RIGHT BUTTON")
                        && !buttonlist.contains("UP BUTTON")
                        && !buttonlist.contains("DOWN BUTTON")) {
                    buttonlist.add("LEFT BUTTON");
                    buttonindexlist.add(String.valueOf(keyCode));
                    gameView.checkbuttonpressed = true;
                } else if (keyCode == 22
                        && !buttonlist.contains("RIGHT BUTTON")
                        && !buttonlist.contains("LEFT BUTTON")
                        && !buttonlist.contains("UP BUTTON")
                        && !buttonlist.contains("DOWN BUTTON")) {
                    buttonlist.add("RIGHT BUTTON");
                    buttonindexlist.add(String.valueOf(keyCode));
                    gameView.checkbuttonpressed = true;
                } else if (keyCode == 19
                        && !buttonlist.contains("UP BUTTON")
                        && !buttonlist.contains("LEFT BUTTON")
                        && !buttonlist.contains("RIGHT BUTTON")
                        && !buttonlist.contains("DOWN BUTTON")) {
                    buttonlist.add("UP BUTTON");
                    buttonindexlist.add(String.valueOf(keyCode));
                    gameView.checkbuttonpressed = true;
                } else if (keyCode == 20
                        && !buttonlist.contains("UP BUTTON")
                        && !buttonlist.contains("LEFT BUTTON")
                        && !buttonlist.contains("RIGHT BUTTON")
                        && !buttonlist.contains("DOWN BUTTON")) {
                    buttonlist.add("DOWN BUTTON");
                    buttonindexlist.add(String.valueOf(keyCode));
                    gameView.checkbuttonpressed = true;
                }

            }

            handleButtonsPressed();
            System.out.println("GET POOP " + buttonindexlist);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        gameView.buttonReleased = keyCode;
        handleAButtonReleased(keyCode);
        handleDPADReleased(keyCode);

        System.out.println("GET POOP " + buttonindexlist);

        return super.onKeyUp(keyCode, event);
    }

    private void handleButtonsPressed() {
        handleDPADPressed();
        handleAButtonPressed();
    }

    private void handleDPADPressed(){
        if (buttonlist.contains("LEFT BUTTON")) {
            gameView.checkbuttonpressed = true;
            gameView.player.entityLeft = true;
            gameView.player.entityRight = false;
            gameView.player.entityUp = false;
            gameView.player.entityDown = false;
        } else if (buttonlist.contains("RIGHT BUTTON")) {
            gameView.checkbuttonpressed = true;
            gameView.player.entityRight = true;
            gameView.player.entityLeft = false;
            gameView.player.entityUp = false;
            gameView.player.entityDown = false;
        } else if (buttonlist.contains("UP BUTTON")) {
            gameView.checkbuttonpressed = true;
            gameView. player.entityUp = true;
            gameView.player.entityRight = false;
            gameView.player.entityLeft = false;
            gameView.player.entityDown = false;
        } else if (buttonlist.contains("DOWN BUTTON")) {
            gameView.checkbuttonpressed = true;
            gameView.player.entityDown = true;
            gameView.player.entityRight = false;
            gameView.player.entityLeft = false;
            gameView.player.entityUp = false;
        } else {
            gameView.checkbuttonpressed = false;
            gameView.player.entityRight = false;
            gameView.player.entityLeft = false;
            gameView.player.entityUp = false;
            gameView.player.entityDown = false;
        }
    }
    private void handleDPADReleased(int keyCode){
        if (keyCode == 21 || keyCode == 22 || keyCode == 19 || keyCode == 20) {
            if (buttonindexlist.contains(String.valueOf(keyCode))) {
                buttonlist.remove(buttonindexlist.indexOf(String.valueOf(keyCode)));
                buttonindexlist.remove(String.valueOf(keyCode));
                gameView.player.entityRight = false;
                gameView.player.entityLeft = false;
                gameView.player.entityUp = false;
                gameView.player.entityDown = false;
                gameView.checkbuttonpressed = false;
            }
        }
    }

    private void handleAButtonPressed(){
        if (buttonlist.contains("A BUTTON")) {
            if (!gameView.player.entityMining) {//Mining function
                gameView.checkbuttonpressed = true;
                gameView.player.entityMining = true;
                gameView.player.animCount = 0;
                gameView.player.animNum = 1;
                gameView.player.entityRight = false;
                gameView.player.entityLeft = false;
                gameView.player.entityUp = false;
                gameView.player.entityDown = false;
            }
        }
    }
    private void handleAButtonReleased(int keyCode){
        if (keyCode == 23 && buttonindexlist.contains(String.valueOf(keyCode))){
            buttonlist.remove(buttonindexlist.indexOf(String.valueOf(keyCode)));
            buttonindexlist.remove(buttonindexlist.indexOf(String.valueOf(keyCode)));
            gameView.player.entityMining = false;
            //Player will continue to walk if the gamer is holding down directional buttons
            if (gameView.player.defaultDirection.equals("right")
                    && buttonlist.contains("RIGHT BUTTON")){
                gameView.player.entityRight = true;
                gameView.checkbuttonpressed = true;
            }
            if (gameView.player.defaultDirection.equals("left")
                    && buttonlist.contains("LEFT BUTTON")){
                gameView.player.entityLeft = true;
                gameView.checkbuttonpressed = true;
            }
            if (gameView.player.defaultDirection.equals("up")
                    && buttonlist.contains("UP BUTTON")){
                gameView.player.entityUp = true;
                gameView.checkbuttonpressed = true;
            }
            if (gameView.player.defaultDirection.equals("down")
                    && buttonlist.contains("DOWN BUTTON")){
                gameView.player.entityDown = true;
                gameView.checkbuttonpressed = true;
            }
        }
    }
}