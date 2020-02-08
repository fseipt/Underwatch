package poke.game.view.gameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Diese Klasse hat alle Spiel-Screens
 * @author Amine Boutahar
 *
 */
public class GameStateManager {
	private ArrayList<GameState> gameStates;
	private int currentState = 0;
	public static final int MENUSTATE = 0;
	public static final int NEWGAME = 1;
	public static final int COMBAT = 4;
	
	public GameStateManager() {
		gameStates =  new ArrayList<GameState>();
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this)); // 0
		gameStates.add(new MenuState(this)); // 0
		gameStates.add(new MenuState(this)); // 0
		gameStates.add(new MenuState(this)); // 0
		gameStates.add(new CombatState(this)); // 4
		
	}
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	public void update() {
		gameStates.get(currentState).update();
	}
	public void draw(Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
}
