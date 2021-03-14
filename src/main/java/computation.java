public class computation {

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
            //if (t.getLeft() != null)
               // t.setLeft(AddHighPriority(t.getLeft(), str, priority, digit));
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
            String c = "&";
            if (i < str.length() && str.charAt(i) == '+') {
                c = "+";
                priority = base_priority * 2;
            }
            if (i < str.length() && str.charAt(i) == '*') {
                c = "*";
                priority = base_priority * 2 + 1;
            }
            if (i < str.length() && str.charAt(i) == '-') {
                c = "-";
                priority = base_priority * 2;
            }
            if (i < str.length() && str.charAt(i) == '/') {
                c = "/";
                priority = base_priority * 2 + 1;
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
            digit = "";
            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                digit += str.charAt(i);
                i++;
            }
            if (digit == "" || c == "&") break;
            //System.out.println(digit);
            if (priority <= res.getPriority() || Character.isDigit(res.getElem().charAt(0)))
            {
                /*
                Tree right = res.getRight();
                Tree left = res.getLeft();
                if (right != null && left != null && Character.isDigit(right.getElem().charAt(0)) && !Character.isDigit(left.getElem().charAt(0))) {
                    Tree temp1 = new Tree(right.getElem(), priority, null,null);
                    Tree temp2 = new Tree(Integer.toString(digit), priority, null,null);
                    right = new Tree(c, priority, temp1, temp2);
                    res.setRight(right);
                }
                else if (right != null && left != null && !Character.isDigit(right.getElem().charAt(0)) && Character.isDigit(left.getElem().charAt(0))) {
                    Tree temp1 = new Tree(left.getElem(), priority, null,null);
                    Tree temp2 = new Tree(Integer.toString(digit), priority, null,null);
                    left = new Tree(c, priority, temp1, temp2);
                    res.setLeft(left);
                }
                else {
                    left = res;
                    right = new Tree(Integer.toString(digit), priority, null, null);
                    res = new Tree(c, priority, left, right);
                }
                */
                Tree left = res;
                Tree right = new Tree(digit, priority, null, null);
                res = new Tree(c, priority, left, right);

            }
            else
            {
                res = AddHighPriority(res, c, priority, digit);
            }
        }
        return res;
    }
}