package com.mygdx.game;

public class Score {
    String planetName;
    int score;

    public Score(String planetName,int score) {
        this.planetName = planetName;
        this.score = score;
	}

    public String getPlanetName() {return this.planetName;}
	public void setPlanetName(String value) {this.planetName = value;}
	
	public int getScore() {return this.score;}
	public void setScore(int value) {this.score = value;}
}
