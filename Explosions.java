import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Explosions extends Entity {
	int count = 0;
	float x, y;
	private Image explosion = ResourceManager.getImage("asteroidExplosion");
	
	public Explosions(float x, float y) {
		super(x, y);
		setGraphic(explosion);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		count++;
		super.update(gc, delta);
		if(count > 10) {
			this.destroy();
		}
	}
}
