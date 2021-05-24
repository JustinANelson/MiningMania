package MiningMania.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import MiningMania.objects.Icons;

public class AssetsHandler {
    public static Icons[] icons;

    public static void load() {
        icons = new Icons[Variables.MAX_ICONS + 1];
        for (int i = 1; i <= Variables.MAX_ICONS; i++) {
            icons[i] = new Icons(32, 32, 80, 80);
            icons[i].setTex(new Texture(Gdx.files.internal("icons/" + i + ".png")));
            icons[i].getTex().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }
    }
}
