package memoriakezeles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        KeretTabla keretTabla = new KeretTabla();
        LapTabla lapTabla = new LapTabla();
        LRU lru = new LRU(lapTabla, keretTabla);

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(",");

        while (scanner.hasNextInt()) {
            lru.nextTask(Math.abs(scanner.nextInt()));
        }
        System.out.print("\n" + lru.laphibakSzama);
    }
}
