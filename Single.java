/**
 * This class is used to model a hand of Single and is a sub-class of Hand. This class would define the abstract methods of Hand ( isValid() and getType()).
 * @author subhayan
 *
 */
public class Single extends Hand {
	 
	/**
	 * This constructor is used to make a hand of single of the specified cards of the player by calling constructor of the superclass Hand.
	 * @param player
	 * 		Player who plays the single
	 * 
	 * @param cards
	 * 		Cards to form a single
	 */
	public Single(CardGamePlayer player, CardList cards)
	{
		super(player,cards); // calling superclass constructor forming a single
	}
	private static final long serialVersionUID = -5586876227643135562L;
	/**
	  * Method to check if this is a valid single.
	 * @return
	 * 		Boolean value of true/false depending on whether hand is single or not
	 */
	public boolean isValid()
	 {
		if(size()==1) // single is size of 1
			return true;
		return false;
	 }
	/**
	  * Method to return a string specifying hand is single
	 * @return
	 * 		String containing "Single"
	 */
	 public String getType()
	 {
		 return "Single";
	 }

	
}
