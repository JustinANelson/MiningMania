package MiningMania.objects;

import com.badlogic.gdx.graphics.Texture;

public class Icons {

    private String name;
    private String dir;
    private Texture tex;
    private int texX;
    private int texY;
    private int width;
    private int height;

    public Icons (int texX, int texY, int width, int height) {
        this.texX = texX;
        this.texY = texY;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public Icons setName(String name) {
        this.name = name;
        return this;
    }

    public String getDir() {
        return dir;
    }

    public Icons setDir(String dir) {
        this.dir = dir;
        return this;
    }

    public Texture getTex() {
        return tex;
    }

    public Icons setTex(Texture tex) {
        this.tex = tex;
        return this;
    }

    public int getTexX() {
        return texX;
    }

    public Icons setTexX(int texX) {
        this.texX = texX;
        return this;
    }

    public int getTexY() {
        return texY;
    }

    public Icons setTexY(int texY) {
        this.texY = texY;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public Icons setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public Icons setHeight(int height) {
        this.height = height;
        return this;
    }
}
