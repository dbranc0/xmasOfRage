package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements KeyboardHandler{

    private Picture menu;
    private int startLevel=0;

    //Calls the menu and create it in the constructor
    public Menu(){
        //MUDAR ISTO DEPOIS
        this.menu=new Picture(UploadImages.PADDING,UploadImages.PADDING,"background/frame_00_delay-0.04s.gif");
        menu.draw();

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

