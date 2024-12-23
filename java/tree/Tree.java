/*
 * Original Code by Surya H.S <https://github.com/erozx/code-collection/tree/main/java/tree>
 * Tree Structure Visualization with Java
 */

import java.io.*;
import java.util.*;

public class Tree {

    public static void main(String[] args) {

        while (true) {
			// Membersihkan layar dan menampilkan menu utama
			Utils.clear();
			Utils.menu(" Menu Utama", new String[]{"Buat Struktur Tree", "Load Struktur Tree", "0,Keluar"});

			try {
				// Meminta input pilihan dari pengguna
				int pilihan = Integer.parseInt(Utils.input("Pilih opsi"));

				switch (pilihan) {
					case 1 -> StructureTree(false, null); // Opsi untuk membuat struktur tree baru

					case 2 -> {
						String pass = "not";
						int loadTree = -1;
						String[] treeList;

						do {
							// Mendapatkan daftar file tree dari direktori "data"
							treeList = Utils.directory(new File("data"));

							// Menyiapkan opsi menu berdasarkan file tree yang tersedia
							String[] menuOptions;
							if (treeList.length == 0) {
								menuOptions = new String[]{"0,Kembali"}; // Jika tidak ada file
							} else {
								menuOptions = new String[treeList.length + 1];
								System.arraycopy(treeList, 0, menuOptions, 0, treeList.length);
								menuOptions[treeList.length] = "0,Kembali"; // Tambahkan opsi kembali
							}

							// Membersihkan layar dan menampilkan menu daftar tree
							Utils.clear();
							Utils.menu(" List Tree", menuOptions);

							try {
								// Meminta input pilihan file tree
								loadTree = Integer.parseInt(Utils.input("Pilih"));

								if (loadTree == 0) {
									pass = "ok"; // Kembali ke menu utama
								} else if (loadTree > 0 && loadTree <= treeList.length) {
									pass = "ok"; // File valid, lanjutkan proses
								} else {
									Utils.println("Opsi tidak valid. Silakan coba lagi.");
								}

							} catch (NumberFormatException e) {
								Utils.println("Input tidak valid. Harap masukkan angka.");
							}
						} while (!pass.equals("ok")); // Ulangi sampai input valid

						// Jika file valid dipilih, baca file dan tampilkan struktur tree
						if (loadTree > 0 && loadTree <= treeList.length) {
							File file = new File("data/" + treeList[loadTree - 1]);
							List<String> lines = Utils.readLinesFromFile(file);
							StructureTree(true, lines);
						}
					}

					case 0 -> {
						// Keluar dari program
						Utils.clear();
						Utils.banner(true);
						Utils.println("─────Thanks─Makasih─Arigatou──────");
						Utils.println("        <github.com/erozx>");
						System.exit(0);
					}

					default -> Utils.println("Opsi tidak valid. Coba lagi."); // Jika input tidak valid
				}

			} catch (NumberFormatException e) {
				Utils.println("Input tidak valid. Harap masukkan angka."); // Jika input bukan angka
			}
		}
	}

    public static void StructureTree(Boolean isLoad, List<String> list) {
		Utils.clear();

		String rootTree;
		TreeNode root;

		// Jika tree diload dari file
		if (isLoad) {
			rootTree = list.get(0).split(">")[0].strip(); // Ambil nama root tree
			root = new TreeNode(rootTree);

			// Tambahkan node ke tree berdasarkan file
			for (int idx = 0; idx < list.size(); idx++) {
				String[] parts = list.get(idx).substring(rootTree.split(">")[0].length() + 1).split(">");
				Utils.addNode(root, parts);
			}
		} else {
			// Jika tree dibuat baru
			while (true) {
				Utils.clear();
				Utils.menu("Buat Struktur Tree", new String[]{"x,Kembali"});
				rootTree = Utils.input("Root Tree");

				if ("x".equals(rootTree)) { // Kembali jika input 'x'
					return;
				}

				if (!rootTree.isEmpty()) { // Pastikan root tree tidak kosong
					break;
				}
			}
			root = new TreeNode(rootTree); // Inisialisasi root tree
		}

		// Menu utama untuk manipulasi tree
		while (true) {
			Utils.clear();
			Utils.banner(true);
			Utils.printTree(root, "", true); // Tampilkan tree
			Utils.menu("onlyMenu", new String[]{",,add:<Add Branch>", ",,del:<Delete Branch>", ",,clr:<Clear Branch>", "s,Simpan", "x,Kembali"});

			try {
				String pilihan = Utils.input("Tree Branch");

				// Menambahkan branch ke tree
				if (pilihan.startsWith("add:")) {
					String[] parts = pilihan.substring(4).split(">");
					Utils.addNode(root, parts);
				}
				// Menghapus branch dari tree
				else if (pilihan.startsWith("del:")) {
					String[] parts = pilihan.substring(4).split(">");
					Utils.deleteNode(root, parts);
				}
				// Membersihkan isi branch tanpa menghapus branch itu sendiri
				else if (pilihan.startsWith("clr:")) {
					String[] parts = pilihan.substring(4).split(">");
					Utils.clearNode(root, parts);
				}

				switch (pilihan) {
					case "s" -> {
						// Simpan struktur tree ke file
						String filenames = rootTree.strip().replace(" ", "-").toLowerCase().replace(".tree", "");
						List<String> treeArray = new ArrayList<>();
						Utils.saveTreeToFlatFile(root, treeArray, "");

						try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + filenames + ".tree"))) {
							for (String line : treeArray) {
								writer.write(line);
								writer.newLine();
							}
							Utils.println("Struktur Tree berhasil disimpan [" + filenames + ".tree]!");
						} catch (IOException e) {
							Utils.println("Gagal menyimpan tree: " + e.getMessage());
						}
						Utils.input("Enter"); // Tunggu input sebelum lanjut
					}
					case "x" -> {
						// Kembali ke menu utama
						Utils.println("Kembali ke menu utama...");
						return;
					}
					default -> Utils.println("Opsi tidak valid. Coba lagi.");
				}
			} catch (NumberFormatException e) {
				Utils.println("Input tidak valid. Harap masukkan angka.");
			}
		}
	}
}

// Kelas TreeNode digunakan untuk merepresentasikan node dalam struktur tree
class TreeNode {
    private final String name; // Nama dari node
    private final List<TreeNode> branches; // Daftar cabang (children) dari node ini

    // Konstruktor untuk membuat node baru dengan nama tertentu
    public TreeNode(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
    }

    // Mendapatkan nama dari node
    public String getName() {
        return name;
    }

    // Mendapatkan daftar cabang (children) dari node ini
    public List<TreeNode> Branches() {
        return branches;
    }

    // Menambahkan cabang (child) baru ke node ini
    public void addBranch(TreeNode branch) {
        branches.add(branch);
    }

    // Mencari cabang (child) berdasarkan nama
    public TreeNode findBranch(String name) {
        for (TreeNode branch : branches) {
            if (branch.getName().equals(name)) {
                return branch; // Mengembalikan cabang yang ditemukan
            }
        }
        return null; // Mengembalikan null jika tidak ditemukan
    }

    // Menghapus cabang (child) tertentu dari node ini
    public void removeBranch(TreeNode branch) {
        branches.remove(branch);
    }

    // Menghapus semua cabang (children) dari node ini
    public void clearBranches() {
        branches.clear();
    }
}

// Kelas Utils menyediakan berbagai utilitas untuk pengelolaan tree dan interaksi pengguna
class Utils {

    private static final Scanner scanner = new Scanner(System.in); // Scanner untuk input pengguna

    // Menampilkan struktur tree secara visual
    public static void printTree(TreeNode node, String indent, boolean isLast) {
        if (node == null) {
            return; // Tidak ada node untuk ditampilkan
        }

        // Menampilkan nama root atau cabang dengan indentasi yang sesuai
        if (indent.isEmpty()) {
            System.out.println("[" + node.getName() + "]");
        } else {
            String branch = isLast ? "└───" : "├───";
            System.out.println(indent + branch + node.getName());
        }

        indent += (isLast ? "    " : "│   ");

        // Rekursif untuk menampilkan cabang berikutnya
        for (int i = 0; i < node.Branches().size(); i++) {
            boolean isLastBranch = (i == node.Branches().size() - 1);
            printTree(node.Branches().get(i), indent, isLastBranch);
        }
    }

    // Menambahkan node baru ke tree
    public static void addNode(TreeNode root, String[] parts) {
        TreeNode currentNode = root;

        for (String part : parts) {
            TreeNode branchNode = currentNode.findBranch(part);

            if (branchNode == null && !part.trim().isEmpty()) {
                branchNode = new TreeNode(part); // Membuat node baru jika tidak ditemukan
                currentNode.addBranch(branchNode);
            }

            currentNode = branchNode; // Berpindah ke node berikutnya
        }
    }

    // Menghapus node dari tree berdasarkan path
    public static void deleteNode(TreeNode root, String[] parts) {
        if (parts.length == 0) return;

        TreeNode currentNode = root;
        for (int i = 0; i < parts.length - 1; i++) {
            currentNode = currentNode.findBranch(parts[i]);
            if (currentNode == null) {
                System.out.println("Path tidak ditemukan: " + String.join(">", parts));
                return;
            }
        }

        String lastNodeName = parts[parts.length - 1];
        TreeNode branchToDelete = currentNode.findBranch(lastNodeName);
        if (branchToDelete != null) {
            currentNode.removeBranch(branchToDelete);
            System.out.println("Node dihapus: " + lastNodeName);
        } else {
            System.out.println("Node tidak ditemukan: " + lastNodeName);
        }
    }

    // Menghapus semua cabang dari node tertentu
    public static void clearNode(TreeNode root, String[] parts) {
        if (parts.length == 0) return;

        TreeNode currentNode = root;
        for (int i = 0; i < parts.length; i++) {
            currentNode = currentNode.findBranch(parts[i]);
            if (currentNode == null) {
                System.out.println("Path tidak ditemukan: " + String.join(">", parts));
                return;
            }
        }

        currentNode.clearBranches(); // Menghapus semua cabang di dalam node
        System.out.println("Semua cabang dihapus untuk: " + parts[parts.length - 1]);
    }

    // Menyimpan struktur tree ke dalam format flat file
    public static void saveTreeToFlatFile(TreeNode node, List<String> result, String path) {
        String currentPath = path.isEmpty() ? node.getName() : path + ">" + node.getName();

        if (node.Branches().isEmpty()) {
            result.add(currentPath); // Menambahkan path jika tidak ada cabang
        } else {
            for (TreeNode branch : node.Branches()) {
                saveTreeToFlatFile(branch, result, currentPath); // Rekursif untuk cabang berikutnya
            }
        }
    }

    // Mendapatkan daftar file dengan ekstensi ".tree" di direktori tertentu
    public static String[] directory(File dir) {
        if (!dir.exists() || !dir.isDirectory()) {
            return new String[0]; // Mengembalikan array kosong jika direktori tidak valid
        }

        List<String> fileList = new ArrayList<>();
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".tree")) {
                    fileList.add(file.getName());
                }
            }
        }

        return fileList.toArray(String[]::new); // Mengembalikan daftar file sebagai array
    }

    // Membaca baris teks dari file
    public static List<String> readLinesFromFile(File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Kesalahan membaca file: " + e.getMessage());
        }
        return lines;
    }

    // Menampilkan teks tanpa pindah baris
    public static void print(String text) {
        System.out.print(text);
    }

    // Menampilkan teks dengan pindah baris
    public static void println(String text) {
        System.out.println(text);
    }

    // Meminta input dari pengguna
    public static String input(String prompt) {
        print("└[" + prompt + "]> ");
        return scanner.nextLine();
    }

    // Membersihkan layar
    public static void clear() {
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Kesalahan saat membersihkan layar: " + e.getMessage());
        }
    }

    // Menampilkan banner aplikasi
    public static void banner(Boolean standalone) {
        println("┌──────── TREE STRUCTURE ────────┐");
        println("│████████ ██████  ███████ ███████│");
        println("│   ██    ██   ██ ██      ██     │");
        println("│   ██    ██████  █████   █████  │");
        println("│   ██    ██   ██ ██      ██     │");
        println("│   ██    ██   ██ ███████ ███████│");
        if (standalone) {
            println("└────────────────────────────────┘");
        } else {
            println("├────────────────────────────────┤");
        }
    }

    // Menampilkan menu interaktif
    public static void menu(String title, String[] menu) {
        int white_space;
        if (!"onlyMenu".equals(title)) {
            banner(false);
            white_space = 32 - title.length();
            print("│" + title);
            for (int w = 0; w < white_space; w++) {
                print(" ");
            }
            println("│");
            println("├────────────────────────────────┤");
        } else {
            println("┌────────────────────────────────┐");
        }

        for (int i = 0; i < menu.length; i++) {
            String menuItem;
            if (menu[i].contains(",")) {
                if (menu[i].contains(",,")) {
                    menuItem = menu[i].replace(",,", "");
                } else {
                    menuItem = menu[i].split(",")[0] + ". " + menu[i].replace(menu[i].split(",")[0] + ",", "");
                }
            } else {
                menuItem = (i + 1) + ". " + menu[i];
            }
            white_space = 32 - menuItem.length();
            print("│" + menuItem);
            for (int w = 0; w < white_space; w++) {
                print(" ");
            }
            println("│");
        }

        println("├────────────────────────────────┘");
    }
}
