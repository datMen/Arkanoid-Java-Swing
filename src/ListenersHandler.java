import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ListenersHandler {
	private Pantalla game;
	public static final int LEFT = 37;
	public static final int RIGHT = 39;
	int old_width = 0;
	public ListenersHandler(Pantalla game) {
		KeyListener listener = new MyKeyListener();
		ComponentListener clistener = new MyComponentListener();
		MouseMotionListener mmlistener = new MyMouseMotionListener();
		MouseListener mlistener = new MyMouseListener();
		game.addKeyListener(listener);
		game.addComponentListener(clistener);
		game.addMouseMotionListener(mmlistener);
		game.addMouseListener(mlistener);
		game.setFocusable(true);
		this.game = game;
	}

	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == LEFT) {
				game.bar.move(LEFT);
			}
			else if (e.getKeyCode() == RIGHT) {
				game.bar.move(RIGHT);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}
	}
	
	public class MyComponentListener implements ComponentListener {
		@Override
	    public void componentResized(ComponentEvent e) {
	        if (e.getID() == 101) {
	        	if (game.getWidth() > old_width) {
	        		game.brick.addBricks();
	        	}
	        	else if (game.getWidth() < old_width) {
	        		game.brick.remBricks();
	        	}
	        	old_width = game.getWidth();
	        }
	    }
		@Override
		public void componentHidden(ComponentEvent e) {}
		@Override
		public void componentMoved(ComponentEvent e) {}
		@Override
		public void componentShown(ComponentEvent e) {}
	}
	
	public class MyMouseMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {
			game.bar.x = e.getX();
		}
	}
	
	public class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Pantalla.startGame(game);
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}


}