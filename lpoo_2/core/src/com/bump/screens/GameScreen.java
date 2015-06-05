package com.bump.screens;

import java.util.ArrayList;

import com.bump.assets.Assets;
import com.bump.game.Bump;
import com.bump.objects.Ball;
import com.bump.objects.Piece;
import com.bump.objects.Square;
import com.bump.objects.Triangle;
import com.bump.objects.Wall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ScreenAdapter implements InputProcessor
{
	private Bump
		game;
	OrthographicCamera
		guiCam;
	private Wall
		wallTop,
		wallBottom,
		wallLeft,
		wallRight;
	ArrayList<Piece>
		pieces = new ArrayList<Piece>();
	Piece
		selectedPiece;
	Ball
		ball, ball2;
	Triangle
		triangle;
	Square
		square;
	World
		world;
	Sprite
		sprite, sprite2, sprite3, sprite4;
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

	public GameScreen(Bump game)
	{
		this.game = game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);

		// TESTING
		sprite = new Sprite(Assets.spriteBall);
		sprite2 = new Sprite(Assets.spriteBall);
		sprite3 = new Sprite(Assets.spriteTriangle);
		sprite4 = new Sprite(Assets.spriteSquare);
		
		world = new World(new Vector2(0, 0f), true);
		
		createWalls();
		ball = new Ball(world, sprite, Assets.windowWidth / 2 / Assets.PIXELS_TO_METERS, Assets.windowHeight / 2 / Assets.PIXELS_TO_METERS);
		pieces.add(ball);
		ball2 = new Ball(world, sprite2, Assets.windowWidth / 2 / Assets.PIXELS_TO_METERS, (Assets.windowHeight + 250) / 2 / Assets.PIXELS_TO_METERS);
		pieces.add(ball2);
		triangle = new Triangle(world, sprite3, (Assets.windowWidth + 300) / 2 / Assets.PIXELS_TO_METERS, (Assets.windowHeight + 250) / 2 / Assets.PIXELS_TO_METERS);
		pieces.add(triangle);
		square = new Square(world, sprite4, (Assets.windowWidth - 300) / 2 / Assets.PIXELS_TO_METERS, (Assets.windowHeight + 250) / 2 / Assets.PIXELS_TO_METERS);
		pieces.add(square);
		
		Gdx.input.setInputProcessor(this);

		debugRenderer = new Box2DDebugRenderer();
	}
	
	public void createWalls()
	{
		wallTop = new Wall();
		wallTop.createWall(world, Assets.windowWidth / Assets.PIXELS_TO_METERS, Assets.windowHeight / Assets.PIXELS_TO_METERS, Assets.windowWidth / Assets.PIXELS_TO_METERS, 0);

		wallBottom = new Wall();
		wallBottom.createWall(world, Assets.windowWidth / Assets.PIXELS_TO_METERS, 0, Assets.windowWidth / Assets.PIXELS_TO_METERS, 0);

		wallLeft = new Wall();
		wallLeft.createWall(world, 0, 0, 0, Assets.windowHeight / Assets.PIXELS_TO_METERS);
		
		wallRight = new Wall();
		wallRight.createWall(world, Assets.windowWidth / Assets.PIXELS_TO_METERS, 0, 0, Assets.windowHeight / Assets.PIXELS_TO_METERS);
	}

	public void render(float delta)
	{
		guiCam.update();
		world.step(1f / 60f, 6, 2);

		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// body.applyTorque(torque, true);
		// ball.body.applyTorque(torque, true);
		// sprite.setPosition((ball.body.getPosition().x * Assets.PIXELS_TO_METERS) - sprite.getWidth() / 2, (ball.body.getPosition().y * Assets.PIXELS_TO_METERS) - sprite.getHeight() / 2);
		// sprite.setRotation((float) Math.toDegrees(ball.body.getAngle()));
		
		for (Piece piece: pieces)
		{
			piece.body.applyTorque(torque, true);
			piece.sprite.setPosition((piece.body.getPosition().x * Assets.PIXELS_TO_METERS) - piece.sprite.getWidth() / 2, (piece.body.getPosition().y * Assets.PIXELS_TO_METERS) - piece.sprite.getHeight() / 2);
			piece.sprite.setRotation((float) Math.toDegrees(piece.body.getAngle()));
		}

		// System.out.println("Speed: (" + body.getLinearVelocity().x + ", " + body.getLinearVelocity().y + ", " + body.getAngularVelocity() + ")");

		game.batcher.setProjectionMatrix(guiCam.combined);

		debugMatrix = game.batcher.getProjectionMatrix().cpy().scale(Assets.PIXELS_TO_METERS, Assets.PIXELS_TO_METERS, 0);

		game.batcher.begin();
		game.batcher.enableBlending();
		
		for (Piece piece: pieces)
		{
			game.batcher.draw(piece.sprite, piece.sprite.getX(), piece.sprite.getY(), piece.sprite.getOriginX(), piece.sprite.getOriginY(), piece.sprite.getWidth(), piece.sprite.getHeight(), piece.sprite.getScaleX(), piece.sprite.getScaleY(), piece.sprite.getRotation());
		}
		
		/*
		if (drawSprite)
		{
			// game.batcher.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
			game.batcher.draw(sprite2, sprite2.getX(), sprite2.getY(), sprite2.getOriginX(), sprite2.getOriginY(), sprite2.getWidth(), sprite2.getHeight(), sprite2.getScaleX(), sprite2.getScaleY(), sprite2.getRotation());
			game.batcher.draw(sprite3, sprite3.getX(), sprite3.getY(), sprite3.getOriginX(), sprite3.getOriginY(), sprite3.getWidth(), sprite3.getHeight(), sprite3.getScaleX(), sprite3.getScaleY(), sprite3.getRotation());
		}
		*/

		game.batcher.end();

		//debugRenderer.render(world, debugMatrix);
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
		
		state = -1;
		for (Piece piece: pieces)
		{
			if (piece.contains(screenX, 720 - screenY))
			{
				selectedPiece = piece;
				state = 0;
				startX = screenX;
				startY = screenY;
			}
		}

		return true;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		//System.out.println ("touchUp: " + screenX + ", " + screenY + ", " + pointer + "," + button);
		if (state == 1)
		{
			selectedPiece.body.applyAngularImpulse(0.1f, true);
			selectedPiece.body.applyForceToCenter((float) 0.5 * (screenX - startX), (float) 0.5 * (startY - screenY), true);
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
