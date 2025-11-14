public class Insertionsort {
    int[] array;
    
    public Insertionsort (int length) {
        this.array = new int[length];
    }

    public void insertionsort() {
        for (int j = 1; j < array.length; j++) {
            int i = j;
            // Enquanto o elemento atual for menor do que o anterior, troca
            while (i > 0 && array[i] < array[i - 1]) {
                swap(i, i - 1);
                i--;
            }
        }
    }

    private void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }



}
