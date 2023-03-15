ReadME File

This is "Not Slay the Spire", an attempt to recreate the roguelike RPG game Slay the Spire.

How to start:
Run the program in Main

How to Play:
- Use the cards, or abilities at the bottom to attack, defend, and more in order
to defeat the enemy!

- Each card costs energy to play. The star icon represents your energy. When your
energy is 0, you cannot play any more cards. Press "Next Turn" when you finish your turn.

- All the cards in your deck are shuffled and dealt each turn. You'll get a random
selection of cards to play, so balance your actions wisely!

- When you defeat the enemy, you'll get the option to add a card to your deck. Then, move onto
the next room and keep going!

GAME MECHANICS:

Health - Shown by the blue bars next to the player and enemy. If you run out, you die.
Kill the enemy in order to progress to the next level.

Cards - The cards at the bottom let you play different abilities, whether attacking, gaining defense,
applying status effects, or more.

Block - The amount of defense you have. When you receive damage from an enemy, your block absorbs
some of this damage.

Poison - When infected with poison, at the end of the turn the recipient loses that amount of health.
Poison decreases by 1 each turn.

Fragile - This means lower defense. The more fragile the player or enemy has, the more damage they receive.

FILE DESCRIPTIONS
-ActionList: a list that holds all the Action objects that are assigned to enemies.
-BattleUI: sets up the UI for the main battle scene.
-Card: a class that describes a Card object, containing the name, description, cost, actionlisteners, getters, and setters.
-CardList: a list of all the gainable cards in the game.
-CardType: enums that describe the different types of cards.
-Enemy: a class that describes the enemy.
-EnemyAction: a class that describes the action that the enemy takes each round.
-EnemyList: a list of all the enemies that can be encountered.
-FailUI: sets up the UI for when the player dies.
-Main: the main scene.
-Player: a class that represents the player, and has all stats like health, poison, fragile, and the deck.
-RewardUI: sets up the UI for when the player defeats the enemy, and where the player can gain a card.
-RoomChoiceUI: sets up the UI for when the player needs to move to the next room.


Have fun!
