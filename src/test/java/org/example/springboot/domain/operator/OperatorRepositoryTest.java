package org.example.springboot.domain.operator;

import lombok.Data;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.rankstat.RankStatRepository;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OperatorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OperatorRepository operatorRepository;

    private Player player;

    @Before
    public void setUp() {
        player = entityManager.persist(
                Player.builder()
                        .userId("testId")
                        .platform("uplay")
                        .build()
        );
    }

    @Test
    public void WhenSaveOperator_Expect_Good() {
        Operator operator = Operator.builder()
                .operatorIndex("3:8")
                .category("def")
                .name("caveira")
                .death(10)
                .kills(10)
                .headShot(10)
                .meleeKills(10)
                .roundLost(10)
                .roundWon(10)
                .timePlayed(10)
                .totalXp(10)
                .player(player)
                .build();

        operatorRepository.save(operator);
        Operator found = entityManager.find(Operator.class, operator.getId());
        assertThat(found).isEqualTo(operator);
    }


    @Test(expected = ConstraintViolationException.class)
    public void When_SaveOperatorPlayerIsNull_Expect_ConstraintViolation() {
        Operator operator = Operator.builder()
                .operatorIndex("3:8")
                .category("def")
                .name("caveira")
                .death(10)
                .kills(10)
                .headShot(10)
                .meleeKills(10)
                .roundLost(10)
                .roundWon(10)
                .timePlayed(10)
                .totalXp(10)
                .build();
        operatorRepository.save(operator);
    }
}
