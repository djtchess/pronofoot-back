package fr.lotofoot.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class GrilleBean {
    
    protected List<MatchBean> listeMatchBean;
    protected boolean isGarantie;
    
    public abstract void createGrille(List<String> listeMatch);


}
