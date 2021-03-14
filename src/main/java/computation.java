import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс вычисления значения выражения полученного в виде строки
 * @author Павел Акованцев
 */
public class computation {
    /** Поле символы встречающиеся в выражении */
    private static char[] symbols;
    /** Поле значения символов встречающихся в выражении */
    private static int[] symbols_d;
    /** Поле количество символов встречающихся в выражении */
    private static int count_s = 0;

    /**
     * Функция вывода сообщения об ошибке ввода
     */
    private static void errorMessage() {
        System.out.println("Error");
    }

    /**
     * Функция добавления операции с высоким приоритетом в дерево
     * @param t - дерево
     * @param str - выражение
     * @param priority - приоритет операции
     * @param digit - число
     */
    private static Tree AddHighPriority(Tree t, String str, int priority, String digit) {
        if (t.getPriority() < priority) {
            if (t.getRight() != null)
                t.setRight(AddHighPriority(t.getRight(), str, priority, digit));
            else
            {
                Tree temp1 = t;
                Tree temp2 = new Tree(digit, priority, null, null);
                t = new Tree(str, priority, temp1, temp2);
            }
        }
        else if (t.getPriority() == priority)
        {
            Tree temp1 = t.getRight();
            Tree temp2 = t.getLeft();
            t.setLeft(temp1);
            Tree left = new Tree(temp2.getElem(), temp2.getPriority(), null, null);
            Tree right = new Tree(digit, priority, null, null);
            temp1 = new Tree(str, priority, left, right);
            t.setRight(temp1);
        }
        else
        {
            Tree temp1 = t;
            Tree temp2 = new Tree(digit, priority, null, null);
            t = new Tree(str, priority, temp1, temp2);
        }
        return t;
    }

    /**
     * Функция преобразования строки-выражения в дерево-выражение
     * @param str - строка-выражение
     */
    public static Tree transform(String str) {
        symbols = new char[str.length()];
        symbols_d = new int[str.length()];
        for (int i = 0; i < str.length(); i++)
        {
            symbols_d[i] = -1;
        }
        int base_priority = 0;
        int i = 0;
        while (str.charAt(i) == '(') {
            base_priority ++;
            i++;
        }
        String digit = "";
        if (Character.isLetter(str.charAt(i)))
        {
            symbols[count_s] = str.charAt(i);
            count_s++;
            digit += str.charAt(i);
            i++;
        }
        else
        {
            while (Character.isDigit(str.charAt(i))) {
                digit += str.charAt(i);
                i++;
            }
        }
        if (digit == "") {
            errorMessage();
            return null;
        }
        Tree res = new Tree(digit, base_priority * 2, null, null);
        while (i < str.length()) {
            int priority = base_priority * 2;;
            while (i < str.length() && str.charAt(i) == '(') {
                base_priority++;
                i++;
            }
            while (i < str.length() && str.charAt(i) == ')') {
                base_priority--;
                i++;
            }
            if (base_priority < 0)
            {
                errorMessage();
                return null;
            }
            if (i >= str.length()) break;
            String c = "&";
            if (i < str.length() && str.charAt(i) == '+') {
                c = "+";
                priority = base_priority * 2;
            }
            else if (i < str.length() && str.charAt(i) == '*') {
                c = "*";
                priority = base_priority * 2 + 1;
            }
            else if (i < str.length() && str.charAt(i) == '-') {
                c = "-";
                priority = base_priority * 2;
            }
            else if (i < str.length() && str.charAt(i) == '/') {
                c = "/";
                priority = base_priority * 2 + 1;
            }
            else {
                errorMessage();
                return null;
            }
            i++;
            while (i < str.length() && str.charAt(i) == '(') {
                base_priority++;
                i++;
            }
            while (i < str.length() && str.charAt(i) == ')') {
                base_priority--;
                i++;
            }
            if (base_priority < 0)
            {
                errorMessage();
                return null;
            }
            digit = "";
            if (i < str.length() && Character.isLetter(str.charAt(i)))
            {
                symbols[count_s] = str.charAt(i);
                count_s++;
                digit += str.charAt(i);
                i++;
            }
            else
            {
                while (i < str.length() && Character.isDigit(str.charAt(i))) {
                    digit += str.charAt(i);
                    i++;
                }
            }
            if (digit == "") {
                errorMessage();
                return null;
            }
            if (priority <= res.getPriority() || Character.isDigit(res.getElem().charAt(0)))
            {
                Tree left = res;
                Tree right = new Tree(digit, priority, null, null);
                res = new Tree(c, priority, left, right);

            }
            else
            {
                res = AddHighPriority(res, c, priority, digit);
            }
        }
        if (base_priority != 0)
        {
            errorMessage();
            return null;
        }
        return res;
    }

    /**
     * Функция замены переменных в дереве-выражении на их числовые значения
     * @param tree - дерево-выражение
     */
    public static Tree character_replacement(Tree tree) throws IOException {
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        if (Character.isLetter(tree.getElem().charAt(0)))
        {
            boolean check = false;
            char c = tree.getElem().charAt(0);
            for (int i = 0; i < count_s; i++)
            {
                if (symbols[i] == c && symbols_d[i] != -1)
                {
                    check = true;
                    tree.setElem(Integer.toString(symbols_d[i]));
                    break;
                }
            }
            while (!check) {
                check = true;
                System.out.println("Enter value " + c);
                String str = reader.readLine();
                for (int i = 0; i < str.length(); i++) {
                    if (!Character.isDigit(str.charAt(i))) {
                        check = false;
                        break;
                    }
                }
                if (!check)
                    errorMessage();
                else
                {
                    for (int i = 0; i < count_s; i++)
                    {
                        if (symbols[i] == c)
                        {
                            symbols_d[i] = Integer.parseInt(str);
                            break;
                        }
                    }
                    tree.setElem(str);
                }
            }
        }
        if (tree.getLeft() != null) tree.setLeft(computation.character_replacement(tree.getLeft()));
        if (tree.getRight() != null) tree.setRight(computation.character_replacement(tree.getRight()));
        return tree;
    }


    /**
     * Функция вычисления значения дерева-выражения
     * @param tree - дерево-выражение
     */
    public static double calculate(Tree tree)
    {
        if (tree.getElem().charAt(0) == '+')
            return calculate(tree.getRight()) + calculate(tree.getLeft());
        if (tree.getElem().charAt(0) == '-')
            return calculate(tree.getLeft()) - calculate(tree.getRight());
        if (tree.getElem().charAt(0) == '*')
            return calculate(tree.getRight()) * calculate(tree.getLeft());
        if (tree.getElem().charAt(0) == '/')
            return calculate(tree.getLeft()) / calculate(tree.getRight());
        if (Character.isDigit(tree.getElem().charAt(0)))
            return Integer.parseInt(tree.getElem());
        else return 0;
    }
}