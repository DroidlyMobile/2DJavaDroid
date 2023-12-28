package dnamobile.ca.a2dgamejavadroid.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import dnamobile.ca.a2dgamejavadroid.GameView;
import dnamobile.ca.a2dgamejavadroid.R;

public class Player extends EntityInfo{

    public Player(GameView gameView){
        this.gameView = gameView;
        setupPlayerInfo();
    }

    private void setupPlayerInfo() {
        posX = gameView.tileSize * 10;
        posY = gameView.tileSize *10;
        speed = 10;
        entityWidth = gameView.tileSize;
        entityHeight = gameView.tileSize;
        screenX = gameView.cameraWidth/2 - entityWidth/2;
        screenY = gameView.cameraHeight/2 - entityHeight/2;
        initializeSpriteSheet(R.drawable.blueboy_sheet);
        defaultImg = entityImg[0];
    }

    public void update(){
        checkDirection();
        updateAnimation();
    }
    public void checkDirection(){
        if (currentDirection.equals("right")){
            posX += speed;
        }else if (currentDirection.equals("left")){
            posX -= speed;
        }else if (currentDirection.equals("up")){
            posY -= speed;
        }else if (currentDirection.equals("down")){
            posY += speed;
        }
    }
    public void updateAnimation(){
        animCount ++;
        if (animCount > 12){
            if (animNum == 1){
                animNum = 2;
            }else if (animNum == 2){
                animNum = 1;
            }
            animCount = 0;
        }
        checkAnimationDirection();
    }

    private void checkAnimationDirection() {

        if (currentDirection.equals("down")){
            if (animNum == 1){
                defaultImg = entityImg[0];
            }
            if (animNum == 2){
                defaultImg = entityImg[1];
            }
        }
        if (currentDirection.equals("up")){
            if (animNum == 1){
                defaultImg = entityImg[2];
            }
            if (animNum == 2){
                defaultImg = entityImg[3];
            }
        }
        if (currentDirection.equals("right")){
            if (animNum == 1){
                defaultImg = entityImg[6];
            }
            if (animNum == 2){
                defaultImg = entityImg[7];
            }
        }
        if (currentDirection.equals("left")){
            if (animNum == 1){
                defaultImg = entityImg[4];
            }
            if (animNum == 2){
                defaultImg = entityImg[5];
            }
        }
    }

}
