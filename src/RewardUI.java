import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RewardUI {
    JFrame mainScene;
    JPanel anotherPanel, optionsPanel, bottomRow;
    JButton skipButton;
    JLabel rewardTitle, playerStats;
    Player player;
    Enemy enemy;

    public RewardUI(Player player){
        EnemyList enemyList = new EnemyList();
        this.player = player;
        this.enemy = EnemyList.getEnemy(0);
        CardList cardList = new CardList(player, enemy);
        mainScene = new JFrame("Choose A Reward"); //Main Scene
        mainScene.setPreferredSize(new Dimension(600, 400));
        mainScene.pack();

        rewardTitle = new JLabel("Victory! Choose a reward:", JLabel.CENTER);
        rewardTitle.setFont(new Font("Zapfino", Font.BOLD,20));
        mainScene.add(rewardTitle, BorderLayout.NORTH);

        anotherPanel = new JPanel();
        anotherPanel.setPreferredSize(new Dimension(300, 100));

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
        optionsPanel.setPreferredSize(new Dimension(600, 250));
        int x = (int) (Math.random()*3)+1;
        int y = (int) (Math.random()*4)+1;
        int z = (int) (Math.random()*5)+1;
        int w = (int) (Math.random()*6)+1;
        optionsPanel.add(createCardPanel(cardList.getCard(x),x));
        optionsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        optionsPanel.add(createCardPanel(cardList.getCard(y),y));
        optionsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        optionsPanel.add(createCardPanel(cardList.getCard(z),z));
        optionsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        optionsPanel.add(createCardPanel(cardList.getCard(w),w));
        optionsPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        anotherPanel.add(optionsPanel);

        bottomRow = new JPanel();
        bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.X_AXIS));

        playerStats = new JLabel("Player: " + player.getCurrentHealth() + "/" + player.getMaxHealth() + ", Gold:" + player.getGold());

        skipButton = new JButton("Skip Rewards");
        skipButton.addActionListener(new ActionListener() {//this actionlistener will permanently add the card to the player's deck
            public void actionPerformed(ActionEvent ae) {
                RoomChoiceUI roomChoiceUI = new RoomChoiceUI(player);
                mainScene.setVisible(false);
                mainScene.dispose();
            }
        });

        mainScene.add(anotherPanel, BorderLayout.CENTER);

        bottomRow.add(skipButton);
        bottomRow.add(playerStats);
        mainScene.add(bottomRow, BorderLayout.SOUTH);


        mainScene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScene.setVisible(true);
    }
    public JPanel createCardPanel(Card c, int index){
        JPanel cardPanel = new JPanel();
        JPanel surroundingPanel = new JPanel();
        surroundingPanel.setLayout(new BoxLayout(surroundingPanel, BoxLayout.Y_AXIS));
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setPreferredSize(new Dimension(140, 210));
        cardPanel.setBorder(BorderFactory.createLineBorder(c.getType().getColor(c.getType()),5)); //https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        JLabel titleL = new JLabel("<html><center>"+c.getTitle() + " (" + c.getType().toString()+")"+"</html>"); //https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextArea.html
        titleL.setFont(new Font("Chicago", Font.BOLD,12));
        cardPanel.add(titleL, BorderLayout.NORTH);
        JLabel descL = new JLabel("<html><center><font size='3' color=black> "+ c.getDescription() + "</font> <font size='4'color=blue>"+ " (" +c.getCost() + ")" + "</font></html>");

        surroundingPanel.add(new JLabel(c.cardArt));
        surroundingPanel.add(descL);

        cardPanel.add(surroundingPanel);

        JButton x = new JButton("Choose");
        x.addActionListener(new ActionListener() {//this actionlistener will permanently add the card to the player's deck
            public void actionPerformed(ActionEvent ae) {
                player.gainCardT(index);
                optionsPanel.remove(cardPanel);
                RoomChoiceUI roomChoiceUI = new RoomChoiceUI(player);
                mainScene.setVisible(false);
                mainScene.dispose();
            }
        });
        cardPanel.add(x, BorderLayout.SOUTH);
        return cardPanel;
    }

    public static void main(String[] args) {
        EnemyList enemyList = new EnemyList();
        Enemy enemy = enemyList.getEnemy(2);
        Player player = new Player(enemy);

        RewardUI x = new RewardUI(player);
    }
}


