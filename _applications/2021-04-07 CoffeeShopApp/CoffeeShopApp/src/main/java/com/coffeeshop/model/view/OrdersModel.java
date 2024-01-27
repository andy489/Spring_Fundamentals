package com.coffeeshop.model.view;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrdersModel {

    private List<OrderView> allOrders;

    private List<EmployeeOrderView> employeeOrders;

    private Integer totalTime;

    public OrdersModel() {
        allOrders = new ArrayList<>();
        employeeOrders = new ArrayList<>();
    }
}
