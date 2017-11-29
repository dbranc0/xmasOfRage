package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements KeyboardHandler{

    private Picture menu;
    private Picture menu1;
    private int startLevel=0;
    private int roll=0;


    //Calls the menu and create it in the constructor
    public Menu(){
        //MUDAR ISTO DEPOIS
        this.menu=new Picture(0,UploadImages.PADDING,"background/java.png");
        this.menu1=new Picture(menu.getMaxX(),UploadImages.PADDING,"background/java1.png");

        menu.draw();
        menu1.draw();


    }

    public void imageRoll(){
        if(menu.getX() < 810 && menu.getMaxX() > 0){
            if(menu1.getMaxX() == 0){menu1.translate(960*2 - 10,0);}
            menu.translate(-10,0);
            menu1.translate(-10,0);
        }else if(menu1.getX() < 810 && menu1.getMaxX() > 0){
            if(menu.getMaxX() == 0){menu.translate(960*2 - 10,0);}
            menu1.translate(-10,0);
            menu.translate(-10,0);
        }

        System.out.println("Menu: " + menu.getX() + " - " + menu.getMaxX());
        System.out.println("Menu1: " + menu1.getX() + " - " + menu1.getMaxX());

    }


    //The key listner for the menu
    //Method to listen the keyboard
    public void keyListener(){

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent  menu =new KeyboardEvent();

        menu.setKey(KeyboardEvent.KEY_SPACE);
        menu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(menu);




    }


    //GETTER
    //To get the end of the menu
    public int getStartLevel(){
        return this.startLevel;
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent){

        if(startLevel==0){
            switch(keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_SPACE:
                    System.out.println("Hello Darkness");
                    this.startLevel=1;
                    Rectangle fade=new Rectangle(UploadImages.PADDING,UploadImages.PADDING,443,249);
                    fade.setColor(Color.DARK_GRAY);
                    fade.fill();
                    break;
            }
        }


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent){

    }


}

