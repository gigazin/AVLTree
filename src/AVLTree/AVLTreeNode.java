package AVLTree;

public class AVLTreeNode {
    private int info;
    private AVLTreeNode parent;
    private AVLTreeNode right;
    private AVLTreeNode left;
    private int balance;

    public AVLTreeNode(int info) {
        this.info = info;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public AVLTreeNode getParent() {
        return parent;
    }

    public void setParent(AVLTreeNode parent) {
        this.parent = parent;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public void setRight(AVLTreeNode right) {
        this.right = right;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode left) {
        this.left = left;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
