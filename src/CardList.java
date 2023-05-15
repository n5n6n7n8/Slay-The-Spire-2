
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
        }, "src/CS StS art/cards/kitchengun.png"));

        allCards.add(new Card("Attack", CardType.ATTACK, "Deal 5 dmg", 1, e -> {
            if (player.getEnergy() >= 1) {
                enemy.loseHealth(5+player.getStrength());
            }
        }, "src/CS StS art/cards/kitchengun.png"));
        allCards.add(new Card("Defend", CardType.BLOCK, "Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(5);

            }
        }, "src/CS StS art/cards/kitchengun.png"));
        allCards.add(new Card("Slice", CardType.ATTACK, "Deal 8 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(8+player.getStrength());
            }
        }, "src/CS StS art/cards/slice.jpeg"));
        allCards.add(new Card("Pummel", CardType.ATTACK, "Deal 12 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(12+player.getStrength());
            }
        }, "src/CS StS art/cards/pummel.png"));
        allCards.add(new Card("Serrated Dice", CardType.ATTACK, "Deal random number (1-6) dmg three times.", 2, e -> {
            if(player.getEnergy()>=2){
                int x = 0;
                x += ((int) (Math.random()*6)+1) + player.getStrength();
                x += ((int) (Math.random()*6)+1) + player.getStrength();
                x += ((int) (Math.random()*6)+1) + player.getStrength();
                enemy.loseHealth(x);
            }
        }, "src/CS StS art/cards/serrateddice.png"));
        allCards.add(new Card("Hit and Run", CardType.ATTACK, "Deal 15 dmg. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(15+player.getStrength());
            }
        },true, "src/CS StS art/cards/hitandrun.png"));
        allCards.add(new Card("Kunai", CardType.STAR, "Deal 3 dmg. Exhaust", 0, e -> enemy.loseHealth(3+player.getStrength()), true, "src/CS StS art/cards/kunai.png"));
        allCards.add(new Card("Break Block", CardType.ATTACK, "Deal 5 dmg. If enemy still has block, deal extra 7 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(5+player.getStrength());
                if(enemy.getBlock()>0){
                    enemy.loseHealth(7+player.getStrength());
                }
            }
        }, "src/CS StS art/cards/breakblock.jpeg"));
        allCards.add(new Card("Boomerang", CardType.ATTACK, "Deal 6 dmg. For the next 2 turns, deal 6 dmg", 3, e -> {
            if(player.getEnergy()>=3){
                enemy.loseHealth(6+player.getStrength());
                enemy.boomerangCounter=2;
            }
        }, "src/CS StS art/cards/boomerang.png"));
        allCards.add(new Card("Infinite Gun", CardType.ATTACK, "Deal "+ player.infiniteGunDMG + " dmg. Increase this card's damage by 1", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(player.infiniteGunDMG+player.getStrength());
                player.infiniteGunDMG+=1;
                allCards.get(11).setDescription("Deal "+ player.infiniteGunDMG + " dmg. Increase this card's damage by 1. Exhaust");
            }
        }, "src/CS StS art/cards/infinite.png"));
        allCards.add(new Card("Flechettes", CardType.ATTACK, "For every attack in your deck, deal 2 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(player.countCardsByType(CardType.ATTACK)*2+(player.getStrength()));
            }
        }, "src/CS StS art/cards/flechettes.png"));
        allCards.add(new Card("Barricade", CardType.BLOCK, "Gain 8 block, gain 1 soul.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(8);
                player.gainSoul(1);
            }
        }, "src/CS StS art/cards/barricade.png"));
        allCards.add(new Card("Rampart", CardType.BLOCK, "Gain 14 block", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(14);
            }
        }, "src/CS StS art/cards/rampart.png"));
        allCards.add(new Card("Steel Dice", CardType.BLOCK, "Gain a random number (4-12) block", 2, e -> {
            if(player.getEnergy()>=2){
                int i = (int) (Math.random()*8) + 4;
                player.gainBlock(i);
            }
        }, "src/CS StS art/cards/steeldice.png"));
        allCards.add(new Card("Card Counting", CardType.BLOCK, "For every block card in the deck, gain 3 block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(player.countCardsByType(CardType.BLOCK));
            }
        }, "src/CS StS art/cards/cardcounting.png"));
        allCards.add(new Card("Bastion", CardType.BLOCK, "Gain 10 block and lose energy", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(player.countCardsByType(CardType.BLOCK));
                player.gainEnergy(player.getEnergy()*-1);
            }
        }, "src/CS StS art/cards/bastion.png"));
        allCards.add(new Card("Hedgehog", CardType.ATTACK, "Deal 6 dmg and gain 6 block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(6);
                enemy.loseHealth(6);
            }
        },"src/CS StS art/cards/hedgehog.png"));
        allCards.add(new Card("Panic Button", CardType.BLOCK, "Gain 32 block", 3, e -> {
            if(player.getEnergy()>=3){
                player.gainBlock(32);
            }
        }, true, "src/CS StS art/cards/panicbutton.png"));
        allCards.add(new Card("Frugal", CardType.BLOCK, "Gain 10 block. Keep all block until next turn", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(10);
                player.setWillKeepBlock(true);
            }
        }, "src/CS StS art/cards/frugal.png"));
        allCards.add(new Card("Ad Hominem", CardType.BLOCK, "Erase enemy's block. Gain 7 block", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainBlock(enemy.getBlock()*-1);
                player.gainBlock(7);
            }
        }, "src/CS StS art/cards/adhominem.png"));

        allCards.add(new Card("Bullet Time", CardType.BLOCK, "Gain 14 block and add 3 Kunais to your hand", 3, e -> {
            if(player.getEnergy()>=3){
                player.gainBlock(14);
                player.gainCardDraw(8);
                player.gainCardDraw(8);
                player.gainCardDraw(8);
            }
        },true, "src/CS StS art/cards/bullet time.png"));
        allCards.add(new Card("Vial", CardType.POISON, "Apply 3 poison, gain 2 soul", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(3);
                player.gainSoul(2);
            }
        }, "src/CS StS art/cards/vial.png"));
        allCards.add(new Card("Dart Frog", CardType.POISON, "Apply 4 poison, deal 6 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainPoison(4);
                enemy.loseHealth(6+player.getStrength());
            }
        }, "src/CS StS art/cards/dartfrog.png"));
        allCards.add(new Card("Acid Rain", CardType.POISON, "Apply 6 poison to enemies, apply 3 poison to self", 1, e -> {
            if(player.getEnergy()>=2){
                enemy.gainPoison(6);
                player.gainPoison(3);
            }
        },"src/CS StS art/cards/acidrain.png"));
        allCards.add(new Card("Catalyst", CardType.POISON, "Double the enemy's poison", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainPoison(enemy.getPoison()*2);
            }
        }, "src/CS StS art/cards/catalyst.png"));
        allCards.add(new Card("Parasitism", CardType.POISON, "Deal 5 dmg. Heal unblocked dmg and apply poison unblocked dmg", 2, e -> {
            if(player.getEnergy()>=2){
                int x = enemy.loseHealth(5+player.getStrength());
                player.gainHealth(x);
                enemy.gainPoison(x);
            }
        }, "src/CS StS art/cards/parasitism.png"));
        allCards.add(new Card("Plague Knight", CardType.POISON, "Apply 5 poison, gain 6 block, draw 1 card. costs 1 soul. Exhaust", 1, e -> {
            if(player.getEnergy()>=2&&player.getSoul()>=2){
                enemy.gainPoison(5);
                player.gainBlock(6);
                player.gainSoul(-1);
            }
        },true,1, "src/CS StS art/cards/plague.png"));
        allCards.add(new Card("Poisoned Chalice", CardType.POISON, "In 3 turns, apply 10 poison. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.poisonChalice = 3;
            }
        },true, "src/CS StS art/cards/poisonchalice.png"));
        allCards.add(new Card("Leaky Flask", CardType.POISON, "Apply 7 poison but gain 3 fragile. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(7);
                player.gainFragile(3);
            }
        },true, "src/CS StS art/cards/leakyflask.png"));
        allCards.add(new Card("Antidote", CardType.POISON, "If you have poison, gain 3 health. Gain 4 block", 0, e -> {
            if(player.getEnergy()>=0){
                if(player.getPoison()>0){
                    player.gainHealth(3);
                    player.gainBlock(4);
                }
            }
        }, "src/CS StS art/cards/antidote.png"));
        allCards.add(new Card("Banshee", CardType.FRAGILE, "Apply 2 fragile. Gain 1 soul", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainFragile(2);
                player.gainSoul(1);
            }
        }, "src/CS StS art/cards/banshee.png"));
        allCards.add(new Card("Force Field", CardType.FRAGILE, "Apply 2 fragile. Gain 5 block", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainFragile(2);
                player.gainBlock(5);
            }
        }, "src/CS StS art/cards/forcefield.png"));
        allCards.add(new Card("Towers of Glass", CardType.FRAGILE, "Gain 6 block. If you have fragile, gain 7 more block", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(6);
                if(player.getFragile()>0){
                    player.gainBlock(7);
                }
            }
        }, "src/CS StS art/cards/towerofglass.png"));
        allCards.add(new Card("Demon Core", CardType.FRAGILE, "Apply 4 fragile to enemy and 4 fragile to self. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainFragile(4);
                enemy.gainFragile(4);
            }
        }, "src/CS StS art/cards/demoncore.png"));
        allCards.add(new Card("Shatter Glass", CardType.FRAGILE, "Enemy loses 10 block. Deal 6 dmg", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainBlock(-10);
                enemy.loseHealth(6 + player.getStrength());
            }
        }, "src/CS StS art/cards/shatterglass.png"));
//        allCards.add(new Card("Personal Attack", CardType.FRAGILE, "Apply 2 fragile. Add 2 Box Jellyfish to deck", 2, e -> {
//            if(player.getEnergy()>=2){
//                enemy.gainFragile(2);
//                player.gainCardDraw(105);
//                player.gainCardDraw(105);
//            }
//        }));
        allCards.add(new Card("Kitchen Gun", CardType.FRAGILE, "Set enemy HP to 1 if it has 9 fragile or more. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                if(enemy.getFragile()>=9){
                    enemy.loseHealth(enemy.getCurrentHealth()-1);
                }
            }
        },true,"src/CS StS art/cards/kitchengun.png"));
        allCards.add(new Card("Implode", CardType.FRAGILE, "Enemy fragile x1.5. Exhaust", 0, e -> {
            if(player.getEnergy()>=0){
                enemy.gainFragile(enemy.getFragile()/2);
            }
        },true, "src/CS StS art/cards/implode.png"));
        allCards.add(new Card("Dog", CardType.FRAGILE, "Deal 4 dmg. Apply 1 fragile.", 1, e -> {
            if(player.getEnergy()>=2){
                enemy.gainFragile((int)(enemy.getFragile()/2));
            }
        }, "src/CS StS art/cards/doge.png"));
        allCards.add(new Card("Dream Nail", CardType.SOUL, "Increase chance to get soul each turn. Apply 2 fragile. Exhaust.", 2, e -> {
            if(player.getEnergy()>=1){
                player.gainSoulChance(2);
                enemy.gainFragile(2);
                player.gainSoul(-1);
            }
        }, true, "src/CS StS art/cards/dream nail.png"));
        allCards.add(new Card("Focus", CardType.SOUL, "Heal 3. Costs 2 soul. Exhaust", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainHealth(3);
                player.gainSoul(-2);
            }
        },true, "src/CS StS art/cards/focus.png"));
        allCards.add(new Card("Focus", CardType.SOUL, "Heal 3. Costs 2 soul. Exhaust", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainHealth(3);
                player.gainSoul(-2);
            }
        },true, "src/CS StS art/cards/focus.png"));
        allCards.add(new Card("Midas Touch", CardType.SOUL, "Deal 12 dmg. Gain gold equal to half of enemy health. costs 1 soul. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(12 + player.getStrength());
                player.gainGold(enemy.getCurrentHealth()/2);
                player.gainSoul(-1);
            }
        },true, "src/CS StS art/cards/midas.png"));
        allCards.add(new Card("Beemerang", CardType.SOUL, "Deal 3 dmg. For every soul, deal 2 dmg", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(player.getStrength());
                enemy.loseHealth(player.getSoul()*2 + (player.getStrength()));
            }
        }, "src/CS StS art/cards/beemerang.png"));
        allCards.add(new Card("Ginso Tree", CardType.SOUL, "Remove all negative statuses from self. Gain 1 soul", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainPoison(player.getPoison()*-1);
                player.gainFragile(player.getFragile()*-1);
                player.gainSoul(1);
            }
        }, "src/CS StS art/cards/ginso.png"));
        allCards.add(new Card("Quantum Moon", CardType.SOUL, "Random number 1-6 = X. Gain X energy. Draw 1 card. Exhaust", 3, e -> {
            if(player.getEnergy()>=3){
                int x = (int) (Math.random()*6)+1;
                player.gainEnergy(x);
                player.gainSoul(-1);
            }
        },true,1, "src/CS StS art/cards/quantum.png"));
        allCards.add(new Card("Exagryph", CardType.SOUL, "Add 3 star cards to hand. Costs 2 Soul. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCardHand(player.getValidStarIndex());
                player.gainCardHand(player.getValidStarIndex());
                player.gainCardHand(player.getValidStarIndex());
                player.gainSoul(-2);
            }
        },false, 3, "src/CS StS art/cards/exagryph.png"));
        allCards.add(new Card("Charged Creeper", CardType.SOUL, "Apply 4 fragile. In 3 turns, deal 15 damage to enemy and 5 damage to self. Costs 2 soul", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainSoul(-2);
                enemy.gainFragile(4);
                enemy.chargedCreeper = 3;
            }
        },true, "src/CS StS art/cards/charged_creeper.png"));
        allCards.add(new Card("Amongst Ourselves", CardType.SOUL, "Add a curse to deck. Deal 15 dmg. Increase soul chance.", 1, e -> {
            if(player.getEnergy()>=1){
                //idk
                enemy.loseHealth(15+player.getStrength());
                player.gainSoulChance(1);
            }
        }, "src/CS StS art/cards/amongst.jpeg"));
        allCards.add(new Card("A Furious Cocktail", CardType.SOUL, "Apply one of everything to self. Gain 3 soul. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainSoul(3);
                player.gainStrength(1);
                player.gainFragile(1);
                player.gainPoison(1);
                player.gainGold(1);
                player.gainBlock(1);
            }
        }, "src/CS StS art/cards/furiouscocktail.png"));
        allCards.add(new Card("Golden Path", CardType.SOUL, "Gain 1 energy. Gain 5 Block. Remove all fragile. Costs 2 soul. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                if(player.getMaxHealth()/2>player.getCurrentHealth()){
                    player.gainStrength(1);
                    player.gainBlock(5);
                    player.setWillKeepBlock(true);
                }
            }
        }, true, "src/CS StS art/cards/goldenpath.png"));
        allCards.add(new Card("Dead Cells", CardType.SOUL, "If under 50% health, gain 1 strength, gain 5 block. Keep all block till next turn. Costs 2 soul.", 0, e -> {
            if(player.getEnergy()>=0){
                if(player.getMaxHealth()/2>player.getCurrentHealth()){
                    player.gainStrength(1);
                    player.gainBlock(5);
                    player.setWillKeepBlock(true);
                }
            }
        }, "src/CS StS art/cards/deadcells.png"));
        allCards.add(new Card("CrossCode", CardType.SOUL, "Gain 5 money for each soul card in the deck. Costs 1 soul. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.countCardsByType(CardType.SOUL);
            }
        },true, "src/CS StS art/cards/crosscode.png"));
        allCards.add(new Card("CARD is MORE", CardType.SOUL, "Put a star card in your hand. Gain 1 soul.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCardHand(player.getValidStarIndex());
            }
        },false, 0, true, "src/CS StS art/cards/cardismore.png"));
        allCards.add(new Card("White Space", CardType.SOUL, "Improve soul chance, lose 2 health. Exhaust", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainSoulChance(1);
                player.loseHealth(2);
            }
        }, "src/CS StS art/cards/whitespace.png"));
        //Lose 5% soul chance. Gain 5 soul. Exhaust.
        allCards.add(new Card("Phosphor", CardType.SOUL, "Draw 1 card, gain 1 energy. Costs 1 soul.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainEnergy(1);
                player.gainSoul(-1);

            }
        },true, 1, "src/CS StS art/cards/phosphor.png"));
        allCards.add(new Card("Miasma", CardType.STRENGTH, "Gain 2 strength. Gain 2 soul.", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainStrength(2);
                player.gainSoul(2);
            }
        }, "src/CS StS art/cards/miasma.png"));
        allCards.add(new Card("Ritual", CardType.STRENGTH, "Lose 1 strength. Gain 15 block. Heal 2.", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainStrength(-1);
                player.gainBlock(15);
                player.gainHealth(2);
            }
        }, "src/CS StS art/cards/ritual.png"));
        allCards.add(new Card("Sell your Soul", CardType.STRENGTH, "Lose 6 health. Gain 4 strength. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.loseHealth(6);
                player.gainStrength(4);
            }
        },true, "src/CS StS art/cards/sellyoursoul.png"));
        allCards.add(new Card("Finesse", CardType.STRENGTH, "If you have fragile, gain 3 strength. Exhaust.", 2, e -> {
            if(player.getEnergy()>=2){
                if(player.getFragile()>0){
                    player.gainStrength(3);
                }
            }
        },true, "src/CS StS art/cards/finesse.png"));
        allCards.add(new Card("Paper Airplanes", CardType.STRENGTH, "Gain a Kunai for every strength card you have. Exhaust.", 1, e -> {
            if(player.getEnergy()>=1){
                int x = player.countCardsByType(CardType.STRENGTH);
                for (int i = 0; i < x; i++) {
                    //player.gainCard();
                }
            }
        }, true, 0, true, "src/CS StS art/cards/paperairplanes.png"));
        allCards.add(new Card("Heavy Blade", CardType.STRENGTH, "Deal 14 damage. Strength affects this card 2 times.", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth((int) (14+(player.getStrength()*1.5)));
            }
        }, "src/CS StS art/cards/heavyblade.png"));
        allCards.add(new Card("Eye for an Eye", CardType.STRENGTH, "Gain 1 strength for each card played this turn. Enemy gains 1 strength. exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(3);
            }
        },true, "src/CS StS art/cards/eyeforaneye.png"));
        allCards.add(new Card("Capitalism", CardType.MONEY, "Spend 20 gold, gain 1 energy, draw 1 card. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                enemy.gainPoison(3);
            }
        },true, 1, "src/CS StS art/cards/capitalism.png"));
        allCards.add(new Card("Tomahawk", CardType.MONEY, "Spend 15 gold, deal 10 dmg.", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(3);
            }
        }, "src/CS StS art/cards/tomahawk.png"));
        allCards.add(new Card("Treasure", CardType.MONEY, "Gain 15 gold and 3 HP. Exhaust.", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(3);
            }
        }, true, "src/CS StS art/cards/treasure.png"));
        allCards.add(new Card("Diamond Shield", CardType.MONEY, "Spend 15 gold, gain 10 block.", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.gainPoison(3);
            }
        },true, "src/CS StS art/cards/diamondshield.png"));
        allCards.add(new Card("Hand of Greed", CardType.MONEY, "Spend 25 gold. Deal 17 dmg. If kills enemy, gain 50 gold. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                enemy.gainPoison(3);
            }
        }, true, "src/CS StS art/cards/handofgreed.png"));
        allCards.add(new Card("Cash Out", CardType.MONEY, "If money was spent this turn, deal 12 dmg. Lose energy.", 1, e -> {
            if(player.getEnergy()>=1){

                player.gainEnergy(player.getEnergy()*-1);
            }
        }, "src/CS StS art/cards/cashout.png"));
        allCards.add(new Card("Secret Base", CardType.MONEY, "Multiply soul by 3, gain that amount of money.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainGold(Math.abs(player.getSoul())*3);
            }
        }, "src/CS StS art/cards/secretbase.png"));
        allCards.add(new Card("Maneki-Neko", CardType.MONEY, "Gain 40 gold after 3 turns. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.manaki=3;
            }
        },"src/CS StS art/cards/manakineko.png"));
        allCards.add(new Card("Tax Evasion", CardType.ENERGY, "All cards in hand cost 0. Gain 8 money. Exhaust.", 3, e -> {
            if(player.getEnergy()>=3){
                player.godMode=true;
                player.gainGold(8);
            }
        }, "src/CS StS art/cards/taxfraud.png"));
        allCards.add(new Card("Laboratory", CardType.ENERGY, "Draw 2 cards, gain 2 energy. Gain 1 soul. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainEnergy(2);
            }
        }, true, 2, "src/CS StS art/cards/laboratory.png"));
        allCards.add(new Card("Salted Wounds", CardType.ENERGY, "Deal 8 dmg. If the enemy has fragile, gain 1 energy.", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(8);
                if(enemy.getFragile()>0){
                    player.gainEnergy(1);
                }
            }
        }, "src/CS StS art/cards/saltedwounds.png"));
        allCards.add(new Card("Violence is", CardType.SHUFFLE, "Deal 10 dmg. Gain 2 fragile. Add \"The Answer\" to discard pile.", 2, e -> {
            if(player.getEnergy()>=3){
                enemy.loseHealth(10+player.getStrength());
                player.gainFragile(2);
                player.gainCardDiscard(112);
            }
        }, false, 0, true, "src/CS StS art/cards/violenceis.png"));
        allCards.add(new Card("Encore", CardType.SHUFFLE, "Draw 3 cards. Gain 1 soul.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainSoul(1);
            }
        }, false, 3, "src/CS StS art/cards/encore.png"));
        allCards.add(new Card("Wave of the Hand", CardType.SHUFFLE, "Draw a card. Gain 1 energy. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainEnergy(1);
            }
        }, true, 1, "src/CS StS art/cards/waveofthehand.png"));
        allCards.add(new Card("Drawing Dead", CardType.SHUFFLE, "Draw 3 cards. Lose 2 health. Gain 1 soul.", 0, e -> {
            if(player.getEnergy()>=0){
                player.loseHealth(2);//card inflict
                player.gainSoul(1);
            }
        },false, 3, "src/CS StS art/cards/drawingdead.png"));


        allCards.add(new Card("Celestial Walls", CardType.SKY, "Gain 6 block. Add 2 Star cards to deck", 1, e -> {
            if(player.getEnergy()>=1){
               player.gainBlock(6);
               player.gainCardHand(player.getValidStarIndex());
                player.gainCardHand(player.getValidStarIndex());
            }
        }, "src/CS StS art/cards/celestialwalls.png"));
        allCards.add(new Card("Celestial Spears", CardType.SKY, "Deal 6 dmg. Add 2 Star cards to deck", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(6+player.getStrength());
                player.gainCardHand(player.getValidStarIndex());
                player.gainCardHand(player.getValidStarIndex());
            }
        }, "src/CS StS art/cards/celestialspears.png"));
        allCards.add(new Card("Celestial Wall", CardType.SKY, "Gain 6 block. Add 2 Star cards to deck", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainBlock(6);
                player.gainCardHand(player.getValidStarIndex());
                player.gainCardHand(player.getValidStarIndex());
            }
        }, "src/CS StS art/cards/celestialwalls.png"));
        allCards.add(new Card("Celestial Spears", CardType.SKY, "Deal 6 dmg. Add 2 Star cards to deck", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(6+player.getStrength());
                player.gainCardHand(player.getValidStarIndex());
                player.gainCardHand(player.getValidStarIndex());
            }
        },"src/CS StS art/cards/celestialspears.png"));
        allCards.add(new Card("Constellation", CardType.SKY, "Add 1 star card to hand.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainCardHand(player.getValidStarIndex());
            }
        },"src/CS StS art/cards/constellation.png"));
        allCards.add(new Card("Luma", CardType.SKY, "Add 3 star cards to draw pile. Costs 1 soul.", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCardDraw(player.getValidStarIndex());
                player.gainCardDraw(player.getValidStarIndex());
                player.gainCardDraw(player.getValidStarIndex());
                player.gainSoul(-1);
            }
        }, "src/CS StS art/cards/luma.png"));
        allCards.add(new Card("Solar Flare", CardType.SKY, "Deal 3 dmg for every star card in deck", 1, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(player.countCardsByType(CardType.STAR)*3);
            }
        }, "src/CS StS art/cards/solarflare.png"));
        allCards.add(new Card("Neutron Star", CardType.SKY, "Gain 10 block. Add a star card to draw pile. Draw a card.", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainBlock(10);
                player.gainCardDraw(player.getValidStarIndex());
            }
        }, false, 1, "src/CS StS art/cards/neutronstar.png"));
        allCards.add(new Card("Curiosity", CardType.SKY, "If the enemy intends to attack, add 2 star cards to hand.", 1, e -> {
            if(player.getEnergy()>=1){
                if(enemy.getAction().actionType==ActionType.LOSE_HEALTH){
                    player.gainCardHand(player.getValidStarIndex());
                    player.gainCardHand(player.getValidStarIndex());
                }
            }
        }, false, 0, true, "src/CS StS art/cards/curiosity.png"));
        allCards.add(new Card("Supernova", CardType.SKY, "Deal 2 dmg for each star card played this battle. Apply 2 fragile.", 2, e -> {
            if(player.getEnergy()>=2){
                for (int i = 0; i < player.starCardsPlayed; i++) {
                    enemy.loseHealth(2);
                }
                enemy.gainFragile(2);
            }
        }, "src/CS StS art/cards/supernova.png"));
        allCards.add(new Card("Take Five", CardType.HEAL, "Heal 5, attack for 5, apply 5 fragile, apply 5 poison, gain 5 crit dmg. Costs 3 soul. Exhaust", 5, e -> {
            if(player.getEnergy()>=5&&player.getSoul()>=3){
                player.gainHealth(5);
                enemy.loseHealth(5+player.getStrength());
                enemy.gainFragile(5);
                enemy.gainPoison(5);
                enemy.gainCritDmg(5);
                player.gainSoul(-3);
            }
        },true, "src/CS StS art/cards/takefive.png"));
        allCards.add(new Card("Shepard Tone", CardType.HEAL, "Gain 5 HP, every turn this battle lose 1 HP. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainHealth(5);
                player.shepardTone=true;
            }
        },true, "src/CS StS art/cards/shepardtone.png"));
        allCards.add(new Card("Sine Wave", CardType.HEAL, "On an even turn, gain 4 HP. On an odd turn, gain 2 HP", 2, e -> {
            if(player.getEnergy()>=2){
                if(player.turnNumber%2==0){
                    player.gainHealth(4);
                }
                else{
                    player.gainHealth(2);
                }
            }
        },true, "src/CS StS art/cards/sinewave.png"));
        allCards.add(new Card("Negative Harmony", CardType.HEAL, "Heal amount of fragile. Lose health amount of strength. Gain 2 soul", 1, e -> {
            if(player.getEnergy()>=2){
                player.gainHealth(player.getFragile());
                player.loseHealthRaw(player.getStrength());
                player.gainSoul(2);
            }
        }, "src/CS StS art/cards/negativeharmony.png"));
        allCards.add(new Card("Lydian", CardType.HEAL, "Deal 10 dmg. Gain 1 soul. If enemy is below 20% health, gain 2 max HP. Exhaust", 3, e -> {
            if(player.getEnergy()>=2){
                enemy.loseHealth(10+player.getStrength());
                player.gainSoul(1);
                if((((double) enemy.getCurrentHealth())/enemy.getMaxHealth())<=20.0){
                    player.gainMaxHealth(2);
                }
            }
        }, true, "src/CS StS art/cards/lydian.png"));
        allCards.add(new Card("Power Chord", CardType.HEAL, "Gain 4 strength. Heal 4. Costs 2 soul", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainStrength(4);
                player.gainHealth(4);
                player.gainSoul(2);
            }
        }, "src/CS StS art/cards/powerchord.png"));
        allCards.add(new Card("Deus Ex Machina", CardType.SPECIAL, "If the enemy is 40 or lower health, set HP to 1.", 2, e -> {
            if(player.getEnergy()>=2){
                if(enemy.getCurrentHealth()<=40){
                    enemy.loseHealthRaw(enemy.getCurrentHealth()-1);
                }
            }
        }, "src/CS StS art/cards/deusexmachina.png"));
        allCards.add(new Card("Deus Ex Machina", CardType.SPECIAL, "If the enemy is 40 or lower health, set HP to 1.", 2, e -> {
            if(player.getEnergy()>=2){
                if(enemy.getCurrentHealth()<=40){
                    enemy.loseHealthRaw(enemy.getCurrentHealth()-1);
                }
            }
        },"src/CS StS art/cards/deusexmachina.png"));
        allCards.add(new Card("Veni", CardType.SPECIAL, "Add Vidi to draw pile. Exhaust", 1, e -> {
            if(player.getEnergy()>=1){
                player.gainCardDraw(97);
            }
        },true, 0, true, "src/CS StS art/cards/veni.png"));
        allCards.add(new Card("Vidi", CardType.SPECIAL, "Add Vici to draw pile. Exhaust", 2, e -> {
            if(player.getEnergy()>=2){
                player.gainCardDraw(98);
            }
        },true, 0, true, "src/CS StS art/cards/vidi.png"));
        allCards.add(new Card("Vici", CardType.SPECIAL, "Deal 45 dmg twice. Gain 2 strength", 3, e -> {
            if(player.getEnergy()>=3){
                enemy.loseHealth(45+player.getStrength());
                enemy.loseHealth(45+player.getStrength());
                player.gainStrength(2);
            }
        }, "src/CS StS art/cards/vici.png"));
        allCards.add(new Card(true, "src/CS StS art/cards/null.png"));//only curse card lol
        allCards.add(new Card("Guard", CardType.STAR, "Gain 4 block. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainBlock(4);
            }
        },true, "src/CS StS art/cards/guard.png"));
        allCards.add(new Card("The Thinker", CardType.STAR, "Gain 14 Block. Exhaust.. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainHealth(5);
            }
        },true, "src/CS StS art/cards/thethinker.png"));
        allCards.add(new Card("Ruler of Everything", CardType.STAR, "Heal 5. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainHealth(5);
            }
        },true, "src/CS StS art/cards/rulerofeverything.png"));
        allCards.add(new Card("One Step From Eden", CardType.STAR, "Gain 5 soul. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainHealth(5);
            }
        },true, "src/CS StS art/cards/onestepfromeden.png"));
        allCards.add(new Card("Box Jellyfish", CardType.STAR, "Apply 5 poison. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainHealth(5);
            }
        },true, "src/CS StS art/cards/boxjellyfish.png"));
        allCards.add(new Card("Tempest of Steel", CardType.STAR, "Add 4 kunai to hand. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainCardHand(7);
                player.gainCardHand(7);
                player.gainCardHand(7);
            }
        },true, 0, true, "src/CS StS art/cards/tempestofsteel.png"));
        allCards.add(new Card("Qi", CardType.STAR, "Gain 1 energy. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainEnergy(1);
            }
        },true, "src/CS StS art/cards/qi.png"));
        allCards.add(new Card("The Absurd", CardType.STAR, "Apply 5 Fragile. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                enemy.gainFragile(5);
            }
        },true, "src/CS StS art/cards/theabsurd.png"));
        allCards.add(new Card("Omega", CardType.STAR, "Gain 2 Strength. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainStrength(2);
            }
        },true, "src/CS StS art/cards/omega.png"));
        allCards.add(new Card("Perpetual Motion", CardType.STAR, "Draw 3 cards. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.drawCard();
                player.drawCard();
                player.drawCard();
            }
        },true, 3, "src/CS StS art/cards/perpetualmotion.png"));
        allCards.add(new Card("The Unmoved Mover", CardType.STAR, "Double your energy. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.gainEnergy(player.getEnergy());
            }
        },true, "src/CS StS art/cards/unmovedmover.png"));
        allCards.add(new Card("Amber Cage", CardType.STAR, "Keep all block for next turn. Exhaust.", 0, e -> {
            if(player.getEnergy()>=0){
                player.setWillKeepBlock(true);

            }
        },true, "src/CS StS art/cards/ambercage.png"));
        allCards.add(new Card("The Answer", CardType.SPECIAL, "Deal 8 dmg 4 times. Exhaust", 0, e -> {
            if(player.getEnergy()>=1){
                enemy.loseHealth(8+player.getStrength());
                enemy.loseHealth(8+player.getStrength());
                enemy.loseHealth(8+player.getStrength());
                enemy.loseHealth(8+player.getStrength());
            }
        },true, "src/CS StS art/cards/theanswer.png"));

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
