/**
 * 
 */
package fr.lotofoot.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.lotofoot.bean.GrilleGeneraleBean;
import fr.lotofoot.bean.MatchBean;
import fr.lotofoot.service.GrilleGeneraleService;


/**
 * @author djacquot
 * 
 */
public class GrilleGeneraleServiceImpl implements GrilleGeneraleService {

    private GrilleGeneraleBean grilleGeneraleBean;

    public GrilleGeneraleServiceImpl() {

    }

    public String getGrille() {
        return null;
    }

    public List<GrilleGeneraleBean> genererListeGrille(GrilleGeneraleBean maGrille) {
        System.out.println(" nbMatchTriple : " + maGrille.getNbMatchTriple());
        System.out.println(" nbMatchDouble : " + maGrille.getNbMatchDouble());

        // calcul du nombre de grille simple
        int nbGrilleSimple = 1;
        if (maGrille.getNbMatchTriple() > 0)
            nbGrilleSimple *= Math.pow(3, maGrille.getNbMatchTriple());
        if (maGrille.getNbMatchDouble() > 0)
            nbGrilleSimple *= Math.pow(2, maGrille.getNbMatchDouble());
        System.out.println(" nbGrilleSimple à avoir : " + nbGrilleSimple);
        maGrille.setNbGrilleSimple(Double.valueOf(nbGrilleSimple));

        List<GrilleGeneraleBean> listeGrille = new ArrayList<>();
        if (maGrille.getNbMatchTriple() > 0) listeGrille = decomposerGrilleTriple(maGrille.getListeMatchBean());
        else listeGrille.add(maGrille);

        var ref = new Object() {
            List<GrilleGeneraleBean> listeGrille2 = new ArrayList<>();
        };

        if (maGrille.getNbMatchDouble() > 0) {
            listeGrille.forEach(grille -> {
                ref.listeGrille2.addAll(decomposerGrilleDouble(grille.getListeMatchBean()));
            } );
        }

        if (!ref.listeGrille2.isEmpty()) listeGrille = ref.listeGrille2;

        return listeGrille;

    }

    /**
     *  Permet de décomposer une liste de grille triple en grille simple
     * @param listeMatchADecomposee
     * @return
     */
    @Override
    public List<GrilleGeneraleBean> decomposerGrilleTriple(List<MatchBean> listeMatchADecomposee) {

        List<GrilleGeneraleBean> listeGrille = new ArrayList<GrilleGeneraleBean>();

        // Création de 3 nouvelles grilles
        GrilleGeneraleBean grilleGeneraleBean1 = null;
        GrilleGeneraleBean grilleGeneraleBeanN = null;
        GrilleGeneraleBean grilleGeneraleBean2 = null;

        // Création des 3 liste de match
        List<MatchBean> listeMatch1 = new ArrayList<MatchBean>();
        List<MatchBean> listeMatchN = new ArrayList<MatchBean>();
        List<MatchBean> listeMatch2 = new ArrayList<MatchBean>();

        // traitement de la grille à décomposer

        // récupération de la liste des match de la grille à décomposer
        Iterator<MatchBean> itListeMatchADecomposer = listeMatchADecomposee.iterator();
        boolean matchDecompose = false;
        while (itListeMatchADecomposer.hasNext()) {
            // traitement du match à décomposer
            MatchBean matchADecomposer = itListeMatchADecomposer.next();
            // si c'est un match triple
            if (matchADecomposer.isMatchTriple() && !matchDecompose) {
                MatchBean matchBean1 = new MatchBean(true, false, false);
                listeMatch1.add(matchBean1);
                MatchBean matchBeanN = new MatchBean(false, true, false);
                listeMatchN.add(matchBeanN);
                MatchBean matchBean2 = new MatchBean(false, false, true);
                listeMatch2.add(matchBean2);
                matchDecompose = true;
            } else {
                listeMatch1.add(matchADecomposer);
                listeMatchN.add(matchADecomposer);
                listeMatch2.add(matchADecomposer);
            }
        }

        if (!listeMatch1.isEmpty()) {
            grilleGeneraleBean1 = new GrilleGeneraleBean();
            grilleGeneraleBean1.createGrille2(listeMatch1);
        }
        if (!listeMatchN.isEmpty()) {
            grilleGeneraleBeanN = new GrilleGeneraleBean();
            grilleGeneraleBeanN.createGrille2(listeMatchN);
        }
        if (!listeMatch2.isEmpty()) {
            grilleGeneraleBean2 = new GrilleGeneraleBean();
            grilleGeneraleBean2.createGrille2(listeMatch2);
        }

        if (grilleGeneraleBean1 != null) {
            if (grilleGeneraleBean1.getNbMatchTriple() > 0) {
                listeGrille.addAll(decomposerGrilleTriple(grilleGeneraleBean1.getListeMatchBean()));
            } else {
                listeGrille.add(grilleGeneraleBean1);
            }
        }

        if (grilleGeneraleBeanN != null) {
            if (grilleGeneraleBeanN.getNbMatchTriple() > 0) {
                listeGrille.addAll(decomposerGrilleTriple(grilleGeneraleBeanN.getListeMatchBean()));
            } else {
                listeGrille.add(grilleGeneraleBeanN);
            }
        }

        if (grilleGeneraleBean2 != null) {
            if (grilleGeneraleBean2.getNbMatchTriple() > 0) {
                listeGrille.addAll(decomposerGrilleTriple(grilleGeneraleBean2.getListeMatchBean()));
            } else {
                listeGrille.add(grilleGeneraleBean2);
            }
        }
        return listeGrille;

    }

    /**
     * Permet de décomposer une liste de grille double en grille simple
     * @param listeMatchADecomposee
     * @return
     */
    public List<GrilleGeneraleBean> decomposerGrilleDouble(List<MatchBean> listeMatchADecomposee) {

        List<GrilleGeneraleBean> listeGrille = new ArrayList<GrilleGeneraleBean>();

        // Création de 3 nouvelles grilles
        GrilleGeneraleBean grilleGeneraleBean1 = null;
        GrilleGeneraleBean grilleGeneraleBean2 = null;

        // Création des 3 liste de match
        List<MatchBean> listeMatch1 = new ArrayList<MatchBean>();
        List<MatchBean> listeMatch2 = new ArrayList<MatchBean>();

        // traitement de la grille à décomposer

        // récupération de la liste des match de la grille à décomposer
        Iterator<MatchBean> itListeMatchADecomposer = listeMatchADecomposee.iterator();
        boolean matchDecompose = false;
        while (itListeMatchADecomposer.hasNext()) {
            // traitement du match à décomposer
            MatchBean matchADecomposer = itListeMatchADecomposer.next();
            boolean listeAlimentee = false;
            // si c'est un match Double
            if (matchADecomposer.isMatchDouble() && !matchDecompose) {
                if (matchADecomposer.getProno1()) {
                    MatchBean matchBean1 = new MatchBean(true, false, false);
                    listeMatch1.add(matchBean1);
                    listeAlimentee = true;
                }
                if (matchADecomposer.getProno2()) {
                    MatchBean matchBeanN = new MatchBean(false, true, false);
                    if (!listeAlimentee) {
                        listeMatch1.add(matchBeanN);
                    } else {
                        listeMatch2.add(matchBeanN);
                    }
                }
                if (matchADecomposer.getProno3()) {
                    MatchBean matchBean2 = new MatchBean(false, false, true);
                    listeMatch2.add(matchBean2);
                }
                matchDecompose = true;
            } else {
                listeMatch1.add(matchADecomposer);
                listeMatch2.add(matchADecomposer);
            }
        }

        if (!listeMatch1.isEmpty()) {
            grilleGeneraleBean1 = new GrilleGeneraleBean();
            grilleGeneraleBean1.createGrille2(listeMatch1);
        }
        if (!listeMatch2.isEmpty()) {
            grilleGeneraleBean2 = new GrilleGeneraleBean();
            grilleGeneraleBean2.createGrille2(listeMatch2);
        }

        if (grilleGeneraleBean1 != null) {
            if (grilleGeneraleBean1.getNbMatchDouble() > 0) {
                listeGrille.addAll(decomposerGrilleDouble(grilleGeneraleBean1.getListeMatchBean()));
            } else {
                listeGrille.add(grilleGeneraleBean1);
            }
        }

        if (grilleGeneraleBean2 != null) {
            if (grilleGeneraleBean2.getNbMatchDouble() > 0) {
                listeGrille.addAll(decomposerGrilleDouble(grilleGeneraleBean2.getListeMatchBean()));
            } else {
                listeGrille.add(grilleGeneraleBean2);
            }
        }

        return listeGrille;

    }
    
    

}