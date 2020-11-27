package fr.pronofoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EuroMillion {

    public static void main(String args[]){
        System.out.println("test");
        Random rand = new Random();
        List<Integer> listeNb = new ArrayList<Integer>();

        for (int i=0; i<5; i++){
            int nombreAleatoire = rand.nextInt(51 - 1 + 1) + 1;
            if (listeNb.contains(Integer.valueOf(nombreAleatoire))){
                i--;
            }else {
                listeNb.add(nombreAleatoire);
            }
        }

        int etoile1 = rand.nextInt(12 - 1 + 1) + 1;
        int etoile2 = etoile1;
        while (etoile1 == etoile2) {
            etoile2 = rand.nextInt(12 - 1 + 1) + 1;
        }

        for(int num : listeNb){
            System.out.println(num);
        }
        System.out.println(" * ");
        System.out.println(etoile1);
        System.out.println(etoile2);


    }
}
