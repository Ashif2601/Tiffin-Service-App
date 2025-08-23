import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class QuizApp extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options = new JRadioButton[4];
    private ButtonGroup group;
    private JButton nextButton;
    private int currentQuestion = 0, score = 0;
    private String[][] questions;
    private String userName;
    private Map<String, java.util.List<String[]>> questionsByTopic = new HashMap<>();
    private boolean answered = false;

    public QuizApp(String userName) {
        this.userName = userName;
        setTitle("Quiz App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize questions
        initializeQuestions();

        // Panel for quiz
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 80, 10, 10); // 15% left alignment

        questionLabel = new JLabel("Question will appear here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        quizPanel.add(questionLabel, gbc);

        group = new ButtonGroup();
        gbc.gridy++;

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 14));
            options[i].setForeground(Color.WHITE);
            options[i].setBackground(Color.GRAY); // Default color
            options[i].setOpaque(true);
            group.add(options[i]);
            gbc.gridy++;
            quizPanel.add(options[i], gbc);
        }

        nextButton = new JButton("Submit");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!answered) {
                    checkAnswer();
                } else {
                    nextQuestion();
                }
            }
        });

        gbc.gridy++;
        quizPanel.add(nextButton, gbc);

        add(quizPanel, BorderLayout.CENTER);
    }

    private void initializeQuestions() {
        questionsByTopic.put("Geography", new ArrayList<>(java.util.List.of(
                new String[]{"What is the capital of France?", "Berlin", "Paris", "Madrid", "Rome", "2"},
                new String[]{"Which continent is the Sahara Desert in?", "Asia", "Africa", "Australia", "Europe", "2"},
                new String[]{"What is the largest ocean?", "Atlantic", "Indian", "Pacific", "Arctic", "3"},
                new String[]{"Which of the following geographical term related to the piece of sub-continental land that is surrounded by water?", "Peninsula", "Gulf", "Strait", "Island", "4"}
        )));

        questionsByTopic.put("Drill", new ArrayList<>(java.util.List.of(
                new String[]{"Who started the drill?", "Sam Manekshaw", "General Drale", "Pt. Hridayanath Kunjru", "K.M.Cariappa", "2"},
                new String[]{"When was the drill started?", "1948", "1666", "1857", "1999", "2"},
                new String[]{"Where did the drill originate from?", "America", "India", "France", "Germany", "4"},
                new String[]{"How many types of drill are there?", "3", "4", "5", "2", "4"},
                new String[]{"How many parts are there in a word of command?", "2", "4", "3", "5", "1"},
                new String[]{"Who is authorized to give a national salute?", "Battalion commander", "President", "General", "None of the above", "2"},
                new String[]{"What is the quick march?", "90 steps/min", "100 steps/min", "120 steps/min", "140 steps/min", "3"},
                new String[]{"How many type of Salutes are there?", "2", "4", "5", "3", "4"},
                new String[]{"What is the initial activity of drill?", "Discipline", "Attention", "Coordination", "Very quick", "2"},
                new String[]{"What is the speed of the squad in slow march?", "60 steps/min", "70 steps/min", "65 steps/min", "75 steps/min", "2"}
        )));

        questionsByTopic.put("Technology", new ArrayList<>(java.util.List.of(
                new String[]{"Who developed Java?", "Microsoft", "Apple", "Sun Microsystems", "IBM", "3"},
                new String[]{"What does CPU stand for?", "Central Processing Unit", "Computer Personal Unit", "Central Program Utility", "Control Processing Unit", "1"},
                new String[]{"Which company developed Windows OS?", "Apple", "Microsoft", "Google", "Linux", "2"}
        )));
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            String[] questionData = questions[currentQuestion];

            questionLabel.setText(questionData[0]);

            // Clear previous selection and reset option colors
            group.clearSelection();
            for (JRadioButton option : options) {
                option.setBackground(Color.GRAY);
            }

            // Store options with their original index
            java.util.List<String[]> optionList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                optionList.add(new String[]{questionData[i + 1], String.valueOf(i + 1)});
            }

            Collections.shuffle(optionList); // Shuffle options

            // Assign shuffled options to buttons & update correct answer index
            String correctOptionIndex = questionData[5];
            for (int i = 0; i < 4; i++) {
                options[i].setText(optionList.get(i)[0]);

                if (optionList.get(i)[1].equals(correctOptionIndex)) {
                    questionData[5] = String.valueOf(i + 1);
                }
            }

            answered = false;
            nextButton.setText("Submit");
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Over! " + userName + ", your score: " + score + " Thank you!");
            System.exit(0);
        }
    }

    private void checkAnswer() {
        boolean selected = false;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = true;
                if (String.valueOf(i + 1).equals(questions[currentQuestion][5])) {
                    options[i].setBackground(Color.GREEN);
                    score++;
                } else {
                    options[i].setBackground(Color.RED);
                }
            }
        }

        if (!selected) {
            JOptionPane.showMessageDialog(this, "Please select an answer!");
            return;
        }

        highlightCorrectAnswer();
        answered = true;
        nextButton.setText("Next");
    }

    private void highlightCorrectAnswer() {
        int correctIndex = Integer.parseInt(questions[currentQuestion][5]) - 1;
        options[correctIndex].setBackground(Color.GREEN);
    }

    private void nextQuestion() {
        currentQuestion++;
        loadQuestion();
    }

    public static void main(String[] args) {
        String userName = JOptionPane.showInputDialog("Enter your name:");
        QuizApp quiz = new QuizApp(userName);
        quiz.initializeQuestions();

        // Ask user to select a topic
        String[] topics = quiz.questionsByTopic.keySet().toArray(new String[0]);
        String selectedTopic = (String) JOptionPane.showInputDialog(
                null, "Select a topic:", "Quiz Topics",
                JOptionPane.QUESTION_MESSAGE, null, topics, topics[0]
        );

        if (selectedTopic != null && quiz.questionsByTopic.containsKey(selectedTopic)) {
            java.util.List<String[]> questionList = quiz.questionsByTopic.get(selectedTopic);
            Collections.shuffle(questionList); // 🔄 Shuffle question order
            quiz.questions = questionList.toArray(new String[0][]);
            quiz.currentQuestion = 0;
            quiz.loadQuestion();
        }

        SwingUtilities.invokeLater(() -> quiz.setVisible(true));
    }
}
