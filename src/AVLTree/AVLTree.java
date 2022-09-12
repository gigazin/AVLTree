package AVLTree;

public class AVLTree {
    private AVLTreeNode root;

    public AVLTree() {
    }

    public AVLTreeNode getRoot() {
        return root;
    }

    public void setRoot(AVLTreeNode newRoot) {
        this.root = newRoot;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public AVLTreeNode search(int info, AVLTreeNode actualNode) {
        while (actualNode != null) {
            if (actualNode.getInfo() == info) {
                return actualNode;
            } else if (info > actualNode.getInfo()) {
                actualNode = actualNode.getRight();
            } else {
                actualNode = actualNode.getLeft();
            }
        }
        return null;
    }

    public void insert(int info, AVLTreeNode actualNode) {
        if (isEmpty()) { // Empty tree, inserting in root.
            this.root = new AVLTreeNode(info);
            balanceAdjust(root);
        } else { // Tree isn't empty, searching a position to insert the node.
            if (info < actualNode.getInfo()) { // Value is lower than actual value.
                /* Node to the left of the actual node isn't empty.
                 * Recursively search until an empty node is found. */
                if (actualNode.getLeft() != null) {
                    insert(info, actualNode.getLeft());
                } else { // Found an empty node, inserting.
                    AVLTreeNode newNode = new AVLTreeNode(info);
                    actualNode.setLeft(newNode);
                    newNode.setParent(actualNode);
                }
            } else if (info > actualNode.getInfo()) { // Value is higher than actual value.
                /* Node to the right of the actual node isn't empty.
                 * Recursively search until an empty node is found. */
                if (actualNode.getRight() != null) {
                    insert(info, actualNode.getRight());
                } else { // Found an empty node, inserting.
                    AVLTreeNode newNode = new AVLTreeNode(info);
                    actualNode.setRight(newNode);
                    newNode.setParent(actualNode);
                }
            } else {
                System.out.println("Repeated value.");
            }
        }
        /* Checking and balancing the tree after every insertion. */
        balanceAdjust(root);
        root = treeBalancer(root);
    }

    public void walk() {
        if (this.isEmpty()) {
            System.out.println("Empty tree.");
        } else {
            walkInOrder(root);
        }
    }

    private void walkInOrder(AVLTreeNode root) {
        if (root != null) {
            walkInOrder(root.getLeft());
            System.out.println(root.getInfo() + " ");
            walkInOrder(root.getRight());
        }
    }

    public AVLTreeNode delete(int info, AVLTreeNode actualNode) {
        if (isEmpty()) { // Empty tree, can't remove.
            System.out.println("Empty tree.");
            return null;
        }
        /* Checks if the given value is lower or higher than the value of the actual node.
         * If higher, goes right. Else, goes left. */
        if (info < actualNode.getInfo()) {
            actualNode.setLeft(delete(info, actualNode.getLeft()));
        } else if (info > actualNode.getInfo()) {
            actualNode.setRight(delete(info, actualNode.getRight()));
        } else {
            /* Checks if the node has no children. */
            if (actualNode.getRight() == null && actualNode.getLeft() == null) {
                /* If node is root, remove root by setting it to null.
                 * Else, node is leaf, remove it by setting it to null. */
                if (actualNode == root) {
                    this.root = null;
                } else {
                    actualNode = null;
                }
            } else if (actualNode.getLeft() == null) {
                actualNode = actualNode.getRight();
            } else if (actualNode.getRight() == null) {
                actualNode = actualNode.getLeft();
            } else {
                AVLTreeNode auxiliaryNode = actualNode.getLeft();
                while (auxiliaryNode.getRight() != null) {
                    auxiliaryNode = auxiliaryNode.getRight();
                }
                actualNode.setInfo(auxiliaryNode.getInfo());
                auxiliaryNode.setInfo(info);
                actualNode.setLeft(delete(info, actualNode.getLeft()));
            }
        }
        /* After deleting a node, check if the tree isn't empty. Also, adjust its balance if needed. */
        if (!this.isEmpty() && (getBalanceFactor() < -1 || getBalanceFactor() > 1)) {
            balanceAdjust(actualNode);
            this.root = treeBalancer(root);
        }
        return actualNode;
    }

    public int getBalanceFactor() {
        return treeRightHeight() - treeLeftHeight();
    }

    public int treeLeftHeight() {
        int height = 0;
        AVLTreeNode auxiliaryNode;
        auxiliaryNode = this.root;
        while (auxiliaryNode != null) {
            height++;
            auxiliaryNode = auxiliaryNode.getLeft();
        }
        return height;
    }

    public int treeRightHeight() {
        int height = 0;
        AVLTreeNode auxiliaryNode;
        auxiliaryNode = this.root;
        while (auxiliaryNode != null) {
            height++;
            auxiliaryNode = auxiliaryNode.getRight();
        }
        return height;
    }

    public AVLTreeNode rotateRight(AVLTreeNode actualNode) {
        AVLTreeNode auxiliaryNode = actualNode.getLeft();
        auxiliaryNode.setParent(actualNode.getParent());
        if (auxiliaryNode.getRight() != null) {
            auxiliaryNode.getRight().setParent(actualNode);
        }
        actualNode.setParent(auxiliaryNode);
        actualNode.setLeft(auxiliaryNode.getRight());
        auxiliaryNode.setRight(actualNode);
        if (auxiliaryNode.getParent() != null) {
            if (auxiliaryNode.getParent().getRight() == actualNode) {
                auxiliaryNode.getParent().setRight(auxiliaryNode);
            } else if (auxiliaryNode.getParent().getLeft() == actualNode) {
                auxiliaryNode.getParent().setLeft(auxiliaryNode);
            }
        }
        balanceAdjust(auxiliaryNode);
        return auxiliaryNode;
    }

    public AVLTreeNode doubleRotateRight(AVLTreeNode actualNode) {
        AVLTreeNode firstAuxNode = actualNode.getLeft();
        actualNode.setLeft(rotateLeft(firstAuxNode));
        AVLTreeNode secondAuxNode = rotateRight(actualNode);
        return secondAuxNode;
    }

    public AVLTreeNode rotateLeft(AVLTreeNode actualNode) {
        AVLTreeNode auxiliaryNode = actualNode.getRight();
        auxiliaryNode.setParent(actualNode.getParent());
        if (auxiliaryNode.getLeft() != null) {
            auxiliaryNode.getLeft().setParent(actualNode);
        }
        actualNode.setParent(auxiliaryNode);
        actualNode.setRight(auxiliaryNode.getLeft());
        auxiliaryNode.setLeft(actualNode);
        if (auxiliaryNode.getParent() != null) {
            if (auxiliaryNode.getParent().getRight() == actualNode) {
                auxiliaryNode.getParent().setRight(auxiliaryNode);
            } else if (auxiliaryNode.getParent().getLeft() == actualNode) {
                auxiliaryNode.getParent().setLeft(auxiliaryNode);
            }
        }
        balanceAdjust(auxiliaryNode);
        return auxiliaryNode;
    }

    public AVLTreeNode doubleRotateLeft(AVLTreeNode actualNode) {
        AVLTreeNode firstAuxNode = actualNode.getRight();
        actualNode.setRight(rotateRight(firstAuxNode));
        AVLTreeNode secondAuxNode = rotateLeft(actualNode);
        return secondAuxNode;
    }

    public void balanceAdjust(AVLTreeNode actualNode) {
        actualNode.setBalance(treeHeight(actualNode.getLeft()) - treeHeight(actualNode.getRight()));
        if (actualNode.getRight() != null) {
            balanceAdjust(actualNode.getRight());
        }
        if (actualNode.getLeft() != null) {
            balanceAdjust(actualNode.getLeft());
        }
    }

    public int treeHeight(AVLTreeNode actualNode) {
        if (actualNode == null) {
            return -1;
        }
        if (actualNode.getRight() == null && actualNode.getLeft() == null) {
            return 0;
        } else if (actualNode.getLeft() == null) {
            return 1 + treeHeight(actualNode.getRight());
        } else if (actualNode.getRight() == null) {
            return 1 + treeHeight(actualNode.getLeft());
        } else {
            if (treeHeight(actualNode.getLeft()) > treeHeight(actualNode.getRight())) {
                return 1 + treeHeight(actualNode.getLeft());
            } else {
                return 1 + treeHeight(actualNode.getRight());
            }
        }
    }

    public AVLTreeNode treeBalancer(AVLTreeNode actualNode) {
        if (actualNode.getBalance() == 2 && actualNode.getLeft().getBalance() >= 0) {
            actualNode = rotateRight(actualNode);

        } else if (actualNode.getBalance() == -2 && actualNode.getRight().getBalance() <= 0) {
            actualNode = rotateLeft(actualNode);

        } else if (actualNode.getBalance() == 2 && actualNode.getLeft().getBalance() < 0) {
            actualNode = doubleRotateRight(actualNode);

        } else if (actualNode.getBalance() == -2 && actualNode.getRight().getBalance() > 0) {
            actualNode = doubleRotateLeft(actualNode);
        }

        if (actualNode.getRight() != null) {
            treeBalancer(actualNode.getRight());
        }
        if (actualNode.getLeft() != null) {
            treeBalancer(actualNode.getLeft());
        }
        return actualNode;
    }

}
