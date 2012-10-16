package ch.zhaw.arsphema.controller;

import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractController implements InputProcessor {

    enum IngameKeys {
        UP, DOWN, BACK, CONFIRM, SHOT
    }


    static Map<IngameKeys, Boolean> keys = new HashMap<AbstractController.IngameKeys, Boolean>();

    static {
        keys.put(IngameKeys.UP, false);
        keys.put(IngameKeys.DOWN, false);
        keys.put(IngameKeys.SHOT, false);
    }

    ;

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean touchMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
