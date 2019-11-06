import java.util.concurrent.ThreadLocalRandom;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        print_prompt("Generate array:");
        int[] random_array = generate_random_array(20);
        print_array(random_array);
        int start = ThreadLocalRandom.current().nextInt(0, random_array.length - 1);
        print_prompt("Start to score:");
        print_init_arrow(start);
        print_array(random_array);
        score(start, random_array);
    }

    private static void score(int start, int[] random_array) throws InterruptedException {
        int size = random_array.length;
        int distance = random_array[start];
        if (distance > start) {
            // can only move to right
            print_next_arrow(start, random_array[start], "right");
        } else {
            if (distance + start > size) {
                // can only move to left
                print_next_arrow(start, random_array[start], "left");
            }else{
                // can move to both sides
                print_next_arrow(start, random_array[start], "left");
            }
        }

        print_array(random_array);
    }

    private static int[] generate_random_array(int max) {
        // the size of an array in the range [2, max]
        int size = ThreadLocalRandom.current().nextInt(2, max + 1);
        int[] array = new int[size];
        for (int i = 0; i < size - 1; i++) {
            int distance = size - i > i ? size - i : i;
            array[i] = ThreadLocalRandom.current().nextInt(1, distance);
        }
        array[size - 1] = 0;
        return array;
    }

    private static int width = 5;

    private static void print_array(int[] array) throws InterruptedException {
        int size = array.length;
        System.out.print("+");
        System.out.print(String.format("%" + (size * width - 2) + "s", " ").replace(" ", "-"));
        System.out.print("+");
        System.out.println();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                System.out.print(String.format("%-" + (width - 1) + "s", "| " + array[i]));
                System.out.print("|");
            } else {
                System.out.print(String.format("%-" + width + "s", "| " + array[i]));
            }
        }
        System.out.println();
        System.out.print("+");
        System.out.print(String.format("%" + (size * width - 2) + "s", " ").replace(" ", "-"));
        System.out.print("+");
        System.out.println();
        System.out.println();
        Thread.sleep(1000);
    }

    private static void print_init_arrow(int index) throws InterruptedException {
        System.out.println("Randomly start from index " + index + ".");
        Thread.sleep(800);
        System.out.println(String.format("%" + (index * width + width / 2 + 1) + "s", "\u25BC"));
    }

    private static void print_next_arrow(int index, int step, String direction) throws InterruptedException {
        System.out.println("From index " + index + ", move to " + direction + " " + step + " steps.");
        Thread.sleep(800);
        if(direction.equals("left")){
            System.out.println(String.format("%" + ((index - step) * width + width / 2 + 1) + "s", "\u25BC"));
        }else{
            System.out.println(String.format("%" + ((index + step) * width + width / 2 + 1) + "s", "\u25BC"));
        }
    }

    private static void print_prompt(String prompt) throws InterruptedException {
        System.out.println();
        // System.out.println(String.format("%100s", "=").replace(" ", "="));
        System.out.println(String.format("%-100s", "\u25C9 " + prompt));
        // System.out.println(String.format("%100s", "=").replace(" ", "="));
        System.out.println();
        Thread.sleep(1000);
    }
}