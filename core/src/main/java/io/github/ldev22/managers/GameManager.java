package io.github.ldev22.managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import io.github.ldev22.objects.Basket;
import io.github.ldev22.objects.Ball;

public class GameManager {
    public static Basket basket;
    static Texture basketTexture;
    public static Sprite backgroundSprite;
    public static Texture backgroundTexture;

    public Ball ball;
    static Texture ballTexture;

    private static final float BALL_RESIZE_FACTOR = 2500f;
    private static final float BASKET_RESIZE_FACTOR = 3000f;
    private static float BACK_BTN_RESIZE_FACTOR = 1500f;
    public static Array<Ball> balls = new Array<Ball>();

    public static void initialize(float width, float height) {
        basket = new Basket();

        basketTexture = new Texture(Gdx.files.internal("data/basket.png"));
        basket.basketSprite = new Sprite(basketTexture);
        basket.basketSprite.setSize(basket.basketSprite.getWidth() * (width/BASKET_RESIZE_FACTOR), basket.basketSprite.getHeight() * (width/BASKET_RESIZE_FACTOR));
        basket.setPosition(0,0);

        basket.basketRectangle.setSize(basket.basketSprite.getWidth(), basket.basketSprite.getHeight());
        backgroundTexture = new Texture(Gdx.files.internal("data/background.jpg"));
        backgroundSprite = new Sprite(backgroundTexture);

        backgroundSprite.setSize(width, height);

        ballTexture = new Texture(Gdx.files.internal("data/ball.png"));

        SpawnManager.initialize(width, height, ballTexture);
    }

    public static void renderGame(SpriteBatch batch) {
        backgroundSprite.draw(batch);
        basket.render(batch);

        SpawnManager.run(balls);
        for(Ball ball : balls){
            if(ball.isAlive){
                ball.update();
                ball.render(batch);
            }
        }

        SpawnManager.cleanup(balls);
    }

    public static void dispose() {
        backgroundTexture.dispose();
        basketTexture.dispose();
        ballTexture.dispose();
        balls.clear();
    }
}
