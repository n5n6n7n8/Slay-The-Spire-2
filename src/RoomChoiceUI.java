import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomChoiceUI {
    JFrame mainScene;
    JPanel anotherPanel, optionsPanel;
    JLabel rewardTitle;
    Player player;
    int enemyChoice;
    public RoomChoiceUI(Player player){
        enemyChoice = (int) (Math.random()*7)+1;
        this.player = player;


        mainScene = new JFrame("Next Room..."); //Main Scene
        mainScene.setPreferredSize(new Dimension(600, 400));
        mainScene.pack();

        rewardTitle = new JLabel("Progress to the next room", JLabel.CENTER);
        rewardTitle.setFont(new Font("Zapfino", Font.BOLD,20));
        mainScene.add(rewardTitle,BorderLayout.NORTH);

        anotherPanel = new JPanel();
        anotherPanel.setPreferredSize(new Dimension(100, 100));

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
        optionsPanel.setPreferredSize(new Dimension(360, 150));

        optionsPanel.add(createCardPanel());

        anotherPanel.add(optionsPanel);
        mainScene.add(anotherPanel, BorderLayout.CENTER);


        mainScene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScene.setVisible(true);
    }
    public JPanel createCardPanel(){
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setPreferredSize(new Dimension(100, 140));
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.black)); //https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        JLabel titleL = new JLabel("<html>" + "Fight an enemy" + "</html>", JLabel.CENTER); //https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextArea.html
        cardPanel.add(titleL, BorderLayout.NORTH);

        JButton x = new JButton("Press to continue");
        x.addActionListener(new ActionListener() {//this actionlistener will permanently add the card to the player's deck
            public void actionPerformed(ActionEvent ae) {
                EnemyList enemyList = new EnemyList();
                Enemy enemy = EnemyList.getEnemy(enemyChoice);
                ActionList actionList = new ActionList(player, enemy);
                enemy.addActionToEnemy(actionList.getAction(enemyChoice));
                player.resetGame(enemy);
                BattleUI x = new BattleUI(player);
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

        player.gainCardT(1);
        player.gainCardT(1);
        player.gainCardT(4);
        player.gainCardT(2);
        player.gainCardT(2);
        player.gainCardT(13);
        player.gainCardT(5);
        player.gainCardT(14);

        RoomChoiceUI roomChoiceUI = new RoomChoiceUI(player);
    }
}
