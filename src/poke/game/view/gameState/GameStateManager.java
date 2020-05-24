package poke.game.view.gameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import poke.Controller;

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
	public static final int SELECTION = 2;
	public static final int SELECTIONITEMS = 3;
	public static final int SELECTIONMOVES = 4;
	public static final int COMBAT = 5;
	public static final int ALLTYPES = 6;
	public static final int LOAD = 7;
	public GameStateManager(Controller c) {
		
		
		this.gameStates =  new ArrayList<GameState>();
		this.currentState = MENUSTATE;
		this.gameStates.add(new MenuState(this)); // 0 -> MenuState
		this.gameStates.add(new MenuState(this)); // 1 -> NewGame
		this.gameStates.add(new SelectionState(this,c)); // 2 -> Selection
		this.gameStates.add(new SelectionStateItems(this, c)); // 3 -> SelectionItems
		this.gameStates.add(new SelectionStateMoves(this, c)); // 4 -> SelectionMoves
		this.gameStates.add(new CombatState(this, c)); // 5 -> Combat
		this.gameStates.add(new MenuState(this)); // 6 -> Alltypes
		this.gameStates.add(new MenuState(this)); // 7 -> Load
		
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
