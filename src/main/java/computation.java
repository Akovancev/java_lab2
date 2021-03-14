public class computation {

    private static void errorMessage() {
        System.out.println("Error");
    }

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

    public static Tree transform(String str) {
        int base_priority = 0;
        int i = 0;
        while (str.charAt(i) == '(') {
            base_priority ++;
            i++;
        }
        String digit = "";
        while (Character.isDigit(str.charAt(i))) {
            digit += str.charAt(i);
            i++;
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
            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                digit += str.charAt(i);
                i++;
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