package br.com.application.infra;

import br.com.order.application.infra.GeneralConfiguration;
import org.junit.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeneralConfigurationTest {

    private final GeneralConfiguration generalConfiguration = new GeneralConfiguration();

    @Test
    public void shouldReturnConfiguredModelMapper() {
        ModelMapper modelMapper = generalConfiguration.modelMapper();

        assertThat(modelMapper).isNotNull();
        assertThat(modelMapper.getConfiguration().getPropertyCondition()).isEqualTo(Conditions.isNotNull());
        assertThat(modelMapper.getConfiguration().getMatchingStrategy()).isEqualTo(MatchingStrategies.STRICT);
    }

    @Test
    public void testModelMapperConfiguration2() {
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
