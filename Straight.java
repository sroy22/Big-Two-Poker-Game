/**
 * This class is used to model a hand of Straight and is a sub-class of Hand. This class would define the abstract methods of Hand ( isValid() and getType()).
 * @author subhayan
 *
 */
public class Straight extends Hand {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This constructor is used to make a hand of straight of the specified cards of the player by calling constructor of the superclass Hand.
	 * @param player
	 * 		Player who plays the straight
	 * 
	 * @param cards
	 * 		Cards to form a straight
	 */
	public Straight(CardGamePlayer player, CardList cards)
	{
		super(player,cards); // calling superclass constructor forming a straight hand
	}
	/**
	  * Method to check if this is a valid straight.
	 * @return
	 * 		Boolean value of true/false depending on whether hand is straight or not
	 */
	public boolean isValid()
	 {sort();// sorting the cards
	 if(size()==5) // Straight is of size 5
	 {
		 
	 
	 if((getCard(0).getRank()==10)&&(getCard(1).getRank()==11)&&(getCard(2).getRank()==12)&&(getCard(3).getRank()==0)&&(getCard(4).getRank()==1)) // case when J Q K A 2
		 	return true;
	 
	 else if((getCard(1).getRank()-getCard(0).getRank()==1) && (getCard(2).getRank()-getCard(1).getRank()==1) && (getCard(3).getRank()-getCard(2).getRank()==1)&& (getCard(4).getRank()-getCard(3).getRank()==1))// in Straight each card is having rank greater than previous card by 1
			return true;
	 else
		 return false;
	 }
	 else
		 return false;
	 }
	
	/**
	  * Method to return a string specifying hand is straight
	 * @return
	 * 		String containing "Straight"
	 */
	 public String getType()
	 {
		 return "Straight";
	 }

}
