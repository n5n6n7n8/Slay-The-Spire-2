import java.awt.event.ActionListener;
import java.util.ArrayList;
public class Enemy {
    private int maxHealth;
    private int currentHealth;
    private boolean isDead;
    private String name;
    private ArrayList<CardType> immunity = new ArrayList<>();
    private ArrayList<CardType> weakness = new ArrayList<>();
    private ActionListener action;

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
    public void addActionToEnemy(EnemyAction enemyAction){
        action = enemyAction.getAction();
    }
    public ActionListener getAction(){
        return action;
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
        String toReturn = "Status Effects: ";
        if(poison==0&&soul==0&&strength==0&&fragile==0){
            return toReturn + "NONE";
        }
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
            maxHealth=x;
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
        if(poison<0){
            poison--;
        }
        if(fragile<0){
            fragile--;
        }
        if(poisonChalice>0){
            if(poisonChalice==1){
                gainPoison(10);
            }
            poisonChalice--;
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
