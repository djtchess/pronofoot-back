package fr.pronofoot.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class MatchModel {
    private Long id;
    private Date utcDate;
    private int matchDay;
    private Date dateMaj;
    private int butHomeFull;
    private int butAwayFull;
    private int butHomeHalf;
    private int butAwayHalf;
    private int butHomeExtra;
    private int butAwayExtra;
    private int butHomePenalties;
    private int butAwayPenalties;
    private TeamModel homeTeam;
    private TeamModel awayTeam;

}