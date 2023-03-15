import java.awt.*;
import java.sql.Array;
import java.util.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);

        //Order of Operations for player enemy setup
        //Create new enemy list
        //create an enemy with index from enemylist
        //create a player with argument enemy
        //create an actionlist with arguments player and enemy
        //add the action to the enemy
        EnemyList enemyList = new EnemyList();
        Enemy enemy = enemyList.getEnemy(2);
        Player player = new Player(enemy);
        ActionList actionList = new ActionList(player, enemy);
        enemy.addActionToEnemy(actionList.getAction(2));
        player.gainCardT(1);
        player.gainCardT(1);
        player.gainCardT(10);
        player.gainCardT(2);
        player.gainCardT(2);
        player.gainCardT(20);
        player.gainCardT(5);
        player.gainCardT(11);
        player.resetGame(enemy);
        BattleUI x = new BattleUI(player);

    }
}
class ListJavaFonts {

    public static void main(String[] args) {
        String fonts[] =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);
        }
    }

}
