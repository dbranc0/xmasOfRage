package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class UploadImages {

    public static final int PADDING=10;
    private Picture [] background;
    private Picture [] player;
    private Picture [] enemy;
    private Picture ground;

    //Load the background images
    public void loadImages(){

        for (int i=0;i<43;i++){
           if(i<9){
               background [i] = new Picture (PADDING,PADDING,"background/frame_0"+i+"_delay-0.04s.gif");
           }else if(i<19){
               background [i] = new Picture (PADDING,PADDING,"background/frame_1"+i+"_delay-0.04s.gif");
           }else if (i<29){
               background [i] = new Picture (PADDING,PADDING,"background/frame_2"+i+"_delay-0.04s.gif");
           }else if (i<39){
               background [i] = new Picture (PADDING,PADDING,"background/frame_3"+i+"_delay-0.04s.gif");
           }else{
               background [i] = new Picture (PADDING,PADDING,"background/frame_4"+i+"_delay-0.04s.gif");
           }


        }

        //Load the picture for the moving ground
        ground=new Picture(0,0,"moving_ground/moving_ground.png");


    }


    //Load the player sprites
    public Picture [] loadPlayer(){

        //Loop to stores the images
        for (int i=0; i<player.length;i++){
            player[i]=new Picture(0,0,"player/0"+i+"-player.png");
        }

        return this.player;
    }

    //Load the enemies sprites (TESTE, MUDAR )
    /*
    public Picture [] loadEnemies(EnemyType newEnemy){

        //Loop to stores the images
        for (int i =0;i<enemy.length;i++){
            enemy[i]=new Picture(0,0,);
        }

        return this.enemy;

    }

*/

    //GETTER
    //Gets the background image
    public Picture [] getBackground(){
        return this.background;
    }

}