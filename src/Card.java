import java.awt.event.ActionListener;


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
    public Card(boolean n){
        title = "Null";
        description = "Does nothing";
        type = CardType.CURSE;
        cost = 100;
        action = e -> {

        };
        isNull = n;
    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = false;
        drawNum = 0;
        isStar = false;
    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, boolean e){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = e;
        drawNum = 0;
        isStar = false;
    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, int d){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = false;
        drawNum = d;
        isStar = false;
    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, boolean e, int d){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = e;
        drawNum = d;
        isStar = false;
    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, boolean e, int d, boolean willAdd){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = e;
        drawNum = d;
        this.willAdd = willAdd;
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
