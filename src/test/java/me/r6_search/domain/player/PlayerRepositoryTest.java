package me.r6_search.domain.player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerRepository playerRepository;

    public static final Player normalPlayer = Player.builder()
            .platform("uplay")
            .userId("Piliot")
            .profileId("beec3d7b-a925-48a0-94bd-9896541dcbd5")
            .build();

    public static final Player abnormalPlayer = Player.builder()
            .platform("uplay")
            .userId("sjaklsv102xxda")
            .profileId("hello this is profile Id")
            .build();

    String username = "piliot";
    String platform = "uplay";
    String profileId = "test_profile_id";

    @Before
    public void setUp() {
        entityManager.clear();
    }

    @Test
    public void When_SavePlayer_Expect_Good() {
        Player player = Player.builder()
                .platform(platform)
                .userId(username)
                .profileId(profileId)
                .build();

        playerRepository.save(player);

        Player foundPlayer = entityManager.find(Player.class, player.getId());
        assertThat(player).isEqualTo(foundPlayer);
    }

    @Test
    public void When_FindExistPlayer_Expect_Good() {
        Player player = Player.builder()
                .platform(platform)
                .userId(username)
                .profileId(profileId)
                .build();

        playerRepository.save(player);
        Player foundPlayer = playerRepository.findByPlatformAndAndUserId(platform, username);

        assertThat(player).isEqualTo(foundPlayer);
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveBlankUsername_Expect_ConstraintException() {
        Player player = Player.builder()
                .platform(platform)
                .userId("")
                .profileId(profileId)
                .build();

        playerRepository.save(player);
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveBlankPlatform_Expect_ConstraintException() {
        Player player = Player.builder()
                .platform("")
                .userId(username)
                .profileId(profileId)
                .build();

        playerRepository.save(player);
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveNullPlatform_Expect_ConstraintException() {
        Player player = Player.builder()
                .platform(null)
                .userId(username)
                .profileId(profileId)
                .build();

        playerRepository.save(player);
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveNullUsername_Expect_ConstraintException() {
        Player player = Player.builder()
                .platform(platform)
                .userId(null)
                .profileId(profileId)
                .build();

        playerRepository.save(player);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void When_DuplicateUsernameAndPlatform_Expect_DataIntegrityViolation() {
        Player player1 = Player.builder()
                .platform(platform)
                .userId(username)
                .profileId(profileId)
                .build();
        Player player2 = Player.builder()
                .platform(platform)
                .userId(username)
                .profileId(profileId)
                .build();

        playerRepository.save(player1);
        playerRepository.save(player2);
    }
}
