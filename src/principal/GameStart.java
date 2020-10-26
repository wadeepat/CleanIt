package principal;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameStart {

	public static void main(String[] args) {
		Game game = Game.getGame();
		game.startGame();
	}
}
