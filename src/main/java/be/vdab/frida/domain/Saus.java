package be.vdab.frida.domain;

public class Saus {
     private final Long nummer;
     private final String naam;
     private final String[] ingredienten;

    public Saus(Long nummer, String naam, String[] ingredienten) {
        this.nummer = nummer;
        this.naam = naam;
        this.ingredienten = ingredienten;
    }

    public Long getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String[] getIngredienten() {
        return ingredienten;
    }
}
