import java.util.*;

public class Recursion {

    public static void main (String[] args) {
        TreeNode head = new TreeNode(1);
        head.next = new TreeNode(4);
        head.next.next = new TreeNode(7);
        head.next.next.next = new TreeNode(10);

        TreeNode head2 = new TreeNode(2);
        head2.next = new TreeNode(3);
        head2.next.next = new TreeNode(5);
        head2.next.next.next = new TreeNode(8);


    }

    public static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode next;
        TreeNode () {}
        TreeNode (int val) {this.val = val;}
        TreeNode (int val, TreeNode right, TreeNode left, TreeNode next) {
            this.left = left;
            this.right = right;
            this.val = val;
            this.next = next;
        }
    }

    //Add Two Numbers
    public TreeNode addTwoNumbers(TreeNode l1, TreeNode l2) {
        TreeNode dummyHead = new TreeNode(0);
        TreeNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new TreeNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new TreeNode(carry);
        }
        return dummyHead.next;
    }   
    
    //Decode String
    int index = 0;
    String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index)))
                result.append(s.charAt(index++));
            else {
                int k = 0;
                // build k while next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index)))
                    k = k * 10 + s.charAt(index++) - '0';
                // ignore the opening bracket '['    
                index++;
                String decodedString = decodeString(s);
                // ignore the closing bracket ']'
                index++;
                // build k[decodedString] and append to the result
                while (k-- > 0)
                    result.append(decodedString);
            }
        }
        return new String(result);
    } 
    
    
    //
    
    







}