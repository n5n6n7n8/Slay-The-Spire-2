import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EFortuneUI {
    JFrame mainScene;
    JPanel cardRow, anotherPanel;
    JLabel rewardTitle;
    Player player;
    BufferedImage portrait;
    public EFortuneUI(Player p){
        player = p;
        mainScene = new JFrame("Card Master"); //Main Scene
        mainScene.setPreferredSize(new Dimension(700, 600));
        mainScene.getContentPane().setBackground(new Color(22, 67, 140));

        cardRow = new JPanel();

        cardRow.setLayout(new BoxLayout(cardRow, BoxLayout.X_AXIS));
        cardRow.setPreferredSize(new Dimension(500, 230));

        JPanel cardPanel = createCardPanel();
        cardRow.add(cardPanel);
        cardRow.add(createCardPanel());
        cardRow.add(createCardPanel());

        portrait = null;
        try { //https://www.w3schools.com/java/java_try_catch.asp
            portrait = ImageIO.read(new File("src/CS StS art/events/cardmasters/fortune/fortuneteller.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dogPicture = portrait.getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        mainScene.add(new JLabel(new ImageIcon(dogPicture)), BorderLayout.CENTER);

        mainScene.add(cardRow, BorderLayout.SOUTH);
        mainScene.pack();
        mainScene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScene.setVisible(true);
    }

    public JPanel createCardPanel(){
        BufferedImage cardImage = null;
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setPreferredSize(new Dimension(300, 190));
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3)); //https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        JLabel titleL = new JLabel("<html>" + "Fortune" + "</html>", JLabel.CENTER); //https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextArea.html
        cardPanel.add(titleL, BorderLayout.NORTH);

        JButton x = new JButton("Use");
        x.addActionListener(new ActionListener() {//this actionlistener will permanently add the card to the player's deck
            public void actionPerformed(ActionEvent ae) {

            }
        });
        cardPanel.add(x, BorderLayout.SOUTH);

        try { //https://www.w3schools.com/java/java_try_catch.asp
            cardImage = ImageIO.read(new File("src/CS StS art/events/cardmasters/fortune/wheel.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image fCardImage = cardImage.getScaledInstance(110, 165, Image.SCALE_DEFAULT); //https://mkyong.com/java/how-to-resize-an-image-in-java/
        cardPanel.add(new JLabel(new ImageIcon(fCardImage)), BorderLayout.CENTER);
        return cardPanel;
    }

    public static void main(String[] args) {
        EnemyList enemyList = new EnemyList();
        Enemy enemy = enemyList.getEnemy(2);
        Player player = new Player(enemy);

        player.gainCardT(1);
        player.gainCardT(1);
        player.gainCardT(4);
        player.gainCardT(2);
        player.gainCardT(2);
        player.gainCardT(13);
        player.gainCardT(5);
        player.gainCardT(14);

        EFortuneUI fortune = new EFortuneUI(player);
    }
}

