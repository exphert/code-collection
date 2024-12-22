/*
 * Original Code by Surya H.S <github.com/erozx>
 * Tree Structure Visualization with Java
 */

import java.io.*;
import java.util.*;

public class Tree {

    public static void main(String[] args) {

        while (true) {
            Utils.clear();
            Utils.menu(" Menu Utama", new String[]{"Buat Struktur Tree", "Load Struktur Tree", "0,Keluar"});
            try {
                int pilihan = Integer.parseInt(Utils.input("Pilih opsi"));
                switch (pilihan) {
                    case 1 -> StructureTree(false, null);
                    case 2 -> {
                        String pass = "not";
                        int loadTree = -1;
                        String[] treeList;

                        do {
                            // Get the list of tree files
                            treeList = Utils.directory(new File("data"));

                            // Create menu options
                            String[] menuOptions;
                            if (treeList.length == 0) {
                                menuOptions = new String[]{"0,Kembali"};
                            } else {
                                menuOptions = new String[treeList.length + 1];
                                System.arraycopy(treeList, 0, menuOptions, 0, treeList.length);
                                menuOptions[treeList.length] = "0,Kembali";
                            }

                            // Display menu
                            Utils.clear();
                            Utils.menu(" List Tree", menuOptions);

                            // Get user input
                            try {
                                loadTree = Integer.parseInt(Utils.input("Pilih"));

                                if (loadTree == 0) {
                                    pass = "ok";
                                } else if (loadTree > 0 && loadTree <= treeList.length) {
                                    pass = "ok"; // Allow valid selection
                                } else {
                                    // Utils.println("Opsi tidak valid. Silakan coba lagi.");
                                }
                            } catch (NumberFormatException e) {
                                // Utils.println("Input tidak valid. Harap masukkan angka.");
                            }
                        } while (!pass.equals("ok"));

                        if (loadTree > 0 && loadTree <= treeList.length) {
                            // Load the selected tree
                            File file = new File("data/" + treeList[loadTree - 1]); // Ensure correct path
                            List<String> lines = Utils.readLinesFromFile(file);
                            StructureTree(true, lines);
                        }
                    }
                    case 0 -> {
                        Utils.clear();
                        Utils.banner(true);
                        Utils.println("──────Thanks─Makasih─Arigatou─────");
                        Utils.println(":)");
                        System.exit(0);
                    }
                    default -> Utils.println("Opsi tidak valid. Coba lagi.");
                }
            } catch (NumberFormatException e) {
                Utils.println("Input tidak valid. Harap masukkan angka.");
            }
        }
    }

    public static void StructureTree(Boolean isLoad, List<String> list) {
        Utils.clear();

        // Get the root name only if it's not loading
        String rootTree;
        TreeNode root;

        if (isLoad) {
            // If loading, create root node based on the first element in the list
            rootTree = list.get(0).split(">")[0].strip(); // Assuming the first element is the root name
            root = new TreeNode(rootTree); // Extract and set the root node

            for (int idx = 0; idx < list.size(); idx++) {
                // Remove the root part of the path and split remaining path by ">"
                String[] parts = list.get(idx).substring(rootTree.split(">")[0].length() + 1).split(">");
                Utils.addNode(root, parts);
            }
        } else {
            // If creating new structure
            while(true){
                Utils.clear();
                Utils.menu("Buat Struktur Tree", new String[]{"x,Kembali"});
                rootTree = Utils.input("Root Tree");

                if ("x".equals(rootTree)) {
                    return; // Exit if the user chose to go back
                }

                if(!rootTree.isEmpty()){
                    break;
                }
            }
            root = new TreeNode(rootTree);
        }

        while (true) {
            Utils.clear();
            Utils.banner(true);
            Utils.printTree(root, "", true);
            Utils.menu("onlyMenu", new String[]{",,add:Add Child",",,del:Delete Child","s,Simpan", "x,Kembali"});
            try {
                String pilihan = Utils.input("Tree Child");

                if (pilihan.startsWith("add:")) {
                    String[] parts = pilihan.substring(4).split(">");
                    Utils.addNode(root, parts);
                } else if (pilihan.startsWith("del:")) {
                    String[] parts = pilihan.substring(4).split(">");
                    Utils.deleteNode(root, parts);
                }

                switch (pilihan) {
                    case "s" -> {
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
                        Utils.input("Enter");
                    }
                    case "x" -> {
                        Utils.println("Kembali ke menu utama...");
                        return; // Exit the function to return to the main menu
                    }
                    default -> Utils.println("Opsi tidak valid. Coba lagi.");
                }
            } catch (NumberFormatException e) {
                Utils.println("Input tidak valid. Harap masukkan angka.");
            }
        }
    }

}

class TreeNode {
    private final String name;
    private final List<TreeNode> children;

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public TreeNode findChild(String name) {
        for (TreeNode child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    // Add the removeChild method
    public void removeChild(TreeNode child) {
        children.remove(child);
    }
}


class Utils {

    private static final Scanner scanner = new Scanner(System.in);

    public static void printTree(TreeNode node, String indent, boolean isLast) {
        if (node == null) {
            return;
        }

        // Print the current node without prefix for root
        if (indent.isEmpty()) {
            System.out.println("[" + node.getName() + "]");  // Print the root node without any prefix
        } else {
            String branch = isLast ? "└───" : "├───";
            System.out.println(indent + branch + node.getName());
        }

        // Prepare the new indent for children
        indent += (isLast ? "    " : "│   ");

        // Recurse for children
        for (int i = 0; i < node.getChildren().size(); i++) {
            boolean isLastChild = (i == node.getChildren().size() - 1);
            printTree(node.getChildren().get(i), indent, isLastChild);
        }
    }

    public static void addNode(TreeNode root, String[] parts) {
        TreeNode currentNode = root;

        for (String part : parts) {
            TreeNode childNode = currentNode.findChild(part);

            if (childNode == null) {
                // Create new child node if it doesn't exist
                childNode = new TreeNode(part);
                currentNode.addChild(childNode);
            }
            // Move to the next level in the tree
            currentNode = childNode;
        }
    }

    public static void deleteNode(TreeNode root, String[] parts) {
        if (parts.length == 0) return;

        TreeNode currentNode = root;
        for (int i = 0; i < parts.length - 1; i++) {
            currentNode = currentNode.findChild(parts[i]);
            if (currentNode == null) {
                System.out.println("Path not found: " + String.join(">", parts));
                return;
            }
        }

        String lastNodeName = parts[parts.length - 1];
        TreeNode childToDelete = currentNode.findChild(lastNodeName);
        if (childToDelete != null) {
            currentNode.removeChild(childToDelete);
            System.out.println("Node deleted: " + lastNodeName);
        } else {
            System.out.println("Node not found: " + lastNodeName);
        }
    }



    public static void saveTreeToFlatFile(TreeNode node, List<String> result, String path) {
        // Build the current path
        String currentPath = path.isEmpty() ? node.getName() : path + ">" + node.getName();

        // If the node has no children, save the path as a leaf
        if (node.getChildren().isEmpty()) {
            result.add(currentPath);
        } else {
            // If the node has children, recursively process them
            for (TreeNode child : node.getChildren()) {
                saveTreeToFlatFile(child, result, currentPath);
            }
        }
    }

    public static String[] directory(File dir) {
        if (!dir.exists() || !dir.isDirectory()) {
            return new String[0]; // Return empty array if the directory doesn't exist or is not valid
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

        // Convert List to raw String array
        return fileList.toArray(String[]::new);
    }

    public static List<String> readLinesFromFile(File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    public static void print(String text) {
        System.out.print(text);
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static String input(String prompt) {
        print("└[" + prompt + "]> ");
        return scanner.nextLine();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

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

        // Print menu options
        for (int i = 0; i < menu.length; i++) {
            String menuItem;
            if (menu[i].contains(",")) {
                if(menu[i].contains(",,")){
                    menuItem = menu[i].replace(",,", "");
                } else {
                    menuItem = menu[i].split(",")[0] + ". " + menu[i].replace(menu[i].split(",")[0] + ",", "");
                }
            } else {
                menuItem = (i + 1) + ". " + menu[i].replace(".tree", "");
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
