public class Action {
    ActionType actionType;
    int value;
    Card card;
    ActionType actionType2;
    int value2;
    Card card2;

    boolean isDual;
    public Action(boolean b){
        isDual = b;
    }
    public Action(int v, ActionType a, boolean b){
        value = v;
        actionType = a;
        card = null;
        isDual = b;
    }
    public Action(int v, ActionType a, Card c, boolean b){
        value = v;
        actionType = a;
        card = c;
        isDual = b;
    }
    public void addDual(int v, ActionType a, Card c){
        value2 = v;
        actionType2 = a;
        card2 = c;
    }
    public void addDual(int v, ActionType a){
        value2 = v;
        actionType2 = a;
    }
    public void setValue(int v){
        value = v;
    }
    public void setActionType(ActionType a){
        actionType = a;
    }
    public void setDual(boolean d){
        isDual = d;
    }
    @Override
    public String toString(){
        return "" + value + " " + actionType;
    }


}
enum ActionType {
    LOSE_HEALTH,
    GAIN_HEALTH,
    GAIN_BLOCK,
    LOSE_BLOCK,
    LOSE_GOLD,
    GAIN_STRENGTH,
    INFLICT_POISON,
    INFLICT_WEAK,
    INFLICT_FRAIL,
    GIVE_CARD,
}
