package fr.lotofoot.bean;

import java.util.List;
import java.util.stream.Collectors;

public class GrilleSimpleBean extends GrilleBean{
    
    public GrilleSimpleBean(){
        super();
    }

    @Override
    public void createGrille(List<String> listeMatch) {
        // TODO Stub de la méthode généré automatiquement
        
    }

    
    public String toString()
    {
        return String.join("",listeMatchBean.stream().map(MatchBean::getProno).collect(Collectors.toList()));
    }

    
}
