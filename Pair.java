
/**
 * This class is used to model a hand of Pair and is a sub-class of Hand. This class would define the abstract methods of Hand ( isValid() and getType()).
 * @author subhayan
 *
 */
public class Pair extends Hand {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This constructor is used to make a hand of pair of the specified cards of the player by calling constructor of the superclass Hand.
	 * @param player
	 * 		Player who plays the pair
	 * 
	 * @param cards
	 * 		Cards to form/check a pair
	 */
	public Pair(CardGamePlayer player, CardList cards)
	{
		super(player,cards); // calling superclass constructor to form pair
	}
	/**
	  * Method to check if this is a valid pair.
	 * @return
	 * 		Boolean value of true/false depending on whether hand is pair or not
	 */
	public boolean isValid()
	 {
		if(size()==2&& getCard(0).getRank()==getCard(1).getRank())// pair has size of 2 and both cards are of same rank
			return true;
		return false;
	 }
	/**
	  * Method to return a string specifying hand is pair
	 * @return
	 * 		String containing "Pair"
	 */
	 public String getType()
	 {
		 return "Pair";
	 }
}
