import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.randomtower.engine.ME;
import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;


public class Bullet extends Entity {
	
	public static String BULLET = "bullet";
	//private final String[] enemies = (Asteroid.ASTEROID);

	public Bullet(float x, float y) {
		super(x, y);
		Image bullet = ResourceManager.getImage("bullet");
		setGraphic(bullet);
		setHitBox(2, 1, 15, 15);
		addType(BULLET);
	}
	
	public void update(GameContainer gc, int delta) {
		y -= (.7 * delta);
		if(y <= 0) {
			this.destroy();
		}
		try {
			super.update(gc, delta);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void collisionResponse(Entity e) {
		this.destroy();
	}
}
