package com.example.bro010.starcatcherv3;

/**
 * Created by Idan on 06/12/2017.
 */

public class Player {
    private long playerid;
    private String playerName; // yonih0m0 'c'
    private  String score;

    public Player( ){
        this.playerName = "";
        this.score = "";
    }

    public Player(Player p){
        this.playerName =p.playerName;
        this.score =p.score;
        this.playerid=p.playerid;

    }
    public Player(String playerName, String score,long playerid) {
        this.playerName = playerName;
        this.score = score;
        this.playerid=playerid;

    }

    public long getPlayerid() {
        return playerid;
    }

    public void setPlayerid(long playerid) {
        this.playerid = playerid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", score=" + score +
                '}';
    }
}
