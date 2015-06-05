package com.bump.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bump.assets.Assets;

public class Triangle extends Piece
{
	public Triangle(World world, Sprite sprite, float pos_x, float pos_y)
	{
		this.world = world;
		this.sprite = sprite;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(pos_x, pos_y);
		body = world.createBody(bodyDef);

		body.setLinearDamping(1f);
		body.setAngularDamping(1f);

		PolygonShape shape = new PolygonShape();
		//shape.setAsBox(this.sprite.getWidth() / 2 / Assets.PIXELS_TO_METERS, this.sprite.getHeight() / 2 / Assets.PIXELS_TO_METERS);
		shape.set(new float[]
				{
					- this.sprite.getWidth() / 2 / Assets.PIXELS_TO_METERS,
					- this.sprite.getHeight() / 2 / Assets.PIXELS_TO_METERS,
					this.sprite.getWidth() / 2 / Assets.PIXELS_TO_METERS,
					- this.sprite.getHeight() / 2 / Assets.PIXELS_TO_METERS,
					0f,
					this.sprite.getHeight() / 2 / Assets.PIXELS_TO_METERS
				});

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.9f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.3f;

		body.createFixture(fixtureDef);
		shape.dispose();
	}
	public boolean contains(float x, float y)
	{
		if (Assets.distance(this.body.getPosition().x * Assets.PIXELS_TO_METERS, this.body.getPosition().y * Assets.PIXELS_TO_METERS, x, y) <= this.sprite.getWidth() / 2)
			return true;
		else
			return false;
	}
}
