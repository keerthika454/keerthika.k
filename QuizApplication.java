import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizApplication {

    private List<Question> questions;
    private int score;
    private List<Boolean> answerResults;
    private static final int TIME_LIMIT_SECONDS = 10;

    public QuizApplication() {
        questions = new ArrayList<>();
        score = 0;
        answerResults = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"A. Paris", "B. London", "C. Berlin", "D. Madrid"}, 0));
        questions.add(new Question("Who wrote 'Hamlet'?", new String[]{"A. Charles Dickens", "B. William Shakespeare", "C. Mark Twain", "D. Jane Austen"}, 1));
        questions.add(new Question("What is the smallest planet in our solar system?", new String[]{"A. Earth", "B. Mars", "C. Mercury", "D. Venus"}, 2));
        questions.add(new Question("Which element has the chemical symbol 'O'?", new String[]{"A. Gold", "B. Oxygen", "C. Silver", "D. Iron"}, 1));
        questions.add(new Question("What is the capital of Japan?", new String[]{"A. Beijing", "B. Seoul", "C. Bangkok", "D. Tokyo"}, 3));
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());

            String[] options = question.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println(options[j]);
            }

            long startTime = System.currentTimeMillis();
            long endTime = startTime + TimeUnit.SECONDS.toMillis(TIME_LIMIT_SECONDS);

            boolean answered = false;
            int answer = -1;

            while (System.currentTimeMillis() < endTime && !answered) {
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                    if (answer >= 1 && answer <= options.length) {
                        answered = true;
                    } else {
                        System.out.println("Invalid option. Please choose between 1 and " + options.length + ".");
                    }
                }
            }

            if (answered) {
                if (answer - 1 == question.getCorrectAnswerIndex()) {
                    score++;
                    answerResults.add(true);
                } else {
                    answerResults.add(false);
                }
            } else {
                System.out.println("Time's up! Moving to the next question.");
                answerResults.add(false);
            }
        }

        displayResults();
        scanner.close();
    }

    private void displayResults() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        System.out.println("Summary of Answers:");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            boolean correct = answerResults.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            System.out.println("Your answer was " + (correct ? "correct" : "incorrect") + ".");
        }
    }

    public static void main(String[] args) {
        QuizApplication quizApp = new QuizApplication();
        quizApp.startQuiz();
    }
}
