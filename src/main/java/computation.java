public class computation {

    private static Tree AddHighPriority(Tree t, String str, String digit) {
        if (t.getRight() != null)
            t.setRight(AddHighPriority(t.getRight(), str, digit));
        else if (t.getLeft() != null)
            t.setLeft(AddHighPriority(t.getLeft(), str, digit));
        else {
            Tree temp1 = t;
            Tree temp2 = new Tree(digit, 1, null, null);
            t = new Tree(str, 1, temp1, temp2);
        }
        return t;
    }

    public static Tree transform(String str) {
        int priority = 0;
        int i = 0;
        while (str.charAt(i) == '(') {
            priority++;
            i++;
        }
        String digit = "";
        while (Character.isDigit(str.charAt(i))) {
            digit += str.charAt(i);
            i++;
        }
        Tree res = new Tree(digit, priority, null, null);
        while (i < str.length()) {
            String c = "&";
            if (str.charAt(i) == '+') {
                c = "+";
                priority = 0;
            }
            if (str.charAt(i) == '*') {
                c = "*";
                priority++;
            }
            if (str.charAt(i) == '-') {
                c = "-";
                priority = 0;
            }
            if (str.charAt(i) == '/') {
                c = "/";
                priority++;
            }
            i++;
            digit = "";
            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                digit += str.charAt(i);
                i++;
            };
            //System.out.println(digit);
            if (priority <= res.getPriority())
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
                res = AddHighPriority(res, c, digit);
            }
        }
        return res;
    }

}