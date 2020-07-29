package me.r6_search.model.operator;

import me.r6_search.model.weeklyoperator.WeeklyOperator;
import me.r6_search.model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OperatorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OperatorRepository operatorRepository;

    private Player player;
    private WeeklyOperator weeklyOperator;

    @Before
    public void setUp() {
        player = entityManager.persist(
                Player.builder()
                        .userId("testId")
                        .platform("uplay")
                        .profileId("test_profile_id")
                        .build()
        );

        weeklyOperator = entityManager.persist(
                WeeklyOperator.builder()
                    .player(player)
                    .week(1)
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
                .build();

        operatorRepository.save(operator);
        Operator found = entityManager.find(Operator.class, operator.getId());
        assertThat(found).isEqualTo(operator);
    }


    @Test
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
