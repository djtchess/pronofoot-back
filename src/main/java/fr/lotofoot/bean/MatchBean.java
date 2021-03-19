package fr.lotofoot.bean;

/**
 * 
 * @author djacquot
 *
 */
public class MatchBean {
    
    private boolean prono1;
    private boolean prono2;
    private boolean prono3;
    private String prono;
    private boolean matchSimple;
    private boolean matchDouble;
    private boolean matchTriple;
    
    /**
     * Constructor
     */
    public MatchBean(){
        
    }
    
    /**
     * full constructor
     */
    public MatchBean(boolean prono1, boolean prono2, boolean prono3, String prono){
        this.prono1 = prono1;
        this.prono2 = prono2;
        this.prono3 = prono3;
        this.prono = prono;
    }
    
    public MatchBean(boolean prono1, boolean prono2, boolean prono3){
        this.prono1 = prono1;
        this.prono2 = prono2;
        this.prono3 = prono3;
        getPronoMatch();
    }
    
    /**
     * 
     * @return resultatProno le pronostique du match
     */
    public String getPronoMatch(){
        StringBuffer resultatProno = new StringBuffer("");
        if (prono1) resultatProno.append("1");
        if (prono2) resultatProno.append("N");
        if (prono3) resultatProno.append("2");
        this.prono =  resultatProno.toString();
        return resultatProno.toString(); 
    }

    /**
     * @return le prono1
     */
    public boolean getProno1() {
        return prono1;
    }

    /**
     * @param prono1 le prono1 à définir
     */
    public void setProno1(boolean prono1) {
        getPronoMatch();
        this.prono1 = prono1;
    }

    /**
     * @return le prono2
     */
    public boolean getProno2() {
        return prono2;
    }

    /**
     * @param prono2 le prono2 à définir
     */
    public void setProno2(boolean prono2) {
        this.prono2 = prono2;
        getPronoMatch();
    }

    /**
     * @return le prono3
     */
    public boolean getProno3() {
        return prono3;
        
    }

    /**
     * @param prono3 le prono3 à définir
     */
    public void setProno3(boolean prono3) {
        this.prono3 = prono3;
        getPronoMatch();
    }

    /**
     * @return le prono
     */
    public String getProno() {
        return prono;
    }

    /**
     * @param prono le prono à définir
     */
    public void setProno(String prono) {
        this.prono = prono;
    }

    /**
     * @return le matchSimple
     */
    public boolean isMatchSimple() {
        matchSimple = false;
        if (this.prono1 && !this.prono2 && !this.prono3) matchSimple = true;
        if (!this.prono1 && this.prono2 && !this.prono3) matchSimple = true;
        if (!this.prono1 && !this.prono2 && this.prono3) matchSimple = true;        
        return matchSimple;
    }



    /**
     * @return le matchDouble
     */
    public boolean isMatchDouble() {
        matchDouble = false;
        if (this.prono1 && this.prono2 && !this.prono3) matchDouble = true;
        if (this.prono1 && !this.prono2 && this.prono3) matchDouble = true;
        if (!this.prono1 && this.prono2 && this.prono3) matchDouble = true;        
        return matchDouble;
    }

    /**
     * @return le matchTriple
     */
    public boolean isMatchTriple() {
        this.matchTriple = (this.prono1 && this.prono2 && this.prono3);
        return matchTriple;
    }




}
