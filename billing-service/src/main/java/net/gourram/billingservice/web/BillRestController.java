
package net.gourram.billingservice.web;

import net.gourram.billingservice.entities.Bill;
import net.gourram.billingservice.feign.CustomerServiceRestClient;
import net.gourram.billingservice.feign.InventoryServiceRestClient;
import net.gourram.billingservice.model.Customer;
import net.gourram.billingservice.repository.BillRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class BillRestController {

    private final BillRepository billRepository;
    private final CustomerServiceRestClient customerServiceRestClient;
    private final InventoryServiceRestClient inventoryServiceRestClient;

    public BillRestController(
            BillRepository billRepository,
            CustomerServiceRestClient customerServiceRestClient, InventoryServiceRestClient inventoryServiceRestClient) {
        this.billRepository = billRepository;
        this.customerServiceRestClient = customerServiceRestClient;
        this.inventoryServiceRestClient = inventoryServiceRestClient;
    }

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Bill not found: " + id));

        try {
            Customer customer =
                    customerServiceRestClient.findCustomerById(
                            bill.getCustomerId());

            bill.setCustomer(customer);
            bill.getProductItems().forEach(productI -> {
                productI.setProduct(
                        inventoryServiceRestClient.getProduct(productI.getProductId()));

            });

            return bill;

        } catch (Exception e) {
            e.printStackTrace();

            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Error calling customer-service: " + e.getMessage(),
                    e);
        }
    }
}
