package com.coffeeshop.service;

import com.coffeeshop.mapper.MapStructMapper;
import com.coffeeshop.model.dto.OrderAddDto;
import com.coffeeshop.model.entity.OrderEntity;
import com.coffeeshop.model.view.EmployeeOrderView;
import com.coffeeshop.model.view.OrdersModel;
import com.coffeeshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


import static java.util.stream.Collectors.groupingBy;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MapStructMapper mapper;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            MapStructMapper mapper,
            UserService userService,
            CategoryService categoryService
    ) {

        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public void placeOrder(OrderAddDto orderAddDto) {
        OrderEntity orderEntity = mapper.toOrderEntity(orderAddDto);

        orderEntity.setCategory(categoryService.getByName(orderAddDto.getCategory()));
        orderEntity.setEmployee(userService.getCurrentUser());
        orderEntity.setOrderTime(orderAddDto.getOrderTime().plusHours(2));

        orderRepository.saveAndFlush(orderEntity);
    }

    public OrdersModel getOrdersModel() {

        OrdersModel ordersModel = new OrdersModel();

        List<OrderEntity> orders = orderRepository
                .findAll(Sort.by(Sort.Direction.ASC, "orderTime")).stream().toList();

        // All employees with at least one order
        Map<String, List<OrderEntity>> collect = orders.stream()
                .collect(groupingBy(o -> o.getEmployee().getUsername()));

        // Add employees with no orders
        userService.getAllUsernames().forEach(u -> collect.putIfAbsent(u, new ArrayList<>()));

        ordersModel.setAllOrders(orders.stream().map(mapper::toOrderViewFullMap).toList());
        ordersModel.setTotalTime(orders.stream().mapToInt(o -> o.getCategory().getNeededTime()).sum());
        ordersModel.setEmployeeOrders(collect.entrySet().stream()
                .map(e -> EmployeeOrderView.of(e.getKey(), e.getValue().size())).
                sorted(Comparator.comparing(EmployeeOrderView::getCount).reversed())
                .toList()
        );

        return ordersModel;
    }

    public void readyOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
