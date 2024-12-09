import java.util.*;

public class RockPaperScissorsAI {
    private Scanner scanner;
    private final Random random;
    private int playerScore;
    private int aiScore;
    private final List<String> choices;
    private Map<String, String> winningMoves;

    public RockPaperScissorsAI() {
        scanner = new Scanner(System.in);
        random = new Random();
        playerScore = 0;
        aiScore = 0;
        
        choices = Arrays.asList("rock", "paper", "scissors");
        
        winningMoves = new HashMap<>();
        winningMoves.put("rock", "scissors");
        winningMoves.put("scissors", "paper");
        winningMoves.put("paper", "rock");
    }

    public RockPaperScissorsAI(List<String> choices, Random random) {
        this.choices = choices;
        this.random = random;
    }

    public void play() {
        System.out.println("Welcome to Rock-Paper-Scissors!");
        
        while (true) {
            String playerChoice = getPlayerChoice();
            String aiChoice = getAIChoice();

            System.out.println("\nYou chose: " + playerChoice);
            System.out.println("AI chose: " + aiChoice);

            determineWinner(playerChoice, aiChoice);
            displayScore();

            if (!playAgain()) break;
        }

        displayFinalResult();
    }

    private String getPlayerChoice() {
        while (true) {
            System.out.print("\nEnter your choice (rock/paper/scissors) or 'quit': ");
            String choice = scanner.nextLine().toLowerCase();
            
            if (choice.equals("quit")) {
                System.exit(0);
            }
            
            if (choices.contains(choice)) {
                return choice;
            }
            
            System.out.println("Invalid choice. Try again.");
        }
    }

    private String getAIChoice() {
        // Simple AI with some strategy
        if (random.nextDouble() < 0.7) {
            // 70% chance of random choice
            return choices.get(random.nextInt(choices.size()));
        } else {
            // 30% chance of "smart" choice based on previous rounds
            return getPredictiveChoice();
        }
    }

    private String getPredictiveChoice() {
        // Basic predictive strategy
        if (playerScore > aiScore) {
            // If player is winning, AI tries to counter
            return winningMoves.get(choices.get(random.nextInt(choices.size())));
        }
        return choices.get(random.nextInt(choices.size()));
    }

    private void determineWinner(String playerChoice, String aiChoice) {
        if (playerChoice.equals(aiChoice)) {
            System.out.println("It's a tie!");
        } else if (winningMoves.get(playerChoice).equals(aiChoice)) {
            System.out.println("You win this round!");
            playerScore++;
        } else {
            System.out.println("AI wins this round!");
            aiScore++;
        }
    }

    private void displayScore() {
        System.out.println("\nScore:");
        System.out.println("You: " + playerScore);
        System.out.println("AI: " + aiScore);
    }

    private boolean playAgain() {
        System.out.print("\nDo you want to play again? (yes/no): ");
        return scanner.nextLine().toLowerCase().equals("yes");
    }

    private void displayFinalResult() {
        System.out.println("\nFinal Score:");
        System.out.println("You: " + playerScore);
        System.out.println("AI: " + aiScore);

        if (playerScore > aiScore) {
            System.out.println("Congratulations! You won the game!");
        } else if (playerScore < aiScore) {
            System.out.println("AI won the game. Better luck next time!");
        } else {
            System.out.println("The game ended in a tie!");
        }
    }

    public static void main(String[] args) {
        RockPaperScissorsAI game = new RockPaperScissorsAI();
        game.play();
    }
}