package br.com.order.application;

import br.com.order.App;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = App.class)
class AppTest {

    @Test
    void contextLoads() {
        // Verifica se o contexto da aplicação Spring Boot carrega corretamente
    }
}
