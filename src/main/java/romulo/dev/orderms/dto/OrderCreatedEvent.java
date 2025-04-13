package romulo.dev.orderms.dto;

import java.util.List;

public record OrderCreatedEvent(Long CodigoPedido,
		Long CodigoCliente,
		List<OrderItemEvent> itens) {

}
