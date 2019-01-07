# Setting up the Project
1. Ensure Android Studio is set up properly with your Android SDK installed.
2. Clone the repository by clicking [here](/https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0641).
3. Using Android Studio, click "New -> Import Project" and select `group_0641/Phase2/GameCentre`.
4. Let Android Studio automatically build the project.
5. Run the app using a Pixel 2 running API 27 Android Virtual Device.

# Registering and Logging In
1. On startup, there should be a blank screen with buttons "Login" and "Register", if this is your first time running the app locally, press "Register", otherwise skip to step 5.
2. Enter a preferred username using only alphanumeric characters and underscores.
3. Enter a preferred password and confirm the password in the following field.
4. Press "Register" - you should be taken back to the main menu.
5. Press "Login" and enter the credentials you used to register an account.

# Selecting a Game
1. Once logged in, you will be directed to a game selection screen, simply tap the game you would like to play.

# Running the Tile Game
1. If you tapped "Sliding Tiles" on the game selection screen, you will be directed to the main menu of Sliding Tiles.
2. Press "New Game" to start a new game. You may also load from a previously saved game, change settings, or view the local leaderboards.

## How to Play
* Rearrange the tiles so that the numbers are in order with the blank tile on the bottom right.
* In order to make a move, tap any tile adjacent to the blank tile and the tile will be swapped with the blank tile.
* Try to finish with as few moves in as short a time as possible! They count towards your score.

## Undoing a Move
1. In order to undo a move, swipe right to left while inside the game screen.
2. If you selected unlimited undos or gave yourself a custom number of undos in the settings menu, the action is the same.

## Saving the Game
1. Exit the main game by pressing the 'back' button built into your Android device.
2. Once back on the main game menu, press "Save Game".
3. Either select "New Save" to save the game in a new slot, or select any of the existing saves to overwrite the existing save.
4. This save will record the layout of the board, number of undos remaining, and other key game features for later.

## Loading a Saved Game
1. On the main game menu, press "Load Game".
2. Select any of the save slots to load into the game saved.
* By default, your current game will always be autosaved into the autosave slot.
* If you enter the load game screen without tapping the "New Game" button, a randomly generated board will be saved in the autosave slot. Therefore, the autosave slot is not intended for permanent storage.

## Game Scoring
* The game is scored using a scoreboard system.
* Every time you finish a game, your score will be saved and the leaderboards screen will automatically pop up, recording your new score onto the scoreboard.
* If you would like to access the scoreboards, you may do so on the main menu by tapping the "Scoreboard" button.

## Changing Settings:
* To change game settings before playing, launch the settings menu from the main screen.
* Your settings are not saved when the app is closed - however, existing saved games will continue to use the undo and complexity settings at creation. The autosave setting is not saved with your saved games.

### Tile Complexity
* Select a complexity using the on screen buttons and press apply.

### Changing the number of Undos
* Setting the number of undos: By default, players have unlimited undos.
* Set the amount of undos you would like by entering a number into the "Choose Undo Limit" text box.
* If you prefer an easier experience, you may set the Undo Limit to -1, indicating unlimited undos.

### Setting the Autosave Interval
* To update the autosave interval, enter an integer greater than 0.
* Your progress will be saved every time you make the entered number of moves.

# Running the Pawn Race Game
1. If you tapped "Pawn Race" on the game selection screen, you will be directed to the main menu of Pawn Race.
2. Press "New Game" to start a new game. You may also load from a previously saved game, change settings, or view the local leaderboards.

## How to Play
* The player (you) may either start as White or Black (with White being the default)
* Each player starts with 7 pawns, with a randomly selected gap on a rank every game
* Your goal is to get a pawn to the opposite end of the board before your opponent, the computer
* The computer uses an AI with adjustable difficulty
* Standard chess rules apply - moving forward, moving forward twice on the first move, sideward captures, and en passant captures
* To move, simply tap the piece you would like to move and tap the target square you would like to move to
* The moves must be valid moves, otherwise you must restart from the first tap
* Once you move, you must wait for the computer to move, which can be quite slow for higher difficulties
* The game is over when either a player reaches the end of the board, a player has no more pieces, or a player has pieces but no more available moves (in which case it is a stalemate)

## Undoing a Move
1. In order to undo a move, swipe right to left while inside the game screen. This will use up one of your undos, so be strategic!
2. If you selected unlimited undos or gave yourself a custom number of undos in the settings menu, the action is the same.

## Saving the Game
1. Exit the main game by pressing the 'back' button built into your Android device.
2. Once back on the main game menu, press "Save Game".
3. Either select "New Save" to save the game in a new slot, or select any of the existing saves to overwrite the existing save.
4. This save will record the layout of the board, number of undos remaining, and other key game features for later.

## Loading a Saved Game
1. On the main game menu, press "Load Game".
2. Select any of the save slots to load into the game saved.
* By default, your current game will always be autosaved into the autosave slot.
* If you enter the load game screen without tapping the "New Game" button, a randomly generated board will be saved in the autosave slot. Therefore, the autosave slot is not intended for permanent storage.

## Game Scoring
* The game is scored using a scoreboard system.
* Every time you finish a game, your score will be saved and the leaderboards screen will automatically pop up, recording your new score onto the scoreboard.
* If you would like to access the scoreboards, you may do so on the main menu by tapping the "Scoreboard" button.

## Changing Settings:
* To change game settings before playing, launch the settings menu from the main screen.
* Your settings are not saved when the app is closed - however, existing saved games will continue to use the undo and complexity settings at creation. The autosave setting is not saved with your saved games.

### Game Color
* Select the color you would like to play as in the settings menu and click "Apply"
* Note that White moves first and Black moves second

### Setting the Difficulty
* Select the difficulty of the computer you would like to play as in the settings menu and click "Apply"
* The easy difficulty searches 3 moves ahead
* The medium difficulty searches 4 moves ahead and dynamically increases its search range as the game progresses
* The hard difficulty searches 6 moves ahead

### Changing the Number of Undos
* Setting the number of undos: By default, players have unlimited undos.
* Set the amount of undos you would like by entering a number into the "Choose Undo Limit" text box.
* If you prefer an easier experience, you may set the Undo Limit to -1, indicating unlimited undos.

### Setting the Autosave Interval
* To update the autosave interval, enter an integer greater than 0.
* Your progress will be saved every time you make the entered number of moves.

# Running the 3072 Game
1. If you tapped "3072" on the game selection screen, you will be directed to game activity of 3072.

## How to Play
* Standard rules of 2038 apply
* You swipe up, down, left, or right to merge tiles with the same number
* Your goal is to create the largest possible number without running out of moves
* Each time you swipe, a random new number will appear on the board
* The game is over once you run out of moves

## Game Scoring
* The game is scored as the game progresses using the sum of all tiles
* Every time you finish a game, your score will be saved and the leaderboards screen will automatically pop up, recording your new score onto the scoreboard.
