import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class Addition {

    int  sum =0, inputSum;

    JFrame frame = new JFrame("Addition Speed Enhancer");

    public Addition(){

        JButton nextButton = new JButton("Next");
        JButton showAnswerButton = new JButton("Show Answer");

        //Panels
        JPanel panel = new JPanel((new BorderLayout(10, 10)));
        JPanel panel2 = new JPanel(new BorderLayout(50,0));
        JPanel panel3 = new JPanel(new BorderLayout(0,10));


        //text area
        JTextArea textArea = new JTextArea("",9, 15);
        textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textArea.setEditable(false);
        textArea.setText(getAdditionText());
        textArea.setFont(new Font("SansSerif", Font.BOLD, 40));

        panel.add(textArea, BorderLayout.NORTH);


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

        panel3.add(inputField, BorderLayout.NORTH);
        panel3.add(checkField, BorderLayout.SOUTH);

        panel.add(panel3, BorderLayout.CENTER);


        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputSum == sum)
                    nextQuestion();

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
                nextQuestion();
            }
        });

        showAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkField.setText(Integer.toString(sum));
            }
        });



        //frame
        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(560, 700);
        frame.setLocationRelativeTo(null);
    }

    public String getAdditionText(){
        String s = new String();
        s = "";
        int numbers[] = new int[9];

        for(int x=0; x<9; x++){
            numbers[x] = generateRandomIntIntRange(11,995);
            sum += numbers[x];
        }

        for(int i=0; i<9; i++){
            if(i!=8)
                s = s + numbers[i] + "\n";
            else
                s = s + numbers[i];
        }
        return s;
    }

    public int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void nextQuestion(){
        frame.dispose();
        new Addition();
    }
}
