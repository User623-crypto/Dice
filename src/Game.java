import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JFrame implements Runnable,MouseListener{
	
	private int sum = 0;

	 public Dice dice1,dice2,dice3,dice4,dice5;
	 public JButton button;
	 public JLabel l1 = new JLabel("sdgsdg");

	 private int click_count = 0;
	 public Player player,player1;
	 public Player[] player_vector;
	 JPanel show_turn = new JPanel();
	 private String turn_player = "";
	 private JLabel turn_text = new JLabel("TURN: "+turn_player);
	 //Create a vector to Hold all dices;
	 public Dice [] dice_vector;
	 public Dice [] select_dice;
	 private int select_index = 0;
 	 
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
		
		
		//Dice_img = new Add_Image("Dice.png");
		
		//Shto zaret ne fushe
		
		dice1 = new Dice(20,10,"dice" +generateRandom()+".png",this);
		dice1.setName("zari1");
		
		dice2 = new Dice(20,110,"dice" +generateRandom()+".png",this);
		dice2.setName("zari2");
		
		dice3 = new Dice(20,210,"dice" +generateRandom()+".png",this);
		dice3.setName("zari3");
		
		dice4 = new Dice(20,310,"dice" +generateRandom()+".png",this);
		dice4.setName("zari4");
		
		dice5 = new Dice(20,410,"dice" +generateRandom()+".png",this);
		dice5.setName("zari5");
		
		dice_vector = new Dice[] {dice1,dice2,dice3,dice4,dice5};

		//shton Mouse Listener te cdo imazh i zarave
		for(int i = 0;i<5;i++)
		{
			dice_vector[i].addMouseListener(this);
		}
		player = new Player(300,0);
	    player1 = new Player(550,0);
	    player_vector = new Player[] {player,player1};
		
		//player.setLayout(null);
		
		
		//player.setBounds(player.player_x(),player.player_y(), 200, this.getHeight());
		getContentPane().add(player1);
		getContentPane().add(player);
		showplayer_turn();
		
		
		//shton mouse Listener te cdo label i lojtareve
		for(int i = 0;i<2;i++)
		{
			for(int j = 0;j<3;j++)
			{
				
				player_vector[i].txt_vector[j].addMouseListener(this);
			}
		}
		//System.out.println(sum);
		play_game();

		
	}
	
	public void play_game()
	{
		
		//kur click buttonin per te heddhur zarat
		button.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  sum = 0;
			  //gjenerimi randon i imazheve per zarat
			  for(int i = 0;i<dice_vector.length;i++)
			  {
				  dice_vector[i].change_dice("dice"+ generateRandom()+".png");
				  //sum = sum + generateRandom();
				  
				  
			  }
			  //per te ruajtur rradhen e lojtareve 
			  //qe mos te llogariten piket per lojtrin 2 kur e ka rradhen lotari 1
			  if(click_count%2==0)
			  {
				  Player.turn++;
				  
			  }
			  else {
				  Player.turn += 2;
			  }
			  
			  click_count++;
			  
			  //regon rradhen ... 
			  if(Player.turn%2 == 0)
				{
					turn_player = "player";
					turn_text.setText("TURN: "+turn_player);
				}
				else if(Player.turn%2 == 1)
				{
					turn_player = "player1";
					turn_text.setText("TURN: "+turn_player);
				}
			  System.out.println(return_sum());
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
	show_turn.setBounds(200, 600, 200, 60);
	show_turn.setBackground(Color.RED);
	getContentPane().add(show_turn);
}
    //The method that gets executed by the thread
    public void run(){//ASAP
        //setFocusable(true); 
        
        

        /* Menaxhimi i frames*/
        long lastTime=System.nanoTime();
        double nanoSecondConversion=1000000000.0/60;//60 frames per second
        double changeInSeconds=0;

        
        //Standard
        while(true){
            requestFocus();
            
           long now=System.nanoTime();
           changeInSeconds +=(now-lastTime)/nanoSecondConversion;
            while(changeInSeconds>=1)
            {
               // play_game(); 
                changeInSeconds=0;
            }
            
            lastTime=now;

              
        }
       
        //Dispose  the frame
        

    }

    public void try_()
    {
    		//df
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		for(int i = 0;i<player.txt_vector.length;i++)
		{
			if(e.getSource() == player_vector[Player.turn%2].txt_vector[i])
			{
				//daf
				player_vector[Player.turn%2].txt_vector[i].setText(Integer.toString(sum));
				Player.turn = -1;
				//Player.turn++;
			}
		}
		
		
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

	
 
	
	
}
