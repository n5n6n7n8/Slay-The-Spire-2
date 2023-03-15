import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class ActionList {
    private static ArrayList<EnemyAction> allActions = new ArrayList<EnemyAction>();
    public ActionList(Player player, Enemy enemy){
        allActions.add(new EnemyAction("Uhhh", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.loseHealth(1);
            }
        }));
        allActions.add(new EnemyAction("Slime action", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = (int) (Math.random()*2);
                if(c==0){
                    player.loseHealth(6);
                    System.out.println("Enemy attacked for 6!");
                }
                else {
                    enemy.gainBlock(4);
                    System.out.println("Enemy blocked 4!");
                }
            }
        }));
        allActions.add(new EnemyAction("Greater slime action", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemy.gainPoison(20);
                int c = (int) (Math.random()*2);
                if(c==0){
                    player.loseHealth(6);
                    System.out.println("Enemy attacked for 6!");
                }
                else {
                    enemy.gainBlock(5);
                    System.out.println("Enemy blocked 5!");
                }
            }
        }));
        allActions.add(new EnemyAction("Giga slime action", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = (int) (Math.random()*5);
                if(c<2){
                    player.loseHealth(14);
                    System.out.println("Enemy attacked for 14!");
                }
                if(c<5){
                    enemy.gainBlock(9);
                    System.out.println("Enemy blocked 9!");
                }
                else {
                    enemy.gainStrength(1);
                    System.out.println("Enemy got stronger!");
                }
            }
        }));
        allActions.add(new EnemyAction("Spider action", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = (int) (Math.random()*2);
                if(c==0){
                    player.loseHealth(8);
                    System.out.println("Enemy attacked for 8!");
                }
                else {
                    enemy.gainBlock(12);
                    System.out.println("Enemy blocked 12!");
                }
            }
        }));
        allActions.add(new EnemyAction("Weird enemy spider action", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = (int) (Math.random()*8);
                if(c<=3){
                    enemy.gainBlock(15);
                    System.out.println("Enemy blocked 15!");
                }
                if(c<7){
                    player.loseHealth(6);
                    System.out.println("Enemy attacked for 6!");
                }
                else {
                    player.gainPoison(2);
                    System.out.println("Enemy poisoned you!");
                }
            }
        }));
        allActions.add(new EnemyAction("Giant enemy spider action", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = (int) (Math.random()*4);
                if(c==0){
                    player.loseHealth(9);
                    System.out.println("Enemy attacked for 9!");
                }
                if(c==1){
                    enemy.gainBlock(10);
                    System.out.println("Enemy blocked 10!");
                }
                if(c==2){
                    player.gainPoison(3);
                    System.out.println("Enemy poisoned you!");
                }
                else{
                    player.loseHealth(13);
                    System.out.println("Enemy attacked for 13!");
                }
            }
        }));
        allActions.add(new EnemyAction("Wizard action", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = (int) (Math.random()*3);
                if(c==0){
                    player.loseHealth(20);
                    System.out.println("Enemy attacked for 20!");
                }
                else{
                    enemy.gainHealth(5);
                    System.out.println("Enemy healed for 5!");
                }
            }
        }));
        allActions.add(new EnemyAction("Feral Squirrel action", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = (int) (Math.random()*3);
                if(c==0){
                    player.loseHealth(12);
                    player.gainPoison(3);
                    System.out.println("Enemy attacked for 12 and poisoned you!");
                }
                else{
                    enemy.gainBlock(7);
                    System.out.println("Enemy blocked 7!");
                }
            }
        }));
        allActions.add(new EnemyAction("goblin action", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = (int) (Math.random()*3);
                if(c==0){
                    player.gainGold(-3);
                    player.loseHealth(8);
                    System.out.println("Enemy attacked for 8!");
                }
                if(c==1){
                    enemy.gainBlock(12);
                    System.out.println("Enemy blocked 12!");
                }
                else{
                    player.loseHealth(10);
                    System.out.println("Enemy attacked 10!");
                }
            }
        }));


    }
    public EnemyAction getAction(int i){
        if(i<0||i>=allActions.size()){
            return allActions.get(0);
        }
        else{
            return allActions.get(i);
        }
    }
}
