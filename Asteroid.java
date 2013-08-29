import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import it.randomtower.engine.ME;
import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;
	
	
public class Asteroid extends Entity {
		
	public static String ASTEROID = "asteroid";
	private Image asteroid = ResourceManager.getImage("asteroid");
	private Image explosion = ResourceManager.getImage("asteroidExplosion");
	int rotation;
	int timer = 0;
	int level;
	boolean dead = false;
		
	public Asteroid(float x, float y) {
		super(x, y);	
		setCentered(true);
		setGraphic(asteroid);
		setHitBox(0, 0, 35, 35);
		addType(ASTEROID);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);
		if(collide("bullet", x, y) != null) {
			this.destroy();
			Explosions ex = new Explosions(x-80,y-70);
			ex.setGraphic(explosion);
			world.add(ex);
		} 
		if(collide("spaceship", x, y) != null) {
			this.destroy();
		}
		y += ((.1 * level) * delta);
		rotation += 1;
		this.setAngle(rotation);
		if(y > ME.world.getHeight()) {
			this.destroy();
			setDead(true);
		}
	}
	
	public float getY() {
		return y;
	}
	
	public void setLevel(int level) {
		this. level = level;
	}
	public void setDead(boolean dead) {
		dead = true;
	}
	public boolean isDead() {
		return dead;
	}
}
