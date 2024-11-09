package br.com.nomeempresa.restaurante.application.inout.mapper;

import br.com.nomeempresa.restaurante.application.inout.input.PaymentInput;
import br.com.nomeempresa.restaurante.application.inout.output.PaymentBalanceOutput;
import br.com.nomeempresa.restaurante.application.inout.output.PaymentOutput;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentInputOutputMapper {
    PaymentInputOutputMapper INSTANCE = Mappers.getMapper(PaymentInputOutputMapper.class);

    @Mapping(source = "order", target = "order", qualifiedByName = "mapOrderIdToOrder")
    Payment paymentRequestToPayment(final PaymentInput paymentInput);

    PaymentOutput paymentToPaymentResponse(final Payment payment);

    PaymentBalanceOutput paymentToPaymentBalanceOutput(final Payment payment);


    @Named("mapOrderIdToOrder")
    default Order mapOrderIdToOrder(final Long orderId) {
        return new Order(
            orderId,
            null,
            null,
            null,
            null

        );
    }

}
