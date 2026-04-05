package io.github.ldev22.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import io.github.ldev22.catchball.CatchBall;

public class InputManager extends InputAdapter{
    OrthographicCamera camera;
    static Vector3 temp = new Vector3();

    public InputManager(OrthographicCamera camera){
        this.camera = camera;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX, screenY, 0);
        camera.unproject(temp);

        float touchX = temp.x;
        float touchY = temp.y;

        GameManager.basket.handleTouch(touchX, touchY);

        return false;
    }


}
