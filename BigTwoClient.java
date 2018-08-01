import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BigTwoClient implements CardGame, NetworkGame
{
	private int numOfPlayers;
	private Deck deck;
	private ArrayList<CardGamePlayer> playerList=new ArrayList<CardGamePlayer>();
	private ArrayList<Hand> handsOnTable;
	private int playerID=-1;
	private String playerName;
	private String serverIP;
	private int serverPort;
	private Socket sock;
	private ObjectOutputStream oos;
	private int currentIdx;
	private BigTwoTable table;
	
	public BigTwoClient()
	{	
		CardGamePlayer c1=new CardGamePlayer(); // creating players
		CardGamePlayer c2=new CardGamePlayer();
		CardGamePlayer c3=new CardGamePlayer();
		CardGamePlayer c4=new CardGamePlayer();
		playerList.add(c1); // adding players to playerList
		playerList.add(c2);
		playerList.add(c3);
		playerList.add(c4);
		numOfPlayers=4;
		handsOnTable=new ArrayList<Hand>(); // creating ArrayList to store the hands on table
		table=new BigTwoTable(this);//creating BigTwoTable object
		
		
		
		this.playerName= JOptionPane.showInputDialog("Please Enter Your Name");
		setPlayerName(playerName);
		
		makeConnection();
		
	}
	
	public int getNumOfPlayers()
	{
		return numOfPlayers;
	}
	public Deck getDeck()
	{
		return deck;
	}
	public ArrayList<CardGamePlayer> getPlayerList()
	{
		return playerList;
	}
	public ArrayList<Hand> getHandsOnTable()
	{
		return handsOnTable;
	}
	public int getCurrentIdx()
	{
		return currentIdx;
	}
	public void start(Deck deck)
	{
		table.printMsg("Game starts\n");
		handsOnTable.clear();
		for(int i=0;i<4;i++)
			playerList.get(i).removeAllCards(); // removing all cards of each player
		
		
		for(int i=0;i<4;i++)
			for(int j=0;j<13;j++)
				playerList.get(i).addCard(deck.getCard(13*i+j)); // giving each player 13 cards
				
		
		Card starting=new Card(0,2); // card holding 3 of diamonds
		
		for(int i=0;i<4;i++)
			if(playerList.get(i).getCardsInHand().contains(starting))//getting the player who has 3 of diamonds
				{
				currentIdx=i; // setting the index of player who has 3 of diamonds
				}
		
		table.disable(); // disabling all players initially
		if(currentIdx==playerID)
		{
			table.enable(); // enabling the current player
			table.printMsg("Your turn\n");
		}
		else
			{table.printMsg(playerList.get(currentIdx).getName()+ "'s turn\n");
			table.disable();
			}
		
		
		table.repaint();
	}
	public void makeMove(int playerId, int[] cardIdx)
	{
		sendMessage(new CardGameMessage(CardGameMessage.MOVE,-1,cardIdx));
	}
	public void checkMove(int playerId, int[] cardIdx)
	{
		
		
		boolean isFine=true; // boolean variable to represent a legal move
		if(isFine) // legal move
				{playerList.get(currentIdx).sortCardsInHand(); //sorting the current player's cards
				}
			isFine=true; // re-setting value of isFine
			if(cardIdx!=null) // if cardIdx is not empty
			{
				if(handsOnTable.size()!=0 && handsOnTable.get(handsOnTable.size()-1).getPlayer().getName()==playerList.get(currentIdx).getName()) // condition when there have been 3 passes (current player is the player who last played a move) and player can make any move
				{
					CardList cardPlayed= playerList.get(currentIdx).play(cardIdx); // cardList cardPlayed has the cards played by the current player
					Hand temp=composeHand(playerList.get(currentIdx),cardPlayed); // forming or validating a hand out of the given played cards
				
					
					
					if(temp!=null) // if hand is valid
					{handsOnTable.add(temp); // adding the hand of the player to the table
					table.printMsg("{" + temp.getType()+"} "); // printing the hand type
					print(cardPlayed); // printing the cards played
					table.printMsg("\n");
					for(int i=0;i<cardPlayed.size();i++)
						playerList.get(currentIdx).getCardsInHand().removeCard(cardPlayed.getCard(i)); // removing the cards played by the player from his set of cards
					
					if(endOfGame()) // when game is over
					{	
						table.disable(); // game panel should be disabled
						String s;
					
						s="Game ends\n"; // printing the messages on the message area signalling game over
						for(int i=0;i<playerList.size();i++)
						{	if(playerList.get(i).getCardsInHand().size()==0) // searching for the player who won the game
								{s=s+playerList.get(i).getName()+" wins the game.\n";
								
								}
							else
							s=s+playerList.get(i).getName()+ " has " + playerList.get(i).getCardsInHand().size()+" cards in hand.\n";
						table.disable();
						}
						
						int option=JOptionPane.showOptionDialog(null,s,null,JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
						if(option==JOptionPane.OK_OPTION)
							sendMessage(new CardGameMessage(CardGameMessage.READY,-1,null));
						
						
							
					}
					if(!endOfGame()) // if game is not over
						{currentIdx=(currentIdx+1)%4; // changing index of player to the next player
						//table.printMsg(playerList.get(currentIdx).getName() + "' turn\n");	
						//table.setActivePlayer(playerID); // setting active player to next player
						}
					}
					else
					{
						print(cardPlayed); // printing the cards the player wanted to play
						table.printMsg("Not a legal move!!!\n");
					}	
				}
				else // all other cases
				{
				CardList cardPlayed= playerList.get(currentIdx).play(cardIdx); // cardList cardPlayed has the cards played by the current player
				Hand temp=composeHand(playerList.get(currentIdx),cardPlayed); // forming a hand out of the given cards
				 
				
				
				if(handsOnTable.isEmpty()) // initial condition when game starts
				{
					if(temp!=null && temp.contains(new Card(0,2))) // checking if player has played a hand with 3 of diamonds
						isFine=true; // legal move
					else
						isFine=false; // not a legal move
				}
				else // for all other cases
				{
					if(temp!=null) // if not pass or not legal move
					isFine=temp.beats(handsOnTable.get(handsOnTable.size()-1)); // checking if current hand beats last hand on table
					else
						isFine=false;
				}
			if(isFine) //legal move beating the hand on the table
			{
				for(int i=0;i<cardPlayed.size();i++)
					playerList.get(currentIdx).getCardsInHand().removeCard(cardPlayed.getCard(i)); // removing the cards that the player has played
				if(endOfGame()) // when game is over
				{	//bigTwoConsole.setActivePlayer(-1);//there is no active player 
					//bigTwoConsole.repaint(); // prints all the cards in hand of each player
					table.printMsg("\n");
					table.disable(); // when game is over the game panel should be disabled
					String s;
					String k="";
					s="Game ends\n";
					for(int i=0;i<playerList.size();i++)
					{	if(playerList.get(i).getCardsInHand().size()==0) // searching for the player who won the game
							{s=s+playerList.get(i).getName()+" wins the game.\n";
							}
						else
						s=s+ playerList.get(i).getName()+ " has " + playerList.get(i).getCardsInHand().size()+" cards in hand.\n";
					table.disable();
					}
					int option=JOptionPane.showOptionDialog(null,s,null,JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
					if(option==JOptionPane.OK_OPTION)
						sendMessage(new CardGameMessage(CardGameMessage.READY,-1,null));
					
					}
				if(temp!=null) // if hand is valid
				{handsOnTable.add(temp); // adding the hand to the table
				table.printMsg("{" + temp.getType()+"} ");
				print(cardPlayed);
				table.printMsg("\n");
				}
			currentIdx=(currentIdx+1)%4; // changing the index to the next player
			//table.setActivePlayer(playerID); // setting active player to the next player
			//table.printMsg(playerList.get(currentIdx).getName() + "' turn\n");	
			table.repaint();
			}
			else
			{
				if(temp!=null)
					{table.printMsg("{" + temp.getType()+"}"); 
					print(cardPlayed); // printing the type of hand and cards the player wanted to play
					table.printMsg("<==Not a legal move!!!\n");
					}
				else
				{
					print(cardPlayed); // printing the cards the player wanted to play
					table.printMsg("<==Not a legal move!!!\n");
				}
			}
			}
			}
			else //  if cardIDx is null/empty which happens when pass/not legal move
			{
				
				
				if(handsOnTable.isEmpty() || handsOnTable.get(handsOnTable.size()-1).getPlayer().getName()==playerList.get(currentIdx).getName()) // initial condition when game starts and when current player is same as player who last played
					{table.printMsg("{pass} <==Not a legal move!!!\n");
					isFine=false;
					}
				else
				{	
					
				table.printMsg("{Pass}\n"); // null is the condition for pass
				
				currentIdx=(currentIdx+1)%4; // changing index to next player
				//table.setActivePlayer(playerID); // setting active player to next player
			
				//table.printMsg("Player" + currentIdx + "' turn\n");	
				table.repaint();
				}
			}
	}
	public boolean endOfGame()
	{
		return playerList.get(currentIdx).getNumOfCards()==0;
	}
	public int getPlayerID()
	{
		return playerID;
	}
	public void setPlayerID(int playerID)
	{
		this.playerID=playerID;
	}
	public String getPlayerName()
	{
		return playerName;
	}
	public void setPlayerName(String playerName)
	{
		this.playerName=playerName;
	}
	public String getServerIP()
	{
		return serverIP;
	}
	public void setServerIP(String serverIP)
	{
		this.serverIP=serverIP;
	}
	public int getServerPort()
	{
		return serverPort;
	}
	public void setServerPort(int serverPort)
	{
		this.serverPort=serverPort;
	}
	
	public void makeConnection()
	{
		setServerIP("127.0.0.1"); // setting IP
		setServerPort(2396); // setting the port
		try
		{
		sock=new Socket(getServerIP(), getServerPort()); // creating new socket
		oos=new ObjectOutputStream(sock.getOutputStream());
		ServerHandler threadJob = new ServerHandler();
		Thread myThread= new Thread(threadJob);
		myThread.start();
		sendMessage(new CardGameMessage(CardGameMessage.JOIN,-1,playerName));
		sendMessage(new CardGameMessage(CardGameMessage.READY,-1,null));
		
	}
		catch ( Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	public void print(CardList cards) {
		for (int k = 0; k < cards.size(); k++) {
			String string = "";
				string = string + "[" + cards.getCard(k) + "]";
			if (k % 13 != 0) {
				string = " " + string;
			}
			table.printMsg(string);
			if (k % 13 == 12 || k == cards.size() - 1) {
				table.printMsg("");
			}
		}
}
	
	public synchronized void parseMessage(GameMessage message)
	{
		if(message.getType()==0)
		{	
			setPlayerID(message.getPlayerID());
			table.setActivePlayer(message.getPlayerID());
			if(message.getData()!=null)
			{
				String[] names= (String[]) message.getData();
				for(int i=0;i<names.length;i++)
					playerList.get(i).setName(names[i]);
			}
			table.repaint();
			
		}
		else if(message.getType()==1)
		{
			playerList.get(message.getPlayerID()).setName((String)message.getData());
			
			
		}
		else if(message.getType()==2)
		{	
			table.printMsg("Server is full\n");
			
		}
		else if(message.getType()==3)
		{	table.printMsg((String)message.getData()+"has left the game\n");
			playerList.get(message.getPlayerID()).setName("");
			if(!endOfGame())
			{
				table.disable();
				sendMessage(new CardGameMessage(CardGameMessage.READY,-1,null));
				
			
			}
			
		}
		else if(message.getType()==4)
		{	
			table.printMsg(playerList.get(message.getPlayerID()).getName()+" is ready \n");
			
		}
		else if(message.getType()==5)
		{	
			this.deck=(BigTwoDeck)message.getData();
			start((BigTwoDeck)message.getData());
			
		}
		else if(message.getType()==6)
		{	
			checkMove(message.getPlayerID(),(int [])message.getData());
			table.disable();
			if(currentIdx==playerID)
			{
				table.enable();	
				table.printMsg("Your turn\n");
			}
			else
				table.printMsg(playerList.get(currentIdx).getName()+ "'s turn\n");
			table.repaint();
			
		}
		else if(message.getType()==7)
		{	
			table.printChat((String)message.getData());
			
			
		}
		
	}
	public void sendMessage(GameMessage message)
	{
		try
		{
			oos.writeObject(message);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static Hand composeHand(CardGamePlayer player, CardList cards)
	{
		Hand newPair=new Pair(player,cards); // checking pair out of given cards
		if(newPair.isValid())
			return newPair;
		
		Hand newQuad=new Quad(player,cards); //checking quad out of given cards
		if(newQuad.isValid())
			return newQuad;
		
		Hand newSingle=new Single(player,cards);//checking single out of given cards
		if(newSingle.isValid())
			return newSingle;
		Hand newStraightFlush=new StraightFlush(player,cards);//checking StraightFlush out of given cards. Checking for StraightFlush has to be done before Straight.
		if(newStraightFlush.isValid())
			return newStraightFlush;
		Hand newStraight=new Straight(player,cards); //checking Straight out of given cards
		if(newStraight.isValid())
			return newStraight;
		Hand newTriple=new Triple(player,cards); //checking Triple out of given cards
		if(newTriple.isValid())
			return newTriple;
		Hand newFlush=new Flush(player,cards); //checking Flush out of given cards
		if(newFlush.isValid())
			return newFlush;
		Hand newFullHouse=new FullHouse(player,cards);//checking FullHouse out of given cards
		if(newFullHouse.isValid())
			return newFullHouse;
		
		return null; // if hand is not of any of the given hand types
		
		
						
					
		
	}
	public static void main(String[] args)
	{
		BigTwoClient bigTwoClientObject=new BigTwoClient();
		
	}
	public class ServerHandler implements Runnable
	{
		private ObjectInputStream input;
		
		public ServerHandler()
		{
			try
			{input = new ObjectInputStream(sock.getInputStream());
			}
			catch ( Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		public void run()
		{
			try
			{
			CardGameMessage cdm ;
					
			{while((cdm=(CardGameMessage)input.readObject())!=null)
				{parseMessage(cdm);
				
				}
			}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	

}
