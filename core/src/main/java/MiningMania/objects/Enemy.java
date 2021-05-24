package MiningMania.objects;

import java.io.Serializable;

import MiningMania.networking.packets.Packet;

public class Enemy extends Packet implements Serializable {
    public String name;

    public Enemy(String name){
        this.name = name;
    }
    public Enemy(){

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
