<?php
// Koneksi ke database
$conn = new mysqli('localhost', 'root', '', 'simple_crud');

// Periksa koneksi
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Hapus data berdasarkan id
$id = $_GET['id'];
$conn->query("DELETE FROM users WHERE id=$id");

// Kembali ke halaman utama setelah data dihapus
header("Location: index.php");
?>
