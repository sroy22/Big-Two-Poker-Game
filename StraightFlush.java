/**
 * This class is used to model a hand of StraightFlush and is a sub-class of Hand. This class would define the abstract methods of Hand ( isValid() and getType()).
 * @author subhayan
 *
 */
public class StraightFlush extends Hand {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This constructor is used to make a hand of straightFlush of the specified cards of the player by calling constructor of the superclass Hand.
	 * @param player
	 * 		Player who plays the straightFlush
	 * 
	 * @param cards
	 * 		Cards to form a straightFlush
	 */
	public StraightFlush(CardGamePlayer player, CardList cards)
	{
		super(player,cards);// calling superclass constructor
	}
	/**
	  * Method to check if this is a valid straightFlush.
	 * @return
	 * 		Boolean value of true/false depending on whether hand is straightFlush or not
	 */
	public boolean isValid()
	 {
		
		if(size()==5) // StraighFlush is of size 5
		{
			if((getCard(0).getSuit()==getCard(1).getSuit()) && (getCard(1).getSuit()==getCard(2).getSuit())&& (getCard(2).getSuit()==getCard(3).getSuit())&& (getCard(4).getSuit()==getCard(3).getSuit())) // Straight flush has cards of same suit
			{
				sort(); // sorting the cards in order
				if((getCard(0).getRank()==10) && (getCard(1).getRank()==11) && (getCard(2).getRank()==12)&& (getCard(3).getRank()==0) && (getCard(4).getRank()==1)) // case when J Q K A 2
					return true;
				else if((getCard(1).getRank()-getCard(0).getRank()==1)&& (getCard(2).getRank()-getCard(1).getRank()==1)&& (getCard(3).getRank()-getCard(2).getRank()==1)&& (getCard(4).getRank()-getCard(3).getRank()==1)) //in StraightFlush each card is having rank greater than previous card by 1 of same suit
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
	/**
	 * Over-ridden method of Hand to check if this StraightFlush beats the specified hand
	 * @param hand
	 * 		The specified hand to be checked
	 * @return
	 * 		Boolean value of true/false if this StraightFlush beats specified hand
	 */
	boolean beats(Hand hand)
	{
		if(hand!=null)
		{
			if(size()==5 && hand.size()==5)
			{	sort();
				hand.sort();
				if(hand.getType()=="Straight") // StraightFlush can beat Straight
					return true;
				else if(hand.getType()=="Flush") // StraightFlush can beat Flush
					return true;
				else if(hand.getType()=="FullHouse") // StraightFlush can beat FullHouse
					return true;
				else if(hand.getType()=="Quad") // StraightFlush can beat Quad
					return true;
				else if(getCard(4).compareTo(hand.getCard(4))==1)// StraightFlush of higher top card beats StraightFlush of lower top card
						return true;
				else if(getCard(4).getRank()==hand.getCard(4).getRank())
				{
					if(getCard(4).getSuit()>hand.getCard(4).getSuit())// top card having higher suit of Straightflush beats top card having lower suit of StraightFlush when both have same rank
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
	  * Method to return a string specifying hand is straightFlush
	 * @return
	 * 		String containing "StraightFlush"
	 */
	 public String getType()
	 {
		 return "StraightFlush";
	 }

}
