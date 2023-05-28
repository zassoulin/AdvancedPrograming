package ap.scrabble.gameclient.model.message;

public enum MessageType {
    // =========== HOST ===========
    // Description: Couldn't join the lobby because the player's name is already taken.
    // Arg: String playerName
    // View: Display error message and stay in the main menu view (the user need to choose a different user name).
    // Remote-Model: Forward message to view and forfeit `JoinGame` (TODO: figure out whether to close the opened socket?).
    PLAYER_ALREADY_EXISTS,
    // Description: Couldn't join the host (host server is closed or full), but not because of the player's name is taken.
    // Arg: null (TODO TBD?)
    // View: Display error message and stay in the main menu view.
    // Remote-Model: Forward message to view and forfeit `JoinGame` (TODO: figure out whether to close the opened socket?).
    CANT_JOIN_HOST,
    // Description: A player joined the lobby - update the list of player names.
    // Arg: List<String> playerNames
    // View: Update the list of player names to the display.
    // Remote-Model: Update `playerList`, forward message to view (and asynchronously wait for `START_MESSAGE`).
    PLAYER_ADDED,
    // Description: The request to join the game has been successful
    // Arg: List<String> playerNames
    // View: Update the list of player names to the display,Wait for host to start game.
    // Remote-Model: Update `playerList`, forward message to view (and asynchronously wait for `START_MESSAGE`).
    PLAYER_ADDED_SUCCESSFULLY,//TODO: NOT NEEDED FOR NOW
    // Description: Update what's the current player's name.
    // Arg: String playerName
    // View: Highlight the name of the current player.
    // Remote-Model: Check if it's our player's turn and forward message to view.
    CURRENT_PLAYER,
    // Description: Is it our turn?
    // Arg: boolean isOurTurn
    // View: when `true`: Switch to play-mode - Unlock the view to user inputs and request `GetTiles` from `Model`.
    //       when `false: Switch to watch-mode - Lock the view to user inputs.
    // Remote-Model: N/A.
    MY_TURN,
    // Description: Illegal word was played.
    // Arg: String word (concatenation of the letters)
    // View: Stay in play mode, the user needs to input a different word.
    // Remote-Model: Forward the message to view.
    ILLEGAL_WORD,
    // Description: Update the score and board.
    // Arg: ap.scrabble.gameclient.model.board.GameData gameData
    // View: Update the view with the new scores and board (it happens at the end of a player's turn, so it can overwrites the player's placed word).
    // Remote-Model: Forward the message to view.
    UPDATE_GAME_DATA,
    // Description: The game ended.
    // Arg: null
    // View: Display the game over view.
    // Remote-Model: Forward the message to view and close sockets and stuff.
    GAME_OVER,
    // Description: Start the game with the current players in the lobby.
    // Arg: ap.scrabble.gameclient.model.board.GameData gameData
    // View: Move to board view and display the current scores and board (what about player names?).
    // Remote-Model: Forward the message to view (TODO and?...).
    GAME_STARTED,
    // ============================

    // ========== CLIENT ==========
    // Arg: String clientName
    ADD_PLAYER,

    PLAY_REMOTE_PLAYER_TURN,

    // ============================

    // TODO: Decide what to do with the "Unused and/or To Be Decided later"
    // ======== UNUSED/TBD ========
    JOIN_GAME,
    PLAY_NEXT_TURN,
    // ============================

    // =========== TEST ===========
    // Description: Test message to host.
    // Arg: "hello"
    HELLO_HOST,
    // Description: Response from host.
    // Arg: "response"
    RESPONSE_HOST,
    // Description: Test message from host.
    // Arg: "test"
    THIS_IS_HOST,
    // Description: Response back from client.
    // Arg: "hi"
    HI_HOST_THIS_IS_CLIENT,
    // ============================
}