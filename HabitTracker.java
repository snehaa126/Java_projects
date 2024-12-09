import java.time.LocalDate;
import java.util.*;

public class HabitTracker {
    private static class Habit {
        String name;
        Map<LocalDate, Boolean> completionLog;

        Habit(String name) {
            this.name = name;
            this.completionLog = new HashMap<>();
        }

        void markCompleted(LocalDate date) {
            completionLog.put(date, true);
        }

        double getCompletionRate() {
            if (completionLog.isEmpty()) return 0.0;
            
            long completedDays = completionLog.values().stream()
                .filter(completed -> completed)
                .count();
            
            return (double) completedDays / completionLog.size() * 100;
        }

        void displayProgress() {
            System.out.println("\nProgress for " + name + ":");
            
            // ASCII Bar Chart
            int completionRate = (int) getCompletionRate();
            System.out.print("Progress: [");
            for (int i = 0; i < completionRate / 10; i++) {
                System.out.print("█");
            }
            for (int i = completionRate / 10; i < 10; i++) {
                System.out.print(".");
            }
            System.out.printf("] %d%%\n", completionRate);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Habit> habits = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Personal Habit Tracker ---");
            System.out.println("1. Add New Habit");
            System.out.println("2. Log Habit Completion");
            System.out.println("3. View Habit Progress");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter habit name: ");
                    String habitName = scanner.nextLine();
                    habits.add(new Habit(habitName));
                }

                case 2 -> {
                    if (habits.isEmpty()) {
                        System.out.println("No habits created yet!");
                        break;
                    }
                    
                    System.out.println("Select a habit:");
                    for (int i = 0; i < habits.size(); i++) {
                        System.out.println((i + 1) + ". " + habits.get(i).name);
                    }
                    
                    int habitIndex = scanner.nextInt() - 1;
                    habits.get(habitIndex).markCompleted(LocalDate.now());
                    System.out.println("Habit logged successfully!");
                }

                case 3 -> {
                    for (Habit habit : habits) {
                        habit.displayProgress();
                    }
                }

                case 4 -> {
                    System.out.println("Exiting Habit Tracker. Keep growing!");
                    return;
                }
            }
        }
    }
}