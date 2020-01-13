package poke.game.programmlogik;
/**
 * Die Exception wenn etwas versucht wird zu pullen oder peeken wenn der Stack leer ist
 * @author Herr Seipt
 * @version 2019-12-14
 *
 */
public class WrongArgumentException extends Exception{
	/**
	 * Konstruktor der Stackfullempty ohne Text
	 */
	public WrongArgumentException() {
		super("Es wurde ein Argument angegeben das nicht legitim ist");
	}
	/**
	 * Konstruktor der Stackfullempty ohne Text
	 * @param der Text
	 */
	public WrongArgumentException(String text) {
		super(text);
	}

}
