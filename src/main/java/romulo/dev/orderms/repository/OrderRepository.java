package romulo.dev.orderms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import romulo.dev.orderms.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long>{

}
