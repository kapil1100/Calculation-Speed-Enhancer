import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Tables {

        double  sum =1;                //multiplication of given problem
        int inputSum;               //input multiplication answers

        public static JComboBox choice = new JComboBox(new String[]{"(1-10) x upto table 17","5 digit x upto table 17"});

        public Tables() {
            go();
        }

        public void go(){
            JFrame frame = new JFrame("Table Speed Enhancer");
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
            int i = choice.getSelectedIndex();

            KeyListener keyListener0 = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) { }

                public void keyPressed(KeyEvent keyEvent) { }

                @Override
                public void keyReleased(KeyEvent e) {
                    inputSum = parseInt(inputField.getText());
                    if(inputSum == sum)
                        checkField.setText("Correct");
                    else
                        checkField.setText("Wrong");
                }
            };

            KeyListener keyListener1 = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) { }

                public void keyPressed(KeyEvent keyEvent) { }

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

            if(i==0)
                inputField.addKeyListener(keyListener0);
            else if(i==1)
                inputField.addKeyListener(keyListener1);




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

        //upto table 17 sums
        public String getMultiplicationText2x2(){
            String s ="";
            int numbers[] = new int[2];

            numbers[0] = generateRandomIntIntRange(4,17);
            numbers[1] = generateRandomIntIntRange(2,9);
            sum = numbers[0] * numbers[1];

            String num1 = Integer.toString(numbers[0]);
            String num2 = Integer.toString(numbers[1]);
            s = num1 + "x";
            s+=num2;
            return s;
        }

        //5 digit number x upto table 17 sums
        public String getMultiplicationText5x2(){
            String s ="";
            int numbers[] = new int[2];

            numbers[0] = generateRandomIntIntRange(11011,99999);
            numbers[1] = generateRandomIntIntRange(11,17);
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
