package store.system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer id;

    @Column(name = "time")
    private Long creationTime;

    @Column(name = "closed")
    private Boolean closed;

    @ManyToOne(targetEntity = User.class)
    @Column (name = "user")
    private User user;

    public Cart() { }

}