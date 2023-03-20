import java.util.*;
public class Player {
    private ArrayList<Card> trueDeck;
    public ArrayList<Card> hand;
    public ArrayList<Card> drawPile;
    public ArrayList<Card> discardPile;
    private ArrayList<Integer> trueDeckIndexes = new ArrayList<>();

    private int maxHealth;
    private int currentHealth;
    private boolean isDead;
    private CardType immunity;
    private CardType weakness;

    private int maxEnergy;
    private int energy;

    private int drawSize;

    private int gold;


    //Status effects
    private int block;
    private int poison;
    private int soul;
    private int soulChance;
    private int strength;
    private int critChance;
    private int critDmg;
    private int fragile;

    //special effects from cards
    public int turnNumber = 1;
    public boolean willKeepBlock;
    public int boomerangCounter = 0;
    public int infiniteGunDMG = 5;
    public int pileOShields = 4;
    public boolean shepardTone;
    public int misery = 0;


    CardList cardEncyclopedia;

    //Constructors
    public Player(Enemy e){
        trueDeck = new ArrayList<Card>(); //will change
        //https://howtodoinjava.com/java/collections/arraylist/arraylist-clone-deep-copy/
        discardPile = new ArrayList<>();
        hand = new ArrayList<>();
        cardEncyclopedia = new CardList(this, e);
        maxHealth = 60;
        currentHealth = 54;
        immunity = CardType.NONE;
        weakness = CardType.NONE;

        willKeepBlock = false;
        shepardTone = false;


        maxEnergy = 3;
        energy = maxEnergy;

        drawSize = 5;

        gold = 0;

        block = 0;
        poison = 0;
        soul = 0;
        soulChance = 14;
        strength = 0;
        critChance = 12;
        critDmg = 0;
        fragile = 0;
    }
    //Health Methods
    public int getCurrentHealth(){
        return currentHealth;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void loseHealth(int toLose){
        int x = toLose;
        x = (int) (x*(1+(0.20*fragile)));
        //what if enemy has strength?
        x-=block;
        if(x<0){
            x=0;
        }
        if(getSoul()<0){
            x+=(getSoul()/2);
        }
        currentHealth-=x;
        if(currentHealth<0){
            isDead = true;
            currentHealth=0;
        }
    }
    public void loseHealth(int toLose, CardType type){
        if(type==immunity){
            loseHealth((int) (toLose*0.75));
        }
        else if(type==weakness){
            loseHealth((int) (toLose*1.25));
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
        if(currentHealth<0){
            isDead = true;
        }
        if(currentHealth>maxHealth){
            maxHealth=x;
        }
    }
    public void gainMaxHealth(int x){
        maxHealth+=x;
    }
    public boolean checkIfDead(){
        return isDead;
    }

    public void gainGold(int i){
        gold+=i;
    }
    public int getGold(){
        return gold;
    }

    public void receiveAction(Action a){
        switch(a.actionType){
            case LOSE_HEALTH:
                loseHealth(a.value);
                break;
            case INFLICT_POISON:
                gainPoison(a.value);
                break;
            default:
                break;
        }
        if(a.isDual){
            switch(a.actionType2){
                case LOSE_HEALTH:
                    loseHealth(a.value2);
                    break;
                case INFLICT_POISON:
                    gainPoison(a.value2);
                    break;
                default:
                    break;
            }
        }
    }

    //Update each turn method
    public void nextTurn() {
        int s = hand.size();
        for (int i = 0; i < s; i++) {
            System.out.println("ADDING HAND tO DISCARD " + hand.size());
            discardPile.add(hand.remove(0));
        }
        System.out.println("Hand size: " + hand.size());
        drawCards();
        //recieve enemy action
        //calculate what to do
        turnNumber++;
        energy = maxEnergy;
        if(!willKeepBlock){
            block = 0;
        }
        else{
            willKeepBlock = false;
        }
        loseHealthRaw(getPoison());
        if(poison<0){
            poison--;
        }
        if(fragile<0){
            fragile--;
        }

        //special effects / relics?
        if(shepardTone){
            loseHealthRaw(1);
        }
        if(boomerangCounter>0){
            boomerangCounter--;
        }
        if(misery>0){
            gainBlock(11);
            misery--;
        }
    }
    public void resetGame(Enemy e){//reset game for new battle
        cardEncyclopedia = new CardList(this, e);
        currentHealth+=2;

        energy = maxEnergy;
        poison = 0;
        block = 0;
        soul = 0;
        strength = 0;
        critDmg = 0;

        //statuses
        willKeepBlock = false;
        boomerangCounter = 0;
        infiniteGunDMG = 5;
        pileOShields = 4;
        trueDeck = new ArrayList<Card>();
        for (int i = 0; i < trueDeckIndexes.size(); i++) {
            trueDeck.add(cardEncyclopedia.getCard(trueDeckIndexes.get(i)));
        }
       //https://howtodoinjava.com/java/collections/arraylist/arraylist-clone-deep-copy/
        drawPile = (ArrayList<Card>) trueDeck.clone();
        Collections.shuffle(drawPile);
        drawCards();
    }

    //STATUSES
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
        return toReturn.substring(0, toReturn.length()-1);
    }
    public void gainEnergy(int i){
        energy+=i;
        energy = Math.max(0,energy);
    }
    public void gainBlock(int i){
        block+=i;
        block = Math.max(0,block);
    }
    public void gainPoison(int i){
        poison+=i;
        poison = Math.max(0,poison);
    }
    public void gainSoul(int i){
        soul+=i;
        if(soul<-6){
            soul=-6;
        }
        if(soul>6){
            soul=6;
        }
    }
    public void gainSoulChance(int i){
        soulChance+=i;
    }
    public void trySoulChance(){
        if((int) (Math.random()*100)<soulChance){
            gainSoul(1);
        }
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
        if(fragile>9){
            fragile=9;
        }
    }
    public int getEnergy(){
        return energy;
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

    //special effects methods
    public void setWillKeepBlock(boolean f){
        willKeepBlock = f;
    }


    //Have representations and methds for the cards in the player's hand,
    //drawing, discarding, shuffling the deck
    //


    //Card Methods
    //true deck methods. these permanently affect the deck.
    public void gainCardT(int index){
        trueDeck.add(cardEncyclopedia.getCard(index));
        trueDeckIndexes.add(index);
    }
    public Card loseCardT(int index){
        if(index<trueDeck.size()) {
            trueDeckIndexes.remove(index);
            return trueDeck.remove(index);
        }
        return null;
    }

    //regular deck methods. These are for referencing the deck in battle, as the cards in battle can be added or removed, but never permanently.
    public void gainCardDraw(int index){
        drawPile.add(cardEncyclopedia.getCard(index));
    }
    public void gainCardHand(int index){
        hand.add(cardEncyclopedia.getCard(index));
    }
    public void gainCardDiscard(int index){
        discardPile.add(cardEncyclopedia.getCard(index));
    }
    public void gainCardDraw(Card c){
        drawPile.add(cardEncyclopedia.getCard(c));
    }
    public Card loseCardDiscard(Card toRemove){
        for (int i = 0; i < discardPile.size(); i++) {
            if(toRemove.equals(discardPile.get(i))){
                return discardPile.remove(i);
            }
        }
        return null;
    }
    public Card loseCardHand(Card toRemove){
        for (int i = 0; i < hand.size(); i++) {
            if(toRemove.equals(hand.get(i))){
                return hand.remove(i);
            }
        }
        //hand.removeIf(toLook -> toLook.equals(toRemove)); //intelliJ autocorrect
        return null;
    }
    public Card loseCardDraw(Card toRemove){
        for (int i = 0; i < drawPile.size(); i++) {
            if(toRemove.equals(drawPile.get(i))){
                return drawPile.remove(i);
            }
        }
        return null;
    }
    public Card loseCardDraw(int i){
        if(i<drawPile.size()) {
            return drawPile.remove(i);
        }
        return null;
    }


    public Card getCard(Card a){
        for (int i = 0; i < trueDeck.size(); i++) {
            if(a.equals(trueDeck.get(i))){
                return trueDeck.get(i);
            }
        }
        return null;
    }
    public Card getCard(int i){
        if(i<trueDeck.size()) {
            return trueDeck.get(i);
        }
        return null;
    }
    public ArrayList<Card> getTrueDeck(){
        return trueDeck;
    }
    public ArrayList<Card> getDraw(){
        return drawPile;
    }
    public ArrayList<Card> getDiscardPile(){
        return discardPile;
    }
    public String getDrawString(){
        String toReturn = "<html>";
        for (int i = 0; i < drawPile.size(); i++) { //https://stackoverflow.com/questions/7447691/is-there-any-multiline-jlabel-option
            toReturn+=drawPile.get(i);
            toReturn+=" <br> ";
            toReturn+=" <br> ";
        }
        toReturn +="</html>";
        return toReturn;
    }
    public String getDiscardString(){
        String toReturn = "<html>";
        for (int i = 0; i < discardPile.size(); i++) { //https://stackoverflow.com/questions/7447691/is-there-any-multiline-jlabel-option
            toReturn+=discardPile.get(i);
            toReturn+=" <br> ";
            toReturn+=" <br> ";
        }
        toReturn +="</html>";
        return toReturn;
    }

    //for debugging
//    public void printDeck(){
//        for (int i = 0; i < deck.size(); i++) {
//            System.out.println(deck.get(i));
//        }
//    }

    public int countCardsByType(CardType c){
        int count = 0;
        for (int i = 0; i < drawPile.size(); i++) {
            if(drawPile.get(i).getType()==c){
                count++;
            }
        }
        for (int i = 0; i < discardPile.size(); i++) {
            if(discardPile.get(i).getType()==c){
                count++;
            }
        }
        for (int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getType()==c){
                count++;
            }
        }
        return count;
    }

    public void addToDiscard(Card c){
        loseCardHand(c);
        discardPile.add(c);
    }
    public ArrayList<Card> drawCards(){
        if(drawSize>drawPile.size()){
            reShuffle();
        }
        for (int i = 0; i < drawSize; i++) {
            if(!drawPile.isEmpty()){
                hand.add(drawPile.remove(0));
            }
        }
        return hand;
    }
    public void reShuffle(){
        Collections.shuffle(discardPile);
        int s = discardPile.size();
        for (int i = 0; i < s; i++) {
            drawPile.add(discardPile.remove(0));
        }
    }
}
