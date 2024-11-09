package br.com.nomeempresa.restaurante.application.inout.mapper;

import br.com.nomeempresa.restaurante.application.inout.input.CustomerInput;
import br.com.nomeempresa.restaurante.application.inout.output.CustomerOutput;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Customer;
import br.com.nomeempresa.restaurante.domain.core.domain.valueobjects.CPF;
import br.com.nomeempresa.restaurante.domain.core.domain.valueobjects.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerInputOutputMapper {
    CustomerInputOutputMapper INSTANCE = Mappers.getMapper(CustomerInputOutputMapper.class);

    @Mappings({
        @Mapping(source = "email", target = "email", qualifiedByName = "mapEmail"),
        @Mapping(source = "cpf", target = "cpf", qualifiedByName = "mapCpf")})
    Customer customerRequestToCustomer(final CustomerInput customerInput);
    @Mappings({
        @Mapping(source = "email", target = "email", qualifiedByName = "mapEmailToString"),
        @Mapping(source = "cpf", target = "cpf", qualifiedByName = "mapCpfToString")})
    CustomerOutput customerToCustomerResponse(final Customer customer);

    @Named("mapEmail")
    default Email mapEmail(final String email) {
        return new Email(email);
    }

    @Named("mapCpf")
    default CPF mapCpf(final String cpf) {
        return new CPF(cpf);
    }

    @Named("mapEmailToString")
    default String mapEmailToString(final Email email) {
        return email.getValue();
    }

    @Named("mapCpfToString")
    default String mapCpfToString(final CPF cpf) {
        return cpf.getValue();
    }
}
