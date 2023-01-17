package MesClasses;

public class CritereMarque implements Critere {

    private String marque;

    public CritereMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public boolean estSatisfaitPar(Voiture v) {
        if(this.marque == "All") return true;
        return v.getMarque().equals(this.marque);
    }
}
