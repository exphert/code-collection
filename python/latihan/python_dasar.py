# Author: Surya H.S [Teknik Informatika]
# Materi: Belajar bahasa python dari yang paling dasar
# Github: https://github.com/erozx


# 1. Tipe Data =========================================
# Di Python, tipe data adalah jenis nilai yang bisa disimpan di dalam variabel.
# Integer digunakan untuk angka bulat, Float untuk angka desimal, String untuk teks, dan Boolean untuk nilai True/False.
umur = 21  # integer
tinggi = 1.75  # float
nama = "Ali"  # string
is_student = True  # boolean

# Catatan: Di Python, kamu tidak perlu menyatakan tipe variabel secara eksplisit, karena Python menggunakan tipe dinamis.
# Contoh: Kamu bisa mengganti tipe 'umur' menjadi string, dan Python akan langsung menyesuaikannya tanpa deklarasi tambahan.

# 2. Variabel =========================================
# Variabel digunakan untuk menyimpan data yang bisa dipakai berulang kali. Misalnya, nama, prodi, dan angkatan.

nama = "Hadi"
prodi = "Informatika"
angkatan = 24

# Penggunaan variabel:
# Fungsi print() digunakan untuk mencetak output di layar. Dalam contoh ini, kita ingin mencetak kalimat yang berisi variabel.
# Saat mencetak variabel angka seperti 'angkatan', kita harus mengubahnya menjadi string dengan fungsi str().
# Kenapa? Karena kita tidak bisa menggabungkan langsung antara string (teks) dengan number (angka) tanpa mengonversinya.
print(f"\nContoh penggunaan `variabel`")
print("Halo, saya " + nama + " dari " + prodi + " angkatan " + str(angkatan))

# Catatan: Fungsi str() digunakan untuk mengonversi tipe data non-string menjadi string agar bisa digabung dengan teks lain.

# Contoh penggunaan variabel dengan f-string
print(f"\nContoh penggunaan `variabel` dengan `f-string`")
print(f"Halo, saya {nama} dari {prodi} angkatan {angkatan}")

# Catatan:
# F-string adalah cara praktis untuk menggabungkan variabel langsung ke dalam string.
# Kita cukup menuliskan huruf 'f' di depan tanda kutip, dan menggunakan tanda kurung kurawal {} untuk menyisipkan variabel.
# Tidak perlu lagi menggunakan fungsi str() untuk mengonversi angka menjadi string, karena f-string otomatis menangani itu.

# 3. Array (List) =========================================
# List adalah tipe data yang bisa menyimpan beberapa nilai sekaligus. Nilai ini bisa diakses menggunakan indeks.
# Indeks pertama di dalam list dimulai dari 0, jadi elemen pertama punya indeks 0, elemen kedua indeks 1, dan seterusnya.
nama_mahasiswa = ["Ali", "Budi", "Citra"]

# Untuk mengakses elemen tertentu, kita menggunakan tanda kurung siku [] dan menuliskan indeks elemen yang ingin diambil.
print(f"\nContoh penggunaan `array`")
print(nama_mahasiswa[0])  # Output: Ali

# Catatan: [0] artinya kita meminta elemen pertama dari list. Python menghitung indeks dari 0, bukan dari 1, jadi indeks 0 adalah elemen pertama.

# Kita juga bisa menambahkan elemen baru ke dalam list menggunakan metode append().
nama_mahasiswa.append("Dewi")  # Menambahkan 'Dewi' ke dalam list
print(nama_mahasiswa)

# 4. Percabangan (if-elif-else) =========================================
# Percabangan digunakan untuk mengambil keputusan berdasarkan kondisi tertentu.
# Python menggunakan if untuk memeriksa kondisi. elif (else if) digunakan jika kondisi pertama salah, dan else menangani semua kondisi lainnya.
print(f"\nContoh `percabangan`")

umur = 18

if umur > 20:
    print("Kamu sudah dewasa")
elif umur == 18:
    print("Kamu baru dewasa")
else:
    print("Kamu masih muda")

# Catatan: Dalam percabangan, kondisi diperiksa dari atas ke bawah. Jika kondisi pertama (umur > 20) benar, maka blok if akan dijalankan, jika tidak maka elif atau else akan dijalankan.

# 5. Perulangan (for dan while) =========================================
# Perulangan digunakan untuk menjalankan kode berulang kali.
# Perulangan for biasanya digunakan untuk iterasi (melintasi) nilai dalam range (rentang angka) atau list.

# For loop dengan range
print(f"\nContoh `perulangan`")
for i in range(5):
    print("Angka:", i)

# Catatan: range(5) menghasilkan rentang angka dari 0 hingga 4 (5 angka, tetapi dimulai dari 0). Setiap iterasi, variabel i akan mengambil nilai dari rentang tersebut.

# While loop digunakan jika kita ingin menjalankan kode sampai suatu kondisi terpenuhi.
x = 0
while x < 5:
    print("Nilai x:", x)
    x += 1  # Jangan lupa untuk mengubah nilai x agar loop tidak berjalan tanpa henti.

# Catatan: While akan terus berjalan selama kondisinya benar (True). Ketika x tidak lagi kurang dari 5, loop berhenti.

# 6. Print Output =========================================
# Fungsi print() digunakan untuk mencetak teks atau hasil perhitungan ke layar.
print(f"\nContoh fungsi `print()`")
print("Selamat datang di belajar Python!")

# 7. Input Output =========================================
# Fungsi input() digunakan untuk mengambil masukan dari pengguna.
# Masukan dari input() selalu dalam bentuk string, jadi jika kita ingin mengubahnya menjadi angka, kita bisa menggunakan fungsi int() atau float().
print(f"\nContoh fungsi `input()`")
nama = input("Masukkan nama Anda: ")
print("Halo, " + nama + "!")

# Catatan: input() mengembalikan nilai dalam bentuk string, jadi jika kita meminta input angka dan ingin melakukan perhitungan, kita perlu mengonversinya ke tipe data angka (int/float).

# 8. Function =========================================
# Fungsi adalah blok kode yang dirancang untuk menjalankan tugas tertentu.
# Fungsi membantu dalam mengelompokkan kode agar lebih terorganisir dan bisa dipanggil berulang kali tanpa menulis ulang kode yang sama.
def sapa_pengguna(nama):
    print("Halo, " + nama)

print(f"\nContoh penggunaan `function`")
sapa_pengguna("Ali")  # Output: Halo, Ali

# Catatan: Di dalam fungsi, kita bisa menggunakan parameter untuk menerima input saat fungsi dipanggil. Di contoh ini, 'nama' adalah parameter.

# 9. Class =========================================
# Class adalah cetak biru (blueprint) untuk membuat objek.
# Objek adalah contoh nyata dari class, dan memiliki atribut (data) serta method (fungsi).
class Mahasiswa:
    def __init__(self, nama, prodi):
        self.nama = nama
        self.prodi = prodi

    def tampilkan_info(self):
        print(f"Nama: {self.nama}, Prodi: {self.prodi}")

# Membuat objek dari class Mahasiswa
print(f"\nContoh penggunaan `class`")
mhs1 = Mahasiswa("Ali", "Informatika")
mhs1.tampilkan_info()  # Output: Nama: Ali, Prodi: Informatika

# Catatan: Fungsi __init__ adalah konstruktor yang digunakan untuk menginisialisasi objek saat dibuat.
# Self adalah parameter pertama dari semua method di class, dan digunakan untuk merujuk ke objek itu sendiri.

# 10. Program Membuat Pola =========================================

# Membuat pola persegi menggunakan for loop.
print(f"\nContoh program untuk membuat `pola persegi`")
for i in range(5):
    for j in range(5):
        print("*", end=" ")
    print()

# Membuat pola segitiga sederhana
print(f"\nContoh program untuk membuat `pola segitiga sederhana`")
for i in range(5):
    for j in range(i+1):
        print("*", end=" ")
    print()

# Membuat pola segitiga simetris
print(f"\nContoh program untuk membuat `pola segitiga simetris`")
tinggi = 5

for i in range(tinggi):
    # Cetak spasi untuk memusatkan bintang
    print(" " * (tinggi - i - 1), end="")

    # Cetak bintang dengan jumlah ganjil (2*i + 1)
    print("*" * (2 * i + 1))

# Catatan:
# 1. Untuk pola persegi, kita menggunakan dua loop for:
#    - Loop pertama (luar) mengontrol baris.
#    - Loop kedua (dalam) mengontrol kolom dan mencetak bintang di setiap kolom.
#    - Kita menggunakan `end=" "` untuk menjaga agar bintang dicetak dalam satu baris sebelum baris baru dimulai dengan `print()`.

# 2. Untuk pola segitiga sederhana:
#    - Loop luar mengontrol jumlah baris (dari 0 hingga 4).
#    - Loop dalam mengontrol jumlah bintang yang dicetak, yang bertambah setiap kali `i` bertambah.
#    - Di baris pertama, hanya satu bintang dicetak, lalu bertambah sesuai dengan nilai `i`.

# 3. Untuk pola segitiga simetris:
#    - Pertama, kita mencetak sejumlah spasi untuk memusatkan bintang. Jumlah spasi di setiap baris berkurang seiring bertambahnya nilai `i`.
#    - Kemudian, kita mencetak bintang dengan jumlah ganjil yang dapat dihitung menggunakan rumus (2 * i + 1).
#    - Hasilnya adalah segitiga yang berpusat dengan jumlah bintang bertambah di setiap baris.

# 11. Looping Array dengan Foreach (for-in) =========================================
# Melintasi elemen dalam list menggunakan for-in (konsep seperti foreach di bahasa lain)
print(f"\nContoh program untuk melakukan `perulangan array`")
nama_mahasiswa = ["Ali", "Budi", "Citra"]

for nama in nama_mahasiswa:
    print(nama)

# Catatan: Python menggunakan for-in untuk melintasi (iterate) elemen-elemen dalam list. Setiap elemen dalam list akan diakses satu per satu.
