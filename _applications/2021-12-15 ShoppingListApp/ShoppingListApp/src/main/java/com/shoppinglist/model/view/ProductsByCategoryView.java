package com.shoppinglist.model.view;

import com.shoppinglist.model.enumerated.CategoryEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ProductsByCategoryView {

    private final EnumMap<CategoryEnum, List<ProductView>> productsMap;

    private final EnumMap<CategoryEnum, BigDecimal> productsPriceMap;

    private BigDecimal totalCost;

    public ProductsByCategoryView(EnumMap<CategoryEnum, List<ProductView>> productsMap) {
        this.productsMap = productsMap;
        this.productsPriceMap = new EnumMap<>(CategoryEnum.class);

        this.totalCost = BigDecimal.ZERO;

        productsMap.forEach((key, value) -> {
            BigDecimal reducePrice = productsMap.get(key).stream().map(ProductView::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

            productsPriceMap.put(key, reducePrice);

            totalCost = totalCost.add(reducePrice);
        });

        if (totalCost.compareTo(BigDecimal.ZERO) == 0) {
            totalCost = null;
        }
    }
}
