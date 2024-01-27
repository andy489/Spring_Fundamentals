package com.battleships.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Entity(name = "ships")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
public class ShipEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer health;

    @Column(nullable = false)
    private Integer power;

    @Column(nullable = false)
    private Date created;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private UserEntity owner;

    public String stat() {
        String fmt = "|  %s  |  %s  |  %s  |";
        return String.format(fmt, name, health, power);
    }

}
