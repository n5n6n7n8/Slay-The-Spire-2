import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Enemy {
    private int maxHealth;
    private int currentHealth;
    private boolean isDead;
    private String name;
    private ArrayList<CardType> immunity = new ArrayList<>();
    private ArrayList<CardType> weakness = new ArrayList<>();
    private Action nextAction;

    //status effect variables
    private int block = 0;
    private int poison = 0;
    private int soul = 0;
    private int strength = 0;
    private int critChance;
    private int critDmg;
    private int fragile = 0;

    //special effect variables (from cards)
    public int poisonChalice = 0;
    public int chargedCreeper = 0;
    public int boomerangCounter = 0;

    //some sort of actions list that says what moves the enemy can do

    //CONSTRUCTORS
    public Enemy(int maxH, String name){ //Enemy with max health and name
        maxHealth = maxH;
        currentHealth = maxH;
        this.name = name;
        immunity.add(CardType.NONE);
        weakness.add(CardType.NONE);
    }
    public Enemy(int lowerRange, int higherRange, String name){ //Enemy with random amount of max health with certain range
        maxHealth = (int) (Math.random()*(higherRange-lowerRange))+lowerRange;
        currentHealth = maxHealth;
        this.name = name;
        immunity.add(CardType.NONE);
        weakness.add(CardType.NONE);
    }
    public Enemy(int maxH, String name, ArrayList<CardType> i, ArrayList<CardType> w){ //Enemy with max health and name
        maxHealth = maxH;
        currentHealth = maxH;
        this.name = name;
        immunity = i;
        weakness = w;
    }
    public Enemy(int lowerRange, int higherRange, String name, ArrayList<CardType> i, ArrayList<CardType> w){ //Enemy with random amount of max health with certain range
        maxHealth = (int) (Math.random()*(higherRange-lowerRange))+lowerRange;
        currentHealth = maxHealth;
        this.name = name;
        immunity = i;
        weakness = w;
    }
    public Enemy(int lowerRange, int higherRange, String name, CardType i, CardType w){ //Enemy with random amount of max health with certain range
        maxHealth = (int) (Math.random()*(higherRange-lowerRange))+lowerRange;
        currentHealth = maxHealth;
        this.name = name;
        immunity.add(i);
        weakness.add(w);
    }

    public abstract Action generateAction();
    public void setAction(Action a){
        nextAction = a;
    }
    public Action getAction(){
        return nextAction;
    }
    public Icon getLabelOfAction(){
        System.out.println("GETTING LABEL OF ACTION");
        BufferedImage bImage = null;
        String directory = "";
        ActionType actionT = nextAction.actionType;
        if(actionT==ActionType.LOSE_HEALTH){
            directory = "src/CS StS art/battle/intents/attack.png";
        }
        else if(actionT==ActionType.GAIN_HEALTH){
            directory = "src/CS StS art/Hearlth_icon.png";
        }
        else if(actionT==ActionType.GAIN_BLOCK){
            directory = "src/CS StS art/Block_Icon.png";
        }
        else if(actionT==ActionType.GAIN_STRENGTH) {
            directory = "src/CS StS art/battle/intents/buff.png";
        }
        else if(actionT==ActionType.INFLICT_FRAIL||actionT==ActionType.INFLICT_WEAK||actionT==ActionType.INFLICT_POISON){
            directory = "src/CS StS art/battle/intents/debuff.png";
        }

        try { //https://www.w3schools.com/java/java_try_catch.asp
            bImage = ImageIO.read(new File(directory));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image newImage = bImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        return new ImageIcon(newImage);

    }
    public Action setAndGetAction(Action a){
        Action n = nextAction;
        nextAction = a;
        return n;
    }

    public void receiveAction(Action a){
        switch(a.actionType){
            case GAIN_HEALTH:
                gainHealth(a.value);
                break;
            case GAIN_BLOCK:
                gainBlock(a.value);//will add more once more enemies are coded
                break;
            case GAIN_STRENGTH:
                gainStrength(a.value);
                break;
            default:
        }
        if(a.isDual){
            switch(a.actionType2){
                case GAIN_HEALTH:
                    gainHealth(a.value2);
                    break;
                case GAIN_BLOCK:
                    gainBlock(a.value2);//will add more once more enemies are coded
                    break;
                case GAIN_STRENGTH:
                    gainStrength(a.value2);
                    break;
                default:
            }
        }
    }

    //CARD TYPE
    public ArrayList<CardType> getImmunity(){
        return immunity;
    }
    public ArrayList<CardType> getWeakness(){
        return weakness;
    }
    //MORE GETTERS AND SETTERS
    public void gainBlock(int i){
        block+=i;
        if(block<0){
            block=0;
        }
    }
    public void gainPoison(int i){
        poison+=i;
        poison = Math.max(0,poison);
    }
    public void gainSoul(int i){
        soul+=i;
        soul = Math.max(0,soul);
    }
    public void gainStrength(int i){
        strength+=i;
        strength = Math.max(0,strength);
    }
    public void gainCritChance(int i){
        critChance+=i;
        critChance = Math.max(0,critChance);
    }
    public void gainCritDmg(int i){
        critDmg+=i;
        critDmg = Math.max(0,critDmg);
    }
    public void gainFragile(int i){
        fragile+=i;
        fragile = Math.max(0,fragile);
    }
    public int getBlock() {
        return block;
    }
    public int getPoison() {
        return poison;
    }
    public int getSoul() {
        return soul;
    }
    public int getStrength() {
        return strength;
    }
    public int getCritChance() {
        return critChance;
    }
    public int getCritDmg() {
        return critDmg;
    }
    public int getFragile() {
        return fragile;
    }
    public String listStatuses(){
        String toReturn = " ";
//        if(poison==0&&soul==0&&strength==0&&fragile==0){
//            return toReturn + "NONE";
//        }
        if(poison>0){
            toReturn+= "(" + poison + ") Poison,";
        }
        if(fragile>0){
            toReturn+= "(" + fragile + ") Fragile,";
        }
        if(strength>0){
            toReturn+= "(" + strength + ") Strength,";
        }
        if(soul>0){
            toReturn+= "(" + soul + ") Soul,";
        }
        return toReturn.substring(0,toReturn.length()-1);
    }


    //HEALTH
    public int getMaxHealth(){
        return maxHealth;
    }
    public int getCurrentHealth(){
        return currentHealth;
    }
    public int loseHealth(int toLose){ //take into account player crit, status effects, and relics
        int healthLost = currentHealth;
        int x = toLose;
        x = (int) (x*(1+(0.15*fragile))); //fragile effects
        if(((int)(Math.random()*100))<12){ //crit effects
            x+=5;
        }
        x-=block; //block effect
        gainBlock(toLose*-1);
        if(x<0){
            x=0;
        }
        currentHealth-=x;
        if(currentHealth<=0){
            isDead = true;
            currentHealth=0;
        }
        return healthLost-currentHealth;
    }
    public void loseHealth(int toLose, CardType type){ //if the card is a special type, enemy can lose more health depending on that.
        if(immunity.contains(type)){
            loseHealth((int) (toLose*0.85));
        }
        else if(weakness.contains(type)){
            loseHealth((int) (toLose*1.15));
        }
    }
    public void loseHealthRaw(int toLose){
        currentHealth-=toLose;
        if(currentHealth<=0){
            isDead = true;
            currentHealth=0;
        }
    }
    public void gainHealth(int x){
        currentHealth+=x;
        if(currentHealth>0){
            isDead = true;
        }
        if(x>maxHealth){
            currentHealth = maxHealth;
        }
    }
    public String getName(){
        return name;
    }
    public boolean checkIfDead(){
        return isDead;
    }
    @Override
    public String toString(){
        return name + ". Health: " + currentHealth + "/" + maxHealth +", Immunity: " + immunity + ", Weakness: " + weakness;
    }

    //Turn methods

    public void nextTurn(){
        block = 0;
        loseHealthRaw(poison);
        if(poison>0){
            poison--;
        }
        if(fragile>0){
            fragile--;
        }
        if(poisonChalice>0){
            if(poisonChalice==1){
                gainPoison(10);
            }
            poisonChalice--;
        }
        if(boomerangCounter>0){
            boomerangCounter--;
        }
        if(chargedCreeper>0){
            if(chargedCreeper==1){
                loseHealth(15);
            }
            chargedCreeper--;
        }
        //poison decrease
        //block erase
        //relic stuff
    }
}

