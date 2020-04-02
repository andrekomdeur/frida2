package be.vdab.frida.services;
import be.vdab.frida.domain.Snack;

import java.util.List;
import java.util.Optional;
public interface SnackService {
    void update(Snack snack);
    Optional<Snack> findById(long id);
    List<Snack> findByBeginNaam(String beginNaam);
}
