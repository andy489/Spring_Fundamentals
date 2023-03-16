package com.shoppinglist.model.view;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class ProductView {

    private Long id;

    private String name;

    private BigDecimal price;
}
