package org.example.springboot.domain.operatorindex;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorIndexRepositoryTest {

    @Autowired
    OperatorIndexRepository repository;

    @After
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void getOperatorIndex() {
        String name = "caveira";
        String category = "def";
        int nameOasisId = 207671;
        int ctuOasisId = 207757;
        String index = "1:8";

        String statisticPvpId = "operatorpvp_caveira_interrogations:1:8";
        String statisticPveId = "operatorpve_caveira_interrogations:1:8";
        int statisticPvpOasisId = 207945;
        int statisticPveOasisId = 207952;

        OperatorIndex operatorIndex =
                OperatorIndex.builder()
                        .name(name)
                        .category(category)
                        .nameOasisId(nameOasisId)
                        .ctuOasisId(ctuOasisId)
                        .index(index)
                        .statisticPveId(statisticPveId)
                        .statisticPveOasisId(statisticPveOasisId)
                        .statisticPvpId(statisticPvpId)
                        .statisticPvpOasisId(statisticPvpOasisId)
                        .build();

        repository.save(operatorIndex);

        List<OperatorIndex> operatorIndexList = repository.findAll();

        OperatorIndex testOp = operatorIndexList.get(0);

        assertThat(testOp.getCategory()).isEqualTo(category);
        assertThat(testOp.getName()).isEqualTo(name);
        assertThat(testOp.getNameOasisId()).isEqualTo(nameOasisId);
        assertThat(testOp.getIndex()).isEqualTo(index);
        assertThat(testOp.getCtuOasisId()).isEqualTo(ctuOasisId);
        assertThat(testOp.getStatisticPveId()).isEqualTo(statisticPveId);
        assertThat(testOp.getStatisticPvpId()).isEqualTo(statisticPvpId);
        assertThat(testOp.getStatisticPveOasisId()).isEqualTo(statisticPveOasisId);
        assertThat(testOp.getStatisticPvpOasisId()).isEqualTo(statisticPvpOasisId);

    }
}
