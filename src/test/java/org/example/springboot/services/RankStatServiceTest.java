package org.example.springboot.services;

import org.example.springboot.domain.rankstat.RankStatRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.service.PlayerService;
import org.example.springboot.service.RankStatService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RankStatServiceTest {
    @InjectMocks
    RankStatService rankStatService;

    @Mock
    UbiApi ubiApi;

    @Mock
    PlayerService playerService;

    @Mock
    RankStatRepository rankStatRepository;



    @Before
    public void setUp() {
//        when(ubiApi.getRankStat()).thenReturn(
//            null
//        );
    }
}
