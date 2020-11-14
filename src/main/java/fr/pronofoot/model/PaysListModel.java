package fr.pronofoot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PaysListModel {

    private Long count;
    private List<PaysModel> paysList;

}