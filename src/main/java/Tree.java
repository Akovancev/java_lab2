public class Tree {
    private char elem;
    private int priority;
    private Tree left;
    private Tree right;

    public Tree() {
        this.elem = '&';
        this.priority = 0;
        this.left = null;
        this.right = null;
    }
    public Tree(char elem, int priority, Tree left, Tree right) {
        this.elem = elem;
        this.priority = priority;
        this.left = left;
        this.right = right;
    }

    public char getElem() {
        return elem;
    }

    public void setElem(char elem) {
        this.elem = elem;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public void setRight(Tree right) {
        this.right = right;
    }
}
