
package net.gourram.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.gourram.billingservice.model.Customer;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Bill {

    @Id
    @GeneratedValue
    private Long id;

    private Date billingDate;

    private Long customerId;

    @OneToMany(mappedBy = "bill")
    @JsonManagedReference
    private List<ProductItem> productItems;
    @Transient
    private Customer customer;
}
