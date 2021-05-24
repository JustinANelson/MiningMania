package MiningMania.helpers;

import com.badlogic.gdx.Gdx;

public class Variables {

    public static String buildVersion = "0.01";
    public static int clientType;
    public static String name;
    public static int androidVersion;

    public static final int ANDROID_CLIENT = 1;
    public static final int DESKTOP_CLIENT = 2;
    public static final int HTML_CLIENT = 3;

    //public static final int MAX_ICONS = Objects.requireNonNull(new File("../assets/icons").listFiles()).length;
    public static final int MAX_ICONS = Gdx.files.internal("icons").list().length;

}
