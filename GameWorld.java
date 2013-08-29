
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;
import java.util.Random;

import it.randomtower.engine.ME;
import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.World;

public class GameWorld extends World {
	
	Random r = new Random();
	int numAsteroids = r.nextInt(10);
	int timer = 0;
	public static int levelNum = 0;
	Image space = null;
	int score = 0; 
	int level = 1;
	int lag = 200;
	Player p;
	float position = 0;
	int alienTimer = 0;
	int levelTimer = 0;
	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	public GameWorld(int id, GameContainer gc) {
		super(id, gc);
	}
	public GameWorld(int id) {
		super(id);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException{
		//initialize everything here
		super.init(gc, game);
		p = new Player(getWidth()/2, getHeight()/2);
		p.depth += 5;
		add(p);
		space = ResourceManager.getImage("space");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		//delta calculates frames per second (stay at same speed regardless of framerate)
		super.update(gc, game, delta);
		timer++;
		levelTimer++;
		int randomInt = r.nextInt(550);
		int randomAlien = r.nextInt(550);
		if(levelTimer >= 750) {
			level++;
			levelTimer = 0;
		}
		if(timer >= (lag - (level*20))) {		
			Asteroid a = new Asteroid(randomInt, -50);
			asteroids.add(a);
			a.depth += 1;
			add(a);
			timer = 0;	
		}
		if(level >= 4) {
			alienTimer++;
			if(alienTimer >= (lag - level*30)) {
				Alien alien = new Alien(randomAlien, -50);
				aliens.add(alien);
				add(alien);
				alienTimer = 0;
			}
		}
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).setLevel(level);
			if(asteroids.get(i).getY() > ME.world.getHeight()) {
				asteroids.remove(i);
				p.health -=1;
			}
		}
		for(int j = 0; j < aliens.size(); j++) {
			aliens.get(j).setLevel(level);
			if(aliens.get(j).getY() > ME.world.getHeight()) {
				aliens.remove(j);
				p.health -= 1;
			}
		}
		if(p.health == 0) {
			p.destroy();
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(space, 0, 0);
		super.render(gc, game, g);
		g.setColor(Color.white);
		g.drawString("Health: " + p.health, 1, 1);
		g.drawString("Level: " + level, 520, 1);
	}
	
	public int getID() {
		return 1;
	}
}
