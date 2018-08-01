
/**
 * This class is a sub-class of the Deck class and is used to model a deck of cards used in Big Two card game
 * @author subhayan
 *
 */
public class BigTwoDeck extends Deck {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Initialize the deck of cards of Big Two Game.
	 */
	
	public void initialize() {
		removeAllCards();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				BigTwoCard card = new BigTwoCard(i, j); // making BigTwo cards
				addCard(card); // adding the BigTwo Cards formed
	
	
		
			}
		}
	}
	
	
}
