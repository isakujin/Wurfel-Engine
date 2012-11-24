package Game.Blocks;

import Game.Gameplay;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.Log;

/**
 * A Block is a wonderfull piece of information and a geometrical object.
 * @author Benedikt
 */
public class Block {
    private final int id;
    
    /**
        * How much <b>h</b>ealth <b>p</b>oints has the block?
        * When it is 0 it get's destroyed.
        */
    private int hp = 100;
    /**
        * the value which can be used for storing information about a sub version of the object
        */
    private int value = 0;
    /**
       * The Name of the Block.
       */
    public final String name;
    /**
     * Can light travel throug block?
     */
    private boolean transparent;

    /**
        * Is this Block an obstacle or can you pass through?
        */
    private boolean obstacle;

    /**
       * width of the image
       */
    public static final int width = 160;
    /**
       *height of the image 
       */
    public static final int height = 160;
    
    /**
     * The size the block is rendered at.
     */
    public static int displWidth;
    /**
     * The height the block is rendered at.
     */
    public static int displHeight;
    
    /**
     * How much bigger is the width than the hight (block height not image height) of a block?
     */
    public static final float aspectRatio;
    /**
     * The X positon of the Block sprite
     */
    public int[] spriteX = new int[9];
    /**
     * The Y position of the Block sprite
     */
    public int[] spriteY = new int[9];
    
    /**
     * Has the positions of the sprites for rendering with sides
     * 1. Dimension id
     * 2. Dimension: value
     * 3. Dimension: Side
     * 4. Dimension: X- or Y-coordinate
     */
    public static final int[][][][] SidesSprites = new int[99][9][3][2];
    
    private int lightlevel = 50;
    /**
     * The offset of the image in X direction
     */
    private int offsetX = 0;
    /**
     * The offset of the image in Y direction
     */
    private int offsetY = 0;
    
    /**
     * The sprite image which contains every block image
     */
    public static SpriteSheet Blocksheet;
    
    /**
     * Changes the order the block is rendered. When renderorder = 1 the Block is drawn in front of the right block. When it is -1 it is draw behind the left block. 0 is default.
     */
    public int renderorder = 0;
    
    /**
     * Render top side?
     */
    public boolean renderTop = false;
    /**
     * Render Left Side?
     */
    public boolean renderLeft = false;
    /**
     * Render Right Side
     */
    public boolean renderRight = false;
    private boolean visible;
    
    static {
        aspectRatio = width/(height/2);
        Log.debug("Aspect ratio of blocks: "+ Float.toString(aspectRatio));
        SidesSprites[1][0][0][0] = 3;
        SidesSprites[1][0][0][1] = 0;
        SidesSprites[1][0][1][0] = 4;
        SidesSprites[1][0][1][1] = 0;
        SidesSprites[1][0][2][0] = 5;
        SidesSprites[1][0][2][1] = 0;
        SidesSprites[2][0][0][0] = 0;
        SidesSprites[2][0][0][1] = 1;
        SidesSprites[2][0][1][0] = 1;
        SidesSprites[2][0][1][1] = 1;
        SidesSprites[2][0][2][0] = 2;
        SidesSprites[2][0][2][1] = 1;
        //asphalt
        SidesSprites[4][0][0][0] = 1;
        SidesSprites[4][0][0][1] = 3;
        SidesSprites[4][0][1][0] = 2;
        SidesSprites[4][0][1][1] = 3;
        SidesSprites[4][0][2][0] = 3;
        SidesSprites[4][0][2][1] = 3;
        //concrete
        SidesSprites[7][0][0][0] = 4;
        SidesSprites[7][0][0][1] = 4;
        SidesSprites[7][0][1][0] = 5;
        SidesSprites[7][0][1][1] = 4;
        SidesSprites[7][0][2][0] = 0;
        SidesSprites[7][0][2][1] = 5;
        SidesSprites[8][0][0][0] = 2;
        SidesSprites[8][0][0][1] = 5;
        SidesSprites[8][0][1][0] = 3;
        SidesSprites[8][0][1][1] = 5;
        SidesSprites[8][0][2][0] = 0;
        SidesSprites[8][0][2][1] = 6;
        SidesSprites[8][1][0][0] = 1;
        SidesSprites[8][1][0][1] = 5;
        SidesSprites[8][1][1][0] = 5;
        SidesSprites[8][1][1][1] = 5;
        SidesSprites[8][1][2][0] = 2;
        SidesSprites[8][1][2][1] = 6;
        SidesSprites[8][2][0][0] = 5;
        SidesSprites[8][2][0][1] = 0;
        SidesSprites[8][2][1][0] = 4;
        SidesSprites[8][2][1][1] = 5;
        SidesSprites[8][2][2][0] = 1;
        SidesSprites[8][2][2][1] = 6;
        SidesSprites[9][0][0][0] = 3;
        SidesSprites[9][0][0][1] = 6;
        SidesSprites[9][0][1][0] = 4;
        SidesSprites[9][0][1][1] = 6;
        SidesSprites[9][0][2][0] = 5;
        SidesSprites[9][0][2][1] = 6;
        SidesSprites[40][0][0][0] = 3;
        SidesSprites[40][0][0][1] = 1;
        SidesSprites[40][0][1][0] = 4;
        SidesSprites[40][0][1][1] = 1;
        SidesSprites[40][0][2][0] = 5;
        SidesSprites[40][0][2][1] = 1;
        SidesSprites[40][1][0][0] = 3;
        SidesSprites[40][1][0][1] = 1;
        SidesSprites[40][1][1][0] = 4;
        SidesSprites[40][1][1][1] = 1;
        SidesSprites[40][1][2][0] = 5;
        SidesSprites[40][1][2][1] = 1;
        
        //pack-u-like
//        SidesSprites[0][0][0] = "0-1.png";
//        SidesSprites[0][0][1]= "0-1.png";
//        SidesSprites[0][0][2]= "0-2.png";
//        SidesSprites[1][0][0] = "1-0.png";
//        SidesSprites[1][0][1]= "1-1.png";
//        SidesSprites[1][0][2] = "1-2.png";
//        SidesSprites[2][0][0] = "2-0.png";
//        SidesSprites[2][0][1] = "2-1.png";
//        SidesSprites[2][0][2] = "2-2.png";
//        SidesSprites[4][0][0] = "4-0.png";
//        SidesSprites[4][0][1] = "4-1.png";
//        SidesSprites[4][0][2] = "4-2.png";
//        SidesSprites[7][0][0] = "7-0.png";
//        SidesSprites[7][0][1] = "7-1.png";
//        SidesSprites[7][0][2] = "7-2.png";
//        SidesSprites[8][0][0] = "8-0.png";
//        SidesSprites[8][0][1] = "8-1-0.png";
//        SidesSprites[8][0][2] = "8-2-0.png";
//        SidesSprites[8][1][0] = "8-1-1.png";
//        SidesSprites[8][1][1] = "8-1-1.png";
//        SidesSprites[8][1][2] = "8-1-2.png";
//        SidesSprites[8][2][0] = "8-2-0.png";
//        SidesSprites[8][2][1] = "8-2-1.png";
//        SidesSprites[8][2][2] = "8-2-2.png";
//        SidesSprites[9][0][0] = "9-0.png";
//        SidesSprites[9][0][1] = "9-1.png";
//        SidesSprites[9][0][2] = "9-2.png";
//        SidesSprites[40][0][0] = "20-0.png";
//        SidesSprites[40][0][1] = "20-1.png";
//        SidesSprites[40][0][2] = "20-2.png";
//        SidesSprites[40][1][0] = "20-0.png";
//        SidesSprites[40][1][1] = "20-1.png";
//        SidesSprites[40][1][2] = "20-2.png";
    }

    /**
     * Creates an air block. 
     */ 
    public Block(){
        this(0,0);
    }
    
    /**
     * Creates a block (id) with value 0. 
     *  @param id 
     */ 
    public Block(int id){
        this(id,0);
    }    
    
    /**
     * Creates a block (id) with value (value).
     * @param id 
     * @param value 
     */    
    public Block(int id, int value){
        this.id = id;
        this.value = value;
        
        //define the default SideSprites
        switch (id){
            case 0: name = "air";
                    transparent = true;
                    obstacle = false;
                    break;
            case 1: name = "gras";
                    transparent = false;
                    obstacle = true;
                    spriteX[0]=0;
                    spriteY[0]=1;
                    break;
            case 2: name = "dirt";
                    transparent = false;
                    obstacle = true;
                    spriteX[0]=0;
                    spriteY[0]=2;
                    break;
            case 3: name = "stone";
                    transparent = false;
                    obstacle = true;
                    spriteX[0]=1;
                    spriteY[0]=1;
                    break;
            case 4: name = "asphalt";
                    transparent = false;
                    obstacle = true;
                    spriteX[0]=1;
                    spriteY[0]=2;
                    break;
            case 5: name = "cobblestone";
                    transparent = false;
                    obstacle = true;
                    spriteX[0]=2;
                    spriteY[0]=1;
                    break;
            case 6: name = "pavement";
                    transparent = false;
                    obstacle = true;
                    spriteX[0]=2;
                    spriteY[0]=2;
                    break;
            case 7: name = "concrete";
                    transparent = false;
                    obstacle = true;
                    spriteX[0]=3;
                    spriteY[0]=0;
                    break;
            case 8: name = "sand";
                    transparent = false;
                    obstacle = true;
                    spriteX[0]=3;
                    spriteY[0]=2;
                    break;      
            case 9: name = "water";
                    transparent = false;
                    obstacle = false;
                    break;    
            case 20:name = "red brick wall";
                    transparent = false;
                    obstacle = true;
                    spriteX[0]=1;
                    spriteY[0]=0;
                    break;
            case 40:name = "player";
                    transparent = true;
                    obstacle = false;
                    spriteX[0]=2;
                    spriteY[0]=0;
                    break;
            case 50:name = "strewbed";
                    transparent = true;
                    obstacle = false;
                    break;
            case 70:name = "campfire";
                    transparent = true;
                    obstacle = false;
                    spriteX[0]=4;
                    spriteY[0]=0;
                    break;
            default:name = "undefined";
                    transparent = true;
                    obstacle = true;
                    spriteX[0]=4;
                    spriteY[0]=0;
                    break; 
        }
    }
    

    
    /**
     * returns the id of a block
     * @return getId
     */
    public int getId(){
        return this.id;
    }
    
    
    /**
     * 
     * @return
     */
    public int getOffsetX(){
        return offsetX;
    }
    
    /**
     * 
     * @return
     */
    public int getOffsetY(){
        return offsetY;
    }
    
    /**
     * 
     * @param x
     * @param y
     */
    public void setOffset(int x, int y){
       offsetX = x;
       offsetY = y;
       if (offsetY > 0)
            renderorder = 1;
       else if (offsetX < 0 && offsetY < 0)
                renderorder = -1;
            else renderorder = 0;
    }
    
    /**
     * 
     * @param side Which side? (0 - 2)
     * @return the X-Coodinate of the Sprite
     */
    public int getSideSpritePosX(int side){
        return SidesSprites[id][value][side][0];        
    }
    
    /**
     * 
     * @param side Which side? (0 - 2)
     * @return the Y-Coodinate of the Sprite
     */
    public int getSideSpritePosY(int side){
        return SidesSprites[id][value][side][1];        
    }
    
    /**
     *  
     * @param side Which side? (0 - 2)
     * @return a image ot the side
     */
    public Image getSideSprite(int side){
        return Blocksheet.getSubImage(SidesSprites[id][value][side][0], SidesSprites[id][value][side][1]);
    }
    


    /**
     * 
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    /**
     * 
     * @return
     */
    public boolean isVisible(){
        return visible;
    }
    
    /**
     * 
     * @return
     */
    public boolean isObstacle() {
        return obstacle;
    }

    /**
     * 
     * @param obstacle
     */
    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }
    
    /**
     * 
     * @return
     */
    public boolean isTransparent() {
        return transparent;
    }

    /**
     * 
     * @param transparent
     */
    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    /**
     * 
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * 
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

        /** How bright is the block?
     * The lightlevel is a number between 0 and 100. 100 is full bright. 0 is black.
     * @return 
     */
    public int getLightlevel() {
        return lightlevel;
    }

        /** Set the brightness of the Block.
     * The lightlevel is a number between 0 and 100. 100 is full bright. 0 is black.
     * @param lightlevel 
     */
    public void setLightlevel(int lightlevel) {
        this.lightlevel = lightlevel;
    }
    
    
    
    /**
     * 
     * @param x
     * @param y
     * @param z
     */
    public void renderblock(int x,int y, int z) {
        //draw every block except air
        if (id != 0){
            //System.out.println("X: "+x+" Y:"+y+" Z: "+z);
            //Block renderBlock = Controller.map.data[x][y][z]; 
            
            if (Gameplay.controller.rendermethod){ 
                if (renderTop) renderSide(x,y,z, 1);
                if (renderLeft) renderSide(x,y,z, 0);
                if (renderRight) renderSide(x,y,z, 2);

            } else {
                Image temp = Block.Blocksheet.getSubImage(spriteX[0], spriteY[0]);

                //calc  brightness
                float brightness = lightlevel / 100f;
                //System.out.println("Lightlevel " + Controller.map.data[x][y][z].lightlevel + "-> "+lightlevel);
                
                //or paint whole block with :
                //int brightness = renderBlock.lightlevel * 255 / 100;
                //new Color(brightness,brightness,brightness).bind(); 
                
                temp.setColor(0, brightness,brightness,brightness);
                temp.setColor(1, brightness,brightness, brightness);

                brightness -= .1f;
                //System.out.println(lightlevel);

                temp.setColor(2, brightness, brightness, brightness);
                temp.setColor(3, brightness, brightness, brightness);
                
                temp.drawEmbedded(
                    (int) (Gameplay.view.camera.getZoom()*Gameplay.view.camera.x)
                    + x*Block.displWidth
                    + (y%2) * (int) (Block.displWidth/2)
                    + getOffsetX()
                    ,
                    (int) (Gameplay.view.camera.getZoom()*Gameplay.view.camera.y/2)
                    + y*Block.displHeight/4
                    - z*Block.displHeight/2
                    + getOffsetY() * (1/Block.aspectRatio)
                );
                
//                Block.Blocksheet.renderInUse(
//                    (int) (zoom*Controller.map.posX) + x*Block.displWidth + (y%2) * (int) (Block.displWidth/2) + renderBlock.getOffsetX(),
//                    (int) (zoom*Controller.map.posY / 2) + y*Block.displHeight/4 - z*Block.displHeight/2 + renderBlock.getOffsetY(),
//                    renderBlock.spritex,
//                    renderBlock.spritey
//                );
            }
        }
    }
    /**
     * 
     * @param x
     * @param y
     * @param z
     * @param sidenumb The number of the side. 0 left, 1 top 2, right
     * @param renderBlock The block which gets rendered
     */
    private void renderSide(int x, int y, int z,int sidenumb){
        Image sideimage = getSideSprite(sidenumb);
        
        if (Gameplay.controller.goodgraphics){
            if (sidenumb == 0){
                int brightness = lightlevel * 255 / 100;
                new Color(brightness,brightness,brightness).bind();
            } else {
                Color.black.bind();
            }
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MULT);
        }
        
        //calc  brightness
        float brightness = lightlevel / 50f;
                
        sideimage.setColor(0, brightness,brightness,brightness);
        sideimage.setColor(1, brightness,brightness, brightness);

        if (sidenumb!=1) brightness -= .3f;

        sideimage.setColor(2, brightness, brightness, brightness);
        sideimage.setColor(3, brightness, brightness, brightness);
        
        sideimage.drawEmbedded(
            - (int) (Gameplay.view.camera.getZoom() * Gameplay.view.camera.x)
            + x*Block.displWidth
            + (y%2) * (int) (Block.displWidth/2)
            + Gameplay.view.camera.getZoom() * getOffsetX()
            ,            
            - (int) (Gameplay.view.camera.getZoom() * Gameplay.view.camera.y)
            + y*Block.displHeight/4
            - z*Block.displHeight/2
            + ( sidenumb == 1 ? -Block.displHeight/4:0)//the top is drawn /4 Blocks higher
            + Gameplay.view.camera.getZoom() * getOffsetY() * (1/Block.aspectRatio)
        );
    }
        
            /**
     * creates the new sprite image at a specific zoom factor. Also calculates displWidth and displHeight which change with zooming.
     * @param zoom the zoom factor of the new image
     */
    public static void reloadSprites(float zoom) {
        try {
            if (Gameplay.controller.rendermethod){
                SpriteSheet srcBlockSheet = new SpriteSheet("Game/Blockimages/SideSprite.png", width, (int) (height*0.75));
            
                Image scaledBlockSheet = srcBlockSheet.getScaledCopy(zoom);
                //int spacing = ((scaledBlockSheet.getWidth()) /(int)(srcBlockSheet.getWidth()/width) - (int) (width*zoom)) ; // calculate spacing by dividing by amount of blocks in a row and later gettin the rest
                //int spacing = (int) (4*zoom);
                //displWidth = (int) ((scaledBlockSheet.getWidth()+4)/5 - spacing);
                displWidth = (int) (width*zoom);
                displHeight = (int) (height*zoom);

                //GameplayState.iglog.add("Spacing:"+spacing);
                Gameplay.iglog.add("BlockWidth"+displWidth);

                Blocksheet = new SpriteSheet(
                    scaledBlockSheet,
                    displWidth,
                    (int) (displHeight*0.75)
                );
            } else {
                SpriteSheet srcBlockSheet = new SpriteSheet("Game/Blockimages/Blocksprite.png", width, height, 4);
            
                Image scaledBlockSheet = srcBlockSheet.getScaledCopy(zoom);
                int spacing = ((scaledBlockSheet.getWidth()) /5 - (int) (width*zoom)) ; // divide by amount of blocks in a row
                //int spacing = (int) (4*zoom);
                //displWidth = (int) ((scaledBlockSheet.getWidth()+4)/5 - spacing);
                displWidth = (int) (width*zoom);
                displHeight = (int) (height*zoom);

                Gameplay.iglog.add("Spacing:"+spacing);
                Gameplay.iglog.add("BlockWidth"+displWidth);

                Blocksheet = new SpriteSheet(
                    scaledBlockSheet,
                    displWidth,
                    displHeight,
                    spacing
                );
            }
        } catch (SlickException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
