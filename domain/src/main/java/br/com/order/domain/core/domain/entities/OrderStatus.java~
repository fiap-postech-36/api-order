package br.com.order.domain.core.domain.entities;

public enum OrderStatus {

    CANCELED("Cancelado", -1, 4),
    CREATED("Criado", 0, 5),
    RECEIVED("Recebido", 1 , 2),
    IN_PREPARATION("Em preparação", 2, 1),
    READY("Pronto", 3, 0),
    FINISHED("Finalizado", 4, 3);


    private final String description;

    private final int order;

    private final int filterOrder;
    OrderStatus(final String description, final int order, final int filterOrder) {
        this.description = description;
        this.order = order;
        this.filterOrder = filterOrder;
    }

    public String getDescription() {
        return description;
    }

    public int getOrder() {
        return order;
    }

    public int getFilterOrder() {
        return filterOrder;
    }

    public static OrderStatus getFromOrder(final int order) {
        for (OrderStatus status : OrderStatus.values()) {
            if (order == status.getOrder()) {
                return status;
            }
        }
        return null;
    }

    public OrderStatus getNext() {
        return getFromOrder(this.getOrder() + 1);
    }
}
