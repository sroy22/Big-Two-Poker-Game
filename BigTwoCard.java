
/**
 * This class is a sub-class of the Card Class and is used to model a card used in Big Two card game. 
 * @author subhayan
 *
 */
public class BigTwoCard extends Card {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor to build a card with a specified suit and rank.
	 * @param suit
	 * 			Integer value between 0 and 3 representing the suit of the card
	 * @param rank
	 * 			Integer value between 0 and 12 representing the rank of the card
	 */
	public BigTwoCard(int suit, int rank)
	{
		super(suit, rank); // calling the superclass constructor
	}
	
	/**
	 * Compares this card with the specified card for order for Big Two Game
	 * 
	 * @param card
	 *            the card to be compared
	 * @return a negative integer, zero, or a positive integer if this card is
	 *         less than, equal to, or greater than the specified card respectively
	 */
	public int compareTo(Card card) {
		
		
		if(this.rank==card.rank)// when ranks of cards are same
		{
			if(this.suit==card.suit)
				return 0;
			else if(this.suit>card.suit) // higher suit determines the higher card
				return 1;
			else 
				return -1;
		}
		else if(this.rank==1 || card.rank==1) // when one of the cards is a '2'
		{
			if(this.rank==1)
				return 1;
			else
				return -1;
		}
		else if(this.rank==0 || card.rank==0) // when one of the cards is Ace
		{
			if(this.rank==0)
				return 1;
			else
				return -1;
		}
		else if(this.rank>card.rank)//normal condition
			return 1;
		else
			return -1;
	
		
		
	
	
	}
	
	
}
