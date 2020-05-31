package org.example.springboot.domain.player;

import org.example.springboot.service.PlayerService;
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

    @Autowired
    private PlayerService playerService;

    String username = "piliot";
    String platform = "uplay";

    @Before
    public void setUp() {
        entityManager.clear();
    }

    @Test
    public void When_SavePlayer_Expect_Good() {
        Player player = Player.builder()
                .platform(platform)
                .userId(username)
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
                .build();

        playerRepository.save(player);
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveBlankPlatform_Expect_ConstraintException() {
        Player player = Player.builder()
                .platform("")
                .userId(username)
                .build();

        playerRepository.save(player);
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveNullPlatform_Expect_ConstraintException() {
        Player player = Player.builder()
                .platform(null)
                .userId(username)
                .build();

        playerRepository.save(player);
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveNullUsername_Expect_ConstraintException() {
        Player player = Player.builder()
                .platform(platform)
                .userId(null)
                .build();

        playerRepository.save(player);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void When_DuplicateUsernameAndPlatform_Expect_DataIntegrityViolation() {
        Player player1 = Player.builder()
                .platform(platform)
                .userId(username)
                .build();
        Player player2 = Player.builder()
                .platform(platform)
                .userId(username)
                .build();

        playerRepository.save(player1);
        playerRepository.save(player2);
    }
}
