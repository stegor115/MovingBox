//Imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Box extends JPanel implements KeyListener, ActionListener {
	//Frame info
	static int frameWidth = 800;
	static int frameHeight = 800;
	
	//Box Info
	int boxPosX = frameWidth/2;
	int boxPosY = frameHeight/2;
	int boxWidth = 50;
	int boxHeight = 50;
	int move = 25; //Speed of box
	int health = 10;
	
	//Enemy Box
	int badBoxPosX = frameWidth/2;
	int badBoxPosY = (frameHeight/2)-100;
	
	private Timer timer;
	int delay = 8;
	
	public Box(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g){
		//background
		g.setColor(Color.black);
		g.fillRect(0, 0, frameWidth, frameHeight);
		//Box
		if(health >= 1){
			g.setColor(Color.green); //Player Box
			g.fillRect(boxPosX, boxPosY, boxWidth, boxHeight);
		} //end if
		
		g.setColor(Color.red); //Enemy Box
		g.fillRect(badBoxPosX, badBoxPosY, boxWidth, boxHeight);
		
		g.setColor(Color.white); //Health Text
		g.drawString("Remaining Health: " + health, 5, 10);
		
		if(health < 1){
			g.setColor(Color.white);
			g.drawString("You died! Press Space to Restart!", frameWidth/2 - 100, frameHeight/2 + 200);
		} //end if
		
		g.dispose();
	} //end paint
	
	public void actionPerformed(ActionEvent e){
		timer.start();
		repaint();
		
		if(health < 0){
			health = 0;
		} //end if
	} //end actionPerformed
	
	public void keyTyped(KeyEvent e){
		
	} //end keyTyped
	
	public void keyReleased(KeyEvent e){
		
	} //end keyReleased
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){ //Right arrow key
			//Enemy Box Correction
			if (boxPosX == badBoxPosX - boxWidth && (boxPosY == badBoxPosY ||
					boxPosY == badBoxPosY + (boxHeight/2) || 
					boxPosY == badBoxPosY - (boxHeight/2)) ){
				boxPosX-=move;
				health-=1;
				System.out.println("Blocked");
			}//end box correction
			
			//Border Correction
			if(boxPosX >= frameWidth - boxWidth){
				boxPosX = frameWidth - boxWidth;
				System.out.println("Out of bounds!");
			} else {
				boxPosX+=move;
			} //end else
			System.out.println("Right | Pos X =" +  boxPosX);
		} //end Right if
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){ //Left arrow key
			//Enemy Box Correction
			if(boxPosX == badBoxPosX + boxWidth && (boxPosY == badBoxPosY ||
					boxPosY == badBoxPosY + (boxHeight/2) || 
					boxPosY == badBoxPosY - (boxHeight/2)) ){
				boxPosX+=move;
				health-=1;
				System.out.println("Blocked");
			} //end box correction
			
			//Border Correction
			if(boxPosX <= 0){
				boxPosX = 0;
				System.out.println("Out of bounds!");
			} else {
				boxPosX-=move;
			} //end else
			System.out.println("Left | Pos X =" +  boxPosX);
		} //end Left if
		
		if(e.getKeyCode() == KeyEvent.VK_UP){ //Up arrow key
			//Enemy Box Correction
			if(boxPosY == badBoxPosY + boxHeight && (boxPosX == badBoxPosX ||
					boxPosX == badBoxPosX + (boxWidth/2) ||
					boxPosX == badBoxPosX - (boxWidth/2)) ){
				boxPosY+=move;
				health-=1;
				System.out.println("Blocked");
			} //end box correction
			
			//Border Correction
			if(boxPosY <= 0){
				boxPosY = 0;
				System.out.println("Out of bounds!");
			} else{
				boxPosY-=move;
			} //end else
			System.out.println("Up | Pos Y =" +  boxPosY);
		} //end Up if
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			//Enemy Box Correction
			if(boxPosY == badBoxPosY - boxHeight && (boxPosX == badBoxPosX ||
					boxPosX == badBoxPosX + (boxWidth/2) ||
					boxPosX == badBoxPosX - (boxWidth/2)) ){
				boxPosY-=move;
				health-=1;
				System.out.println("Blocked");
			} //end box correction
			
			//Border Correction
			if(boxPosY >= frameHeight - boxHeight){
				boxPosY = frameHeight - boxHeight;
				System.out.println("Out of bounds!");
			} else {
				boxPosY+=move;
			} //end else
			System.out.println("Down | Pos Y =" +  boxPosY);
		} //end Down if
		
		//Respawn
		if(health < 1){
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				health = 10;
				boxPosX = frameWidth/2;
				boxPosY = frameHeight/2;
				System.out.println("Restarted!");
			} //end space if
		} //end restart if
		
	} //end keyPressed
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Moving Box");
		Box stuff = new Box();
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets what happens if you hit the X button in corner
		frame.add(stuff);
	} //end main
}
