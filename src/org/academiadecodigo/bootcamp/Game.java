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


        gameImages.loadImages();


    }

    public void start () throws InterruptedException{

        while(true){

            // TESTE
            Thread.sleep(200);

            //For the menu
            if(level==0){
                gameMenu.keyListener();
                level=gameMenu.getStartLevel();
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
