package fr.pronofoot.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StandingTeamModel {

    private TeamModel team;
    private int playedGames;
    private int won;
    private int draw;
    private int lost;
    private int points;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;

    public void add3Points(){
        this.points += 3;
    }

    public void add1Points(){
        this.points += 1;
    }

    public void addGoalsFor(int goal){
        this.goalsFor += goal;
    }

    public void addGoalsAgainst(int goal){
        this.goalsAgainst += goal;
    }

    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    public void addWon(){
        this.won++;
    }
    public void addLost(){
        this.lost++;
    }
    public void addDraw(){
        this.draw++;
    }
    public void addPlayedGames(){
        this.playedGames++;
    }

}