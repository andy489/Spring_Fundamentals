package com.reseller.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Table(name = "offers")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class OfferEntity extends BaseEntity {

    @Column(nullable = false)
    @Lob
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private ConditionEntity condition;

    @ManyToOne
    private UserEntity owner;

    @ManyToOne
    private UserEntity buyer;

    @Override
    public String toString() {
        return "OfferEntity{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", condition=" + condition +
                ", ownerUser=" + owner +
                ", buyerUser=" + buyer +
                '}';
    }
}
