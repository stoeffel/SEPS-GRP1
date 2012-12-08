package ch.zhaw.arsphema;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "SpaceFighter";
        cfg.useGL20 = true;
        //HTC Desire S
//        cfg.width = 800;
//        cfg.height = 480;
        //Nexus 7
        cfg.width = 1280;
        cfg.height = 800;
        //Galaxy Nexus
//        cfg.width = 1280;
//        cfg.height = 720;

        new LwjglApplication(new MyGdxGame(), cfg);
    }
}
