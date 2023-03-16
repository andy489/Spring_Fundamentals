package com.reseller.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class OfferDto {

    private Long id;

    private String description;

    private BigDecimal price;

    private String conditionName;

    private String sellerUsername;

}
