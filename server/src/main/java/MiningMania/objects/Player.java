package MiningMania.objects;

import java.io.Serializable;

import MiningMania.networking.packets.Packet;

public class Player extends Packet implements Serializable {
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
