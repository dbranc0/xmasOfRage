package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Characters.*;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Music;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Sound;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.TinySound;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game implements KeyboardHandler {

    //This means that you're at the menu when level =0
    private int level = 0;
    private Level currentLevel;
    private Picture background;
    private Picture background2;
    private Player player;
    private Enemy enemies;
    private int enemiesOnScreen = 0;
    private int distance = 0;
    private Music song;
    private Sound sound;
    private AI ai;
    private Keyboard keyboard = new Keyboard(this);
    private KeyboardEvent controlLeft = new KeyboardEvent();
    private KeyboardEvent controlRight = new KeyboardEvent();
    private KeyboardEvent controlPunch = new KeyboardEvent();
    private KeyboardEvent controlKick = new KeyboardEvent();
    private KeyboardEvent controlLeftReleased = new KeyboardEvent();
    private KeyboardEvent controlRightReleased = new KeyboardEvent();
    private KeyboardEvent controlPunchReleased = new KeyboardEvent();
    private KeyboardEvent controlKickReleased = new KeyboardEvent();
    private Figure sprite;
    private int gameCounter;


    //Create the menu
    Menu gameMenu = new Menu();

    //Create the upload images
    UploadImages gameImages = new UploadImages();


    public void init() throws InterruptedException{
        gameCounter = 0;

        background = gameImages.loadImages(false);
        background2 = gameImages.loadImages(true);
        player = new Player();
        sprite= new Figure(CharacterType.PLAYER);
        TinySound.init();
        song = TinySound.loadMusic("music/menu.wav");
        sound=TinySound.loadSound("music/titlescreen.wav");
        ai = new AI();

        //Responsible for the screens
        gameMenu.createScreen(true);
        gameMenu.setLevel();
        //gameOver = gameMenu.createScreen(false);





        //Resets the level
        level = 0;
        enemiesOnScreen = 0;
        distance = 0;

        keyController();

        start();
    }

    public void start() throws InterruptedException {

        song.play(true);
        while (true) {
            if (gameCounter == 1001) {
                gameCounter = 0;
            }
            Thread.sleep(200);

            //For the menu
            if (level == 0) {
                gameMenu.keyListener();
                level = gameMenu.getStartLevel();
                Thread.sleep(1000);
                if (level == 1) {
                    song.unload();
                    sound.play();
                    Thread.sleep(4000);
                    background.draw();
                    background2.draw();


                    song = TinySound.loadMusic("music/level.wav");
                    song.play(true);
                    currentLevel = new Level();

                    //player stats
                    sprite.load("characters/player/right/standing/0.png");
                    sprite.draw();
                    player.changeFacing(Characters.Facing.RIGHT);
                    player.setStanding(sprite, gameCounter);

                }
            }

            if (level == 1) {


                //To see if the player is standing
                if (player.getState() == Characters.State.STANDING) {
                    if (this.gameCounter == 201) {
                        gameCounter = 0;

                    }

                    player.setStanding(sprite, gameCounter);
                    gameCounter++;


                }

                //Checks if the player is dead and opens the game over screen
                if (player.isDead()) {
                    sound=TinySound.loadSound("music/maledeath.wav");
                    sound.play();
                    song.stop();
                    Thread.sleep(500);
                    gameMenu.createScreen(false);
                    //gameOver.draw();
                    Thread.sleep(5000);
                    //clear();
                    break;

                }

                //Only calls this method when there is an enemy on the field
                if (enemiesOnScreen == 1) {
                    switch(ai.pickAction(player, enemies)){
                        case ATTACKING:
                            //System.out.println("ATTACK");
                            enemies.attack(player);
                            sound=TinySound.loadSound("music/punch.wav");
                            sound.play();
                            enemies.animate(enemies.getFrame(),gameCounter,AttackMove.ATTACK);
                            break;
                        case WALKING:
                            //System.out.println("WALKING");
                            enemies.move(gameCounter,enemies.getFrame());
                            break;
                    }


                    //For when the enemy dies
                    if (enemies.getHp() <= 0) {

                        sound=TinySound.loadSound("music/playerpain2.wav");
                        sound.play();
                        enemies.die();

                        if (gameCounter > 1000){
                            enemies.getFrame().loadFrame(1);
                        } else if (gameCounter > 500){
                            enemies.getFrame().loadFrame(0);
                        }
                        enemies.getFrame().delete();
                        distance++;
                        enemiesOnScreen = 0;
                    }

                    //For when the player dies
                    if (player.getHp() <= 0) {
                        player.die();
                    }

                }


                switch (distance) {
                    case 0:
                        battleArea();
                        break;
                    case 25:
                        battleArea();
                        break;
                    case 50:
                        battleArea();
                        break;
                    case 90:
                        battleArea();
                        break;
                    case 120:
                        bossBattle();
                        break;

                    case 130:
                        level = 2;
                        break;
                }
            }

            if (level == 2) {
                song.stop();
                gameImages.showCredits().draw();
                Thread.sleep(5000);
                break;

            }
            gameCounter++;
        }

        //When the player loses the game
        song.stop();
        song.unload();
        ai=null;
        enemies=null;
        player.getFrame().delete();
        player=null;

        init();

    }

    private void bossBattle() {
        song.stop();
        sound=TinySound.loadSound("music/santalaugh.wav");
        sound.play();
        song.unload();
        song = TinySound.loadMusic("music/boss.wav");
        song.play(true);
        if (sprite.getMaxX() >= 400 && enemiesOnScreen == 0) {
            //Create a new enemy
            Enemy enemy = EnemyFactory.createEnemy(true);
            enemies = enemy;
            enemy.getFrame().draw();

            enemiesOnScreen = 1;
        }
        if (enemies == null || enemies.isDead()) {
            sound=TinySound.loadSound("music/santadeath.wav");
            sound.play();
            enemiesOnScreen = 0;
            distance++;
        }
    }


    private void battleArea() throws InterruptedException {
        if (sprite.getMaxX() >= 400 && enemiesOnScreen == 0) {
            //Create a new enemy
            Enemy enemy = EnemyFactory.createEnemy(false);
            enemies = enemy;
            enemy.getFrame().draw();
            enemiesOnScreen = 1;
        }
        if (enemiesOnScreen == 1) {

            if (enemies.isDead()) {
                enemiesOnScreen = 0;
                distance ++;
            }
        }
    }

    public void keyController()  {


        controlRight.setKey(KeyboardEvent.KEY_RIGHT);
        controlLeft.setKey(KeyboardEvent.KEY_LEFT);
        controlPunch.setKey(KeyboardEvent.KEY_C);//Punch
        controlKick.setKey(KeyboardEvent.KEY_X);//Kick

        controlRightReleased.setKey(KeyboardEvent.KEY_RIGHT);
        controlLeftReleased.setKey(KeyboardEvent.KEY_LEFT);
        controlPunchReleased.setKey(KeyboardEvent.KEY_C);//Punch
        controlKickReleased.setKey(KeyboardEvent.KEY_X);//Kick

        controlLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        controlRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        controlPunch.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        controlKick.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        controlLeftReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        controlRightReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        controlPunchReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        controlKickReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(controlLeft);
        keyboard.addEventListener(controlRight);
        keyboard.addEventListener(controlPunch);
        keyboard.addEventListener(controlKick);
        keyboard.addEventListener(controlLeftReleased);
        keyboard.addEventListener(controlRightReleased);
        keyboard.addEventListener(controlPunchReleased);
        keyboard.addEventListener(controlKickReleased);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                player.changeFacing(Characters.Facing.RIGHT);
                if (gameCounter == 201) {
                    gameCounter = 0;
                }

                player.move(this.gameCounter, sprite);
                gameCounter++;

                //Scroll background
                if(enemiesOnScreen==0 && sprite.getMaxX()>=500){
                    if (background.getMaxX() > 500 || background2.getMaxX() > 500) {
                        //Method to roll the screen in loop, if it reached the limits

                        if (!currentLevel.imageRoll(background, background2)) {
                            background.translate(-10, 0);
                            background2.translate(-10, 0);

                        } else {
                            player.getFrame().translate(-20, 0);
                        }
                    }
                    distance++;
                }
                break;

            case KeyboardEvent.KEY_LEFT:

                player.changeFacing(Characters.Facing.LEFT);
                if (gameCounter == 201) {
                    gameCounter = 0;
                }

                player.move(this.gameCounter, sprite);
                gameCounter++;
                break;

            case KeyboardEvent.KEY_C: //Punch
                player.setState();
                if (gameCounter == 201) {
                    gameCounter = 0;
                }
                player.animate(sprite,gameCounter,AttackMove.PUNCH);
                if(enemiesOnScreen==1){
                    if (player.attack(enemies)) {
                        sound=TinySound.loadSound("music/punch.wav");
                        sound.play();
                        int damage = (int) Math.round(Math.random() * 40) + 20;
                        enemies.loseHP(damage);
                    }
                }
                break;

            case KeyboardEvent.KEY_X: //Kick
                player.setState();
                if (gameCounter == 201) {
                    gameCounter = 0;
                }
                player.animate(sprite,gameCounter,AttackMove.KICK);
                if(enemiesOnScreen==1){
                    if (player.attack(enemies)) {
                        int damage = (int) Math.round(Math.random() * 40) + 20;
                        enemies.loseHP(damage);
                    }
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
                player.setStanding(sprite, gameCounter);
    }

}

