package dnamobile.ca.a2dgamejavadroid.World;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dnamobile.ca.a2dgamejavadroid.GameView;

public class TileInfo {

    public GameView gameView;
    public boolean collision = false;
    public Bitmap tileDefaultImg = null;
    public Bitmap[] tileImgs = new Bitmap[100];
    public int tileWidth, tileHeight = 0;
    public int tileNum = 0;
    public int tileLayer1XY[][];

    public void initializeTileSheet(int drawable){
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
            tileImgs[numberOftiles] = Bitmap.createScaledBitmap
                    (Bitmap.createBitmap(spritesheet1,
                                    currentColumn * 16,
                                    currentRow * 16,
                                    16,
                                    16),tileWidth,
                            tileHeight,false);
            currentColumn ++;
            if (currentColumn == maxColumns){
                currentColumn = 0;
                currentRow ++;
            }
            numberOftiles ++;
        }
    }
}
