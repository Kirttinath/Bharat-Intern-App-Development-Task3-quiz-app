package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button submitButton;

    private String[] questions = {
            "Question 1: What is the chemical symbol for gold?",
            "Question 2: Which planet is known as the Red Planet?",
            "Question 3: Which gas do plants absorb from the atmosphere?",
            "Question 4: What is the process by which plants make their own food using sunlight?",
            "Question 5:  What is the smallest unit of matter?",
            "Question 6:  What is the process of a liquid turning into a gas at its boiling point?",
            "Question 7: Who is known as the father of modern physics?",
            "Question 8: Which force is responsible for an object's weight?",
            "Question 9: What is the chemical symbol for water?",
            "Question 10: Which gas makes up the majority of Earth's atmosphere?"
    };

    private String[][] options = {
            {"Go", "Ag", "Au", "Ge"},
            {"Venus", "Mars", "Jupiter", "Saturn"},
            {"Oxygen", "Carbon dioxide", "Nitrogen", "Hydrogen"},
            {"Respiration", "Photosynthesis", " Fermentation", "Transpiration"},
            {" Atom", "Molecule", "Proton", "Electron"},
            {"Freezing", "Evaporation", " Condensation", " Sublimation"},
            {" Isaac Newton", " Galileo Galilei", "  Albert Einstein", "Nikola Tesla"},
            {"Magnetic force", "Frictional force", "Gravitational force", "Electrostatic force"},
            {" H2O", " CO2", "O2", "NaCl"},
            {"Carbon dioxide", "Oxygen", " Nitrogen", "Methane"}
    };

    private int[] correctAnswers = {2, 1,1,1,0,1,2,2,0,2};

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        displayQuestion(currentQuestionIndex);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion(int index) {
        questionTextView.setText(questions[index]);
        RadioButton[] radioButtons = new RadioButton[4];
        radioButtons[0] = findViewById(R.id.option1RadioButton);
        radioButtons[1] = findViewById(R.id.option2RadioButton);
        radioButtons[2] = findViewById(R.id.option3RadioButton);
        radioButtons[3] = findViewById(R.id.option4RadioButton);

        for (int i = 0; i < 4; i++) {
            radioButtons[i].setText(options[index][i]);
        }
    }

    private void checkAnswer() {
        RadioButton selectedRadioButton = findViewById(optionsRadioGroup.getCheckedRadioButtonId());
        if (selectedRadioButton == null) {
            Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedAnswerIndex = optionsRadioGroup.indexOfChild(selectedRadioButton);
        if (selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
            score++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            displayQuestion(currentQuestionIndex);
            optionsRadioGroup.clearCheck();
        } else {
            // Quiz is finished, display the score.
            displayScore();
        }
    }

    private void displayScore() {
        Toast.makeText(this, "Your Score: " + score + " out of " + questions.length, Toast.LENGTH_LONG).show();
        // You can also implement a result activity to show the final score in a more detailed format.
    }
}