package com.bump.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.bump.assets.Assets;

public class Ball extends Piece
{
	public Ball(World world, Sprite sprite, float pos_x, float pos_y)
	{
		this.world = world;
		this.sprite = sprite;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(pos_x, pos_y);
		body = world.createBody(bodyDef);

		body.setLinearDamping(1f);
		body.setAngularDamping(1f);

		CircleShape shape = new CircleShape();
		shape.setRadius(this.sprite.getWidth() / 2 / Assets.PIXELS_TO_METERS);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.9f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 1f;

		body.createFixture(fixtureDef);
		shape.dispose();
	}

	public boolean contains(float x, float y)
	{
		/*
		System.out.println("Esta bola tem centro em: (" + this.body.getPosition().x * Assets.PIXELS_TO_METERS + ", " + this.body.getPosition().y * Assets.PIXELS_TO_METERS + ") e tem raio igual a " + this.sprite.getWidth() / 2 + " px.");
		System.out.println("Voce clicou em: (" + x + ", " + y + ").");
		System.out.println("Distancia: " + Assets.distance(this.body.getPosition().x * Assets.PIXELS_TO_METERS, this.body.getPosition().y * Assets.PIXELS_TO_METERS, x, y));
		System.out.println();
		*/

		if (Assets.distance(this.body.getPosition().x * Assets.PIXELS_TO_METERS, this.body.getPosition().y * Assets.PIXELS_TO_METERS, x, y) <= this.sprite.getWidth() / 2)
			return true;
		else
			return false;
	}
}
