package fr.lotofoot.service;


import java.util.List;

import fr.lotofoot.bean.GrilleGeneraleBean;
import fr.lotofoot.bean.MatchBean;

/**
 * @author djacquot
 *
 */
public interface GrilleGeneraleService {

    public String getGrille();
    public List<GrilleGeneraleBean> genererListeGrille(GrilleGeneraleBean maGrille);
    public List<GrilleGeneraleBean> decomposerGrilleTriple(List<MatchBean> listeMatchADecomposee);
    public List<GrilleGeneraleBean> decomposerGrilleDouble(List<MatchBean> listeMatchADecomposee);

}