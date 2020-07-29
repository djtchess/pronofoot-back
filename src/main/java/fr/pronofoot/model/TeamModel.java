package fr.pronofoot.model;

import fr.pronofoot.jfdata.model.competition.Competition;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamModel {

	//ATTRIBUT
	private String id;
	private PaysModel pays;
	private List<Competition> activeCompetitions;
	private String name;
	private String shortName;
	private String tla;
	private String address;
	private String phone;
	private String website;
	private String email;
	private String founded;
	private String clubColors;
	private String venue;
	private String lastUpdated;

	//CONSTRUCTOR
	public TeamModel() {
		super();
	}


}
