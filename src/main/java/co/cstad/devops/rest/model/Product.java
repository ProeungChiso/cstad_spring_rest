package co.cstad.devops.rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String name;
    private Double price;
    private Integer qty;
    private LocalDateTime importedDate;
    private Boolean status;

    @ManyToOne
    private Category category;

}
