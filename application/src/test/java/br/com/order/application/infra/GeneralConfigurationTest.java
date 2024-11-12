package br.com.order.application.infra;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GeneralConfigurationTest {

    @Test
    void testModelMapperBean() {
        GeneralConfiguration generalConfiguration = new GeneralConfiguration();
        ModelMapper modelMapper = generalConfiguration.modelMapper();

        assertNotNull(modelMapper);
        assertEquals(modelMapper.getConfiguration().getMatchingStrategy(), org.modelmapper.convention.MatchingStrategies.STRICT);
        assertNotNull(modelMapper.getConfiguration().getPropertyCondition());
    }
}
