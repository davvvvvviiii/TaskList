package Lenovo.pl;

import java.io.IOException;
import java.util.Scanner;
import static Lenovo.pl.Methods.*;

public class Main {
    public static void main(String[] args) throws IOException {

        final String FILE_NAME = "src/main/java/Lenovo/pl/tasks.csv";
        final String[] OPTIONS = {"add", "remove", "list", "exit"};

        String[][] tasks;
        tasks = loadDataToTab(FILE_NAME);

        printOptions(OPTIONS);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit":
                    System.out.println(ConsoleColors.RED + "Bye, bye.");
                    System.exit(0);
                    break;
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask(tasks, FILE_NAME);
                    break;
                case "list":
                    printTab(tasks);
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            printOptions(OPTIONS);
        }

    }


}