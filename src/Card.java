import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Card {
    private String title, description;
    private CardType type;
    private int cost;
    private ActionListener action;
    private boolean canExhaust; //will dissapear after player plays it
    private int drawNum; //amount of cards to draw from draw pile if noted
    public boolean willAdd; //if the player will add some other kind of card to hand
    public boolean isStar; //if the card is a star card
    public boolean isNull;
    public String directory;
    public ImageIcon cardArt = new ImageIcon();
    public Card(boolean n, String dir){
        title = "Null";
        description = "Does nothing";
        type = CardType.CURSE;
        cost = 100;
        action = e -> {

        };
        isNull = n;
        directory = dir;
        generateArt();
    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, String dir){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = false;
        drawNum = 0;
        isStar = false;
        directory = dir;
        generateArt();
    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, boolean e, String dir){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = e;
        drawNum = 0;
        isStar = false;
        directory = dir;
        generateArt();

    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, int d, String dir){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = false;
        drawNum = d;
        isStar = false;
        directory = dir;
        generateArt();

    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, boolean e, int d, String dir){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = e;
        drawNum = d;
        isStar = false;
        directory = dir;
        generateArt();
    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, boolean e, int d, boolean willAdd, String dir){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = e;
        this.willAdd = willAdd;
        directory = dir;
        generateArt();
    }
    public void generateArt(){
        BufferedImage picture = null;
        try { //https://www.w3schools.com/java/java_try_catch.asp
            picture = ImageIO.read(new File(directory));
        } catch (IOException r) {
            r.printStackTrace();
        }
        Image finalPicture = picture.getScaledInstance(85, 85, Image.SCALE_DEFAULT);
        cardArt = new ImageIcon(finalPicture);
    }
    //Ideally we want to give the card the player and enemy object through a method and it does what it needs to do

    //
    //getter methods
    public String getTitle(){
        return title;
    }

    public void setDescription(String f){description = f;}

    //other accessibility methods
    public boolean equals(Card a){
        return title==a.title;
    }
    @Override
    public String toString(){
        return title + ", " + type + ", " + description + ". COST: " + cost + ".";
    }

    public ActionListener getAction(){
        return action;
    }
    public CardType getType(){
        return type;
    }
    public String getDescription() {
        return description;
    }
    public boolean getCanExhaust(){
        return canExhaust;
    }
    public int getCost(){
        return cost;
    }
    public int getDrawNum(){return drawNum;}
}
