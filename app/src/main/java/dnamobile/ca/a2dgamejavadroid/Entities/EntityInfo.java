package dnamobile.ca.a2dgamejavadroid.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import dnamobile.ca.a2dgamejavadroid.GameView;

public class EntityInfo {

    public GameView gameView;

    public int posX, posY = 0;
    public int screenX, screenY = 0;
    public int speed = 0;
    public int entityWidth,entityHeight = 0;
    public int animNum = 1;
    public int animCount = 0;

    public Bitmap defaultImg = null;
    public Bitmap[] entityImg = new Bitmap[100];

    public String currentDirection = "down";
    public String defaultDirection = "down";

    public boolean entityLeft,entityRight,entityUp,entityDown = false;
    public boolean entityMining = false;

    public void draw(Canvas canvas){
        canvas.drawBitmap(defaultImg,screenX,screenY,null);
    }

    public void initializeSpriteSheet(int drawable){
        Bitmap spritesheet1;
        int currentColumn = 0;
        int currentRow = 0;
        int numberOftiles = 0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        spritesheet1 = BitmapFactory.decodeResource(gameView.getResources(),
                drawable,
                bitmapOptions);
        int maxColumns = spritesheet1.getWidth()/16;
        int maxRows = spritesheet1.getHeight()/16;
        while (currentRow<maxRows){
            entityImg[numberOftiles] = Bitmap.createScaledBitmap
                    (Bitmap.createBitmap(spritesheet1,
                            currentColumn * 16,
                            currentRow * 16,
                            16,
                            16),entityWidth,
                    entityHeight,false);
            currentColumn ++;
            if (currentColumn == maxColumns){
                currentColumn = 0;
                currentRow ++;
            }
            numberOftiles ++;
        }
    }


}
