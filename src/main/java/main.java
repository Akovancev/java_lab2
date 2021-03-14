public class main {

    public static void main(String[] args) {
        String str = "1*2*3+4*5+6+7+8*9";
        Tree res = computation.transform(str);
        Tree.printTree(res);
    }
}
