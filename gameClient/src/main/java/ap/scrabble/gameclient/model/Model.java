package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Word;

import java.util.Observable;

// The Model's facade - only expose necessary functionality.
// Using abstract class instead of an interface so we can inherit Observable as part of the facade.
public abstract class Model extends Observable {
	// Can be one of these states: ["MAIN_MENU", "CREATE_GAME", "JOIN_GAME", "PLAY"]
	public abstract String getGameState();
	// Add a local player with a name
	public abstract void addLocalPlayer(String name);//for local player  //PLAYER_ADDED if succeeded Player_already_exsits o.w

	public abstract void CreateGame(String name);

	public abstract void JoinGame(String PlayerName);//for Remote player //PLAYER_ADDED if succeeded Player_already_exsits o.w

	public abstract void StartGame();

	public abstract void addWord(Word word);//Update_game_Data if succeeded Illegal word o.w

	public abstract void GetCurrentPlayerTiles();//Returns Player_tiles

	public abstract void LoadGame(String SaveName);
}
