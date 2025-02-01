<?php
// Koneksi ke database
$conn = new mysqli('localhost', 'root', '', 'simple_crud');

// Periksa koneksi
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Ambil data berdasarkan id
$id = $_GET['id'];
$result = $conn->query("SELECT * FROM users WHERE id=$id");
$user = $result->fetch_assoc();

// Update data
if (isset($_POST['update'])) {
    $name = $_POST['name'];
    $email = $_POST['email'];

    // Query untuk update data
    $sql = "UPDATE users SET name='$name', email='$email' WHERE id=$id";
    $conn->query($sql);
    header("Location: index.php");
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
</head>
<body>
    <h1>Edit User</h1>

    <!-- Form untuk mengedit data -->
    <form action="" method="POST">
        <input type="text" name="name" value="<?= $user['name']; ?>" required>
        <input type="email" name="email" value="<?= $user['email']; ?>" required>
        <button type="submit" name="update">Update</button>
    </form>
</body>
</html>
