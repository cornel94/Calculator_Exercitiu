import java.util.Stack;

public class Basic_Calculator {

    public static boolean isLetterOrDigit(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        }
        return false;
    }

    public static int getPrecedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '^') {
            return 3;
        } else {
            return -1;
        }
    }

    public static boolean hasLeftToRightAsociativity(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        }
        return false;
    }

    public static String postfix(String expression) {
        Stack<Character> stack = new Stack<>();
        String output = new String("");
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (isLetterOrDigit(c)) {
                int n = 0;
                while (Character.isDigit(c)){
                    n = n * 10 + (int)(c - '0');
                    i++;
                    c = expression.charAt(i);
                }
                output += n + " ";
                i--;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output += stack.pop();
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek()) &&
                        hasLeftToRightAsociativity(c)) {
                    output += stack.pop();
                }
                if (c != ' '){
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "This expression is invalide";
            }
            output += stack.pop() + " ";
        }
        return output;
    }

    public static int result(String exp) {
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == ' ') {
                continue;}
            if (Character.isDigit(c)) {
                int n = 0;
                while (Character.isDigit(c)) {
                    n = n * 10 + (int) (c - '0');
                    i++;
                    c = exp.charAt(i);
                }
                i--;
                stack.push(n);
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;

                    case '-':
                        stack.push(val2 - val1);
                        break;

                    case '/':
                        stack.push(val2 / val1);
                        break;

                    case '*':
                        stack.push(val2 * val1);
                        break;

                    case '^':
                        stack.push((int) Math.pow(val2, val1));
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String infix = "5 + 2 / ( 3 - 8 ) ^ 5 ^ 2 ";
        String postfix = postfix(infix);
        System.out.println(postfix);
        System.out.println(result(postfix));
    }
}
