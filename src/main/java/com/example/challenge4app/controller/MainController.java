package com.example.challenge4app.controller;


import com.example.challenge4app.model.User;
import com.example.challenge4app.service.MerchantService;
import com.example.challenge4app.service.ProductService;
import com.example.challenge4app.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Scanner;

@Component
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private ProductService productService;

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private String SEPARATOR = "=================================";
    @PostConstruct
    public void init() throws ParseException {
        ShowLoginAplication();
    }

    public void ShowLoginAplication() {
        System.out.println(SEPARATOR +
                "\nSELAMAT DATANG DI BINAR FOOD\n" +
                SEPARATOR +
                " " +
                "\nSILAHKAN LOGIN TERLEBIH DAHULU");
        System.out.println(" ");
        System.out.println("1. Login Untuk Masuk");
        System.out.println("2. Daftar Akun");
        System.out.print("=>");
        int pilihan = scanner.nextInt();
        if(pilihan == 1){
            loginUser();
        } if(pilihan == 2) {
            registerAccount();
        }
        System.exit(0);
    }
    public void registerAccount() {
        System.out.print(SEPARATOR+
                "\nMasukan Email\t\t: ");
        String inputEmail = scanner.next();
        System.out.print("Masukan User Name \t: ");
        String inputUsername = scanner.next();
        System.out.print("Masukan Password \t: ");
        String inputPassword = scanner.next();

        User newUser = new User(inputUsername, inputEmail, inputPassword);

        userService.addUser(newUser);

        System.out.println("Pendaftaran berhasil! Berikut informasi akun Anda:");
        System.out.println("User ID     : " + newUser.getId());
        System.out.println("Username    : " + newUser.getUsername());
        System.out.println("Email       : " + newUser.getEmail());
        System.out.println("Password    : " + newUser.getPassword());

        System.out.println("Masukan angka 1 untuk kembali ke halaman Utama");
        System.out.print("=>");
        int pilihan = scanner.nextInt();

        if (pilihan == 1) {
            ShowLoginAplication();
        }

    }
    public void loginUser() {
        System.out.println(SEPARATOR);
        System.out.println("LOGIN KE BINARFUD");
        System.out.print(SEPARATOR +
                "\nMasukkan User Name \t: ");
        String inputUsername = scanner.next();
        System.out.print("Masukkan Password \t: ");
        String inputPassword = scanner.next();

        boolean isAuthenticated = authenticateUser(inputUsername, inputPassword);

        if (isAuthenticated) {
            System.out.println("Login berhasil!");
            showMainMenu();
        } else {
            System.out.println("Username atau password salah. Silakan coba lagi.");
            loginUser();
        }
    }

    public void showMainMenu() {
        System.out.println(SEPARATOR +
                "\nSELAMAT DATANG DI BINAR FOOD\n" +
                SEPARATOR +
                "\nPilihan Menu:\n" +
                "1. Lihat Semua Produk\n" +
                "2. Menu Merchant\n" +
                "3. Keluar");
        System.out.print("Pilih menu (1/2/3): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                ProductController productMenu = new ProductController(productService);
                 productMenu.showAllProducts();
                break;
            case 2:
                MerchantController mainMenu = new MerchantController(merchantService);
                mainMenu.showMerchantMenu();
                break;
            case 3:
                System.out.println("Terima kasih. Sampai jumpa!");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                showMainMenu();
        }
    }

    private boolean authenticateUser(String username, String password) {
        // TODO: Implementasi autentikasi menggunakan UserService
        // Contoh sederhana: Memeriksa apakah username dan password sesuai dengan data di database
        User user = userService.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }


}