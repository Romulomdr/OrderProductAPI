package romulo.dev.orderms.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import romulo.dev.orderms.controller.dto.ApiResponse;
import romulo.dev.orderms.controller.dto.OrderResponse;
import romulo.dev.orderms.controller.dto.PaginationResponse;
import romulo.dev.orderms.service.OrderService;

@RestController
public class OrderController {

	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/customers/{customerId}/orders")
	public ResponseEntity<ApiResponse<OrderResponse>> ListOrders(
			@PathVariable("customerId") Long costumerId,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
		
		var pageResponse = orderService.findAllByCostumerId(costumerId, PageRequest.of(page, pageSize));
		
		return ResponseEntity.ok(new ApiResponse<>(
				pageResponse.getContent(),
				PaginationResponse.fromPage(pageResponse)
				));
	}
}
