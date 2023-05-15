import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleUI {
    JFrame mainScene;
    JPanel centerScene, northScene, southScene, westScene, eastScene,
            infoBRow, cardsRow, playerPanel, enemyPanel, playerUIPanel, enemyUIPanel, pBlockPanel, eBlockPanel, pEnergyPanel;
    JLabel playerUILabel, playerUILabel2, enemyUILabel, enemyUILabel2, pEnergy, pBlock, eBlock, pStatus, eStatus, enemyIntent;
    JButton showDrawB, showDiscardB, exitB, nextTurnB;
    JProgressBar playerHealthBar, enemyHealthBar;
    Player player;
    Enemy enemy;
    BufferedImage blockImage, energyImage;

    public BattleUI(Player p, Enemy ee) { //CREATE PLAYER AND ENEMY VARIOABLES UP HERE^^ TO REFERENCE INSTEAD OF USAGE IN ARGUMENBTS
        player = p;
        enemy = ee;
        p.resetGame(ee);
        enemy.setAction(enemy.generateAction());
        mainScene = new JFrame("Battle Scene"); //Main Scene


        mainScene.setPreferredSize(new Dimension(900, 600));
        mainScene.pack();
        centerScene = new JPanel(); //Panels on all areas of MainScene
        centerScene.setLayout(new BorderLayout());
        mainScene.add(centerScene, BorderLayout.CENTER);
        northScene = new JPanel();
        mainScene.add(northScene, BorderLayout.NORTH);
        northScene.setPreferredSize(new Dimension(500, 90));
        southScene = new JPanel();
        mainScene.add(southScene, BorderLayout.SOUTH);
        westScene = new JPanel();
        mainScene.add(westScene, BorderLayout.WEST);
        eastScene = new JPanel();
        mainScene.add(eastScene, BorderLayout.EAST);

        //NORTH SCENE
        infoBRow = new JPanel();//Row of buttons
        infoBRow.setLayout(new BoxLayout(infoBRow, BoxLayout.X_AXIS));
        showDrawB = new JButton("Show Draw Pile");
        showDrawB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFrame cardMap = new JFrame("Draw Pile:"); //Main Scene
                cardMap.setPreferredSize(new Dimension(500, 600));
                cardMap.pack();
                cardMap.getContentPane().setBackground(new Color(245, 241, 206));
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(ae1 -> {
                    cardMap.setVisible(false);
                    cardMap.dispose();
                });
                cardMap.add(closeButton, BorderLayout.NORTH);

                JLabel cardsText = new JLabel(player.getDrawString(), JLabel.CENTER);
                cardMap.add(cardsText, BorderLayout.CENTER);


                cardMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                cardMap.setVisible(true);
            }
        });
        showDiscardB = new JButton("Show Discard Pile");
        showDiscardB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFrame cardMap = new JFrame("Discard Pile"); //Main Scene
                cardMap.setPreferredSize(new Dimension(500, 600));
                cardMap.pack();
                cardMap.getContentPane().setBackground(new Color(146, 192, 219));

                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(ae1 -> {
                    cardMap.setVisible(false);
                    cardMap.dispose();
                });
                cardMap.add(closeButton, BorderLayout.NORTH);

                JLabel cardsText = new JLabel(player.getDiscardString(), JLabel.CENTER);
                cardMap.add(cardsText, BorderLayout.CENTER);

                cardMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                cardMap.setVisible(true);
            }
        });

        infoBRow.add(showDrawB);
        infoBRow.add(showDiscardB);

        exitB = new JButton("Exit Game");
        infoBRow.add(exitB);

        northScene.add(infoBRow);

        //SOUTH SCENE
        cardsRow = new JPanel();//row of cards
        cardsRow.setLayout(new BoxLayout(cardsRow, BoxLayout.X_AXIS));

        nextTurnB = new JButton("End Turn");
        nextTurnB.setForeground(new Color(210, 50, 50));
        nextTurnB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                enemy.nextTurn();
                if(enemy.checkIfDead()){
                    RewardUI rewardUI = new RewardUI(player);
                    enemy.gainHealth(1000);
                    mainScene.setVisible(false);
                    mainScene.dispose();
                }
                Action a = enemy.setAndGetAction(enemy.generateAction());//right now, generate action, do appropriate effects on player and enemy

                enemyIntent.setIcon(enemy.getLabelOfAction());
                enemyIntent.setText("("+enemy.getAction().value+")");

                player.receiveAction(a);
                enemy.receiveAction(a);
                if(player.checkIfDead()){
                    FailUI failUI = new FailUI();
                    enemy.gainHealth(1000);
                    mainScene.setVisible(false);
                    mainScene.dispose();
                }
                player.nextTurn();
                if(player.checkIfDead()){
                    FailUI failUI = new FailUI();
                    enemy.gainHealth(1000);
                    mainScene.setVisible(false);
                    mainScene.dispose();
                }
                setUpCards();
                updateEntities();
            }
        });

        setUpCards();
        southScene.add(cardsRow);
        southScene.add(nextTurnB);


        //WEST SCENE
        playerPanel = new JPanel();//vertical row
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        //energy
        pEnergyPanel = new JPanel();
        pEnergyPanel.setLayout(new OverlayLayout(pEnergyPanel));

        pEnergy = new JLabel("2");
        pEnergy.setFont(new Font("SansSerif", Font.BOLD,16));
        pEnergy.setForeground(new Color(80, 80, 210));
        pEnergy.setAlignmentX(0.5f);
        pEnergyPanel.add(pEnergy);

        energyImage = null;
        try { //https://www.w3schools.com/java/java_try_catch.asp
            energyImage = ImageIO.read(new File("src/CS StS art/star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image newEnergyImage = energyImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT); //https://mkyong.com/java/how-to-resize-an-image-in-java/
        JLabel energyPicLabel = new JLabel(new ImageIcon(newEnergyImage));
        energyPicLabel.setAlignmentX(0.5f);
        pEnergyPanel.add(energyPicLabel);
        playerPanel.add(pEnergyPanel);

        //block
        pBlockPanel = new JPanel();
        pBlockPanel.setLayout(new OverlayLayout(pBlockPanel));

        pBlock = new JLabel("");
        pBlock.setForeground(Color.BLACK);
        pBlock.setFont(new Font("SansSerif", Font.BOLD, 16));
        pBlock.setAlignmentX(0.5f);
        pBlockPanel.add(pBlock);

        blockImage = null;
        try { //https://www.w3schools.com/java/java_try_catch.asp
            blockImage = ImageIO.read(new File("src/CS StS art/Block_Icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image newBlockImage = blockImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        JLabel pBlockImage = new JLabel (new ImageIcon(newBlockImage));
        pBlockImage.setAlignmentX(0.5f);
        pBlockPanel.add(pBlockImage);

        playerPanel.add(pBlockPanel);

        //status
        pStatus = new JLabel(p.listStatuses());
        playerPanel.add(pStatus);

        playerPanel.setBorder(BorderFactory.createLineBorder(new Color(20, 20,40),2));
        playerPanel.setBorder(BorderFactory.createCompoundBorder(
                playerPanel.getBorder(),
                BorderFactory.createEmptyBorder(15, 5, 15, 5))); //https://stackoverflow.com/questions/27362165/java-add-space-between-component-and-frame

        westScene.add(playerPanel);


        //EAST SCENE
        enemyPanel = new JPanel();
        enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.Y_AXIS));

        eBlockPanel = new JPanel();
        eBlockPanel.setLayout(new OverlayLayout(eBlockPanel));

        eBlock = new JLabel("");
        eBlock.setForeground(Color.BLACK);
        eBlock.setFont(new Font("SansSerif", Font.BOLD, 16));
        eBlock.setAlignmentX(0.5f);
        eBlockPanel.add(eBlock);
        JLabel eBlockImage = new JLabel(new ImageIcon(newBlockImage));
        eBlockImage.setAlignmentX(0.5f);
        eBlockPanel.add(eBlockImage);

        enemyPanel.add(eBlockPanel);

        enemyIntent = new JLabel(enemy.getLabelOfAction());
        enemyIntent.setText("");
        enemyPanel.add(enemyIntent);


        eStatus = new JLabel(enemy.listStatuses());
        enemyPanel.add(eStatus);
        enemyPanel.setBorder(BorderFactory.createLineBorder(new Color(20, 20,40),2));
        enemyPanel.setBorder(BorderFactory.createCompoundBorder(
                enemyPanel.getBorder(),
                BorderFactory.createEmptyBorder(15, 5, 15, 5)));
        eastScene.add(enemyPanel);


        //CENTER SCENE

        //PLAYER CENTER SCENE

        playerUIPanel = new JPanel();
        playerUIPanel.setLayout(new BoxLayout(playerUIPanel, BoxLayout.Y_AXIS));
        playerUILabel = new JLabel("Player: ");
        playerUILabel.setFont(new Font("Chicago", Font.BOLD,13));
        playerUILabel2 = new JLabel("(x/x)");

        playerUIPanel.add(playerUILabel);
        playerUIPanel.add(playerUILabel2);


        //https://www.geeksforgeeks.org/java-swing-jprogressbar/
        playerHealthBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, player.getMaxHealth());//ProgBar
        playerHealthBar.setValue(player.getCurrentHealth());
        playerUIPanel.add(playerHealthBar);


        centerScene.add(playerUIPanel, BorderLayout.WEST);

        //ENEMY CENTER SCENE
        enemyUIPanel = new JPanel();
        enemyUIPanel.setLayout(new BoxLayout(enemyUIPanel, BoxLayout.Y_AXIS));
        enemyUILabel = new JLabel("Enemy UI");
        enemyUILabel.setFont(new Font("Chicago", Font.BOLD,13));
        enemyUIPanel.add(enemyUILabel);

        enemyUILabel2 = new JLabel("");
        enemyUIPanel.add(enemyUILabel2);

        enemyHealthBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, enemy.getMaxHealth());
        enemyHealthBar.setValue(50);
        enemyUIPanel.add(enemyHealthBar);

        centerScene.add(enemyUIPanel, BorderLayout.EAST);
        //OTHER SETUP
        setUpEntities();
        mainScene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScene.setVisible(true);

    }

    //Setting up all UI according to player and enemy stats
    public void setUpEntities() {
        playerHealthBar.setMaximum(player.getMaxHealth());
        enemyHealthBar.setMaximum(enemy.getMaxHealth());
        playerHealthBar.setValue(player.getCurrentHealth());
        enemyHealthBar.setValue(enemy.getCurrentHealth());
        pEnergy.setText(""+player.getEnergy());
        playerUILabel.setText("Player ");
        playerUILabel2.setText("("+player.getCurrentHealth() + "/" + player.getMaxHealth() + ")");
        enemyUILabel.setText(enemy.getName());
        enemyUILabel2.setText(" (" + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + ")");
        pBlock.setText(""+player.getBlock());
        eBlock.setText("" + enemy.getBlock());
    }

    //Method for updating cards ui
    public void updateEntities() {

        playerHealthBar.setValue(player.getCurrentHealth());
        enemyHealthBar.setValue(enemy.getCurrentHealth());
        playerUILabel.setText("Player");
        playerUILabel2.setText("("+player.getCurrentHealth() + "/" + player.getMaxHealth() + ")");
        enemyUILabel.setText(enemy.getName());
        enemyUILabel2.setText("(" + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + ")");
        pEnergy.setText(""+player.getEnergy());
        pBlock.setText(""+player.getBlock());
        eBlock.setText("" + enemy.getBlock());
        pStatus.setText(player.listStatuses());
        eStatus.setText("<html>" + enemy.listStatuses() + "</html>");
    }

    public JPanel createCardPanel(Card c, boolean usable){
        JPanel cardPanel = new JPanel();
        JPanel surroundingPanel = new JPanel();
        surroundingPanel.setLayout(new BoxLayout(surroundingPanel, BoxLayout.Y_AXIS));
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setPreferredSize(new Dimension(140, 250));
        cardPanel.setBorder(BorderFactory.createLineBorder(c.getType().getColor(c.getType()),5)); //https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        JLabel titleL = new JLabel("<html><center>"+c.getTitle() + " (" + c.getType().toString()+")"+"</html>"); //https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextArea.html
        titleL.setFont(new Font("Chicago", Font.BOLD,12));
        cardPanel.add(titleL, BorderLayout.NORTH);
        JLabel descL = new JLabel("<html><center><font size='3' color=black> "+ c.getDescription() + "</font> <font size='4'color=blue>"+ " (" +c.getCost() + ")" + "</font></html>");

        surroundingPanel.add(new JLabel(c.cardArt));
        surroundingPanel.add(descL);

        cardPanel.add(surroundingPanel);
        if (usable&&!c.isNull) {
            JButton x = new JButton("Use");
            x.addActionListener(new ActionListener() {//this actionlistener erases the card from UI, updates energy, does other card related actions
                public void actionPerformed(ActionEvent ae) {
                    if(c.getCost()<=player.getEnergy()){
                        player.cardsPlayed++;
                        if(c.getType()==CardType.STAR){
                            player.starCardsPlayed++;
                        }
                        cardsRow.remove(cardPanel);
                        player.addToDiscard(c);
                        player.gainEnergy(c.getCost()*-1);
                        if(c.getCanExhaust()){
                            player.loseCardDiscard(c);
                        }
                        if(c.getDrawNum()!=0){ //if player draws from deck
                            for (int i = 0; i < c.getDrawNum(); i++) {
                                Card f = player.drawCard();
                                cardsRow.add(createCardPanel(f, true));
                                cardsRow.add(Box.createRigidArea(new Dimension(5, 0)));
                            }
                        }
                        if(c.willAdd){ //if player adds a card to deck
                            setUpCards();
                        }
                        if(enemy.checkIfDead()){
                            RewardUI rewardUI = new RewardUI(player);
                            enemy.gainHealth(1000);
                            mainScene.setVisible(false);
                            mainScene.dispose();
                            return;
                        }
                        player.trySoulChance();
                    }
                    updateEntities();
                }
            });
            x.addActionListener(c.getAction()); //this actionlistener is the specific action in the card
            cardPanel.add(x, BorderLayout.SOUTH);
        }
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                cardPanel.getBorder(),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        return cardPanel;
    }

    public void setUpCards() {
        cardsRow.removeAll();
        ArrayList<Card> hand = player.hand;
        for (int i = 0; i < hand.size(); i++) {
            JPanel x = createCardPanel(hand.get(i), true);
            cardsRow.add(x);
            cardsRow.add(Box.createRigidArea(new Dimension(5, 0))); //https://stackoverflow.com/questions/8335997/how-can-i-add-a-space-in-between-two-buttons-in-a-boxlayout
        }
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

        BattleUI battleUI = new BattleUI(player, enemy);
    }
}
