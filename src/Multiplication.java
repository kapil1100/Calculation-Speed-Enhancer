import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Multiplication {

    double  sum =1;                //multiplication of given problem
    int inputSum;               //input multiplication answers

    public static JComboBox choice = new JComboBox(new String[]{"2x2","5x2"});

    public Multiplication() {
        go();
    }

    public void go(){
        JFrame frame = new JFrame("Multiplication Speed Enhancer");
        JTextArea textArea = new JTextArea();
        JButton nextButton = new JButton("Next");
        JButton showAnswerButton = new JButton("Show Answer");

        //Panels
        JPanel panel = new JPanel((new BorderLayout(15, 10)));          //main panel
        JPanel panel2 = new JPanel(new BorderLayout(20,0));             //bottom panel
        JPanel panel3 = new JPanel(new BorderLayout(0,15));             //center panel


        //Drop down menu

        choice.setFont(new Font("SansSerif", Font.BOLD, 30));

        //action listener of drop down
        choice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(getMultiplicationText());
            }
        });

        panel.add(choice, BorderLayout.NORTH);
        panel.add(panel3, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.SOUTH);


        //text area
        textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textArea.setEditable(false);
        textArea.setText(getMultiplicationText());
        textArea.setFont(new Font("SansSerif", Font.BOLD, 40));

        panel3.add(textArea, BorderLayout.NORTH);


        //text fields
        JTextField inputField = new JTextField();
        JTextField checkField = new JTextField();

        inputField.setFont(new Font("SansSerif", Font.BOLD, 40));
        inputField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        inputField.setEditable(true);
        inputField.grabFocus();
        inputField.setCaretPosition(0);

        checkField.setFont(new Font("SansSerif", Font.BOLD, 40));
        checkField.setEditable(false);
        checkField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        panel3.add(inputField, BorderLayout.CENTER);
        panel3.add(checkField, BorderLayout.SOUTH);


        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputSum == sum)
                    nextQuestion(frame);
            }
        });



        //key listener
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //inputField.setCaretPosition(0);
            }

            public void keyPressed(KeyEvent keyEvent) {
                //inputField.setCaretPosition(0);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputField.setCaretPosition(0);

                inputSum = parseInt(inputField.getText());
                if(inputSum == sum)
                    checkField.setText("Correct");
                else
                    checkField.setText("Wrong");
            }
        };
        inputField.addKeyListener(keyListener);



        //window listener - sets focus on input text field automatically
        frame.addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                inputField.requestFocus();
            }
        });


        //buttons
        panel2.add(showAnswerButton, BorderLayout.WEST);
        panel2.add(nextButton, BorderLayout.EAST);

        panel.add(panel2, BorderLayout.SOUTH);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestion(frame);
            }
        });

        showAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkField.setText(Double.toString(sum));
            }
        });



        //frame
        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);
    }


    public String getMultiplicationText(){
        int i = choice.getSelectedIndex();
        String s="";
        if (i==0)
            s = getMultiplicationText2x2();
        else if (i==1)
            s = getMultiplicationText5x2();

        return s;
    }

    public String getMultiplicationText2x2(){
        String s ="";
        int numbers[] = new int[2];

        numbers[0] = generateRandomIntIntRange(11,99);
        numbers[1] = generateRandomIntIntRange(11,99);
        sum = numbers[0] * numbers[1];

        String num1 = Integer.toString(numbers[0]);
        String num2 = Integer.toString(numbers[1]);
        s = num1 + "x";
        s+=num2;
        return s;
    }

    public String getMultiplicationText5x2(){
        String s ="";
        int numbers[] = new int[2];

        numbers[0] = generateRandomIntIntRange(11011,99999);
        numbers[1] = generateRandomIntIntRange(12,99);
        sum = numbers[0] * numbers[1];

        String num1 = Integer.toString(numbers[0]);
        String num2 = Integer.toString(numbers[1]);
        s = num1 + "x";
        s+=num2;
        return s;
    }

    public int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void nextQuestion(JFrame frame){
        frame.dispose();
        go();
    }
}