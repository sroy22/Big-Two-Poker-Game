/**
 * This class is used to model a hand of Quad and is a sub-class of Hand. This class would define the abstract methods of Hand ( isValid() and getType()).
 * @author subhayan
 *
 */
public class Quad extends Hand {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This constructor is used to make a hand of quad of the specified cards of the player by calling constructor of the superclass Hand.
	 * @param player
	 * 		Player who plays the quad
	 * 
	 * @param cards
	 * 		Cards to form a quad
	 */
	public Quad(CardGamePlayer player, CardList cards)
	{
		super(player,cards);// calling the superclass constructor
	}
	/**
	  * Method to check if this is a valid quad.
	 * @return
	 * 		Boolean value of true/false depending on whether hand is quad or not
	 */
	public boolean isValid()
	 {
		sort();//sorting the cards
		if(size()==5) // Quad is of size 5
		{if(getCard(0).getRank()==getCard(1).getRank()&& getCard(1).getRank()==getCard(2).getRank() && getCard(2).getRank()==getCard(3).getRank())//  If First 4 cards are of same rank
				return true;
		else if(getCard(1).getRank()==getCard(2).getRank()&& getCard(2).getRank()==getCard(3).getRank() && getCard(3).getRank()==getCard(4).getRank())// If last 4 cards are of same rank
				return true;
		else
			return false;
		}
		else
			return false;
	 }
	/**
	 * Over-ridden method of Hand to retrieve the top card of the Quad
	 * @return
	 * 		Top card of the Quad
	 */
	public Card getTopCard()
	{	
		sort();
		if(getCard(0).getRank()==getCard(1).getRank()) // if first 4 cards are of same rank then top card is the 4th card since cards are sorted
			return getCard(3);
		else if(getCard(1).getRank()==getCard(2).getRank()) // if last 4 cards are of same rank then top card is the last card since cards are sorted
			return getCard(4);
		else
			return null;
	}
	/**
	 * Over-ridden method of Hand to check if this quad beats the specified hand
	 * @param hand
	 * 		The specified hand to be checked
	 * @return
	 * 		Boolean value of true/false if this quad beats specified hand
	 */
	boolean beats(Hand hand)
	{
		if(hand!=null)
		{
			if(size()==5 && hand.size()==5)
			{
				if(hand.getType()=="Straight") // Quad can beat Straight
					return true;
				else if(hand.getType()=="Flush") // Quad can beat Flush
					return true;
				else if(hand.getType()=="FullHouse") // Quad can beat FullHouse
					return true;
				else if(getType()==hand.getType())
				{
					if(getTopCard().compareTo(hand.getTopCard())==1) // Quad of higher topCard can beat Quad of lower top Card
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
	  * Method to return a string specifying hand is quad
	 * @return
	 * 		String containing "Quad"
	 */
	 public String getType()
	 {
		 return "Quad";
	 }

}
