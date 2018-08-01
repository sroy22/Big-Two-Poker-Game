
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.awt.event.MouseListener;
/**
 * This class implements the CardGameTable interface. It is used to build a GUI for the Big Two Card Game and handle all user actions.
 * 
 * @author subhayan
 *
 */
public class BigTwoTable implements CardGameTable{
	
	private BigTwoClient game;
	/**
	 * The boolean array which indicates the cards which are selected
	 */
	private boolean [] selected=new boolean[13];
	/**
	 * The integer specifying the index of the active player
	 */
	private int activePlayer;
	/**
	 * The main window of the application of the type JFrame
	 */
	private JFrame frame;
	/**
	 * The panel for showing the cards of each player and the cards played on the table of the type JPane;
	 */
	private JPanel bigTwoPanel;
	/**
	 * The "Play" button for the active player to play his selected cards of type JButton
	 */
	private JButton playButton;
	/**
	 * The "Pass" button for the active player to pass his/her turn to the next player of type JButton
	 */
	private JButton passButton;
	/**
	 * The textArea for showing the current game status as well as end of game messages of type JTextArea
	 */
	private JTextArea msgArea;
	private JTextArea chatArea;
	private JTextField message;
	/**
	 * The 2D array for storing images of the faces of cards of type Image
	 */
	private Image[][] cardImages=new Image[4][];
	/**
	 * The image for the back of the card of type Image
	 */
	private Image cardBackImage;
	/**
	 * The array storing the images of the avatars of the players of type Image
	 */
	private Image[] avatars=new Image[4];
/**
 * The constructor for creating a BigTwoTable
 * @param game
 * 		reference to the card game associated with this table
 */
public BigTwoTable(BigTwoClient game)
{	this.game=game; // setting instance variable to the CardGame game passed as reference
	cardBackImage=new ImageIcon(getClass().getResource("b.gif")).getImage(); // getting the image of back of cards
	Image[] symbol= {
			new ImageIcon(getClass().getResource("batman_128.png")).getImage(),
			new ImageIcon(getClass().getResource("flash_128.png")).getImage(),
			new ImageIcon(getClass().getResource("green_lantern_128.png")).getImage(),
			new ImageIcon(getClass().getResource("superman_128.png")).getImage()
			
			
	}; // getting images of the avatars
	avatars=symbol;
	for(int i=0;i<13;i++)
		selected[i]=false; // re-setting all card selections to false
	Image[] img1= {
			new ImageIcon(getClass().getResource("ad.gif")).getImage(),
			new ImageIcon(getClass().getResource("2d.gif")).getImage(),
			new ImageIcon(getClass().getResource("3d.gif")).getImage(),
			new ImageIcon(getClass().getResource("4d.gif")).getImage(),
			new ImageIcon(getClass().getResource("5d.gif")).getImage(),
			new ImageIcon(getClass().getResource("6d.gif")).getImage(),
			new ImageIcon(getClass().getResource("7d.gif")).getImage(),
			new ImageIcon(getClass().getResource("8d.gif")).getImage(),
			new ImageIcon(getClass().getResource("9d.gif")).getImage(),
			new ImageIcon(getClass().getResource("td.gif")).getImage(),
			
			new ImageIcon(getClass().getResource("jd.gif")).getImage(),
			new ImageIcon(getClass().getResource("qd.gif")).getImage(),
			new ImageIcon(getClass().getResource("kd.gif")).getImage()
			
				}; // getting images of cards by suits
	Image[] img2= {
			new ImageIcon(getClass().getResource("ac.gif")).getImage(),
			new ImageIcon(getClass().getResource("2c.gif")).getImage(),
			new ImageIcon(getClass().getResource("3c.gif")).getImage(),
			new ImageIcon(getClass().getResource("4c.gif")).getImage(),
			new ImageIcon(getClass().getResource("5c.gif")).getImage(),
			new ImageIcon(getClass().getResource("6c.gif")).getImage(),
			new ImageIcon(getClass().getResource("7c.gif")).getImage(),
			new ImageIcon(getClass().getResource("8c.gif")).getImage(),
			new ImageIcon(getClass().getResource("9c.gif")).getImage(),
			new ImageIcon(getClass().getResource("tc.gif")).getImage(),
			
			new ImageIcon(getClass().getResource("jc.gif")).getImage(),
			new ImageIcon(getClass().getResource("qc.gif")).getImage(),
			new ImageIcon(getClass().getResource("kc.gif")).getImage()
			
				};
	Image[] img3= {
			new ImageIcon(getClass().getResource("ah.gif")).getImage(),
			new ImageIcon(getClass().getResource("2h.gif")).getImage(),
			new ImageIcon(getClass().getResource("3h.gif")).getImage(),
			new ImageIcon(getClass().getResource("4h.gif")).getImage(),
			new ImageIcon(getClass().getResource("5h.gif")).getImage(),
			new ImageIcon(getClass().getResource("6h.gif")).getImage(),
			new ImageIcon(getClass().getResource("7h.gif")).getImage(),
			new ImageIcon(getClass().getResource("8h.gif")).getImage(),
			new ImageIcon(getClass().getResource("9h.gif")).getImage(),
			new ImageIcon(getClass().getResource("th.gif")).getImage(),
			
			new ImageIcon(getClass().getResource("jh.gif")).getImage(),
			new ImageIcon(getClass().getResource("qh.gif")).getImage(),
			new ImageIcon(getClass().getResource("kh.gif")).getImage()
			
				};
	Image[] img4= {
			new ImageIcon(getClass().getResource("as.gif")).getImage(),
			new ImageIcon(getClass().getResource("2s.gif")).getImage(),
			new ImageIcon(getClass().getResource("3s.gif")).getImage(),
			new ImageIcon(getClass().getResource("4s.gif")).getImage(),
			new ImageIcon(getClass().getResource("5s.gif")).getImage(),
			new ImageIcon(getClass().getResource("6s.gif")).getImage(),
			new ImageIcon(getClass().getResource("7s.gif")).getImage(),
			new ImageIcon(getClass().getResource("8s.gif")).getImage(),
			new ImageIcon(getClass().getResource("9s.gif")).getImage(),
			new ImageIcon(getClass().getResource("ts.gif")).getImage(),
			
			new ImageIcon(getClass().getResource("js.gif")).getImage(),
			new ImageIcon(getClass().getResource("qs.gif")).getImage(),
			new ImageIcon(getClass().getResource("ks.gif")).getImage()
			
				};
	cardImages[0]=img1; // index of Card Images is based on suit and rank
	cardImages[1]=img2;
	cardImages[2]=img3;
	cardImages[3]=img4;
	
	
	
	frame=new JFrame(); // making new frame
	frame.setMinimumSize(new Dimension(1200,900)); // setting the minimum size of frame
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // frame will close when "Close" is clicked
	JMenuBar jmb= new JMenuBar(); // creating Menu Bar
	frame.setJMenuBar(jmb); // putting the Menu Bar on Frame
	JMenu g=new JMenu("Game"); // creating the title "Game" on Menu Bar
	jmb.add(g); // adding the title to menu Bar
	JMenu msg= new JMenu("Message");
	jmb.add(msg);
	JMenuItem clearMessages= new JMenuItem("Clear");
	msg.add(clearMessages);
	clearMessages.addActionListener(new clearMessageListener());
	JMenuItem connect=new JMenuItem("Connect"); // Creating a Menu Item "Restart" under "Game" title
	g.add(connect); // adding the menu item to menu title
	connect.addActionListener(new ConnectMenuItemListener());// adding the action Listener which is implemented in Class RestartMenuItemListener
	JMenuItem quit = new JMenuItem("Quit"); // creating a Menu Item "Quit" under "Game" title
	quit.addActionListener(new QuitMenuItemListener()); // adding the action Listener which is implement in Class QuitMenuItemListener
	g.add(quit); // adding the menu item to the menu title
	JPanel buttonleft = new JPanel(); // creating a panel for the buttons
	buttonleft.setBackground(Color.green); // setting the color of the panel to green
	frame.setVisible(true); // setting the frame to be visible
	playButton= new JButton("Play"); // creating "Play" button
	playButton.addActionListener(new PlayButtonListener()); // adding the action Listener which is implemented in Class PlayButtonListener
	
	buttonleft.add(playButton); // adding button to panel
	 passButton = new JButton("Pass"); // creating "Pass" Button
	passButton.addActionListener(new PassButtonListener()); // adding the action Listener which is implemented in Class PassButtonListener
	buttonleft.add(passButton); // adding button to panel
	msgArea= new JTextArea(20,40); // creating message area
	msgArea.setLineWrap(true);
	msgArea.setEditable(true); // message area cannot be editable
	
	
	JScrollPane scroller = new JScrollPane(msgArea); // creating a Scroll Pane object for message area which allows message area to be scrolled
	
	scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // there will be vertical scroll bar
	scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // there will not be horizontal scroll bar
	//frame.add(scroller,BorderLayout.EAST); // placing scroller in east
	chatArea=new JTextArea(20,40); // creating a chat area
	chatArea.setLineWrap(true);
	chatArea.setEditable(true);
	JScrollPane chatScroll = new JScrollPane(chatArea); // adding scroll bars to chat area
	chatScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	chatScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
	message = new JTextField(40); // adding the messaging area to write message
	message.addKeyListener(new EnterKeyListener());
	JPanel messagePanel= new JPanel(new BorderLayout());
	JLabel messageLabel= new JLabel("Message:");
	messagePanel.add(messageLabel,BorderLayout.WEST);
	messagePanel.add(message,BorderLayout.CENTER);
	
	
	JPanel rightPanel = new JPanel(new BorderLayout());
	rightPanel.add(scroller,BorderLayout.NORTH);
	rightPanel.add(chatScroll,BorderLayout.CENTER);
	rightPanel.add(messagePanel, BorderLayout.SOUTH);
	frame.add(rightPanel, BorderLayout.EAST);
	
	
	
	
	
	bigTwoPanel=new BigTwoPanel(); // creating bigTwoPanel object
	bigTwoPanel.add(buttonleft,BorderLayout.SOUTH); // adding the button Panel on south of BigTwoPanel
	
	frame.add(bigTwoPanel,BorderLayout.CENTER); // adding BigTwoPanel to center of Frame
	frame.setSize(1200,900); // setting size of Frame
	frame.setVisible(true); // setting frame to be visible
	
	
}

/**
 * Sets the index of the active player (i.e., the current player).
 * 
 * @param activePlayer
 *            an int value representing the index of the active player
 */
public void setActivePlayer(int activePlayer)
{
	this.activePlayer=activePlayer;
}
/**
 * Returns an array of indices of the cards selected.
 * 
 * @return an array of indices of the cards selected
 */
public int[] getSelected()
{
	int[] cardIdx = null;
	int count = 0;
	for (int j = 0; j < selected.length; j++) {
		if (selected[j]) {
			count++; // counting the number of cards selected by player
		} 
	}

	if (count != 0) 
		{
		cardIdx = new int[count]; // creating array of number of selected cards
		count = 0;
		for (int j = 0; j < selected.length; j++) 
		{
			if (selected[j])
				{
				cardIdx[count] = j; // storing the index of selected cards in array
				count++;
				}
		}
		}
	return cardIdx;
}
/**
 * Repaints the GUI.
 */
public void repaint()
{
	frame.repaint();
}
/**
 * Prints the specified string to the message area of the Big Two  table.
 * 
 * @param msg
 *            the string to be printed to the message area of the Big Two
 *            table
 */
public void printMsg(String msg)
{
msgArea.append(msg);	
}
public void printChat(String msg)
{
	chatArea.append(msg);
	chatArea.append("\n");
}
/**
 * Clears the message area of the Big Two table.
 */
public void clearMsgArea()
{
	msgArea.setText(null);
}
/**
 * Resets the GUI.
 */
public void reset()
{
	resetSelected(); // re-sets the cards selected
	clearMsgArea(); // clears the message area
	enable(); // enabling user-actions with GUI
}
/**
 * Enables user interactions.
 */
public void enable()
{
	passButton.setEnabled(true); // passButton can be clicked
	playButton.setEnabled(true); // playButton can be clicked
	bigTwoPanel.setEnabled(true); // BigTwoPanel can be clicked
}
/**
 * Disables user interactions.
 */
public void disable()
{
	passButton.setEnabled(false); // passButton cannot be clicked
	playButton.setEnabled(false); // playButton cannot be clicked
	bigTwoPanel.setEnabled(false); // BigTwoPanel cannot be clicked. Checking condition is also handled in the mouseClicked method.
	//Component[] components = bigTwoPanel.getComponents();
	/*for(Component component: components)
	{
		System.out.println(component);
		component.setEnabled(false);
	}
	*/
	//bigTwoPanel.removeMouseListener(new BigTwoPanel());
	//bigTwoPanel.addMouseListener(null);
	//bigTwoPanel.removeMouseListener(new BigTwoPanel());	

}
/**
 * Resets the list of selected cards to an empty list.
 */

public void resetSelected()
{
	for(int i=0;i<13;i++)
		selected[i]=false; // setting the selected array to be false. re-setting all cards to be not selected
}


/**
 * The inner class that extends the JPanel class and implements the MouseListener interface. 
 * This class overrides the paintComponent() method inherited from JPanel Class to draw the Card Game Table.
 * This class implements the MouseClicked() method from MouseListener interface to handle mouse click events.
 * @author subhayan
 *
 */
public class BigTwoPanel extends JPanel implements MouseListener
{
	/**
	 * 
	 */

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the BigTwoPanel which would set layout of the BigTwoPanel and also add MouseListener to the object of BigTwoPanel.
	 */
	public BigTwoPanel()
	{
		this.setLayout(new BorderLayout());
		
		addMouseListener(this);
		
	}
	public void paintComponent (Graphics g)
	{
		g.setColor(new Color(255,255,0));			//set the color to be yellow
		g.setFont(new Font("jokerman",Font.BOLD,16)); // setting font 
		g.fillRect(0, 0, getWidth(), getHeight()); // making rectangle to get background color
		g.setColor(Color.black); // setting color of line
		for(int i=1;i<5;i++)
			g.fillRect(0, i*getHeight()/5, getWidth(), 1); // drawing a line
		
		for(int i=0;i<4;i++)
		{	//System.out.println("THIS IS i");
			if(i==game.getCurrentIdx())
				g.setColor(Color.BLUE); // blue color represents active player
			else
				g.setColor(Color.black); // black color represents inactive player
			if(game.getPlayerList().get(i).getName()!=null)
			g.drawString(game.getPlayerList().get(i).getName(), 15, 15+i*getHeight()/5); // writing the Player Name
			g.drawImage(avatars[i], 5, 25 + i*getHeight()/5, this); // drawing image of player
		}
		
	
		
		for(int i=0;i<4;i++)
			if(i!=activePlayer)
			{
				for(int j=0;j<game.getPlayerList().get(i).getNumOfCards();j++) // if not active player then display the back of cards
					g.drawImage(cardBackImage, 150 + 20*j, 30 + i*getHeight()/5, this);
				
			}
			else
			{
				for(int j=0;j<game.getPlayerList().get(i).getNumOfCards();j++)
				{	game.getPlayerList().get(i).sortCardsInHand();
					Card card= game.getPlayerList().get(i).getCardsInHand().getCard(j);
					if(selected[j]) // if card has been selected then card should be higher position ( in a raised position as compared to other cards)
						g.drawImage(cardImages[card.suit][card.rank], 150 + 20*j, 10 + i*getHeight()/5, this);
					else // display front of cards
						g.drawImage(cardImages[card.suit][card.rank], 150 + 20*j, 30 + i*getHeight()/5, this);
				}
			}
		
		
	
		g.setColor(Color.BLACK);
		if(game.getHandsOnTable().size()==0) //initial condition when no cards on table 
			g.drawString("No Cards Played Yet", 15, 15+ 4*getHeight()/5);
		else
		{
			g.drawString("Played by", 15, 15 + 4*getHeight()/5); 
			g.drawString(game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getPlayer().getName(), 15, 30 +4*getHeight()/5); // displaying name of player of last hand
			Hand h=game.getHandsOnTable().get(game.getHandsOnTable().size()-1); // displaying hand of cards last player
			for(int k=0;k<h.size();k++)
			{
				Card card=h.getCard(k);
				g.drawImage(cardImages[card.suit][card.rank], 150 + 20*k, 30 + 4*getHeight()/5, this);

				
			}
		
		}
		
		repaint(); // refreshing the GUI
		 
	
		 
		 
	}
	public  void mousePressed(MouseEvent Event)
	{
		
	}
	public  void mouseReleased(MouseEvent Event)
	{
		
	}
	public  void mouseClicked(MouseEvent Event)
	{ 
		
		int num=game.getPlayerList().get(activePlayer).getNumOfCards()-1; // getting index of total cards
		int flag=0;
		
	
		if(num>=0) // cards can be clicked only if player has 1 or more cards
		{if(selected[num] && flag==0 && num!=-1) // checking condition for last card
		{
			if(Event.getY()>=(10 + activePlayer*getHeight()/5) && Event.getY()<=(10 + 97 + activePlayer*getHeight()/5))
				if(Event.getX()>=(150 + 20*num) && Event.getX()<=(150 + 73 + 20*num))
					{selected[num]=false;
					flag=1;
					}
		}
		else
		{
			if(Event.getY()>=(30 + activePlayer*getHeight()/5) && Event.getY()<=(30 + 97 + activePlayer*getHeight()/5))
				if(Event.getX()>=(150 + 20*num) && Event.getX()<=(150 + 73 + 20*num))
					{selected[num]=true;
					flag=1; // this boolean ensures that we check only once
					}
		}
		
	for(int i=num-1;i>=0&& flag==0;i--) // checking from second last card to beginning only if not present in last card
	{
		if(selected[i] && selected[i+1])
		{	
			if(Event.getX()>=(150 + 20*i) && Event.getX()<(150 + 20*(i+1)) && Event.getY()>=(10 + activePlayer*getHeight()/5) && Event.getY()<=(10 + 97 + activePlayer*getHeight()/5))
				{selected[i]=false;
				flag=1; // the boolean ensures that we check only once
				}
		}
		else if (!selected[i] && !selected[i+1])
		{
		
			if(Event.getX()>=(150 + 20*i) && Event.getX()<(150 + 20*(i+1)) && Event.getY()>=(30 + activePlayer*getHeight()/5) && Event.getY()<=(30 + 97 + activePlayer*getHeight()/5))
				{selected[i]=true;
				flag=1;
				}
		}
		else if(selected[i] && !selected[i+1])
		{
			
			if(Event.getX()>=(150 + 20*i) && Event.getX()<=(150 + 20*(i+1)) && Event.getY()>=(10 + activePlayer*getHeight()/5) && Event.getY()<=(10 + 97 + activePlayer*getHeight()/5))
				{selected[i]=false;
				flag=1;
				}
			if(Event.getX()>=(150+20*i) && (Event.getX()<150+73+20*i) && Event.getY()>=(10+activePlayer*getHeight()/5) && Event.getY() <(30 + activePlayer*getHeight()/5))
				{selected[i]=false;
				flag=1;
				}
		}
		else
		{
			
			if(Event.getX()>=(150 + 20*i) && Event.getX()<(150 + 20*(i+1)) && Event.getY()>=(30 + activePlayer*getHeight()/5) && Event.getY()<=(30 + 97 + activePlayer*getHeight()/5))
				{selected[i]=true;
				flag=1;
				}
			if(Event.getX()>=(150+20*i) && Event.getX()<(150+73+20*i) && Event.getY()>=(97+10+activePlayer*getHeight()/5) && Event.getY() <= (97 + 30 + activePlayer*getHeight()/5))
				{selected[i]=true;
				flag=1;
				}
		}
		repaint(); // refreshing the GUI
	}
	}
	
	}
	public  void mouseExited(MouseEvent Event)
	{
		
	}
	public  void mouseEntered(MouseEvent Event)
	{
		
	}
	
	
}
/**
 * The inner class that implements the ActionListener interface. 
 * This class implements the actionPerformed() method from the actionListener interface to handle button click events for the "Play" button.
 * 
 * @author subhayan
 *
 */
public class PlayButtonListener implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	{
		int playCards[]=getSelected();
		if(playCards!=null) // only if player has selected cards
		{
			game.makeMove(activePlayer, playCards); // calling makeMove to check the move and then make the move
			resetSelected(); // resets all cards to be not selected
		}
		
	}
}
public class EnterKeyListener implements KeyListener
{
	public void keyPressed(KeyEvent k)
	{
		if(k.getKeyCode()==KeyEvent.VK_ENTER) // when enter key is pressed
		{
			if(message.getText()!=null || message.getText()!="")
				game.sendMessage(new CardGameMessage(CardGameMessage.MSG,-1,message.getText())); // message entered will be sent 
				message.setText(null); // screen will go back to initial state with no message
			
		}
	}
	public void keyReleased(KeyEvent k)
	{
		
	}
	public void keyTyped(KeyEvent k)
	{
		
	}
}
/**
 * The inner class that implements the ActionListener interface.
 *   This class implements the actionPerformed() method from the actionListener interface to handle button click events for the "Pass" button.
 * @author subhayan
 *
 */
public class PassButtonListener implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	{
		game.makeMove(activePlayer, null); // calling make Move when player has passed
		resetSelected(); // resets all cards to be not selected
	}
}
/**
 * The inner class that implements the ActionListener interface. 
 * This class implements the actionPerformed() method from the ActionListener interface to handle menu-click events for the "Restart" menu item.
 * @author subhayan
 *
 */
public class ConnectMenuItemListener implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	{
		/*BigTwoDeck deck= new BigTwoDeck(); // creates an object of BigTwoDeck
		clearMsgArea(); // clears the message area
		deck.initialize(); // initializes the deck
		deck.shuffle(); // shuffles the deck of cards
		game.start(deck); // starts the game
		resetSelected(); // resets all cards to be not selected
		reset(); // resets the GUI
		*/
		if(game.getPlayerID()>=0 && game.getPlayerID()<=3)
			printMsg("Already connected\n");
		else
		game.makeConnection(); // make COnnection will be called when connect is clicked
	}
}
/**
 * The inner class that implements the ActionListener interface. 
 * This class implements the actionPerformed() method from the ActionListener interface to handle menu-click events for the "Quit" menu item.
 * @author subhayan
 *
 */
public class clearMessageListener implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	{
		chatArea.setText(null);
	}
}
public class QuitMenuItemListener implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	{
		System.exit(0); // closes the application
	}
}
}
