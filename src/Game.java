
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

	 public Dice my_dice; //Zarat
	 public JButton button;
	 private int click_count = 0;
	 public Player player,player1,player2,player3;
	 public Player[] my_player;
	 public Player[] player_vector = new Player[2]; //vendos nr e lojtareve
	 JPanel show_turn = new JPanel();
	 private JLabel turn_text = new JLabel("TURN: "+(Player.turn+1));
	 private int points = 0;
	 //ArrayList per te mbajtur zaret qe do te hiddhen 
	 //public ArrayList<JLabel> dice_arr = new ArrayList();
	 public ArrayList<Integer> dice_arr = new ArrayList();
	 private int same_3_vlera;
	 private int cat_count = 0;
 	 
	public Game()
	{
		
		
		
		//Krijimi i Frames
		this.setSize(870, 700);
	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.CYAN);
		getContentPane().setLayout(null);

		//Create a button to click;
		button = new JButton("Roll the dice");
		button.setBounds(20, 500, 150, 50);
		getContentPane().add(button);
		
		
		
		my_dice = new Dice(20,10);
		getContentPane().add(my_dice);
		//Add all dice label to arraylist
		for(int i =0;i<my_dice.dice_label.length;i++)
			
		{
			dice_arr.add(i);
		}
		//shton Mouse Listener te cdo imazh i zarave
		
		for(int i = 0;i<my_dice.dice_label.length;i++)
		{
			my_dice.dice_label[i].addMouseListener(this);
		}
		
		player = new Player(220,0);
	    player1 = new Player(380,0);
	    player2 = new Player(540,0);
	    player3 = new Player(700,0);
	    my_player = new Player[] {player,player1,player2,player3};
	    for(int i = 0;i<player_vector.length;i++)
	    {
	    	player_vector[i] = my_player[i];
	    	getContentPane().add(player_vector[i]);
	    }
	    //player_vector = Player[] {player,player1,player2,player3};
	
		for(int i = 0;i<player_vector.length;i++)
		{
			for(int j = 0;j<13;j++) //kategoruite
			{
				
				player_vector[i].txt_vector[j].addMouseListener(this);
			}
		}
		showplayer_turn();

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
			  if(player_vector[Player.turn].clickper_round < 20 && cat_count < 2)
				
			  
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
						  //dice_arr.get(i).setEnabled(true);
							dice_arr.add(i);
							//dice_arr.get(i).setEnabled(true);
						}
				  }
					 for(int i = 0;i<dice_arr.size();i++)
					  {
						  int num_ = generateRandom();
						 my_dice.dice_label[dice_arr.get(i)].setEnabled(true);
						  my_dice.change_dice(my_dice.dice_label[dice_arr.get(i)], num_);
						  
						  Dice._point_vector[dice_arr.get(i)] = num_;				  
					  }

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
				player_vector[Player.turn].txt_vector[i].setText("Kategoria "+(i+1)+":"+Integer.toString(pointper_category(i+1)));
				player_vector[Player.turn].clickper_round = 0;
				/*
				 * Do te shtohet funksionet per llogaritjen e pikeve per cdo kategori....
				 */
					
				player_vector[Player.turn].txt_vector[i].removeMouseListener(this);
				
				Player.turn ++;
				
				if(Player.turn == player_vector.length)
					{
						cat_count++;
						Player.turn = 0;
					}
				turn_text.setText("TURN: "+(Player.turn+1));
				 if(cat_count > 2) //do behet me 13  kategori  ...... 
				  {
					  System.out.println("END");
				  }

			}
			
		}
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

				//System.out.println("Array List size: "+ dice_arr.size());

				for(int x = 0;x<my_dice.dice_label.length;x++ )
				{
					
					if(e.getSource() instanceof JLabel)
						
					{
						
						JLabel src = (JLabel) e.getSource();
						
							if(src == my_dice.dice_label[x])
							{
								System.out.println("Clicked"+x);
								//my_dice.change_dice(my_dice.dice_label[x], 7);
								my_dice.dice_label[x].setEnabled(false);
								//dice_arr.add(my_dice.dice_label[x]);
								dice_arr.add(x);
								
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
	
	
	public int pointper_category(int category_num)
	{
		if(category_num == 1)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 1)
				{
					points = points + 1;
				}
			}
		}
		if(category_num == 2)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 2)
				{
					points = points + 2;
				}
			}
		}
		if(category_num == 3)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 3)
				{
					points = points + 3;
				}
			}
		}
		
		
		if(category_num == 4)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 4)
				{
					points = points + 4;
				}
			}
		}
		if(category_num == 5)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 5)
				{
					points = points + 5;
				}
			}
		}
		if(category_num == 6)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 6)
				{
					points = points + 6;
				}
			}
		}
		
		//Te pakten 3 me nje vlere dhe piket jan sa shuma e gjithe zarave
		if(category_num == 7)
		{
			points = 0;
			
			if(longest_freq(Dice._point_vector)>=3)
			{
				for(int i = 0;i<Dice._point_vector.length;i++)
				{
					
						points = points  + Dice._point_vector[i];
					
				}
			}
			//points = my_dice.return_points() + 100;
			
		}
		
		if(category_num == 8)
		{
			points = 0;
			
			if(longest_freq(Dice._point_vector)>=4)
			{
				for(int i = 0;i<Dice._point_vector.length;i++)
				{
					
						points = points  + Dice._point_vector[i];
					
				}
			}
			//points = my_dice.return_points() + 100;
			
		}
		
		if(category_num == 9)
		{
			points = 0;
			
			if(longest_freq(Dice._point_vector)==3 && same_2(Dice._point_vector ) == true)
			{
				points = 25;
			}
			//points = my_dice.return_points() + 100;
			
		}
		
		if(category_num == 10)
		{
			points = 0;
			
			if(continous(Dice._point_vector) == 4)
			{
				points = 30;
			}
			//points = my_dice.return_points() + 100;
			
		}
		if(category_num == 11)
		{
			points = 0;
			
			if(continous(Dice._point_vector) == 5)
			{
				points = 40;
			}
			//points = my_dice.return_points() + 100;
			
		}
		if(category_num == 12)
		{
			points = 0;
			
			if(all_same(Dice._point_vector) == true)
			{
				points = 50;
			}
			//points = my_dice.return_points() + 100;
			
		}
		
		if(category_num == 13)
		{
			points = 0;
			
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				points = points + Dice._point_vector[i];
			}
			//points = my_dice.return_points() + 100;
			
		}
		return points;
	}
	
	public int longest_freq(int Arr[])
	{
		int freq = 1;
		int count = 1;
		for(int i = 0;i<Arr.length-1;i++)
		{
			count = 1;
			for(int j = i+1;j<Arr.length;j++)
			{
				if(Arr[i] == Arr[j])
				{
					count++;
					
				}
			}
			if(count > freq)
			{
				freq = count;
				same_3_vlera = Arr[i];
			}
		}
		return freq;
	}
	

	public boolean same_2(int Arr[])
	{
		int count = 1;
		if(longest_freq(Arr) == 3)
		{
			
			
			for(int i = 0;i<Arr.length-1;i++)
			{
				if(Arr[i] != same_3_vlera)
				{
					for(int j = i+1;j<Arr.length;j++)
					{
						if(Arr[i] == Arr[j])
						{
							count++;
						}
						
					}
				}
				
			}
			//return freq;
		}
		if(count == 2)
			
		{
			return true;
		}
		return false;
	}
	
	public int continous(int Arr[])
	{
		int count = 1;
		
		for(int i = 0;i<Arr.length-1;i++)
		{
			if(i == 2 && count == 1)
				break;
			if(Arr[i] - Arr[i+1] == -1)
			{
				count++;
			}
			
		}
		return count;
	}
	
	public boolean all_same(int Arr[])
	{

		for(int i = 0;i<Arr.length-1;i++)
		{
			if(Arr[i] != Arr[i+1])
				return false;
			
		}
		return true;
	}
	
}


