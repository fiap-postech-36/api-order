package br.com.application.infra;

import br.com.order.application.infra.GeneralConfiguration;
import org.junit.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeneralConfigurationTest {

    @Test
    public void testModelMapperConfiguration() {
        // Cria uma instância de GeneralConfiguration e obtém o ModelMapper configurado
        GeneralConfiguration configuration = new GeneralConfiguration();
        ModelMapper modelMapper = configuration.modelMapper();

        // Verifica se o ModelMapper não é nulo
        assertNotNull(modelMapper);

        // Verifica se a estratégia de correspondência está configurada como STRICT
        assertEquals(MatchingStrategies.STRICT, modelMapper.getConfiguration().getMatchingStrategy());

        // Verifica se a condição de propriedade está configurada para isNotNull
        assertEquals(Conditions.isNotNull(), modelMapper.getConfiguration().getPropertyCondition());
    }
}
