catatan:
untuk databasenya bikin database dengan nama "simple_crud", terus buat struktur dengan nama "users" yang di dalamnya terdapat "id" (tipenya INT(11) AUTO_INCREMENT PRIMARY KEY), "name" (tipenya VARCHAR(100)), dan "email" (tipenya VARCHAR(100)),

atau bisa dengan cara berikut
buka phpmyadmin, pergi ke tabel SQL lalu paste command sql berikut terus pencet tombol GO untuk menjalankan perintah sql:


#========================================
CREATE DATABASE simple_crud;
USE simple_crud;

CREATE TABLE users (
  id INT(11) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100)
);
#========================================