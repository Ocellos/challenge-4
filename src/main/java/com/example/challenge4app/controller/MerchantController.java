package com.example.challenge4app.controller;

import com.example.challenge4app.model.Merchant;
import com.example.challenge4app.model.Product;
import com.example.challenge4app.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Controller
public class MerchantController {
    private final MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private String SEPARATOR = "=================================";

    public void showMerchantMenu() {
        System.out.println(SEPARATOR +
                "\nMenu Merchant\n" +
                SEPARATOR +
                "\nPilihan Menu:\n" +
                "1. Lihat Semua Merchant\n" +
                "2. Lihat Merchant yang Sedang Buka\n" +
                "3. Tambah Merchant\n" +
                "4. Tambah Product\n" +
                "5. Kembali ke Menu Utama");
        System.out.print("Pilih menu (1/2/3/4): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showAllMerchants();
                break;
            case 2:
                showOpenMerchants();
                break;
            case 3:
                addMerchant();
                break;
            case 4:
                addProduct();
                break;
            case 5:
                MainController mainMenu = new MainController();
                mainMenu.showMainMenu();
                break;

            default:
                System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                showMerchantMenu();
        }
    }

    private void showOpenMerchants() {
        List<Merchant> openMerchants = merchantService.getOpenMerchants();

        if (openMerchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang sedang buka.");
        } else {
            System.out.println(SEPARATOR + "\nMerchant yang Sedang Buka:");
            for (Merchant merchant : openMerchants) {
                System.out.println(merchant);
            }
        }

        // Kembali ke menu merchant
        System.out.println("Tekan enter untuk kembali...");
        scanner.nextLine(); // Membersihkan buffer
        scanner.nextLine(); // Menunggu enter dari pengguna
        showMerchantMenu();
    }


    private void showAllMerchants() {
        // TODO: Implementasi logika untuk menampilkan semua merchant
        List<Merchant> merchants = merchantService.getAllMerchants();

        if (merchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang tersedia.");
        } else {
            for (Merchant merchant : merchants) {
                System.out.println(merchant);
            }

            // Pilihan untuk mengedit atau menghapus merchant
            System.out.println("Pilih (masukkan ID merchant) merchant untuk mengubah status merchant :");
            System.out.println("atau ketik '0' untuk kembali :");
            try {
                Long selectedMerchantId = scanner.nextLong();

                if (selectedMerchantId == 0) {
                    showMerchantMenu();
                } else {
                    System.out.println(SEPARATOR +
                            "\nPilihan Menu:\n" +
                            "1. Ubah Status Merchant (Buka/Tutup)\n" +
                            "2. Kembali");
                    System.out.print("Pilih menu (1/2): ");
                    int option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            updateMerchantStatus(selectedMerchantId);
                            break;
                        case 2:
                            showMerchantMenu();
                            break;
                        default:
                            System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                            showAllMerchants();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak sesuai. Masukkan angka.");
                scanner.nextLine();
                showAllMerchants();
            }
        }
    }


    private void addMerchant() {
        System.out.print(SEPARATOR +
                "\nTambah Merchant Baru\n" +
                SEPARATOR +
                "\nMasukkan Nama Merchant: ");
        String name = scanner.next();
        System.out.print("Masukkan Lokasi Merchant: ");
        String location = scanner.next();

        boolean isOpen = false;
        System.out.print("Apakah Merchant Buka (yes/no): ");
        String inputOpen = scanner.next();

        if (inputOpen.equalsIgnoreCase("yes")) {
            isOpen = true;
        } else if (inputOpen.equalsIgnoreCase("no")) {
            isOpen = false;
        }

        Merchant newMerchant = new Merchant(name, location, isOpen);
        merchantService.addMerchant(newMerchant);
        System.out.println("Merchant baru berhasil ditambahkan!");

        // Kembali ke Menu Merchant
        showMerchantMenu();
    }

    private void updateMerchantStatus(Long merchantId) {

        boolean isOpen = false;

        System.out.print(SEPARATOR +
                "\nUbah Status Merchant\n" +
                SEPARATOR +
                "\nApakah Anda ingin membuka atau menutup merchant (yes/no): ");

        String inputOpen = scanner.next();

        if (inputOpen.equalsIgnoreCase("yes")){
            isOpen = true;
        }else {
            isOpen = false;
        }

        merchantService.updateMerchantStatus(merchantId, isOpen);
        System.out.println("Status merchant berhasil diubah!");

        // Kembali ke Menu Merchant
        showAllMerchants();
    }

    private void addProduct() {
        System.out.print(SEPARATOR +
                "\nTambah Produk Baru\n" +
                SEPARATOR +
                "\nPilih Merchant Code:\n");

        // Display available merchants for the user to choose from
        List<Merchant> merchants = merchantService.getAllMerchants();
        if (merchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang tersedia.");
            showMerchantMenu();
            return;
        }

        for (Merchant merchant : merchants) {
            System.out.println(merchant.getMerchantCode() + ". " + merchant.getName());
        }

        try {
            System.out.print("Masukkan Merchant Code: ");
            Long selectedMerchantCode = scanner.nextLong();

            // Validate if the entered merchant code exists
            Merchant selectedMerchant = merchantService.getMerchantByCode(selectedMerchantCode);
            if (selectedMerchant == null) {
                System.out.println("Merchant dengan Merchant Code " + selectedMerchantCode + " tidak ditemukan.");
                showMerchantMenu();
                return;
            }

            // Collect product details
            System.out.print("Masukkan Nama Produk: ");
            String productName = scanner.next();
            System.out.print("Masukkan Harga Produk: ");
            double productPrice = scanner.nextDouble();

            // Create and add the new product
            Product newProduct = new Product(productName, productPrice, selectedMerchant);
            merchantService.addProduct(selectedMerchantCode, newProduct);

            System.out.println("Produk baru berhasil ditambahkan!");

        } catch (InputMismatchException e) {
            System.out.println("Input tidak sesuai. Masukkan angka.");
            scanner.nextLine(); // Clear the buffer
        }

        // Return to the Menu Merchant
        showMerchantMenu();
    }

}
