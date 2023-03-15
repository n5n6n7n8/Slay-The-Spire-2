import java.awt.Color;
//https://www.w3schools.com/java/java_enums.asp
public enum CardType { //if time, include orbs mechanic
    BASIC,
    ATTACK,
    BLOCK,
    POISON,
    SOUL,
    STRENGTH,
    CRIT,
    HEAL,
    FRAGILE,
    ENERGY,
    SHUFFLE,
    CURSE,
    NONE,
    SPECIAL;

    public Color getColor(CardType ct){ //https://www.w3schools.com/java/java_switch.asp
        switch(ct){
            case BASIC:
                return new Color(100, 100, 100);
            case ATTACK:
                return new Color(135, 60, 67);
            case BLOCK:
                return new Color(68, 84, 122);
            case POISON:
                return new Color(61, 196, 86);
            case SOUL:
                return new Color(146, 122, 207);
            case STRENGTH:
                return new Color(179, 55, 21);
            case CRIT:
                return new Color(204, 142, 27);
            case HEAL:
                return new Color(235, 73, 127);
            case FRAGILE:
                return new Color(22, 204, 156);
            case ENERGY:
                return new Color(50, 177, 227);
            case SHUFFLE:
                return new Color(135, 47, 138);
            case CURSE:
                return new Color(46, 39, 34);
            case SPECIAL:
                return new Color(217, 211, 46);
            default:
                return new Color(30, 30,30);

        }

    }
}
