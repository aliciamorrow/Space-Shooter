
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.Random;

import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.World;

public class Menu extends World {
	
	Image wallpaper = null;
	
	public Menu(int id) {
		super(id);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException{
		super.init(gc, game);
		wallpaper = ResourceManager.getImage("wallpaper");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		super.update(gc, game, delta);
		Input input = gc.getInput();
		if (input.isMouseButtonDown(0)) {
			game.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}

	}
	

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(wallpaper, 0, 0);
		super.render(gc, game, g);
	}
	
	public int getID() {
		return 0;
	}
}
