package fr.lotofoot;

import java.util.ArrayList;
import java.util.List;

import fr.lotofoot.bean.GrilleGeneraleBean;
import fr.lotofoot.service.impl.GrilleGeneraleServiceImpl;

public class Lotofoot {

    public static void main(String[] agrs){


        List<String> listeMatch =new ArrayList<>();

        listeMatch.add("1N2");
        listeMatch.add("1N2");
        listeMatch.add("1N");
        listeMatch.add("2");

        listeMatch.add("1N2");
        listeMatch.add("1N2");
        listeMatch.add("1N2");

        GrilleGeneraleBean grilleGeneraleBean = new GrilleGeneraleBean();
        grilleGeneraleBean.createGrille(listeMatch);
        System.out.println(grilleGeneraleBean.toString());


        GrilleGeneraleServiceImpl grilleGeneraleService = new GrilleGeneraleServiceImpl();
        List<GrilleGeneraleBean> listeGrille = grilleGeneraleService.genererListeGrille(grilleGeneraleBean);
        listeGrille.forEach(System.out::println);

        System.out.println(listeGrille.get(0).getCountGarantie(listeGrille.get(3)));
        listeGrille.get(0).isGarantie();

    }



}
