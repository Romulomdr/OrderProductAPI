package romulo.dev.orderms.controller.dto;

import java.math.BigDecimal;

import romulo.dev.orderms.entity.OrderEntity;

public record OrderResponse(Long orderId, Long costumerId, BigDecimal total) {

	
	public static OrderResponse fromEntity(OrderEntity entity) {
		return new OrderResponse(entity.getOrderId(), entity.getCustomerId(), entity.getTotal());
	}
}
