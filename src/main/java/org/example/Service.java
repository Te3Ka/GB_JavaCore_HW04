package org.example;

import lombok.Data;
import org.example.exceptions.CustomerNotExistException;
import org.example.exceptions.ProductNotExistException;
import org.example.exceptions.QuantityException;

import java.util.ArrayList;
import java.util.List;

@Data

public class Service {
    private static List<Customer> customerList = new ArrayList<>();
    private static List<Product> productList = new ArrayList<>();
    private static List<Order> orderList = new ArrayList<>();

    public static List<Order> getOrderList() {
        return orderList;
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static Order makePurchase(String customerName, String product, String quantity)
            throws QuantityException, CustomerNotExistException, ProductNotExistException {
        Customer currentCustomer = null;
        for (Customer customerElement : customerList) {
            if (customerElement.getName().equals(customerName)) {
                currentCustomer = customerElement;
                break;
            }
        }

        Product currentProduct = null;
        for (Product productElement : productList) {
            if (productElement.getNameProduct().equals(product)) {
                currentProduct= productElement;
                break;
            }
        }

        int currentQuantity = Integer.parseInt(quantity);
        if (currentQuantity <= 0 || currentQuantity > 100) {
            throw new QuantityException(customerName, product);
        }
        if (currentCustomer == null) {
            throw new CustomerNotExistException("Customer has been found.");
        }
        if (currentProduct == null) {
            throw new ProductNotExistException("Product has been found");
        }
        return new Order(currentCustomer, currentProduct, currentQuantity);
    }

}
