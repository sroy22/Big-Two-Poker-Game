
/**
 * This class is used to model a hand of flush and is a sub-class of Hand. This class would define the abstract methods of Hand ( isValid() and getType()).
 * @author subhayan
 *
 */
public class Flush extends Hand {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This constructor is used to make a hand of flush of the specified cards of the player by calling constructor of the superclass Hand.
	 * @param player
	 * 			Player who plays the flush
	 * @param cards
	 * 			Cards to form the flush
	 */
	public Flush(CardGamePlayer player, CardList cards)
	{
		super(player,cards); // calling superclass constructor
	}
	/**
	  * Method to check if this is a valid flush.
	 * @return
	 * 		Boolean value of true/false depending on whether hand is flush or not
	 */
	public boolean isValid()
	 {
		if(size()!=5) // flush has 5 cards
			return false;
		int flag=0;
		for(int i=0;i<5;i++)
			if(getCard(i).getSuit()!=getCard(0).getSuit())//flush has all cards of same suit
				flag=1;
	
		if(flag==0) // if hand is flush
			return true;	
		return false;
	 }
	/**
	  * Method to return a string specifying hand is flush
	 * @return
	 * 		String containing "Flush"
	 */
	 public String getType()
	 {
		 return "Flush";
	 }
	 /**
		 * Over-ridden method of Hand to check if this flush beats the specified hand
		 * @param hand
		 * 		The specified hand to be checked
		 * @return
		 * 		Boolean value of true/false if this flush beats specified hand
		 */
	 boolean beats(Hand hand)
	 {
		 if(hand!=null) // if hand is valid/exists
		 {	 
		 if(size()==5 && hand.size()==5) //both hands have to be of size 5
		 {
			 if(hand.getType()=="Straight") // flush can beat straight
				 return true;
			 else if(hand.getType()=="FullHouse"|| hand.getType()=="Quad"|| hand.getType()=="StraightFlush") // flush cannot beat FullHouse, Quad and StraightFlush
				 return false;
			 else if (getCard(0).getSuit()>getCard(0).getSuit()) // flush of higher suit beats flush of lower suit
				 return true;
			 else if(getCard(0).getSuit()==getCard(0).getSuit())
			 {
				 if(getTopCard().compareTo(hand.getTopCard())==1) // if suits are same then current flush will beat the other flush if it has a higher top card
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
}
