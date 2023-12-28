package dnamobile.ca.a2dgamejavadroid.World;

import android.graphics.Canvas;

import dnamobile.ca.a2dgamejavadroid.GameView;
import dnamobile.ca.a2dgamejavadroid.R;

public class TileManager extends TileInfo{

    public TileInfo[] layer1 = new TileInfo[100];
    public TileManager(GameView gameView){
        this.gameView = gameView;
        tileLayer1XY = new int[gameView.maxTilesX][gameView.maxTilesY];
        tileWidth = gameView.tileSize;
        tileHeight = gameView.tileSize;
        initializeTileSheet(R.drawable.tilesheet_layer1);
        setupTileInfo();
    }

    public void draw(Canvas canvas){
        int tileCol = 0;
        int tileRow = 0;
        while (tileCol < gameView.maxTilesX && tileRow < gameView.maxTilesY){
            tileNum = tileLayer1XY[tileCol][tileRow];

            int tilePosX = tileCol * gameView.tileSize;//Sets the tile at the position X in the world times the scaled tilesize 160 in example
            int tilePosY = tileRow * gameView.tileSize;//Sets position Y times scaled tilesize
            int tileScreenX = tilePosX - gameView.player.posX + gameView.player.screenX;
            int tileScreenY = tilePosY - gameView.player.posY + gameView.player.screenY;

            if(tileScreenX > -gameView.tileSize
                    && tileScreenY > -gameView.tileSize
                    && tileScreenX < gameView.cameraWidth
                    && tileScreenY < gameView.cameraHeight){
                if (layer1[tileNum]!=null) {
                    if (layer1[tileNum].tileDefaultImg != null) {
                        canvas.drawBitmap(layer1[tileNum].tileDefaultImg,
                                tileScreenX, tileScreenY, null);
                    }
                }
            }
            tileCol ++;
            if (tileCol == gameView.maxTilesX){//Check if tileCol reaches the end in this case 100 tiles then resets back to 0 then increases rows
                tileCol = 0;
                tileRow++;
            }
        }

    }

    public void setupTileInfo(){
        layer1[0] = new TileInfo();
        layer1[0].tileDefaultImg = tileImgs[0];

    }
}
