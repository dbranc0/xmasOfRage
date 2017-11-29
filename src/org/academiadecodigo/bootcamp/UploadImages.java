package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class UploadImages {

    public static final int PADDING=0;
    private Picture [] background=new Picture[43];
    private Picture [] player;
    private Picture [] enemy;
    private Picture ground;

    //Load the background images
    public Picture [] loadImages(){

        for (int i=0;i<43;i++){

            Picture tempPicture;

            if(i<10){
               tempPicture = new Picture (PADDING,PADDING,"background/frame_0"+i+"_delay-0.04s.gif");
           }else {
                tempPicture = new Picture (PADDING,PADDING,"background/frame_"+i+"_delay-0.04s.gif");
           }

            background[i]=tempPicture;
        }

        return background;

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