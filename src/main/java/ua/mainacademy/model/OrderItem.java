package ua.mainacademy.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "order_items")
@Entity
public class OrderItem extends BaseEntity {

    @ManyToOne(targetEntity = Item.class)
    private Item item;

    @ManyToOne(targetEntity = Order.class)
    private Order order;

    @Column(nullable = false)
    private Integer amount;

    public OrderItem(Integer id, Item item, Order order, Integer amount) {
        super.setId(id);
        this.item = item;
        this.order = order;
        this.amount = amount;
    }
    
}
