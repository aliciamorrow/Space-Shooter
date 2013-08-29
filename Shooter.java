
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.randomtower.engine.ResourceManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.SlickException;



public class Shooter extends StateBasedGame   {
	
	public static final int GAME_STATE = 1;
	public static final int MENU = 0;
	public static final int GAME_OVER = 2;
	
	public Shooter(String title) throws SlickException {
		super(title);
		this.addState(new Menu(MENU));
		this.addState(new GameWorld(GAME_STATE));
		this.addState(new GameOver(GAME_OVER));
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		try {
			ResourceManager.loadResources("data/resources.xml");
		} catch (IOException e) {
			Logger.getLogger(Shooter.class.getName()).log(Level.SEVERE, null, e);
		}
		
		this.getState(MENU).init(gc, this);
		this.getState(GAME_STATE).init(gc, this);
		this.getState(GAME_OVER).init(gc,this);
		enterState(MENU);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Shooter("Space Shooter Game"));
		app.setDisplayMode(600, 600, false);
		app.setTargetFrameRate(60);
		app.start();
	}
}
