
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Dice  extends JLabel{
	private int dice_x;
	private int dice_y;
	private int dice_width;
	private int dice_height;
	private String _path;
	private JLabel label;
	ImageIcon imageic1;
	Image image1;
	Image myimage1;
	
	private ImageIcon icon;
	private Add_Image dice;
	JFrame _frame;
	public Dice(int _dice_x,int _dice_y,String path,JFrame frame)
	{
		dice_x = _dice_x;
		dice_y = _dice_y;
		_path = path;
		_frame = frame;
		 label = new JLabel();
		 label.setBounds(dice_x, dice_y, 70, 70);
		 dice_width = 70;
		 dice_height = 70;
		 add_dice(path);
		 frame.getContentPane().add(label);
		 	
	}
	
	//shton zarin ne label
	public void add_dice(String path)
	{
		imageic1 = new ImageIcon(path);
		image1 = imageic1.getImage();
		myimage1 = image1.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
		imageic1 = new ImageIcon(myimage1);
		label.setIcon(imageic1);
		
	}
	public void change_dice(String path)
	{
		add_dice(path);
	//frame.getContentPane().add(label);
		
	}
	public String return_dice()
	{
		return _path;
	}
	
	public int return_width()
	{
		return dice_width;
	}
	
	public int return_height()
	{
		return dice_height;
	}

	

}
