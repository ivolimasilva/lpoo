package com.bump.objects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Wall.java
 * @author Ivo and Mariana
 */
public class Wall
{
	public Body
		body;

	/**
	 * Creates a wall (static body) for a given world on a given position with a given size
	 * @param world World where the Wall is on
	 * @param pos_x Position on the X axis where the Wall is created
	 * @param pos_y Position on the Y axis where the Wall is created
	 * @param width Width of the Wall
	 * @param height Height of the Wall
	 */
	public void createWall(World world, float pos_x, float pos_y, float width, float height)
	{
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.1f;
		fixtureDef.restitution = 0.3f;
		fixtureDef.friction = 0.0f;

		bodyDef.position.set(pos_x, pos_y);
		this.body = world.createBody(bodyDef);
		shape.setAsBox(width, height);
		this.body.createFixture(fixtureDef);
		this.body = world.createBody(bodyDef);
		
		shape.dispose();
	}
}
