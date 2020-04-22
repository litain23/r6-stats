package org.example.springboot.domain.operatorindex;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorIndexRepositoryTest {

    @Autowired
    OperatorIndexRepository repository;

    @Test
    public void getOperatorIndex() {
        List<OperatorIndex> operatorIndexList = repository.findAll();
        OperatorIndex caveira = operatorIndexList.stream()
                .filter(op -> op.getName().equals("caveira"))
                .findFirst()
                .orElseThrow(NullPointerException::new);

        assertThat(caveira.getOperatorIndex()).isEqualTo("3:8");

    }
}
