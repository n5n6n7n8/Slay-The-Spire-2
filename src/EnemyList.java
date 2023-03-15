import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EnemyList {
    private static ArrayList<Enemy> allEnemies = new ArrayList<Enemy>();
    public EnemyList(){
        allEnemies.add(new Enemy(999, "Training Dummy"));
        allEnemies.add(new Enemy(25,30, "Slime"));
        allEnemies.add(new Enemy(35, 40, "Greater Slime", CardType.POISON, CardType.NONE));
        allEnemies.add(new Enemy(90, 90, "Giga Slime", CardType.POISON, CardType.NONE));
        allEnemies.add(new Enemy(30, 32, "Spider", CardType.NONE, CardType.FRAGILE));
        allEnemies.add(new Enemy(30, 40, "Weird Enemy Spider", CardType.NONE, CardType.NONE));
        allEnemies.add(new Enemy(70, 70, "Giant Enemy Spider", CardType.NONE, CardType.FRAGILE));
        allEnemies.add(new Enemy(50,55, "Wizard", CardType.FRAGILE, CardType.NONE));
        allEnemies.add(new Enemy(25,35, "Goblin", CardType.NONE, CardType.FRAGILE));
    }
    public static Enemy getEnemy(int i){//returns an enemy from the enemy library
        if(i<0||i>=allEnemies.size()){
            return allEnemies.get(0);
        }
        else{
            Enemy toGet = allEnemies.get(i);
            String name = toGet.getName();
            int maxH = toGet.getMaxHealth();
            ArrayList<CardType> immunity = toGet.getImmunity();
            ArrayList<CardType> weakness = toGet.getWeakness();
            return new Enemy(maxH, name, immunity, weakness);
        }
    }
    public static Enemy getEnemy(Enemy other){//returns an enemy from the enemy library
        for (int i = 0; i < allEnemies.size(); i++) {
            if(other.equals(allEnemies.get(i))){
                return allEnemies.get(i);
            }
        }
        return allEnemies.get(0);
    }
}

