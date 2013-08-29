import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import it.randomtower.engine.ME;
import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;


public class Player extends Entity {
	
	public static String SHIP = "spaceship";
	private Image player;
	double speed = .3;
	int timer = 0;
	private int fireRate = 50;
	private int milliCount = 0;
	private int millis = fireRate;
	private int milliStep = millis / 5;
	boolean dead = false;
	boolean canShoot = true;
	int health;
	
	public Player(float x, float y) {
		super(x, y);
		player = ResourceManager.getImage("player");
		setGraphic(player);
		setHitBox(0, 0, 40, 80);
		health = 5;
		addType(SHIP);
		define("right", Input.KEY_RIGHT, Input.KEY_D);
		define("left", Input.KEY_LEFT, Input.KEY_A);
		define("up", Input.KEY_UP, Input.KEY_W);
		define("down", Input.KEY_DOWN, Input.KEY_S);
		define("shoot", Input.KEY_SPACE);
	}
	
	public void update(GameContainer gc, int delta) {
		try {
			super.update(gc, delta);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(check("shoot") && canShoot) {
			milliCount += delta;
			while(milliCount > milliStep) {
				milliCount -= milliStep;
				millis -= milliStep;
			}
			if(millis <= 0) {
				Bullet b = new Bullet(x + 20, y);
				ME.world.add(b);
				b.depth += 2;
				millis = fireRate;
			}
		}
		
		
		
		if(check("right") && x < 535) {
			x += (speed * delta);
		}
		if(check("left") && x > 0) {
			x -= (speed * delta);
		}
		if(check("up") && y > 0) {
			y -= (speed * delta);
		}
		if(check("down") && y < 505) {
			y += (speed *delta);
		}
		if(collide("asteroid", x, y) != null) {
			health -= 1;
		}
		if(collide("alien", x, y) != null) {
			health -= 1;
		}
		if(timer > 750) {
			speed += .1;
			timer = 0;
		}
		
	}
	
	public void setDead(boolean dead) {
		dead = true;
	}
	public boolean isDead() {
		return dead;
	}
}