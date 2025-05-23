package romulo.dev.orderms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import romulo.dev.orderms.dto.OrderCreatedEvent;
import romulo.dev.orderms.service.OrderService;

import static romulo.dev.orderms.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

	private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);
	
	private final OrderService orderService;
	
	public OrderCreatedListener(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@RabbitListener(queues = ORDER_CREATED_QUEUE)
	public void listen(Message<OrderCreatedEvent> message) {
		logger.info("Message consumed: {} ", message);
		
		orderService.save(message.getPayload());
	}
}
