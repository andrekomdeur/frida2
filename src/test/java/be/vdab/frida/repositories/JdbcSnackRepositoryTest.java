package be.vdab.frida.repositories;
import be.vdab.frida.domain.Snack;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
@JdbcTest
@Import(JdbcSnackRepository.class)
@Sql("/insertSnacks.sql")
class JdbcSnackRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String SNACKS = "snacks";
    private final JdbcSnackRepository repository;

    public JdbcSnackRepositoryTest(JdbcSnackRepository repository) {
        this.repository = repository;
    }

    private long idVanTestSnack() {
        return super.jdbcTemplate.queryForObject(
                "select id from Snacks where naam='test'", Long.class);
    }
    @Test
    void update() {
        long id = idVanTestSnack();
        Snack snack = new Snack(id, "test", BigDecimal.ONE);
        repository.update(snack);
        assertThat(super.jdbcTemplate.queryForObject(
                "select prijs from snacks where id=?", BigDecimal.class, id)).isOne();
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestSnack()).get().getNaam()).isEqualTo("test");
    }
    @Test
    void findByNaam() {
        assertThat(repository.findByNaam("test2"))
                .hasSize(super.countRowsInTableWhere(SNACKS, "naam='test2'"))
                .extracting(Snack->Snack.getNaam().toLowerCase()).isSorted();
    }

}
