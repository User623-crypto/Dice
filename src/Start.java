
public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game a = new Game();
		a.setVisible(true);
		Thread gamethread = new Thread(a);
		gamethread.start();

	}

}
