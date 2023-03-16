import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FailUI {
    JFrame mainScene;
    JPanel anotherPanel;
    JLabel label;
    JButton tryAgain;
    BufferedImage picture;
    public FailUI(){
        mainScene = new JFrame("You died!");
        mainScene.setPreferredSize(new Dimension(300, 200));
        mainScene.pack();
        anotherPanel = new JPanel();

        picture = null;
        try { //https://www.w3schools.com/java/java_try_catch.asp
            picture = ImageIO.read(new File("src/CS StS art/dog.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dogPicture = picture.getScaledInstance(140, 140, Image.SCALE_DEFAULT); //https://mkyong.com/java/how-to-resize-an-image-in-java/
        anotherPanel.add(new JLabel(new ImageIcon(dogPicture)));

        label = new JLabel("<html>"+ "You died!" + "</html>", JLabel.CENTER);
        label.setFont(new Font("Chicago", Font.BOLD, 14));
        tryAgain = new JButton("Try Again?");
        tryAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                EnemyList enemyList = new EnemyList();
                Enemy enemy = enemyList.getEnemy(2);
                Player player = new Player(enemy);
                player.gainCardT(1);
                player.gainCardT(1);
                player.gainCardT(10);
                player.gainCardT(2);
                player.gainCardT(2);
                player.gainCardT(20);
                player.gainCardT(5);
                player.gainCardT(11);
                BattleUI x = new BattleUI(player, enemy);
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
