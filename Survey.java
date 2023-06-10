import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Survey {
    private String title;
    private List<Question> questions;

    public Survey(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void displaySurvey() {
        System.out.println("Survey: " + title);
        System.out.println("------------------------");

        for (Question question : questions) {
            question.displayQuestion();
            System.out.println();
        }
    }

    public void gatherResponses() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide your responses:");
        System.out.println("------------------------");

        for (Question question : questions) {
            question.displayQuestion();
            question.displayOptions();
            question.gatherResponse(scanner);
            System.out.println();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Survey survey = new Survey("Customer Satisfaction Survey");

        // Add questions to the survey
        MultipleChoiceQuestion question1 = new MultipleChoiceQuestion("How satisfied are you with our product?");
        question1.addOption("Very satisfied");
        question1.addOption("Satisfied");
        question1.addOption("Neutral");
        question1.addOption("Dissatisfied");
        question1.addOption("Very dissatisfied");
        survey.addQuestion(question1);

        MultipleChoiceQuestion question2 = new MultipleChoiceQuestion("Would you recommend our product to others?");
        question2.addOption("Definitely");
        question2.addOption("Probably");
        question2.addOption("Not sure");
        question2.addOption("Unlikely");
        question2.addOption("Definitely not");
        survey.addQuestion(question2);

        // Display the survey and gather responses
        survey.displaySurvey();
        survey.gatherResponses();

        // Display summary of responses
        System.out.println("Summary of Responses:");
        System.out.println("------------------------");
        for (Question question : survey.questions) {
            question.displayQuestion();
            question.displayOptions();
            question.displayResponse();
            System.out.println();
        }
    }
}

interface Question {
    String getQuestionText();
    void displayQuestion();
    void displayOptions();
    void gatherResponse(Scanner scanner);
    void displayResponse();
}

class MultipleChoiceQuestion implements Question {
    private String questionText;
    private List<String> options;
    private int userResponse;

    public MultipleChoiceQuestion(String questionText) {
        this.questionText = questionText;
        this.options = new ArrayList<>();
        this.userResponse = -1;
    }

    public void addOption(String option) {
        options.add(option);
    }

    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public void displayQuestion() {
        System.out.println("Question: " + questionText);
    }

    @Override
    public void displayOptions() {
        System.out.println("Options:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    @Override
    public void gatherResponse(Scanner scanner) {
        System.out.print("Enter your response (1-" + options.size() + "): ");
        int response = scanner.nextInt();
        if (response >= 1 && response <= options.size()) {
            userResponse = response - 1;
        }
    }

    @Override
    public void displayResponse() {
        if (userResponse != -1) {
            System.out.println("Response: ");
        }}}