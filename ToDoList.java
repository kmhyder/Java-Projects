import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class ToDoList {

    // 🔁 Save tasks to file
    public static void saveTasks(ArrayList<String> tasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.txt"));
            for (String task : tasks) {
                bw.write(task);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // 🔁 Load tasks from file
    public static void loadTasks(ArrayList<String> tasks) {
        try {
            File file = new File("tasks.txt");
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    tasks.add(line);
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> al = new ArrayList<>();

        // ✅ Load tasks from file on startup
        loadTasks(al);

        boolean b = true;

        do {
            System.out.println("\nEnter your choice: add | delete | show | exit");
            String ch = sc.next();
            sc.nextLine(); // consume newline

            switch (ch) {
                case "add":
                    System.out.print("Enter task: ");
                    String name = sc.nextLine();
                    al.add(name);
                    saveTasks(al);  // ✅ Save after adding
                    System.out.println("\"" + name + "\" added.");
                    break;

                case "delete":
                    System.out.print("Enter task number to delete: ");
                    int t = sc.nextInt();
                    sc.nextLine(); // consume newline
                    if (t >= 1 && t <= al.size()) {
                        name = al.remove(t - 1);
                        saveTasks(al);  // ✅ Save after deleting
                        System.out.println("\"" + name + "\" deleted successfully.");
                    } else {
                        System.out.println("Invalid task number!");
                    }
                    break;

                case "show":
                    if (al.isEmpty()) {
                        System.out.println("No tasks found.");
                    } else {
                        System.out.println("Your tasks:");
                        for (int i = 0; i < al.size(); i++) {
                            System.out.println((i + 1) + ". " + al.get(i));
                        }
                    }
                    break;

                case "exit":
                    b = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (b);

        System.out.println("Program exited. Your tasks are saved in tasks.txt.");
        sc.close();
    }
}
