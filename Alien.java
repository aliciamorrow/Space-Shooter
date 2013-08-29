import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.randomtower.engine.ME;
import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;


public class Alien extends Entity {
	
	public static String ALIEN = "alien";
	private int health;
	private Image explosion = ResourceManager.getImage("alienExplosion");
	int level;
	boolean dead = false;
	
	public Alien(float x, float y) {
		super(x, y);
		Image alien = ResourceManager.getImage("alien");
		setHitBox(0, 0, 38, 55);
		setGraphic(alien);
		addType(ALIEN);
		setHealth(5);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);
		if(collide("bullet", x, y) != null) {
			this.destroy();
			AlienExplosions ex = new AlienExplosions(x-370,y-130);
			ex.setGraphic(explosion);
			world.add(ex);
			setDead(true);
		}
		if(collide("spaceship", x, y) != null) {
			this.destroy();
		}
		y += (.2 * (level/2) * delta);
		if(y > ME.world.getHeight()) {
			this.destroy();
			setDead(true);
		}
	}
	
	
	public void collisionResponse(Entity e) {
		health -= 2;
		if(health <= 0) {
			this.destroy();
		
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setDead(boolean dead) {
		dead = true;
	}
	public boolean isDead() {
		return dead;
	}
	public float getY() {
		return y;
	}
}
