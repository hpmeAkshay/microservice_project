package com.productbased.order_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.productbased.order_service.dto.OrderLineItemsDto;
import com.productbased.order_service.dto.OrderRequest;
import com.productbased.order_service.model.Order;
import com.productbased.order_service.model.OrderLineItems;
import com.productbased.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems= orderRequest.getOrderLineItemsDtoList()
		.stream()
		.map(this::mapToDo)
		.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		orderRepository.save(order);
	}
	
	private OrderLineItems mapToDo(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
}
