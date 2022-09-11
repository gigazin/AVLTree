package TreeTester;

import AVLTree.AVLTree;
import AVLTree.AVLTreeNode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        AVLTree avlTree = new AVLTree();
        char option;
        int num;
        String inputNum;
        menu();
        do {
            System.out.print("\nOption >> ");
            option = in.next().charAt(0); in.nextLine();
            switch (Character.toUpperCase(option)) {
                case 'I' -> { // Insert a new node.
                    System.out.print("\nInput >> ");
                    inputNum = in.nextLine();
                    if (!checkInput(inputNum)) {
                        System.out.println("Invalid input.");
                    } else {
                        num = Integer.parseInt(inputNum);
                        avlTree.insert(num, avlTree.getRoot());
                        printTree(avlTree.getRoot(), 1);
                    }
                } // Insert a new node.
                case 'R' -> { // Remove a node.
                    System.out.print("\nInput >> ");
                    inputNum = in.nextLine();
                    if (!checkInput(inputNum)) {
                        System.out.println("Invalid input.");
                    } else {
                        num = Integer.parseInt(inputNum);
                        avlTree.delete(num, avlTree.getRoot());
                        printTree(avlTree.getRoot(), 1);
                    }
                } // Remove a node.
                case 'S' -> { // Search a node.
                    System.out.print("\nInput >> ");
                    inputNum = in.nextLine();
                    if (!checkInput(inputNum)) {
                        System.out.println("Invalid input.");
                    } else {
                        num = Integer.parseInt(inputNum);
                        AVLTreeNode result = avlTree.search(num, avlTree.getRoot());
                        if (result == null) {
                            System.out.println("Value not found.");
                        } else {
                            System.out.println("Value found!");
                        }
                    }
                } // Search a node.
                case 'F' -> { System.out.println("Application finished."); }
                default -> System.out.println("Invalid option.");
            }
        } while (option != 'F');
    }

    public static void menu() {
        System.out.println("========== Options ==========");
        System.out.println("I -> Insert a node.");
        System.out.println("R -> Remove a node.");
        System.out.println("S -> Search a node.");
        System.out.println("F -> Finish the application.");
    }

    public static void printTree(AVLTreeNode rootNode, int level) {
        if (rootNode != null) {
            printTree(rootNode.getRight(), level + 1);
            System.out.print("\n\n");
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.print("{" + rootNode.getInfo() + "}");
            printTree(rootNode.getLeft(), level + 1);
        }
    }

    public static boolean checkInput(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '0' && input.charAt(i) != '1' &&
                    input.charAt(i) != '2' && input.charAt(i) != '3' &&
                    input.charAt(i) != '4' && input.charAt(i) != '5' &&
                    input.charAt(i) != '6' && input.charAt(i) != '7' &&
                    input.charAt(i) != '8' && input.charAt(i) != '9') {
                return false;
            }
        }
        return true;
    }

}
