package br.com.order.domain.application.exception;

import br.com.order.domain.core.exception.CoreExceptionRuntime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoreExceptionRuntimeTest {

    @Test
    void testCoreExceptionRuntimeConstructor() {
        // Criando uma instância da exceção com uma mensagem
        String errorMessage = "This is a test exception message";
        CoreExceptionRuntime exception = new CoreExceptionRuntime(errorMessage);

        // Testando se a mensagem da exceção é igual à fornecida
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testCoreExceptionRuntimeNoMessage() {
        // Criando uma instância da exceção sem uma mensagem
        CoreExceptionRuntime exception = new CoreExceptionRuntime(null);

        // Testando se a exceção foi criada sem mensagem e o valor de getMessage() é null
        assertNull(exception.getMessage());
    }

    @Test
    void testCoreExceptionRuntimeWithMessage() {
        // Criando uma instância da exceção com uma mensagem
        String errorMessage = "Another exception occurred";
        CoreExceptionRuntime exception = new CoreExceptionRuntime(errorMessage);

        // Verificando se a mensagem está sendo passada corretamente para o construtor
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}
