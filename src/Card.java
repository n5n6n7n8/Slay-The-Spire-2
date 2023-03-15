import java.awt.event.ActionListener;


public class Card {
    private String title, description;
    private CardType type;
    private int cost;
    private ActionListener action;
    private boolean canExhaust;
    public Card(String ti, CardType ty, String desc, int c, ActionListener l){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = false;
    }
    public Card(String ti, CardType ty, String desc, int c, ActionListener l, boolean e){
        title = ti;
        type = ty;
        description = desc;
        cost = c;
        action = l;
        canExhaust = e;
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
}
