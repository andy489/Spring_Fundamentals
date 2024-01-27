package com.reseller.model.dto;

import com.reseller.model.entity.enums.ConditionName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class OfferAddDto {

    @NotBlank
    @Size(min = 2, max = 50, message = "description must be between 2 and 50 characters")
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private ConditionName conditionName;

}
