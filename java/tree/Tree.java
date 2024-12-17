/*
 * Original Code by Surya H.S <github.com/erozx>
 * Tree Structure Visualization with Java
 */

import java.io.*;
import java.util.*;

public class Tree {

    public static void main(String[] args) {
        // Loop utama untuk menampilkan menu
        while (true) {
            Utils.displayMenu(" Menu Utama", new String[]{"Buat Struktur Tree", "Load Struktur Tree", "0,Keluar"}, true);

            try {
                int pilihan = Integer.parseInt(Utils.input("Pilih opsi"));

                switch (pilihan) {
                    case 1 -> StructureTree(false, null); // Buat struktur tree baru
                    case 2 -> loadTreeMenu(); // Memuat struktur tree dari file
                    case 0 -> Utils.exitProgram(); // Keluar dari program
                    default -> Utils.println("Opsi tidak valid. Coba lagi.");
                }
            } catch (NumberFormatException e) {
                Utils.println("Input tidak valid. Harap masukkan angka.");
            }
        }
    }

    public static void loadTreeMenu() {
        String[] treeList;
        int loadTree = -1;

        do {
            treeList = Utils.directory(new File("data")); // Mengambil daftar file tree
            String[] menuOptions = createMenuOptions(treeList); // Membuat opsi menu dari file tree
            Utils.displayMenu(" List Tree", menuOptions, true);

            try {
                loadTree = Integer.parseInt(Utils.input("Pilih")); // Meminta pilihan dari pengguna
            } catch (NumberFormatException e) {}
        } while (!Utils.isValidSelection(loadTree, treeList.length)); // Validasi pilihan

        if (loadTree > 0) {
            File file = new File("data/" + treeList[loadTree - 1]);
            List<String> lines = Utils.readLinesFromFile(file); // Membaca baris dari file
            StructureTree(true, lines); // Membuat struktur tree dari data yang dimuat
        }
    }

    public static String[] createMenuOptions(String[] treeList) {
        if (treeList.length == 0) {
            return new String[]{"0,Kembali"}; // Jika tidak ada tree, hanya tampilkan opsi kembali
        }

        String[] options = new String[treeList.length + 1];
        System.arraycopy(treeList, 0, options, 0, treeList.length); // Menyalin daftar tree ke opsi
        options[treeList.length] = "0,Kembali"; // Menambahkan opsi kembali
        return options;
    }

    public static void StructureTree(Boolean isLoad, List<String> list) {
        Utils.clear(); // Menghapus tampilan

        TreeNode root;
        String rootTree;

        if (isLoad) {
            rootTree = loadRootAndBuildTree(list); // Memuat root tree dari data
            root = new TreeNode(rootTree); // Membuat node root

            // Memproses semua anak dari root
            for (int idx = 1; idx < list.size(); idx++) {
                String[] parts = list.get(idx).substring(rootTree.length() + 1).split(">");
                Utils.addNode(root, parts); // Menambahkan node anak
            }
        } else {
            rootTree = promptForRootTree(); // Meminta pengguna memasukkan nama root tree
            if (rootTree == null) {
                return; // Keluar jika pengguna membatalkan
            }
            root = new TreeNode(rootTree); // Membuat node root
        }

        treeManagementMenu(root, rootTree); // Menampilkan menu manajemen tree
    }

    private static String loadRootAndBuildTree(List<String> list) {
        if (list == null || list.isEmpty()) {
            Utils.println("File tree kosong atau tidak ditemukan!"); // teks jika tidak ada data
            return "";
        }
        return list.get(0).strip(); // Mengembalikan nama root dari data
    }

    private static String promptForRootTree() {
        while (true) {
            Utils.displayMenu("Buat Struktur Tree", new String[]{"x,Kembali"}, true);
            String rootTree = Utils.input("Root Tree");

            if ("x".equalsIgnoreCase(rootTree)) {
                return null; // Pengguna memilih untuk keluar
            }

            if (!rootTree.isEmpty()) {
                return rootTree; // Mengembalikan nama root yang valid
            }

            Utils.println("Root Tree tidak boleh kosong. Coba lagi."); // teks kesalahan
        }
    }

    private static void treeManagementMenu(TreeNode root, String rootTree) {
        while (true) {
            Utils.clear(); // Menghapus tampilan
            Utils.banner(true); // Menampilkan banner
            Utils.printTree(root, "", true); // Menampilkan struktur tree
            Utils.displayMenu("onlyMenu", new String[]{",,add:<Add Child>", ",,del:<Delete Child>", "s,Simpan", "x,Kembali"}, false);

            String pilihan = Utils.input("Tree Child");

            if (pilihan.startsWith("add:")) {
                String[] parts = pilihan.substring(4).split(">");
                Utils.addNode(root, parts); // Menambahkan node anak
            } else if (pilihan.startsWith("del:")) {
                String[] parts = pilihan.substring(4).split(">");
                Utils.deleteNode(root, parts); // Menghapus node anak
            } else if ("s".equalsIgnoreCase(pilihan)) {
                String filename = rootTree.strip().replace(" ", "-").toLowerCase(); // Membuat nama file dari root tree

                List<String> treeArray = new ArrayList<>();
                Utils.saveTreeToFlatFile(root, treeArray, ""); // Menyimpan struktur tree ke dalam array

                // Menyimpan array ke dalam file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + filename + ".tree"))) {
                    for (String line : treeArray) {
                        writer.write(line);
                        writer.newLine();
                    }
                    Utils.println("Struktur Tree berhasil disimpan [" + filename + ".tree]!"); // teks sukses
                } catch (IOException e) {
                    Utils.println("Gagal menyimpan tree: " + e.getMessage()); // teks kesalahan
                }
                Utils.input("Enter untuk melanjutkan..."); // Menunggu input dari pengguna
            } else if ("x".equalsIgnoreCase(pilihan)) {
                Utils.println("Kembali ke menu utama..."); // Mengembalikan ke menu utama
                return;
            } else {
                Utils.println("Opsi tidak valid. Coba lagi."); // teks kesalahan
            }
        }
    }

}

class TreeNode {

    private final String name; // Nama node
    private final List<TreeNode> children; // Daftar anak dari node

    public TreeNode(String name) {
        this.name = name; // Menetapkan nama node
        this.children = new ArrayList<>(); // Inisialisasi daftar anak
    }

    public String getName() {
        return name; // Mengembalikan nama node
    }

    public List<TreeNode> getChildren() {
        return children; // Mengembalikan daftar anak
    }

    public void addChild(TreeNode child) {
        children.add(child); // Menambahkan anak ke dalam daftar
    }

    public TreeNode findChild(String name) {
        for (TreeNode child : children) {
            if (child.getName().equals(name)) {
                return child; // Mengembalikan node anak jika ditemukan
            }
        }
        return null; // Mengembalikan null jika tidak ditemukan
    }

    public void removeChild(TreeNode child) {
        children.remove(child); // Menghapus anak dari daftar
    }
}

class Utils {

    private static final Scanner scanner = new Scanner(System.in);

    public static void printTree(TreeNode node, String indent, boolean isLast) {
        if (node == null) {
            return; // Menghentikan jika node null
        }

        // Mencetak node saat ini
        if (indent.isEmpty()) {
            System.out.println("[" + node.getName() + "]");  // Node root
        } else {
            String branch = isLast ? "└── " : "├── ";
            System.out.println(indent + branch + node.getName()); // Mencetak node dengan indentasi
        }

        // Memperbarui indentasi untuk anak
        String childIndent = indent + (isLast ? "    " : "│   ");

        // Rekursi untuk semua anak
        List<TreeNode> children = node.getChildren();
        for (int i = 0; i < children.size(); i++) {
            boolean isLastChild = (i == children.size() - 1);
            printTree(children.get(i), childIndent, isLastChild); // Mencetak anak
        }
    }

    public static void addNode(TreeNode root, String[] parts) {
        TreeNode currentNode = root; // Memulai dari root

        for (String part : parts) {
            if (part.isEmpty()) {
                continue; // Melewatkan bagian yang kosong
            }
            TreeNode childNode = currentNode.findChild(part);

            if (childNode == null) {
                // Jika anak tidak ada, buat dan tambahkan
                childNode = new TreeNode(part);
                currentNode.addChild(childNode);
            }

            // Pindah ke node anak untuk iterasi berikutnya
            currentNode = childNode;
        }
    }

    public static void deleteNode(TreeNode root, String[] parts) {
        if (parts == null || parts.length == 0) {
            System.out.println("Invalid parts to delete.");
            return; // Jika tidak ada bagian untuk dihapus
        }

        TreeNode currentNode = root; // Memulai dari root
        TreeNode parent = null; // Menyimpan parent
        for (String part : parts) {
            if (part.isEmpty()) {
                continue; // Melewatkan bagian yang kosong
            }

            parent = currentNode; // Menyimpan parent saat ini
            currentNode = currentNode.findChild(part); // Mencari anak

            if (currentNode == null) {
                System.out.println("Node tidak ditemukan: " + part); // teks jika node tidak ditemukan
                return; // Menghentikan jika node tidak ditemukan
            }
        }

        if (parent != null) {
            parent.removeChild(currentNode); // Menghapus anak dari parent
            System.out.println("Node dihapus: " + currentNode.getName()); // teks sukses
        }
    }

    public static void displayMenu(String title, String[] options, boolean clear) {
        if (clear) {
            clear(); // Menghapus tampilan
        }
        System.out.println(title); // Menampilkan judul menu
        for (String option : options) {
            System.out.println(option); // Menampilkan opsi
        }
    }

    public static String[] directory(File directory) {
        String[] files = directory.list(); // Mendapatkan daftar file
        return files != null ? files : new String[0]; // Mengembalikan daftar file
    }

    public static List<String> readLinesFromFile(File file) {
        List<String> lines = new ArrayList<>(); // Membuat daftar baris
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line); // Menambahkan baris ke dalam daftar
            }
        } catch (IOException e) {
            println("Gagal membaca file: " + e.getMessage()); // teks kesalahan
        }
        return lines; // Mengembalikan daftar baris
    }

    public static void saveTreeToFlatFile(TreeNode node, List<String> treeArray, String parent) {
        if (node == null) {
            return; // Menghentikan jika node null
        }

        // Menyimpan node ke dalam array
        if (parent.isEmpty()) {
            treeArray.add(node.getName()); // Menyimpan root
        } else {
            treeArray.add(parent + ">" + node.getName()); // Menyimpan anak
        }

        // Rekursi untuk semua anak
        for (TreeNode child : node.getChildren()) {
            saveTreeToFlatFile(child, treeArray, node.getName()); // Menyimpan anak
        }
    }

    public static boolean isValidSelection(int selection, int length) {
        return selection >= 0 && selection <= length; // Validasi pilihan
    }

    public static void exitProgram() {
        // Untuk menghentikan program
        clear();
        banner(true);
        println("──────Thanks─Makasih─Arigatou─────");
        println("");
        System.exit(0);
    }

    public static void print(String text) {
        System.out.print(text); // Print teks tanpa garis baru
    }

    public static void println(String text) {
        System.out.println(text); // Print teks denagn garis baru
    }

    public static String input(String prompt) {
        print("└[" + prompt + "]> "); // Print prompt untuk inputan
        return scanner.nextLine(); // Panggil scanner untuk mengambil input dari user dan mengembalikan hasilnya
    }

    public static void clear() {
        // Untuk membersihkan terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void banner(Boolean standalone) {
        // Menampilkan banner struktur tree
        println("┌──────── TREE STRUCTURE ────────┐");
        println("│████████ ██████  ███████ ███████│");
        println("│   ██    ██   ██ ██      ██     │");
        println("│   ██    ██████  █████   █████  │");
        println("│   ██    ██   ██ ██      ██     │");
        println("│   ██    ██   ██ ███████ ███████│");
        // Menentukan apakah banner adalah standalone atau bagian dari menu
        println(standalone ? "└────────────────────────────────┘" : "├────────────────────────────────┤");
    }

    public static void displayMenu(String title, String[] menu, Boolean clearTerm) {
        if (clearTerm) clear(); // Menghapus tampilan jika diperlukan

        if (!"onlyMenu".equals(title)) {
            banner(false); // Menampilkan banner sebelum menu
            printCenteredTitle(title); // Mencetak judul menu yang terpusat
            println("├────────────────────────────────┤"); // Menampilkan garis pemisah
        } else {
            println("┌────────────────────────────────┐"); // Garis pemisah untuk menu hanya
        }

        // Mencetak opsi menu
        for (int i = 0; i < menu.length; i++) {
            printMenuItem(menu[i], i); // Mencetak setiap item menu
        }

        println("├────────────────────────────────┘"); // Menampilkan garis pemisah di bawah menu
    }

    private static void printCenteredTitle(String title) {
        int white_space = 32 - title.length(); // Menghitung spasi kosong untuk pusat
        print("│" + title); // Mencetak judul
        for (int w = 0; w < white_space; w++) {
            print(" "); // Menambahkan spasi kosong
        }
        println("│"); // Menutup baris judul
    }

    private static void printMenuItem(String menuItem, int index) {
        String formattedItem;
        if (menuItem.contains(",")) {
            if (menuItem.contains(",,")) {
                formattedItem = menuItem.replace(",,", ""); // Menghapus tanda khusus jika ada
            } else {
                String[] parts = menuItem.split(",");
                formattedItem = String.format("%s. %s", parts[0], parts[1]); // Format menu item
            }
        } else {
            formattedItem = String.format("%d. %s", index + 1, menuItem); // Menampilkan indeks menu
        }

        int white_space = 32 - formattedItem.length(); // Menghitung spasi kosong untuk format
        print("│" + formattedItem); // Mencetak item menu
        for (int w = 0; w < white_space; w++) {
            print(" "); // Menambahkan spasi kosong
        }
        println("│"); // Menutup baris item menu
    }


}
