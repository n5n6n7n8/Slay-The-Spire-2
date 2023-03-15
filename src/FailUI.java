import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FailUI {
    JFrame mainScene;
    JPanel anotherPanel;
    JLabel label;
    JButton tryAgain;
    public FailUI(){
        mainScene = new JFrame("You died!");
        mainScene.setPreferredSize(new Dimension(300, 200));
        mainScene.pack();
        anotherPanel = new JPanel();

        label = new JLabel("<html>"+ "You died!" + "</html>", JLabel.CENTER);
        label.setFont(new Font("Chicago", Font.BOLD, 14));
        tryAgain = new JButton("Try Again?");
        tryAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
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
        });
        anotherPanel.add(tryAgain);

        mainScene.add(label, BorderLayout.CENTER);
        mainScene.add(anotherPanel, BorderLayout.SOUTH);

        mainScene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScene.setVisible(true);
    }

    public static void main(String[] args) {
        FailUI x = new FailUI();
    }
}
