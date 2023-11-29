package com.example.challenge4app.controller;

import com.example.challenge4app.model.Product;
import com.example.challenge4app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private String SEPARATOR = "=================================";

    public void showAllProducts() {
        System.out.println(SEPARATOR);
        System.out.println("LIST SEMUA PRODUK");
        System.out.println(SEPARATOR);

        Iterable<Product> productsIterable = productService.getAllProducts();
        List<Product> products = new ArrayList<>();

        for (Product product : productsIterable) {
            products.add(product);
        }

        if (products.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia.");
        } else {
            for (Product product : products) {
                System.out.println("ID Produk: " + product.getProductCode());
                System.out.println("Nama: " + product.getName());
                System.out.println("Harga: " + product.getPrice());
                System.out.println(SEPARATOR);
            }
        }


        System.out.println("Masukan angka 1 untuk kembali ke halaman Utama");
        System.out.print("=>");
        int pilihan = scanner.nextInt();

        if (pilihan == 1) {
            MainController mainMenu = new MainController();
            mainMenu.showMainMenu();
        }
    }
}
