package store.system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "products")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "product_name")
    private String name;

    private Integer price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "product_url")
    private String url;

    public Item(String itemId,String name, Integer price, String url, String imageUrl) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public Item() { }

}