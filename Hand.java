
/**
 * This abstract class is a sub-class of CardList class and is used to model a hand of cards. It has private instance variables for storing the player who plays this hand.
 * It also has methods for getting the player of this hand checking if it is a valid hand, getting the type of hand and checking if it beats a specified hand.
 * @author subhayan
 *
 */

public  abstract class Hand extends CardList  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The player who plays the hand
	 */
	private CardGamePlayer player;
	/**
	 * The constructor for building a hand with specified players and list of  cards
	 * @param player
	 * 		The Player who plays the hand
	 * @param cards
	 * 		The cards to form/check the hand
	 */
	public Hand(CardGamePlayer player, CardList cards)
	{
		for(int i=0;i<cards.size();i++)
			addCard(cards.getCard(i)); //adding the cards of the hand
		this.player=player; // setting the player of the hand
	}
	/**
	 * Default constructor of Hand
	 */
	public Hand(){}
	
	/**
	 * The method to retrieve the player of the hand
	 * @return
	 * 		Player who plays the hand
	 */
	public CardGamePlayer getPlayer()
	{
		return player;
	}
	
	/**
	 * The method to retrieve the top card of the hand
	 * @return
	 * 		Top card of the hand
	 */
	public Card getTopCard() // This method is valid for Flush, Pair, Single, Straight, StraightFlush and Triple. It has been over-ridden in FullHouse and Quad.
	{	Card Top;
		if(size()>0)
		{
		
		
		Top=getCard(0);//assuming first card is top card
		
		
		for(int i=0;i<size();i++)
			if(getCard(i).compareTo(Top)==1) // comparing each card with top card
				{Top=getCard(i); // current top card reference
				}
		}
		else
			Top=null;
	return Top;
		
	}
	/**
	 * The method to check if this hand beats the specified hand
	 * @param hand
	 * 		The specified hand to be checked
	 * @return
	 * 		Boolean value of true/false if this hand beats specified hand
	 */
	boolean beats(Hand hand) // this method is valid for single, pair, triple and straight. It has been over-ridden for other classes.
	{	
		if(hand==null)
			return false;
		if(getType()==hand.getType()) // handtypes are same
		{
			if(getTopCard()==null)
				return false;
			else if(hand.getTopCard()==null)
				return true;
			else if(getTopCard().compareTo(hand.getTopCard())==1) // hand with higher top card beats same hand type with lower top card in general cases
				return true;
			else
				return false;
		}
		return false;
		
	}
	 /**
	  * Abstract method to check if this is a valid hand. Will be defined in the sub-classes of Hand.
	 * @return
	 * 		Boolean value of true/false depending on whether hand is valid or not
	 */
	public abstract boolean isValid();
	 /**
	  * Abstract method to return a string specifying the type of the hand. Will be defined in the sub-classes of Hand.
	 * @return
	 * 		String containing the type of hand
	 */
	public abstract String getType();

}