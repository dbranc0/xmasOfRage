package org.academiadecodigo.bootcamp;

public class Game {

    //This means that you're at the menu when level =0
    private int level=0;
    private UploadImages background;

    //Create the menu
    Menu gameMenu=new Menu();

    //Create the upload images
    UploadImages gameImages=new UploadImages();



    public void init(){


        //for(int i=0;i<gameImages.getBackground().length;i++){
          //  gameImages.getBackground()[i].draw();
        //}


    }

    public void start () throws InterruptedException{

        while(true){

            // TESTE
            Thread.sleep(30);

            //For the menu
            if(level==0){
                gameMenu.keyListener();
                level=gameMenu.getStartLevel();
                gameMenu.imageRoll();
            }

            if(level==1){
                System.out.println("Level 1");
                showBackground();



            }


        }


    }

    public void showBackground(){
        for(int i=0;i<gameImages.getBackground().length;i++){
            gameImages.getBackground()[i].draw();
        }
    }

}
