import java.awt.*;
import java.util.Scanner;

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
        player.gainCardT(4);
        player.gainCardT(4);
        player.gainCardT(4);
        player.gainCardT(4);
        player.gainCardT(4);
        player.gainCardT(8);
        player.gainCardT(9);
        player.gainCardT(10);
        player.gainCardT(12);
        player.gainCardT(11);

        BattleUI battleUI = new BattleUI(player, enemy);

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
class valueTesting{
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            int x = 0;
            x += (int) (Math.random()*6)+1;
            x += (int) (Math.random()*6)+1;
            x += (int) (Math.random()*6)+1;
            System.out.println(x + "\n");
        }
        int t = 123456;
        System.out.println(((t%1000)%1000));
        System.out.println(t%100);
        System.out.println(t%10);

    }
}
