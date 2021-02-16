package ua.mainacademy.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@ToString
@Table(name = "items")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {

    @Column(name = "iem_code", nullable = false)
    private String itemCode;

    @Column(nullable = false)
    private String name;

    private Integer price;

    @Column(name = "init_price")
    private Integer initPrice;

    public Item(Integer id, String itemCode, String name, Integer price, Integer initPrice) {
        super.setId(id);
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.initPrice = initPrice;
    }
}
