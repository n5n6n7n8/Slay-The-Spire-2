import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CardList {
    private ArrayList<Card> allCards = new ArrayList<Card>();

    public CardList(Player player, Enemy enemy){//List of all cards
        //The card with index 0 should not be called unless there is error in code


        //https://www.w3schools.com/java/java_lambda.asp
        //Also, intellij autofill suggested lambdas
        allCards.add(new Card("IndexOutOfBounds", CardType.SPECIAL, "An error occurred", 99, e -> {
            if(player.getEnergy()>=99){
                enemy.loseHealth(100);
            }
        }));

        allCards.add(new Card("Attack", CardType.BASIC, "Deal 5 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(5);
            }
        }));
        allCards.add(new Card("Defend", CardType.BASIC, "Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(5);

            }
        }));
        allCards.add(new Card("Slice", CardType.ATTACK, "Deal 8 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(8);
            }
        }));
        allCards.add(new Card("Pummel", CardType.ATTACK, "Deal 12 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(12);
            }
        }));
        allCards.add(new Card("Serrated Dice", CardType.ATTACK, "Deal random number (1-6) dmg three times", 2, e -> {
            if(player.getEnergy()>=2){
                int x = 0;
                x += (int) (Math.random()*6)+1;
                x += (int) (Math.random()*6)+1;
                x += (int) (Math.random()*6)+1;
                enemy.loseHealth(x);
            }
        }));
        allCards.add(new Card("Flechettes", CardType.ATTACK, "For every attack in your deck, deal 3 dmg", 3, e -> {
            if(player.getEnergy()>=3){
                enemy.loseHealth(player.countCardsByType(CardType.ATTACK)*3);
            }
        }));
        allCards.add(new Card("Hit and Run", CardType.ATTACK, "Deal 15 dmg. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(15);
            }
        },true));
        allCards.add(new Card("Kunai", CardType.ATTACK, "Deal 3 dmg. Exhaust", 0, e -> enemy.loseHealth(3), true));
        allCards.add(new Card("Break Block", CardType.ATTACK, "Deal 5 dmg. If enemy still has block, deal extra 7 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(5);
                if(enemy.getBlock()>0){
                    enemy.loseHealth(7);
                }
            }
        }));
        allCards.add(new Card("Boomerang", CardType.ATTACK, "Deal 6 dmg. For the next 2 turns, deal 6 dmg", 3, e -> {
            if(player.getEnergy()>=3){
                enemy.loseHealth(6);
                player.boomerangCounter=2;
            }
        }));
        allCards.add(new Card("Infinite Gun", CardType.ATTACK, "Deal "+ player.infiniteGunDMG + " dmg. Increase this card's damage by 1", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(player.infiniteGunDMG);
                player.infiniteGunDMG+=1;
                allCards.get(11).setDescription("Deal "+ player.infiniteGunDMG + " dmg. Increase this card's damage by 1. Exhaust");
            }
        }));
        allCards.add(new Card("Barricade", CardType.BLOCK, "Gain 8 block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(8);
                player.gainSoul(1);
            }
        }));
        allCards.add(new Card("Rampart", CardType.BLOCK, "Gain 12 block", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(12);
                player.gainSoul(1);
            }
        }));
        allCards.add(new Card("Steel Dice", CardType.BLOCK, "Gain a random number (4-12) block", 2, e -> {
            if(player.getEnergy()>=2){
                int i = (int) (Math.random()*8) + 4;
                player.gainBlock(i);
            }
        }));
        allCards.add(new Card("Card Counting", CardType.BLOCK, "For every block card in the deck, gain 3 block", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(player.countCardsByType(CardType.BLOCK));
            }
        }));
        allCards.add(new Card("Bastion", CardType.BLOCK, "Gain 10 block and end your turn", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(player.countCardsByType(CardType.BLOCK));
            }
        }));
        allCards.add(new Card("Bullet Time", CardType.BLOCK, "Gain 13 block and add 3 Kunais to your hand", 3, e -> {
            if(player.getEnergy()>=3){
                player.gainBlock(13);
                player.gainCardDraw(8);
                player.gainCardDraw(8);
                player.gainCardDraw(8);
            }
        }));
        allCards.add(new Card("Hedgehog", CardType.BLOCK, "Deal 6 dmg and gain 6 block", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(6);
                enemy.loseHealth(6);
            }
        }));
        allCards.add(new Card("Panic Button", CardType.BLOCK, "Gain 28 block", 4, e -> {
            if(player.getEnergy()>=4){
                player.gainBlock(32);
            }
        }));
        allCards.add(new Card("Pile O' Shields", CardType.BLOCK, "Gain " + player.pileOShields + " . Add a copy of this card to deck with +2 block. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(player.pileOShields);
                player.pileOShields+=2;
                allCards.get(20).setDescription("Deal "+ player.pileOShields + " dmg. Increase this card's damage by 1. Exhaust");
            }
        }));
        allCards.add(new Card("Frugal", CardType.BLOCK, "Gain 10 block. Keep all block until next turn", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(10);
                player.setWillKeepBlock(true);
            }
        }));
        allCards.add(new Card("Ad Hominem", CardType.BLOCK, "Erase enemy's block. Gain 7 block", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainBlock(enemy.getBlock()*-1);
                player.gainBlock(7);
            }
        }));


        allCards.add(new Card("Vial", CardType.POISON, "Apply 3 poison", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(3);
            }
        }));
        allCards.add(new Card("Dart Frog", CardType.POISON, "Apply 3 poison, deal 5 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainPoison(3);
                enemy.loseHealth(5,CardType.POISON);
            }
        }));
        allCards.add(new Card("Acid Rain", CardType.POISON, "Apply 6 poison to enemies, apply 3 poison to self", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainPoison(6);
                player.gainPoison(3);
            }
        }));
        allCards.add(new Card("Catalyst", CardType.POISON, "Double the enemy's poison", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainPoison(enemy.getPoison()*2);
            }
        }));
        allCards.add(new Card("Parasitism", CardType.POISON, "Deal 5 dmg. Heal unblocked dmg and apply poison unblocked dmg", 2, e -> {
            if(player.getEnergy()>=2){
                int x = enemy.loseHealth(5);
                player.gainHealth(x);
                enemy.gainPoison(x);
            }
        }));
        allCards.add(new Card("Plague Knight", CardType.POISON, "Apply 5 poison, deal 6 dmg, gain 3 block. SC: 2. Exhaust", 2, e -> {
            if(player.getEnergy()>=2&&player.getSoul()>=2){
                enemy.gainPoison(5);
                enemy.loseHealth(6, CardType.POISON);
                player.gainBlock(3);
                player.gainSoul(-2);
            }
        },true));
        allCards.add(new Card("Poisoned Chalice", CardType.POISON, "In 3 turns, apply 10 poison. Exhaust", 3, e -> {
            if(player.getEnergy()>=3){
                enemy.poisonChalice = 3;
            }
        },true));
        allCards.add(new Card("Leaky Flask", CardType.POISON, "Apply 7 poison but gain 3 fragile. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(7);
                player.gainFragile(3);
            }
        },true));
        allCards.add(new Card("The Silent", CardType.POISON, "Gain 1 soul. Add 3 Kunai to deck. Apply 5 poison", 4, e -> {
            if(player.getEnergy()>=4){
                player.gainSoul(1);
                player.gainCardDraw(8);
                player.gainCardDraw(8);
                player.gainCardDraw(8);
                enemy.gainPoison(5);
            }
        }));
        allCards.add(new Card("Antidote", CardType.POISON, "If you have poison, gain 4 health. Gain 3 block", 0, e -> {
            if(player.getEnergy()>=0){
                if(player.getPoison()>0){
                    player.gainHealth(4);
                    player.gainBlock(3);
                }
            }
        }));
        allCards.add(new Card("Ghost Note", CardType.HEAL, "Gain 2 soul. Add 1 ruler of everything to deck. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainSoul(2);
                player.gainCardDraw(72);
            }
        }));
        allCards.add(new Card("Take Five", CardType.HEAL, "Heal 5, attack for 5, apply 5 fragile, apply 5 poison, gain 5 crit dmg. Costs 3 soul. Exhaust", 5, e -> {
            if(player.getEnergy()>=5&&player.getSoul()>=3){
                player.gainHealth(5);
                enemy.loseHealth(5);
                enemy.gainFragile(5);
                enemy.gainPoison(5);
                enemy.gainCritDmg(5);
                player.gainSoul(-3);
            }
        },true));
        allCards.add(new Card("Shepard Tone", CardType.HEAL, "Gain 5 HP, every turn this battle lose 1 HP. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainHealth(5);
                player.shepardTone=true;
            }
        },true));
        allCards.add(new Card("Panacea", CardType.HEAL, "Remove all negative status effects. Costs 1 soul", 3, e -> {
            if(player.getEnergy()>=3){
                player.gainFragile(player.getFragile()*-1);
                player.gainPoison(player.getPoison()*-1);
                player.gainSoul(-1);
            }
        },true));
        allCards.add(new Card("Sine Wave", CardType.HEAL, "On an even turn, gain 4 HP. On an odd turn, gain 2 HP", 2, e -> {
            if(player.getEnergy()>=2){
                if(player.turnNumber%2==0){
                    player.gainHealth(4);
                }
                else{
                    player.gainHealth(2);
                }
            }
        },true));
        allCards.add(new Card("Negative Harmony", CardType.HEAL, "Heal amount of fragile. Lose health amount of strength. Gain 2 soul", 1, e -> {
            if(player.getEnergy()>=2){
                player.gainHealth(player.getFragile());
                player.loseHealthRaw(player.getStrength());
                player.gainSoul(2);
            }
        }));
        allCards.add(new Card("Power Chord", CardType.HEAL, "Gain 4 strength. Heal 4. Costs 2 soul", 4, e -> {
            if(player.getEnergy()>=4){
                player.gainStrength(4);
                player.gainHealth(4);
                player.gainSoul(2);
            }
        }));
        allCards.add(new Card("Lydian", CardType.HEAL, "Deal 10 dmg. Gain 1 soul. If enemy is below 20% health, gain 2 max HP. Exhaust", 3, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(10);
                player.gainSoul(1);
                if((((double) enemy.getCurrentHealth())/enemy.getMaxHealth())<=20.0){
                    player.gainMaxHealth(2);
                }
            }
        }, true));
        allCards.add(new Card("Power Chord", CardType.HEAL, "Gain 4 strength. Heal 4. Costs 2 soul", 4, e -> {
            if(player.getEnergy()>=4){
                player.gainStrength(4);
                player.gainHealth(4);
                player.gainSoul(2);
            }
        }));
        allCards.add(new Card("Banshee", CardType.FRAGILE, "Apply 2 fragile. Gain 1 soul", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainFragile(2);
                player.gainSoul(1);
            }
        }));
        allCards.add(new Card("Force Field", CardType.FRAGILE, "Apply 2 fragile. Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainFragile(2);
                player.gainBlock(5);
            }
        }));
        allCards.add(new Card("Towers of Glass", CardType.FRAGILE, "Gain 10 block. If you have fragile, gain 11 more block", 3, e -> {
            if(player.getEnergy()>=3){
                player.gainBlock(10);
                if(player.getFragile()>0){
                    player.gainBlock(11);
                }
            }
        }));
        allCards.add(new Card("Demon Core", CardType.FRAGILE, "Apply 4 fragile to enemy and 4 fragile to self. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainFragile(4);
                enemy.gainFragile(4);
            }
        }));
        allCards.add(new Card("Shatter Glass", CardType.FRAGILE, "Enemy loses 10 block. Deal 6 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainBlock(-10);
                enemy.loseHealth(4, CardType.FRAGILE);
            }
        }));
//        allCards.add(new Card("Personal Attack", CardType.FRAGILE, "Apply 2 fragile. Add 2 Box Jellyfish to deck", 2, e -> {
//            if(player.getEnergy()>=2){
//                enemy.gainFragile(2);
//                player.gainCardDraw(105);
//                player.gainCardDraw(105);
//            }
//        }));
        allCards.add(new Card("Oh the Misery", CardType.FRAGILE, "Gain 11 block for 3 turns. Gain 4 fragile. Heal 1. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainFragile(4);
                player.gainHealth(1);
            }
        }));
        allCards.add(new Card("Kitchen Gun", CardType.FRAGILE, "Set enemy HP to 1 if it has 9 fragile or more. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                if(enemy.getFragile()>=9){
                    enemy.loseHealth(enemy.getCurrentHealth()+1);
                }
            }
        },true));
        allCards.add(new Card("Implode", CardType.FRAGILE, "Enemy fragile x1.5. Exhaust", 0, e -> {
            if(player.getEnergy()>=2){
                enemy.gainFragile((int)(enemy.getFragile()/2));
            }
        },true));
        allCards.add(new Card("Milk", CardType.FRAGILE, "Deal 5 dmg. Apply 1 fragile.", 1, e -> {
            if(player.getEnergy()>=2){
                enemy.gainFragile((int)(enemy.getFragile()/2));
            }
        },true));
        allCards.add(new Card("Gunpoint", CardType.CRIT, "Deal 5 dmg. crit here.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCritDmg(1);
                enemy.loseHealth(player.getCritDmg(),CardType.CRIT);
            }
        },true));
        allCards.add(new Card("Gunpoint", CardType.CRIT, "Deal 5 dmg. crit here.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCritDmg(1);
                enemy.loseHealth(player.getCritDmg(),CardType.CRIT);
            }
        },true));
        allCards.add(new Card("Gunpoint", CardType.CRIT, "Deal 5 dmg. crit here.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCritDmg(1);
                enemy.loseHealth(player.getCritDmg(),CardType.CRIT);
            }
        },true));
        allCards.add(new Card("Gunpoint", CardType.CRIT, "Deal 5 dmg. crit here.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCritDmg(1);
                enemy.loseHealth(player.getCritDmg(),CardType.CRIT);
            }
        },true));
        allCards.add(new Card("Gunpoint", CardType.CRIT, "Deal 5 dmg. crit here.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCritDmg(1);
                enemy.loseHealth(player.getCritDmg(),CardType.CRIT);
            }
        },true));
        allCards.add(new Card("Gunpoint", CardType.CRIT, "Deal 5 dmg. crit here.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCritDmg(1);
                enemy.loseHealth(player.getCritDmg(),CardType.CRIT);
            }
        },true));
        allCards.add(new Card("Gunpoint", CardType.CRIT, "Deal 5 dmg. crit here.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCritDmg(1);
                enemy.loseHealth(player.getCritDmg(),CardType.CRIT);
            }
        },true));
        allCards.add(new Card("Gunpoint", CardType.CRIT, "Deal 5 dmg. crit here.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCritDmg(1);
                enemy.loseHealth(player.getCritDmg(),CardType.CRIT);
            }
        },true));
        allCards.add(new Card("Gunpoint", CardType.CRIT, "Deal 5 dmg. crit here.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCritDmg(1);
                enemy.loseHealth(player.getCritDmg(),CardType.CRIT);
            }
        },true));
        allCards.add(new Card("Dream Nail", CardType.SOUL, "Gain 2 block. Apply 2 fragile. Costs 1 soul", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(2);
                enemy.gainFragile(2);
                player.gainSoul(-1);
            }
        }));
        allCards.add(new Card("Focus", CardType.SOUL, "Heal 3. Costs 3 soul. Exhaust", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainHealth(3);
                player.gainSoul(-3);
            }
        },true));
        allCards.add(new Card("Focus", CardType.SOUL, "Heal 3. Costs 3 soul. Exhaust", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainHealth(3);
                player.gainSoul(-3);
            }
        },true));
        allCards.add(new Card("Midas Touch", CardType.SOUL, "Deal 2 dmg. Gain 2 block. Gain gold equal to enemy health. costs 1 soul. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(2);
                player.gainBlock(2);
                player.gainGold(enemy.getCurrentHealth());
            }
        },true));
        allCards.add(new Card("Beemerang", CardType.SOUL, "For every soul, deal 2 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(player.getSoul()*2);
            }
        }));
        allCards.add(new Card("Ginso Tree", CardType.SOUL, "Remove all poison from self. Gain 3 soul", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainPoison(player.getPoison()*-1);
                player.gainSoul(3);
            }
        }));
        allCards.add(new Card("Quantum Moon", CardType.SOUL, "Random number 1-6 X. Gain X energy. Costs 1 soul. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                int x = (int) (Math.random()*6)+1;
                player.gainEnergy(x);
                player.gainSoul(-1);
            }
        }));
        allCards.add(new Card("Exagryph", CardType.SOUL, "Add 3 Kunai to deck. Costs 2 Soul. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCardDraw(10);
                player.gainCardDraw(10);
                player.gainCardDraw(10);
                player.gainSoul(-2);
            }
        }));
        allCards.add(new Card("Charged Creeper", CardType.SOUL, "Apply 4 fragile. In 3 turns, deal 15 damage, deal 5 damage to self. Costs 2 soul", 4, e -> {
            if(player.getEnergy()>=1){
                player.gainSoul(-2);
                enemy.gainFragile(4);
                enemy.chargedCreeper = 3;
            }
        }));
        allCards.add(new Card("Rampart", CardType.SOUL, "Gain 12 block. Gain 1 soul.", 2, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(12);
                player.gainSoul(1);
            }
        }));
        allCards.add(new Card("A Furious Cocktail", CardType.SOUL, "Apply one of everything to self. Gain 3 soul. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainSoul(1);
                player.gainStrength(1);
                player.gainFragile(1);
                player.gainPoison(1);
                player.gainGold(1);
                player.gainBlock(1);
            }
        }));
        allCards.add(new Card("Luma", CardType.SOUL, "Gain 2 energy. Gain 5 block. Remove all fragile. Costs 2 soul", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainEnergy(2);
                player.gainBlock(5);
                player.gainFragile(player.getFragile()*-1);
                player.gainSoul(-2);
            }
        }));
        allCards.add(new Card("Dead Cells", CardType.SOUL, "If under 50% health, gain 1 strength, gain 5 block. Keep all block till next turn. Costs 2 soul.", 0, e -> {
            if(player.getEnergy()>=0){
                if(player.getMaxHealth()/2>player.getCurrentHealth()){
                    player.gainStrength(1);
                    player.gainBlock(5);
                    player.setWillKeepBlock(true);
                }
            }
        }));
        allCards.add(new Card("Dead Cells", CardType.SOUL, "If under 50% health, gain 1 strength, gain 5 block. Keep all block till next turn. Costs 2 soul.", 0, e -> {
            if(player.getEnergy()>=0){
                if(player.getMaxHealth()/2>player.getCurrentHealth()){
                    player.gainStrength(1);
                    player.gainBlock(5);
                    player.setWillKeepBlock(true);
                }
            }
        }));
        allCards.add(new Card("Ruler of Everything", CardType.SOUL, "Heal 5. Exhaust", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainHealth(5);
            }
        },true));
        allCards.add(new Card("Slice", CardType.ATTACK, "Deal 8 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(8);
            }
        }));
        allCards.add(new Card("Slice", CardType.ATTACK, "Deal 8 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(8);
            }
        }));
        allCards.add(new Card("Slice", CardType.ATTACK, "Deal 8 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(8);
            }
        }));
        allCards.add(new Card("Slice", CardType.ATTACK, "Deal 8 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(8);
            }
        }));
        allCards.add(new Card("Serrated Dice", CardType.ATTACK, "Deal random number (1-6) dmg three times", 3, e -> {
            if(player.getEnergy()>=3){
                int x = 0;
                x += (int) (Math.random()*6)+1;
                x += (int) (Math.random()*6)+1;
                x += (int) (Math.random()*6)+1;
                enemy.loseHealth(x);
            }
        }));
        allCards.add(new Card("Flechettes", CardType.ATTACK, "For every attack in your deck, deal 3 dmg", 3, e -> {
            if(player.getEnergy()>=3){
                enemy.loseHealth(player.countCardsByType(CardType.ATTACK)*3);
            }
        }));
        allCards.add(new Card("Break Block", CardType.ATTACK, "Deal 4 dmg. If enemy still has block, deal extra 7 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(4);
                if(enemy.getBlock()>0){
                    enemy.loseHealth(7);
                }
            }
        }));
        allCards.add(new Card("Break Block", CardType.ATTACK, "Deal 4 dmg. If enemy still has block, deal extra 7 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(4);
                if(enemy.getBlock()>0){
                    enemy.loseHealth(7);
                }
            }
        }));
        allCards.add(new Card("Vial", CardType.POISON, "Apply 3 poison", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(3);
            }
        }));
        allCards.add(new Card("Dart Frog", CardType.POISON, "Apply 3 poison, deal 5 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainPoison(3);
                enemy.loseHealth(5,CardType.POISON);
            }
        }));
        allCards.add(new Card("Vial", CardType.POISON, "Apply 3 poison", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(3);
            }
        }));
        allCards.add(new Card("Dart Frog", CardType.POISON, "Apply 3 poison, deal 5 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainPoison(3);
                enemy.loseHealth(5,CardType.POISON);
            }
        }));
        allCards.add(new Card("Sine Wave", CardType.HEAL, "On an even turn, gain 4 HP. On an odd turn, gain 2 HP", 2, e -> {
            if(player.getEnergy()>=2){
                if(player.turnNumber%2==0){
                    player.gainHealth(4);
                }
                else{
                    player.gainHealth(2);
                }
            }
        },true));
        allCards.add(new Card("Banshee", CardType.FRAGILE, "Apply 2 fragile. Gain 1 soul", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainFragile(2);
                player.gainSoul(1);
            }
        }));
        allCards.add(new Card("Force Field", CardType.FRAGILE, "Apply 2 fragile. Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainFragile(2);
                player.gainBlock(5);
            }
        }));
        allCards.add(new Card("Banshee", CardType.FRAGILE, "Apply 2 fragile. Gain 1 soul", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainFragile(2);
                player.gainSoul(1);
            }
        }));
        allCards.add(new Card("Force Field", CardType.FRAGILE, "Apply 2 fragile. Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainFragile(2);
                player.gainBlock(5);
            }
        }));
        allCards.add(new Card("Hedgehog", CardType.BLOCK, "Deal 6 dmg and gain 6 block", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(6);
                enemy.loseHealth(6);
            }
        }));
        allCards.add(new Card("Hedgehog", CardType.BLOCK, "Deal 6 dmg and gain 6 block", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(6);
                enemy.loseHealth(6);
            }
        }));
        allCards.add(new Card("Hedgehog", CardType.BLOCK, "Deal 6 dmg and gain 6 block", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(6);
                enemy.loseHealth(6);
            }
        }));
        allCards.add(new Card("Attack", CardType.BASIC, "Deal 5 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(5);
            }
        }));
        allCards.add(new Card("Defend", CardType.BASIC, "Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(5);

            }
        }));
        allCards.add(new Card("Attack", CardType.BASIC, "Deal 5 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(5);
            }
        }));
        allCards.add(new Card("Defend", CardType.BASIC, "Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(5);

            }
        }));
        allCards.add(new Card("Attack", CardType.BASIC, "Deal 5 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(5);
            }
        }));
        allCards.add(new Card("Defend", CardType.BASIC, "Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(5);

            }
        }));
        allCards.add(new Card("Attack", CardType.BASIC, "Deal 5 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(5);
            }
        }));
        allCards.add(new Card("Defend", CardType.BASIC, "Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(5);

            }
        }));
    }

    public Card getCard(int i){
        if(i<0||i>=allCards.size()){
            return allCards.get(0);
        }
        else{
            return allCards.get(i);
        }
    }
    public int getSize(){
        return allCards.size();
    }
    public Card getCard(Card other){
        for (int i = 0; i < allCards.size(); i++) {
            if(other.equals(allCards.get(i))){
                return allCards.get(i);
            }
        }
        return allCards.get(0);
    }
}
