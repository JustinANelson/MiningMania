package MiningMania.server.Objects;

import java.io.Serializable;

public class Player implements Serializable {
    public String name;

    public Player(String name){
        this.name = name;
    }
    public Player(){

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
