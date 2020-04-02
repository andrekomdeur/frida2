package be.vdab.frida.domain;
import java.math.BigDecimal;
public class Snack {
    private final Long id;
    private final String naam;
    private final BigDecimal prijs;

    public Snack(Long id, String naam, BigDecimal prijs) {
        this.id = id;
        this.naam = naam;
        this.prijs = prijs;
    }

    public Long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }
}
