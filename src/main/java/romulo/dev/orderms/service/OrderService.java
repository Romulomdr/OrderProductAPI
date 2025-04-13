package romulo.dev.orderms.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import romulo.dev.orderms.dto.OrderCreatedEvent;
import romulo.dev.orderms.entity.OrderEntity;
import romulo.dev.orderms.entity.OrderItem;
import romulo.dev.orderms.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public void save(OrderCreatedEvent event) {
		
		var entity = new OrderEntity();
		
		entity.setOrderId(event.CodigoPedido());
		entity.setCustomerId(event.CodigoCliente());
		entity.setItems(getOrderItems(event));
		entity.setTotal(getTotal(event));
		orderRepository.save(entity);
	}
	
	private BigDecimal getTotal(OrderCreatedEvent event) {
		
		return event.itens().stream().map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	//Separando os pedidos que vieram na resposta em uma lista
	public static List<OrderItem> getOrderItems(OrderCreatedEvent event){
		return event.itens().stream().map(i -> new OrderItem(i.produto(),i.quantidade(),i.preco())).toList();
		
	}
}
