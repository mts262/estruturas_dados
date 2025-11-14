public class Selectionsort {
    int[] array;
    
    public Selectionsort (int length) {
        this.array = new int[length];
    }

    public void selectionsort() {
        for (int i = 0; i < array.length; i++) {
            int minorIndex = 0;
            for (int j = 1+i; j < array.length; j++) {
                if (array[j] < array[minorIndex]) {
                    minorIndex = j;
                }
            }
            swap(i, minorIndex);
        }
    }

    public void selectionsortAPartirDoMaoir() {
        for (int i = 0; i < array.length; i++) {
            int minorIndex = 0;
            for (int j = 1+i; j < array.length; j++) {
                if (array[j] > array[minorIndex]) {
                    minorIndex = j;
                }
            }
            swap(i, minorIndex);
        }
    }

    public void swap (int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
