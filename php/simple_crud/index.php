<?php
// Koneksi ke database
$conn = new mysqli('localhost', 'root', '', 'simple_crud');

// Periksa koneksi
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Tambah data
if (isset($_POST['submit'])) {
    $name = $_POST['name'];
    $email = $_POST['email'];

    // Query untuk menambahkan data ke database
    $sql = "INSERT INTO users (name, email) VALUES ('$name', '$email')";
    $conn->query($sql);
    header("Location: index.php");
}

// Ambil semua data dari tabel users
$result = $conn->query("SELECT * FROM users");

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple CRUD</title>
</head>
<body>
    <h1>CRUD Sederhana dengan PHP</h1>

    <!-- Form untuk menambahkan data -->
    <form action="" method="POST">
        <input type="text" name="name" placeholder="Nama" required>
        <input type="email" name="email" placeholder="Email" required>
        <button type="submit" name="submit">Tambah User</button>
    </form>

    <!-- Tabel untuk menampilkan data -->
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nama</th>
                <th>Email</th>
                <th>Aksi</th>
            </tr>
        </thead>
        <tbody>
            <?php while ($row = $result->fetch_assoc()): ?>
            <tr>
                <td><?= $row['id']; ?></td>
                <td><?= $row['name']; ?></td>
                <td><?= $row['email']; ?></td>
                <td>
                    <!-- Link ke halaman edit dan delete -->
                    <a href="edit.php?id=<?= $row['id']; ?>">Edit</a> |
                    <a href="delete.php?id=<?= $row['id']; ?>" onclick="return confirm('Yakin ingin menghapus?');">Hapus</a>
                </td>
            </tr>
            <?php endwhile; ?>
        </tbody>
    </table>
</body>
</html>
