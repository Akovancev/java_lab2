
/**
 * Класс бинарного дерева
 * @author Павел Акованцев
 */
public class Tree {

    /** Поле элемент дерева */
    private String elem;
    /** Поле приоритет операции */
    private int priority;
    /** Поле ссылка на левую ветвь дерева */
    private Tree left;
    /** Поле ссылка на правую ветвь дерева */
    private Tree right;

    /**
     * Конструктор - по умолчанию
     */
    public Tree() {
        this.elem = "&";
        this.priority = 0;
        this.left = null;
        this.right = null;
    }
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param elem - значение элемента
     * @param priority - приоритет операции
     * @param left - левая ветвь дерева
     * @param right - правая ветвь дерева
     */
    public Tree(String elem, int priority, Tree left, Tree right) {
        this.elem = elem;
        this.priority = priority;
        this.left = left;
        this.right = right;
    }

    /**
     * Функция получения значения элемента дерева
     * @return возвращает значение элемента дерева
     */
    public String getElem() {
        return elem;
    }

    /**
     * Процедура определения значения элемента дерева
     * @param elem - элемент дерева
     */
    public void setElem(String elem) {
        this.elem = elem;
    }

    /**
     * Функция получения приоритета операции
     * @return возвращает приоритет операции
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Процедура определения приоритета операции
     * @param priority - приоритет операции
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Функция получения левой ветви дерева
     * @return возвращает левую ветвь дерева
     */
    public Tree getLeft() {
        return left;
    }

    /**
     * Функция получения правой ветви дерева
     * @return возвращает правую ветвь дерева
     */
    public Tree getRight() {
        return right;
    }

    /**
     * Процедура определения левой ветви дерева
     * @param left - левая ветвь дерева
     */
    public void setLeft(Tree left) {
        this.left = left;
    }

    /**
     * Процедура определения правой ветви дерева
     * @param right - правая ветвь дерева
     */
    public void setRight(Tree right) {
        this.right = right;
    }

    /**
     * Функция вывода дерева в консоль в префиксой форме
     * @param tree - выводимое дерево
     */
    public static void printTree(Tree tree) {
        System.out.println(tree.getElem());
        if (tree.getLeft() != null)
            printTree(tree.getLeft());
        if (tree.getRight() != null)
            printTree(tree.getRight());
    }
}
