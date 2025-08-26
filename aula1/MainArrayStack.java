package aula1;

public class MainArrayStack {
    public static void main (String args[]) {
        ArrayStack stack = new ArrayStack(5);
        stack.push("Instituto");
        stack.push("Federal");
        String temp = (String) stack.peek();
        System.out.println(temp);

        stack.push("da");
        stack.push("Bahia");
        String state = (String) stack.pop();
        System.out.println(state);
        stack.push("Paraíba");
        System.out.println(stack.print());
    }
}
