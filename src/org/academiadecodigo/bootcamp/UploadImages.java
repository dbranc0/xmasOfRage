package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class UploadImages {

    //Load the background images
    public Picture loadImages(boolean isSecondBG){
        if(!isSecondBG) {
            return new Picture(0, 0, "background/background.gif");
        }else{
            return new Picture(1078,0,"background/background.gif");
        }
    }

    public Picture showCredits() {
        return new Picture(0, 0, "background/credits.png");
    }

}