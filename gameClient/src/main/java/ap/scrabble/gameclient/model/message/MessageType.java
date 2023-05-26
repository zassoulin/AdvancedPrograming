package ap.scrabble.gameclient.model.message;

public enum MessageType {
    PLAYER_ALREADY_EXISTS,
    CANT_JOIN_HOST,
    PLAYER_ADDED,
    JOIN_GAME,
    CURRENT_PLAYER,
    MY_TURN,
    OTHER_PLAYER_TURN,
    ILLEGAL_WORD,
    PLAY_NEXT_TURN,
    UPDATE_GAME_DATA,
    GAME_OVER,
    GAME_STARTED,
    QUERY,
    CHALLENGE,
    ADD_PLAYER,
}