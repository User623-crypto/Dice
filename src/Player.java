

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends JPanel {
	public int roll_index = 0;
	public int click = 0;

	static int turn = 0;

	//public JPanel panel;
	private int x;
	private int y;
	public JLabel txt1,txt2,txt3;
	public JLabel[] txt_vector = new JLabel[] {txt1,txt2,txt3};
	private int y_level = 20;
	public int clickper_round = 0;
	public Player(int _x,int _y)
	{
		x = _x;
		y = _y;
		//panel = new JPanel();
		this.setBackground(Color.RED);
		this.setBounds(x, y, 200, 600);
		this.setLayout(new FlowLayout());

		
		//Krijonen Labelat per cdo lojtar
		for(int i = 0;i<txt_vector.length;i++)
		{

			txt_vector[i] = new JLabel("Score "+(i+1)+ ": 0");

			txt_vector[i].setName("el"+(i+1));
			txt_vector[i].setBounds(320, y_level, 100, 50);
			txt_vector[i].setBackground(Color.WHITE);
			txt_vector[i].setBorder(BorderFactory.createEmptyBorder(20, 60, 10, 60));
			//txt_vector[i].add(new JLabel("Score "+ (i+1) +": 0"));
			y_level += 100;
			add(txt_vector[i]);
			
		}
		
	}
	
	
	
	public int player_x()
	{
		return x;
		
	}
	
	public int player_y()
	{
		return y;
	}
	
	

}
