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
		sprite;
	Body
		body, body2;
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
		startY,
		endX,
		endY;

	final float PIXELS_TO_METERS = 100f;

	public GameScreen(Bump game)
	{
		this.game = game;
		/*
		guiCam = new OrthographicCamera(Assets.width, Assets.height);
		guiCam.position.set(Assets.width / 2, Assets.height / 2, 0);
		*/

		// TESTING
		sprite = new Sprite(Assets.spriteBall);
		
		world = new World(new Vector2(0, 0f), true);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / PIXELS_TO_METERS, (sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);
		body = world.createBody(bodyDef);
		bodyDef.position.set((300 + sprite.getX() + sprite.getWidth() / 2) / PIXELS_TO_METERS, (200 + sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);
		body2 = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight() / 2 / PIXELS_TO_METERS);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.1f;

		body.createFixture(fixtureDef);
		body2.createFixture(fixtureDef);
		shape.dispose();

		Gdx.input.setInputProcessor(this);

		debugRenderer = new Box2DDebugRenderer();
		guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void update()
	{
		/*
			if (Gdx.input.justTouched())
			{
				game.setScreen(new MenuScreen(game));
			}
		*/
	}

	public void draw()
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batcher.begin();
		game.batcher.draw(Assets.backgroundGame, 0, 0);
		game.batcher.end();
	}

	public void render(float delta)
	{
		/*
		update();
		draw();
		*/
		guiCam.update();
		world.step(1f / 60f, 6, 2);

		body.applyTorque(torque, true);
		sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.getWidth() / 2, (body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight() / 2);
		sprite.setRotation((float) Math.toDegrees(body.getAngle()));

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batcher.setProjectionMatrix(guiCam.combined);

		debugMatrix = game.batcher.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);

		game.batcher.begin();
		
		if (drawSprite)
			game.batcher.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());

		game.batcher.end();

		debugRenderer.render(world, debugMatrix);
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
			body.applyForceToCenter(screenX - startX, startY - screenY, true);
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
