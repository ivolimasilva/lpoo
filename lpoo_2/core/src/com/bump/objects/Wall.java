package com.bump.objects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Wall
{
	public Body
		body;

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
