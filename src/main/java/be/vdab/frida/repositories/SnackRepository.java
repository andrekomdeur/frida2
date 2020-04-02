package be.vdab.frida.repositories;
import be.vdab.frida.domain.Snack;

import java.util.List;
import java.util.Optional;
public interface SnackRepository {
    void update(Snack snack);
    Optional<Snack> findById(long id);
    List<Snack> findByNaam(String naam);
}

