package org.example;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.exceptions.CustomerNotExistException;
import org.example.exceptions.ProductNotExistException;
import org.example.exceptions.QuantityException;

import javax.management.ObjectName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Data
public class Main {
    public static void main(String[] args)
            throws CustomerNotExistException, ProductNotExistException, QuantityException {
        System.out.println("Online Shop");

        Service.getCustomerList().add(new Customer("Ivanov Ivan",
                LocalDate.of(1990, 1, 1),
                "9998887766", Gender.male));
        Service.getCustomerList().add(new Customer("Ivanova Darya",
                LocalDate.of(1991, 8, 5),
                "9998887755", Gender.female));
        Service.getCustomerList().add(new Customer("Sergeev Ivan",
                LocalDate.of(1992, 2, 6),
                "9998887744", Gender.male));
        Service.getCustomerList().add(new Customer("Petrova Anastasia",
                LocalDate.of(1989, 5, 7),
                "9998887733", Gender.female));
        Service.getCustomerList().add(new Customer("Ivanov Petr",
                LocalDate.of(1988, 6, 12),
                "9998887722", Gender.male));
        Service.getCustomerList().add(new Customer("Sidorov Ivan",
                LocalDate.of(1995, 3, 13),
                "9998887711", Gender.male));
        Service.getCustomerList().add(new Customer("Hidden Name",
                LocalDate.of(2000, 12, 31),
                "9998887711", Gender.notSelected));

        Service.getProductList().add(new Product("Apple", BigDecimal.valueOf(10L)));
        Service.getProductList().add(new Product("Banana", BigDecimal.valueOf(20L)));
        Service.getProductList().add(new Product("Orange", BigDecimal.valueOf(15L)));
        Service.getProductList().add(new Product("Kiwi", BigDecimal.valueOf(30L)));
        Service.getProductList().add(new Product("Onion", BigDecimal.valueOf(3L)));

//        Service.getOrderList().add(new Order(customers.get(0), products.get(0), 5));
//        Service.getOrderList().add(new Order(customers.get(1), products.get(3), 10));
//        Service.getOrderList().add(new Order(customers.get(2), products.get(1), 7));
//        Service.getOrderList().add(new Order(customers.get(3), products.get(2), 2));
        try {
            Order order = Service.makePurchase("Ivanov Petr", "Orange", "5");
            Service.getOrderList().add(order);
            System.out.println(Service.getOrderList());

            Order order2 = Service.makePurchase("Ivanova Darya", "Apple", "2");
            Service.getOrderList().add(order2);
            System.out.println(Service.getOrderList());

            Order order3 = Service.makePurchase("Sergeev Ivan", "Onion", "30");
            Service.getOrderList().add(order3);
            System.out.println(Service.getOrderList());

            Order order4 = Service.makePurchase("Petrova Anastasia", "Kiwi", "5");
            Service.getOrderList().add(order4);
            System.out.println(Service.getOrderList());
//            order = Service.makePurchase("John Dow", "Apple", "10");
//            System.out.println(order);
//            order = Service.makePurchase("Sidorov Ivan", "Pineapple", "4");
//            System.out.println(order);
//            order = Service.makePurchase("Sidorov Ivan", "Kiwi", "-1");
//            System.out.println(order);
        } catch (QuantityException ex) {
            Service.getOrderList()
                    .add(Service.makePurchase(ex.getCustomer(), ex.getProduct(), "1"));
        } catch (ProductNotExistException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            throw ex;
        }

        System.out.println(Service.getOrderList());
        System.out.println(Service.getOrderList().size() + " orders received ");

        System.out.println(congratulations(Service.getCustomerList()));
    }

    public static String congratulations(List<Customer> customers) {
        LocalDate testDate = LocalDate.of(2024, 12, 31);
        List<Customer> customersToCongratulateBirthdate = new ArrayList<>();
        List<Customer> customersToCongratulateNewYear = new ArrayList<>();
        List<Customer> customersToCongratulateDefendersFatherland = new ArrayList<>();
        List<Customer> customersToCongratulateInternationalWomensDay = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.getDateOfBirth().getMonthValue() == testDate.getMonthValue()
                    && customer.getDateOfBirth().getDayOfMonth() == testDate.getDayOfMonth()) {
                customersToCongratulateBirthdate.add(customer);
            }
            if (testDate.getMonthValue() == Holidays.NEW_YEAR.getMonth()
                        && testDate.getDayOfMonth() == Holidays.NEW_YEAR.getDay()) {
                customersToCongratulateNewYear.add(customer);
            }
            if (testDate.getMonthValue() == Holidays.DEFENDER_FATHERLAND_DAY.getMonth()
                    && testDate.getDayOfMonth() == Holidays.DEFENDER_FATHERLAND_DAY.getDay()
                    && customer.getGender() == Gender.male) {
                customersToCongratulateDefendersFatherland.add(customer);
            }
            if (testDate.getMonthValue() == Holidays.INTERNATIONAL_WOMENS_DAY.getMonth()
                    && testDate.getDayOfMonth() == Holidays.INTERNATIONAL_WOMENS_DAY.getDay()
                    && customer.getGender() == Gender.female) {
                customersToCongratulateInternationalWomensDay.add(customer);
            }

        }
        if (!customersToCongratulateBirthdate.isEmpty()) {
            coungratulateToHoliday(customersToCongratulateBirthdate, " on your birthday!");
        }

        if (!customersToCongratulateNewYear.isEmpty()) {
            coungratulateToHoliday(customersToCongratulateNewYear, " on the New Year!");
        } else if (!customersToCongratulateDefendersFatherland.isEmpty()) {
            coungratulateToHoliday(customersToCongratulateDefendersFatherland, " on Defender of the Fatherland Day!");
        } else if (!customersToCongratulateInternationalWomensDay.isEmpty()) {
            coungratulateToHoliday(customersToCongratulateInternationalWomensDay, " on International Women's Day!");
        } else {
            return "Today is not a holiday";
        }
        return "";
    }

    public static void coungratulateToHoliday(List<Customer> customers, String message) {
        for (Customer customer : customers) {
            System.out.println(STR."Congratulations \{customer.getName()}\{message}");
        }
    }
}