import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    private JFrame frame;
    private Addition addition;
    private Multiplication multiplication;
    private Tables tables;

    public static void main(String[] a){
        new Start().go();
    }

    public void go(){

        JPanel panel1, panel2;
        JButton addButton, multiplyButton, tablesButton;
        GridBagConstraints c = new GridBagConstraints();

        frame = new JFrame("Calculation Speed Enhancer");

        panel1 = new JPanel(new GridBagLayout());

        addButton = new JButton("Addition");
        addButton.setSize(70,100);
        multiplyButton = new JButton("Multiplication");
        multiplyButton.setSize(50,70);
        tablesButton = new JButton("Tables");
        tablesButton.setSize(50,70);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                addition = new Addition();
            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                multiplication = new Multiplication();
            }
        });
        tablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                tables = new Tables();
            }
        });


        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20,20,20,20);
        panel1.add(addButton, c);

        c.gridx = 0;
        c.gridy = 1;
        panel1.add(multiplyButton, c);

        c.gridx = 0;
        c.gridy = 2;
        panel1.add(tablesButton, c);

        frame.getContentPane().add(panel1, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(350, 250);
        frame.setLocationRelativeTo(null);

    }
}
