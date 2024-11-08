package Lenovo.pl;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Methods {

    public static void printOptions(String[] tab) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option: " + ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }
    }

    public static String[][] loadDataToTab(String fileName) throws IOException {
        Path dir = Paths.get(fileName);
        if (!Files.exists(dir)) {
            System.out.println("File not exist.");
            System.exit(0);
        }
        String[][] tab = null;
        List<String> strings = Files.readAllLines(dir);
        tab = new String[strings.size()][strings.get(0).split(",").length];

        for (int i = 0; i < strings.size(); i++) {
            String[] split = strings.get(i).split(",");
            System.arraycopy(split, 0, tab[i], 0, split.length);
        }
        return tab;
    }

    public static void printTab(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void addTask() throws IOException {
        final String FILE_NAME = "src/main/java/Lenovo/pl/tasks.csv";
        String[][] tasks = new String[0][];
        tasks = loadDataToTab(FILE_NAME);


        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task due date");
        String dueDate = scanner.nextLine();
        System.out.println("Is your task important: true/false");
        String isImportant = scanner.nextLine();

        tasks =  Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length-1] = new String[3];
        tasks[tasks.length-1][0] = description;
        tasks[tasks.length-1][1] = dueDate;
        tasks[tasks.length-1][2] = isImportant;

        Path path = Paths.get(FILE_NAME);
        StringBuilder content = new StringBuilder();

        for (String[] task : tasks) {
            content.append(String.join(",", task)).append("\n");
        }

        Files.write(path, content.toString().getBytes());
        System.out.println("Tasks saved to file.");
    }

    public static void removeTask(String[][] tab, String fileName) throws IOException {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Select task to remove");
        Scanner scanner = new Scanner(System.in);

        int taskNumber = scanner.nextInt();

        tab = ArrayUtils.remove(tab, taskNumber);

        StringBuilder content = new StringBuilder();
        for (String[] task : tab) {
            content.append(String.join(",", task)).append("\n");
        }

        Files.write(Paths.get(fileName), content.toString().getBytes());
        System.out.println("Task removed and file updated successfully.");

    }

}

