import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        System.out.println("Enter expression");
        String str = reader.readLine();
        Tree res = computation.transform(str);
        if (res != null) {
            res = computation.character_replacement(res);
            System.out.println("Value = " + computation.calculate(res));
        }
    }
}
