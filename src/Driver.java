import java.util.concurrent.ThreadLocalRandom;

public class Driver {
    public static void main(String[] args) {
        int[] random_array = generate_random_array();
        System.out.print(random_array.length);
        for(int i : random_array){
            System.out.print(i + "\t");
        }
    }

    private static int[] generate_random_array() {
        // the size of an array in the range [2, 100]
        int size = ThreadLocalRandom.current().nextInt(2, 100 + 1);
        int[] array = new int[size];
        for (int i = 0; i < array.length -1 ; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        }
        array[array.length-1] = 0;
        return array;
    }
}