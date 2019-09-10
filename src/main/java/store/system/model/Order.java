package store.system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @ManyToOne(targetEntity = Item.class)
    private Item item;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(targetEntity = Cart.class)
    private Cart cart;

    public Order() { }
}
