public class Bubblesort {
    private int[] array;
    
    public Bubblesort (int length) {
        this.array = new int[length];
    }

    public void bubblesort() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    public void bubblesortAPartirDoMenor() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length-2; j > i; j--) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    private void swap (int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
