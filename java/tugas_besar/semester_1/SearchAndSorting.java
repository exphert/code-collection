/*
 * Nama   : Surya Hadini Subagja
 * NPM    : 24552011357
 * Judul  : Tugas Besar UAS Algoritma & Pemrograman Semester 1
 * Materi : Sorting (Bubble Sort) & Searching (Sequential Search)
 * Tanggal: 23/1/2025
 */


import java.util.Arrays;
import java.util.Scanner;

public class SearchAndSorting {

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        // Definisi Scanner untuk menerima input dari pengguna
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan data array (dipisah menggunakan spasi): ");

        // Input: Barisan angka yang dipisahkan dengan spasi (contoh: "11 22 33 44 55")
        // Split: Memisahkan input menjadi array string (contoh: ["11", "22", "33", "44", "55"])
        // mapToInt: Mengonversi array string menjadi array integer (contoh: [11, 22, 33, 44, 55])
        int[] data = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println("Data Mentah: " + Arrays.toString(data));

        // Mengurutkan data menggunakan Bubble Sort
        bubbleSort(data);
        System.out.println("Data Sorting (Bubble Sort): " + Arrays.toString(data));

        // Mencari data menggunakan Sequential Search
        System.out.print("Masukkan data yang ingin dicari: ");
        int query = scanner.nextInt();
        int result = sequentialSearch(data, query);

        // Menampilkan hasil pencarian
        System.out.println(result != -1
                ? "Data ada di index: " + result
                : "Data tidak ada");
    }

    // Bubble Sort: Mengurutkan array secara ascending
    public static void bubbleSort(int[] data) {
        // Iterasi dari elemen pertama hingga elemen ke-n-1
        for (int i = 0; i < data.length - 1; i++) {
            // Iterasi dalam setiap pasangan elemen yang belum tersortir
            for (int j = 0; j < data.length - i - 1; j++) {
                // Jika elemen saat ini lebih besar dari elemen berikutnya, lakukan swap
                if (data[j] > data[j + 1]) {
                    // Menyimpan elemen saat ini dalam variabel sementara
                    int temp = data[j];
                    // Menggantikan elemen saat ini dengan elemen berikutnya
                    data[j] = data[j + 1];
                    // Mengembalikan elemen berikutnya ke elemen saat ini (sebelumnya)
                    data[j + 1] = temp;
                }
            }
        }
    }

    // Sequential Search: Mencari posisi elemen dalam array
    public static int sequentialSearch(int[] data, int query) {
        // Iterasi setiap elemen dalam array
        for (int i = 0; i < data.length; i++) {
            // Jika elemen saat ini sama dengan query, kembalikan index
            if (data[i] == query) {
                return i;
            }
        }
        // Jika elemen tidak ditemukan, kembalikan -1
        return -1;
    }
}

/*
==============================================
          PENJELASAN DAN ANALISIS
==============================================

**Bubble Sort**
- Base Case:
  - Data sudah tertata (ascending): Tidak ada swap yang diperlukan.
  - Data berukuran kecil: Performa tetap optimal karena iterasi tidak terlalu banyak.

- Worst Case:
  - Data tertata secara terbalik (descending): Membutuhkan iterasi maksimum karena semua elemen perlu diswap.
  - Data berukuran besar: Kompleksitas waktu menjadi O(n^2), sehingga proses sorting lebih lambat.

**Sequential Search**
- Base Case:
  - Elemen yang dicari berada di index pertama: Hanya membutuhkan satu perbandingan.
  - Data berukuran kecil: Tidak banyak iterasi yang dilakukan.

- Worst Case:
  - Elemen yang dicari berada di index terakhir: Harus melalui semua elemen dalam array.
  - Elemen tidak ditemukan: Tetap harus memeriksa semua elemen dalam array.
  - Data berukuran besar: Kompleksitas waktu menjadi O(n).

**Keputusan Penggunaan**
Karena kedua algoritma/metode tersebut cocok untuk orang yang baru belajar pemrograman, dan juga mudah untuk dipahami

**Contoh Input dan Output**
Input:
Masukkan data array (dipisah menggunakan spasi): 44 33 55 11 22
Data Mentah: [44, 33, 55, 11, 22]
Data Sorting (Bubble Sort): [11, 22, 33, 44, 55]
Masukkan data yang ingin dicari: 33

Output:
Data ada di index: 2
 */
