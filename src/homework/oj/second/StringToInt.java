package homework.oj.second;

import com.sun.tools.javac.util.List;

import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-13-18:31
 **/
public class StringToInt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String res = "";
        while (sc.hasNextLine()) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                res += sc.nextLine();
            }
            System.out.println(res);
        }
    }
}
