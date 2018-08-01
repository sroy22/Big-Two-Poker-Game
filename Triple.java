/**
 * This class is used to model a hand of Triple and is a sub-class of Hand. This class would define the abstract methods of Hand ( isValid() and getType()).
 * @author subhayan
 *
 */
public class Triple extends Hand {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This constructor is used to make a hand of Triple of the specified cards of the player by calling constructor of the superclass Hand.
	 * @param player
	 * 		Player who plays the Triple
	 * 
	 * @param cards
	 * 		Cards to form a Triple
	 */
	public Triple(CardGamePlayer player, CardList cards)
	{
		super(player,cards);// calling superclass constructor to form Triple
	}
	/**
	  * Method to check if this is a valid Triple.
	 * @return
	 * 		Boolean value of true/false depending on whether hand is Triple or not
	 */
	public boolean isValid()
	 {
		if(size()==3&& (getCard(0).getRank()==getCard(1).getRank()) && (getCard(1).getRank()==getCard(2).getRank())&& getCard(0).getRank()==getCard(2).getRank())// In a triple all cards are of same rank and size is 3
			return true;
		return false;
	 }
	/**
	  * Method to return a string specifying hand is Triple
	 * @return
	 * 		String containing "Triple"
	 */
	 public String getType()
	 {
		 return "Triple";
	 }

}
