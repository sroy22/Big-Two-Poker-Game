
/**
 * This class is used to model a hand of FullHouse and is a sub-class of Hand. This class would define the abstract methods of Hand ( isValid() and getType()).
 * @author subhayan
 *
 */
public class FullHouse extends Hand {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This constructor is used to make a hand of FullHouse of the specified cards of the player by calling constructor of the superclass Hand.
	 * @param player
	 * 			Player who plays the FullHouse
	 * @param cards
	 * 			Cards to form the FullHouse
	 */
	public FullHouse(CardGamePlayer player, CardList cards)
	{
		super(player,cards);// calling constructor of superclass Hand
	}
	/**
	  * Method to check if this is a valid FullHouse.
	 * @return
	 * 		Boolean value of true/false depending on whether hand is FullHouse or not
	 */
	public boolean isValid()
	 {sort(); // sorting the cards in order
	 	if(size()==5) // FullHouse has size of 5
		{if((getCard(0).getRank()==getCard(1).getRank()) && (getCard(1).getRank()==getCard(2).getRank())&& (getCard(3).getRank()==getCard(4).getRank())) // if first 3 cards are of same rank ( cards are sorted)
				return true;
		else if((getCard(0).getRank()==getCard(1).getRank())&&(getCard(2).getRank()==getCard(3).getRank())&& (getCard(3).getRank()==getCard(4).getRank())) // if last 3 cards are of same rank (cards are sorted)
					return true;
		else
			return false;
		}
	 	else
	 		return false;
	 	
	 }
	/**
	 * Over-ridden method of Hand to retrieve the top card of the FullHouse
	 * @return
	 * 		Top card of the FullHouse
	 */
	public Card getTopCard()
	{	sort(); //sorting the cards in order
	if((getCard(0).getRank()==getCard(1).getRank()) && (getCard(1).getRank()==getCard(2).getRank())&& (getCard(3).getRank()==getCard(4).getRank()))// if first 3 cards are of same rank then the third card is topCard since cards are sorted
			return getCard(2);
	else if((getCard(0).getRank()==getCard(1).getRank())&&(getCard(2).getRank()==getCard(3).getRank())&& (getCard(3).getRank()==getCard(4).getRank()))// if last 3 cards are of same rank then the last card is topCard since cards are sorted
			return getCard(4);
	else
		return null;
	}
	 /**
	 * Over-ridden method of Hand to check if this FullHouse beats the specified hand
	 * @param hand
	 * 		The specified hand to be checked
	 * @return
	 * 		Boolean value of true/false if this FullHouse beats specified hand
	 */
	boolean beats(Hand hand)
	{
		if(hand!=null)//if hand is valid/exists
		{
		if(size()==5 && hand.size()==5) // both hands have to be of size 5
		{
			if(hand.getType()=="Straight") // FullHouse can beat straight
				return true;
			else if(hand.getType()=="Flush") // FullHouse can beat Flush
				return true;
			else if(getType()==hand.getType())
				{ if(getTopCard().compareTo(hand.getTopCard())==1) // FullHouse having higher top card beats FullHouse with lower top card
								return true;
				else
					return false;
				}
				else
					return false;
				
		}
		else
			return false;
		}
		else
			return false;
	}
	/**
	  * Method to return a string specifying hand is FullHouse
	 * @return
	 * 		String containing "FullHouse"
	 */
	 public String getType()
	 {
		 return "FullHouse";
	 }

}
