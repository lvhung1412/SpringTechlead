package com.example.springBootTechlead.service.core2;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BT12 {
    @Getter
    @Setter
    public static class Product{
        public String name;
        public String id;
        public Double price;
        public Date dateOfManufacture;

        public Product(String name, String id, Double price, Date dateOfManufacture) {
            this.name = name;
            this.id = id;
            this.price = price;
            this.dateOfManufacture = dateOfManufacture;
        }

        @Override
        public String toString() {
            return "Product [name=" + name + ", id=" + id + ", price=" + price + ", dateOfManufacture="
                    + dateOfManufacture + "]";
        }
    }

    List<Product> products = new ArrayList<>();
    public BT12(){
        products.add(new Product("Laptop", "1", 1200.0, Date.valueOf("2000-11-11")));
        products.add(new Product("Smartphone","2", 800.0, Date.valueOf("2001-12-12")));
        products.add(new Product("Iphone","4", 900.0, Date.valueOf("2003-12-10")));
        products.add(new Product("Android","3", 1100.0, Date.valueOf("2000-09-09")));
    }

    public void add(Product product){
        products.add(product);
    }

    public List<Product> sortByName(){
        Collections.sort(products, Comparator.comparing(p -> p.name));
        return products;
    }

    public List<Product> sortByPrice(){
        Collections.sort(products, Comparator.comparingDouble(p->p.price));
        return products;
    }

    public List<Product> sortByDateOfManufacture(){
        Collections.sort(products, Comparator.comparing(p-> p.dateOfManufacture));
        return products;
    }

    public List<Product> sortByPriceAndDateOfManufacture(){
        Collections.sort(products, Comparator
                .comparing((Product p) -> p.price)
                .thenComparing((Product p) -> p.dateOfManufacture));
        return products;
    }

}
