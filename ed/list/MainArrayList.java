package br.edu.ifba.bsi.ed.list;

public class MainArrayList {
    public static void main (String[] args) {
        ArrayList list = new ArrayList(6);

        list.append("a");
        list.append("b");
        list.append("c");
        list.select(2);
        list.delete(1);
        System.out.println(list.print());
        list.append("d");
        list.append("e");
        list.update("f", 0);
        list.insert("5", 2);
        System.out.println(list.print());
        list.clear();
        list.append("V");
        System.out.println(list.print());


    }
}
