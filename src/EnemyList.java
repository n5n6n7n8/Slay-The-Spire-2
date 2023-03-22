import java.util.ArrayList;

public class EnemyList {
    private static ArrayList<Enemy> allEnemies = new ArrayList<Enemy>();
    public EnemyList(){
        allEnemies.add(new Enemy(999, "Training Dummy") {
            @Override
            public Action generateAction() {
                return new Action(1, ActionType.LOSE_HEALTH, false);
            }
        });
        allEnemies.add(new Enemy(25, 30, "Slime") {
            @Override
            public Action generateAction() {
                int c = (int) (Math.random()*2);
                if(c==0){
                    System.out.println("Enemy will attack for 6!");
                    System.out.println("---------------------");
                    return new Action(6, ActionType.LOSE_HEALTH, false);
                }
                else {
                    System.out.println("Enemy will block 4!");
                    System.out.println("---------------------");
                    return new Action(4, ActionType.GAIN_BLOCK, false);
                }
            }
        });
        allEnemies.add(new Enemy(35, 40, "Greater Slime", CardType.POISON, CardType.NONE) {
            @Override
            public Action generateAction() {
                int c = (int) (Math.random()*5);
                if(c<3){
                    System.out.println("Enemy will attack for 9!");
                    System.out.println("---------------------");
                    return new Action(9, ActionType.LOSE_HEALTH, false);
                }
                else {
                    System.out.println("Enemy will block for 5!");
                    System.out.println("---------------------");
                    return new Action(5, ActionType.GAIN_BLOCK, false);
                }
            }
        });
        allEnemies.add(new Enemy(90, 90, "Giga Slime", CardType.POISON, CardType.NONE) {
            @Override
            public Action generateAction() {
                int c = (int) (Math.random()*6);
                Action a = new Action(false);
                if(c<2){
                    a.setDual(true);
                    a.addDual(14, ActionType.LOSE_HEALTH);
                    System.out.println("Enemy will attack for 14!");
                    System.out.println("---------------------");
                }
                if(c<5){
                    a.setValue(9);
                    a.setActionType(ActionType.GAIN_BLOCK);
                    System.out.println("Enemy will block for 9!");
                    System.out.println("---------------------");
                }
                else {
                    a.setValue(1);
                    a.setActionType(ActionType.GAIN_STRENGTH);
                    System.out.println("Enemy got stronger!");
                    System.out.println("---------------------");
                }
                return a;
            }
        });
        allEnemies.add(new Enemy(30, 32, "Spider", CardType.NONE, CardType.FRAGILE) {
            @Override
            public Action generateAction() {
                int c = (int) (Math.random()*2);
                if(c==0){
                    System.out.println("Enemy attacked for 8!");
                    System.out.println("---------------------");
                    return new Action(8, ActionType.LOSE_HEALTH, false);
                }
                else {
                    System.out.println("Enemy blocked 12!");
                    System.out.println("---------------------");
                    return new Action(12, ActionType.GAIN_BLOCK, false);
                }
            }
        });
        allEnemies.add(new Enemy(30, 40, "Weird Enemy Spider", CardType.NONE, CardType.NONE) {
            @Override
            public Action generateAction() {
                int c = (int) (Math.random()*8);
                Action a = new Action(false);
                if(c<=3){
                    a.setDual(true);
                    a.addDual(15, ActionType.GAIN_BLOCK);
                    System.out.println("Enemy blocked 15!");
                    System.out.println("---------------------");
                }
                if(c<7){
                    a.setValue(6);
                    a.setActionType(ActionType.LOSE_HEALTH);
                    System.out.println("Enemy attacked for 6!");
                    System.out.println("---------------------");
                }
                else {
                    a.setValue(2);
                    a.setActionType(ActionType.INFLICT_POISON);
                    System.out.println("Enemy poisoned you!");
                    System.out.println("---------------------");
                }
                return a;
            }
        });
        allEnemies.add(new Enemy(70, 70, "Giant Enemy Spider", CardType.NONE, CardType.FRAGILE) {
            @Override
            public Action generateAction() {
                int c = (int) (Math.random()*4);
                if(c==0){
                    System.out.println("Enemy attacked for 9!");
                    System.out.println("---------------------");
                    return new Action(9, ActionType.LOSE_HEALTH, false);
                }
                if(c==1){
                    System.out.println("Enemy blocked 10!");
                    System.out.println("---------------------");
                    return new Action(10, ActionType.GAIN_BLOCK, false);
                }
                if(c==2){
                    System.out.println("Enemy poisoned you!");
                    System.out.println("---------------------");
                    return new Action(2, ActionType.INFLICT_POISON, false);
                }
                else{
                    System.out.println("Enemy attacked for 13!");
                    System.out.println("---------------------");
                    return new Action(13, ActionType.LOSE_HEALTH, false);
                }
            }
        });
        allEnemies.add(new Enemy(50, 55, "Wizard", CardType.FRAGILE, CardType.NONE) {
            @Override
            public Action generateAction() {
                int c = (int) (Math.random()*3);
                if(c==0){
                    System.out.println("Enemy attacked for 16!");
                    System.out.println("---------------------");
                    return new Action(16, ActionType.LOSE_HEALTH, false);
                }
                else{
                    System.out.println("Enemy healed for 5!");
                    System.out.println("---------------------");
                    return new Action(5, ActionType.GAIN_HEALTH, false);
                }
            }
        });
        allEnemies.add(new Enemy(70, 100, "Feral Squirrel", CardType.NONE, CardType.FRAGILE) {
            @Override
            public Action generateAction() {
                int c = (int) (Math.random()*3);
                if(c==0){
                    System.out.println("Enemy attacked for 12 and poisoned you!");
                    System.out.println("---------------------");
                    Action a = new Action(12, ActionType.LOSE_HEALTH, true);
                    a.addDual(3, ActionType.INFLICT_POISON);
                    return a;
                }
                else{
                    System.out.println("Enemy blocked 7!");
                    System.out.println("---------------------");
                    return new Action(7, ActionType.GAIN_BLOCK, false);
                }
            }
        });

        allEnemies.add(new Enemy(25, 35, "Goblin", CardType.NONE, CardType.FRAGILE) {
            @Override
            public Action generateAction() {
                int c = (int) (Math.random()*3);
                if(c==0){
                    //player.gainGold(-3);
                    System.out.println("Enemy attacked for 8!");
                    System.out.println("---------------------");
                    return new Action(8, ActionType.LOSE_HEALTH, false);
                }
                if(c==1){
                    System.out.println("Enemy blocked 12!");
                    System.out.println("---------------------");
                    return new Action(12, ActionType.GAIN_BLOCK, true);
                }
                else{
                    System.out.println("Enemy attacked 10!");
                    System.out.println("---------------------");
                    return new Action(10, ActionType.LOSE_HEALTH, true);
                }
            }
        });
    }
    public static Enemy getEnemy(int i){//returns an enemy from the enemy library
        if(i<0||i>=allEnemies.size()){
            return allEnemies.get(0);
        }
        else{
            return allEnemies.get(i);
        }
    }
}

