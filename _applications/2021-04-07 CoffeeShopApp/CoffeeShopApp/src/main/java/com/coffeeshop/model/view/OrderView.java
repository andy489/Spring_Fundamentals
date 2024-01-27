package com.coffeeshop.model.view;

import com.coffeeshop.model.enumerated.CategoryEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class OrderView {

    private Long id;
    private CategoryEnum category;
    private String name;
    private BigDecimal price;

}
