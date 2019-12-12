
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JFrame implements Runnable,MouseListener{
	
	private int sum = 0;

	 public Dice dice1,dice2,dice3,dice4,dice5;
	 public Dice my_dice;
	 public JButton button;
	 public JLabel l1 = new JLabel("sdgsdg");

	 private int click_count = 0;
	 public Player player,player1;
	 public Player[] player_vector;
	 JPanel show_turn = new JPanel();
	// private String turn_player = "1";
	 private JLabel turn_text = new JLabel("TURN: "+(Player.turn+1));
	 //Create a vector to Hold all dices;
	 public Dice [] dice_vector;
	 public JLabel[] select_dice;
	 public JLabel[] temp;
	 private int select_index = 0;
	 private int clk_ = 0;
	 public ArrayList<JLabel> dice_arr = new ArrayList();
	 private int cat_count = 0;
 	 
	public Game()
	{
		
		
		
		//Krijimi i Frames
		this.setSize(800, 700);
	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.CYAN);
		getContentPane().setLayout(null);
		
		//this.addMouseListener(this);
		
		//Create a button to click;
		button = new JButton("Roll the dice");
		button.setBounds(20, 500, 150, 50);
		getContentPane().add(button);
		
		
		
		my_dice = new Dice(20,10);
		getContentPane().add(my_dice);
		//Add all dice label to arraylist
		for(int i =0;i<my_dice.dice_label.length;i++)
			
		{
			dice_arr.add(my_dice.dice_label[i]);
		}
		//shton Mouse Listener te cdo imazh i zarave
		
		for(int i = 0;i<my_dice.dice_label.length;i++)
		{
			my_dice.dice_label[i].addMouseListener(this);
		}
		
		player = new Player(300,0);
	    player1 = new Player(550,0);
	    player_vector = new Player[] {player,player1};

		getContentPane().add(player1);
		getContentPane().add(player);
		for(int i = 0;i<2;i++)
		{
			for(int j = 0;j<3;j++)
			{
				
				player_vector[i].txt_vector[j].addMouseListener(this);
			}
		}
		showplayer_turn();
		
		
		//shton mouse Listener te cdo label i lojtareve
		
		System.out.println("SUM: "+ my_dice.return_points());
		
		
		
			play_game();
		
	}
	
	public void play_game()
	{
		
		//kur click buttonin per te heddhur zarat
		button.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  //Te shtypet buttoni kujr eshte me e vogel se 3 clickper_round
			  if(player_vector[Player.turn].clickper_round < 3 && cat_count < 2)
				
			  
			  {
				  player_vector[Player.turn].clickper_round++;
				  System.out.println("Array List size: "+ dice_arr.size());
				  System.out.println("Player "+ Player.turn + " click/round: "+player_vector[Player.turn].clickper_round);
				  //gjenerimi randon i imazheve per zarat
				  click_count = 0;
				  if(dice_arr.size() == 0)
				  {
					  for(int i =0;i<my_dice.dice_label.length;i++)
						{
							dice_arr.add(my_dice.dice_label[i]);
						}
				  }
					  for(int i = 0;i<dice_arr.size();i++)
					  {
						  int num_ = generateRandom();//****Ketu eshte gabimi sepse i behet random 
						 
						  my_dice.change_dice(dice_arr.get(i), num_);
						  Dice._point_vector[i] = num_;				  
					  }

					 
					  click_count++;
					  
				
				  System.out.println("SUM: "+ my_dice.return_points());
			
				  dice_arr.removeAll(dice_arr);
			  }
			  	
			 
		  }
		  
		 

		
		});
		
		
		
		
		
}

public int generateRandom()
{
	Random num = new Random();
	int rand_num = 1 + num.nextInt(6);
	sum = sum + rand_num;
	return rand_num;
	
}
public int return_sum()
{
	return sum;
}
	
public void showplayer_turn()
{

	show_turn.add(turn_text);
	show_turn.setBounds(120, 600, 200, 60);
	show_turn.setBackground(Color.RED);
	getContentPane().add(show_turn);
}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		for(int i = 0;i<player.txt_vector.length;i++)
		{
			if(e.getSource() == player_vector[Player.turn].txt_vector[i] && player_vector[Player.turn].clickper_round !=0)
			{
				//daf
				player_vector[Player.turn].txt_vector[i].setText(Integer.toString(my_dice.return_points()));
				player_vector[Player.turn].clickper_round = 0;
				
				Player.turn ++;
				if(Player.turn == player_vector.length)
					{
						cat_count++;
						Player.turn = 0;
					}
				 if(cat_count > 2)
				  {
					  System.out.println("END");
				  }

			}
			
		}
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
			
				System.out.println("Array List size: "+ dice_arr.size());
				select_index = 0;
				for(int x = 0;x<my_dice.dice_label.length;x++ )
				{
					
					if(e.getSource() instanceof JLabel)
						
					{
						
						JLabel src = (JLabel) e.getSource();
						
							if(src  == my_dice.dice_label[x])
							{
								System.out.println("Clicked"+x);
								my_dice.change_dice(my_dice.dice_label[x], 7);

								dice_arr.add(my_dice.dice_label[x]);
								
								if(click_count == 0)
									break;
								
								
							}
						
						
					}
				}

				System.out.println("SUM: "+ my_dice.return_points());
			
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
 
	
	
}
