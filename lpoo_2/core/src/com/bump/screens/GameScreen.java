package com.bump.screens;

import com.bump.assets.Assets;
import com.bump.game.Bump;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ScreenAdapter implements InputProcessor
{
	private Bump
		game;
	OrthographicCamera
		guiCam;

	// TESTING
	World
		world;
	Sprite
		sprite, sprite2, sprite3;
	Body
		body, body2, body3,
		wallTop,
		wallBottom,
		wallLeft,
		wallRight;
	Box2DDebugRenderer
		debugRenderer;
	Matrix4
		debugMatrix;
	
	float
		torque = 0.0f;
	boolean
		drawSprite = true;
	int
		state = 0,
		startX,
		startY;

	final float PIXELS_TO_METERS = 100f;

	public GameScreen(Bump game)
	{
		this.game = game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);

		// TESTING
		sprite = new Sprite(Assets.spriteBall);
		sprite2 = new Sprite(Assets.spriteBall);
		sprite3 = new Sprite(Assets.spriteBall);
		
		world = new World(new Vector2(0, 0f), true);
		world.setVelocityThreshold(0.0f);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(Assets.windowWidth / 2 / PIXELS_TO_METERS, Assets.windowHeight / 2 / PIXELS_TO_METERS);
		body = world.createBody(bodyDef);
		bodyDef.position.set((Assets.windowWidth - 250) / 2 / PIXELS_TO_METERS, Assets.windowHeight / 2 / PIXELS_TO_METERS);
		body2 = world.createBody(bodyDef);
		bodyDef.position.set((Assets.windowWidth + 250) / 2 / PIXELS_TO_METERS, Assets.windowHeight / 2 / PIXELS_TO_METERS);
		body3 = world.createBody(bodyDef);
		body.setLinearDamping(0.5f);
		body2.setLinearDamping(0.5f);
		body3.setLinearDamping(0.5f);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(sprite.getWidth() / 2 / PIXELS_TO_METERS);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.3f;
		fixtureDef.friction = 0.0f;

		body.createFixture(fixtureDef);
		body2.createFixture(fixtureDef);
		body3.createFixture(fixtureDef);
		shape.dispose();
		
		createWalls();

		Gdx.input.setInputProcessor(this);

		debugRenderer = new Box2DDebugRenderer();
		//guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void createWalls()
	{
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.1f;
		fixtureDef.restitution = 0.5f;
		fixtureDef.friction = 0.0f;
		
		// Wall Top
		bodyDef.position.set(Assets.windowWidth / PIXELS_TO_METERS, Assets.windowHeight / PIXELS_TO_METERS);
		wallTop = world.createBody(bodyDef);
		shape.setAsBox(Assets.windowHeight, 0);
		wallTop.createFixture(fixtureDef);
		wallTop = world.createBody(bodyDef);
		
		// Wall Bottom
		bodyDef.position.set(Assets.windowWidth / PIXELS_TO_METERS, 0);
		wallBottom = world.createBody(bodyDef);
		shape.setAsBox(Assets.windowHeight, 0);
		wallBottom.createFixture(fixtureDef);
		wallBottom = world.createBody(bodyDef);

		// Wall Left
		bodyDef.position.set(0, 0);
		wallLeft = world.createBody(bodyDef);
		shape.setAsBox(0, Assets.windowHeight);
		wallLeft.createFixture(fixtureDef);
		wallLeft = world.createBody(bodyDef);
		
		// Wall Right
		bodyDef.position.set(Assets.windowWidth / PIXELS_TO_METERS, 0);
		wallRight = world.createBody(bodyDef);
		shape.setAsBox(0, Assets.windowHeight);
		wallRight.createFixture(fixtureDef);
		wallRight = world.createBody(bodyDef);

		shape.dispose();
	}

	public void render(float delta)
	{
		guiCam.update();
		world.step(1f / 60f, 6, 2);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		body.applyTorque(torque, true);
		sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.getWidth() / 2, (body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight() / 2);
		sprite.setRotation((float) Math.toDegrees(body.getAngle()));
		
		sprite2.setPosition((body2.getPosition().x * PIXELS_TO_METERS) - sprite2.getWidth() / 2, (body2.getPosition().y * PIXELS_TO_METERS) - sprite2.getHeight() / 2);
		sprite2.setRotation((float) Math.toDegrees(body2.getAngle()));
		
		sprite3.setPosition((body3.getPosition().x * PIXELS_TO_METERS) - sprite3.getWidth() / 2, (body3.getPosition().y * PIXELS_TO_METERS) - sprite3.getHeight() / 2);
		sprite3.setRotation((float) Math.toDegrees(body3.getAngle()));
		
		// System.out.println("Speed: (" + body.getLinearVelocity().x + ", " + body.getLinearVelocity().y + ", " + body.getAngularVelocity() + ")");

		

		game.batcher.setProjectionMatrix(guiCam.combined);

		debugMatrix = game.batcher.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);

		game.batcher.begin();
		game.batcher.enableBlending();
		
		if (drawSprite)
		{
			game.batcher.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
			game.batcher.draw(sprite2, sprite2.getX(), sprite2.getY(), sprite2.getOriginX(), sprite2.getOriginY(), sprite2.getWidth(), sprite2.getHeight(), sprite2.getScaleX(), sprite2.getScaleY(), sprite2.getRotation());
			game.batcher.draw(sprite3, sprite3.getX(), sprite3.getY(), sprite3.getOriginX(), sprite3.getOriginY(), sprite3.getWidth(), sprite3.getHeight(), sprite3.getScaleX(), sprite3.getScaleY(), sprite3.getRotation());
		}

		game.batcher.end();

		debugRenderer.render(world, debugMatrix);
		
		/*
		if (Math.abs(body.getLinearVelocity().x) < 0.1 && Math.abs(body.getLinearVelocity().y) < 0.1)
			body.setLinearVelocity(new Vector2(0, 0));
		else
			body.setLinearVelocity(new Vector2((float) body.getLinearVelocity().x - 0.01f, (float) body.getLinearVelocity().y - 0.01f));
		*/
	}

	public boolean keyDown(int keycode)
	{
		return false;
	}

	public boolean keyUp(int keycode)
	{
		return false;
	}

	public boolean keyTyped(char character)
	{
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		//System.out.println ("touchDown: " + screenX + ", " + screenY + ", " + pointer + "," + button);
		state = 0;
		startX = screenX;
		startY = screenY;
		return true;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		//System.out.println ("touchUp: " + screenX + ", " + screenY + ", " + pointer + "," + button);
		if (state == 1)
		{
			body.applyForceToCenter((float) 0.1 * (screenX - startX), (float) 0.1 * (startY - screenY), true);
			System.out.println ("Movimento: (" + (screenX - startX) + ", " + (screenY - startY) + ").");
		}
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		//System.out.println ("Dragged: " + screenX + ", " + screenY + ", " + pointer);
		if (state == 0)
			state = 1;
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	public boolean scrolled(int amount)
	{
		return false;
	}
}
