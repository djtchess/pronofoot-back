package fr.lotofoot.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class GrilleGeneraleBean extends GrilleBean {
    
    
   // private List<GrilleSimpleBean> listeGrilleSimple;
    private Double nbMatchSimple;
    private Double nbMatchDouble;
    private Double nbMatchTriple;
    private Double nbGrilleSimple;
    
    /**
     * 
     */
    public GrilleGeneraleBean(){
        super();
        nbMatchSimple = 0d;
        nbMatchDouble = 0d;
        nbMatchTriple = 0d;
        nbGrilleSimple = 0d;
      //  listeGrilleSimple = new ArrayList<GrilleSimpleBean>();;
    }
    
    /**
     * 
     * @param listeMatchBean
     */
    public GrilleGeneraleBean(List<MatchBean> listeMatchBean){
        super(listeMatchBean, false);
      //  listeGrilleSimple = new ArrayList<GrilleSimpleBean>();
    }
    
    
    @Override
    public void createGrille(List<String> listeMatch){

        List<MatchBean> listeMatchBean = new ArrayList<MatchBean>();
        Iterator<String> it = listeMatch.iterator();
        while (it.hasNext()){
            String match = it.next();
            
            MatchBean matchBean = new MatchBean();
            if ("1".equals(match)){
                matchBean.setProno1(true);
                matchBean.setProno2(false);
                matchBean.setProno3(false);
                nbMatchSimple++;
            }
            if ("N".equals(match)){
                matchBean.setProno1(false);
                matchBean.setProno2(true);
                matchBean.setProno3(false);
                nbMatchSimple++;
            }
            if ("2".equals(match)){
                matchBean.setProno1(false);
                matchBean.setProno2(false);
                matchBean.setProno3(true);
                nbMatchSimple++;
            }
            if ("1N".equals(match)){
                matchBean.setProno1(true);
                matchBean.setProno2(true);
                matchBean.setProno3(false);
                nbMatchDouble++;
            }
            if ("N2".equals(match)){
                matchBean.setProno1(false);
                matchBean.setProno2(true);
                matchBean.setProno3(true);
                nbMatchDouble++;
            }
            if ("12".equals(match)){
                matchBean.setProno1(true);
                matchBean.setProno2(false);
                matchBean.setProno3(true);
                nbMatchDouble++;
            }
            if ("1N2".equals(match)){
                matchBean.setProno1(true);
                matchBean.setProno2(true);
                matchBean.setProno3(true);
                nbMatchTriple++;
            }
            listeMatchBean.add(matchBean);                                      
        }
        
        setListeMatchBean(listeMatchBean); 
    }
    
    public void createGrille2(List<MatchBean> listeMatch){
        Iterator<MatchBean> it = listeMatch.iterator();
        while (it.hasNext()){
            MatchBean match = it.next();
            if (match.isMatchTriple()) nbMatchTriple++;
            if (match.isMatchDouble()) nbMatchDouble++;
            if (match.isMatchSimple()) nbMatchSimple++;
        }
        setListeMatchBean(listeMatch);
    }

    public String toString()
    {
        return String.join("",listeMatchBean.stream().map(MatchBean::getProno).collect(Collectors.toList()));
    }


    public int getCountGarantie(GrilleGeneraleBean grilleGeneraleBean){
        int cpt = 0;
        for (int i=0; i<this.listeMatchBean.size(); i++) {
            String pronoMatch1= this.listeMatchBean.get(i).getPronoMatch();
            String pronoMatch2= grilleGeneraleBean.listeMatchBean.get(i).getPronoMatch();
            if (pronoMatch1.equals(pronoMatch2)) cpt++;
        }
        return cpt;
    }



   
}
