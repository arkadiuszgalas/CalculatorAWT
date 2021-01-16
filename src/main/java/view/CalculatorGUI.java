package view;

import java.awt.*;
import java.awt.event.*;
import model.Calculator;

//Class providing calculator panel, buttons and text field
public class CalculatorGUI {
    private Frame f;
    private Panel p1;
    private Label answer;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    private Calculator calculator;
    private boolean readyForNextNumber;

    public CalculatorGUI() {
        calculator = new Calculator();
        readyForNextNumber = true;

        f = new Frame("Calculator");
        answer = new Label("0.0", Label.RIGHT);
        b1 = new Button("7");
        b2 = new Button("8");
        b3 = new Button("9");
        b4 = new Button("+");
        b5 = new Button("4");
        b6 = new Button("5");
        b7 = new Button("6");
        b8 = new Button("-");
        b9 = new Button("1");
        b10 = new Button("2");
        b11 = new Button("3");
        b12 = new Button("*");
        b13 = new Button("0");
        b14 = new Button(".");
        b15 = new Button("=");
        b16 = new Button("/");
    }

    public void launchFrame() {
        //Setting the View
        p1 = new Panel();

        p1.setLayout(new GridLayout(4,4));
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
        p1.add(b6);
        p1.add(b7);
        p1.add(b8);
        p1.add(b9);
        p1.add(b10);
        p1.add(b11);
        p1.add(b12);
        p1.add(b13);
        p1.add(b14);
        p1.add(b15);
        p1.add(b16);

        //Setting the Controllers
        OptButtonHandler opt_handler = new OptButtonHandler();
        NumButtonHandler num_handler = new NumButtonHandler();

        //Digit and dot handlers
        b1.addActionListener(num_handler);
        b2.addActionListener(num_handler);
        b3.addActionListener(num_handler);
        b5.addActionListener(num_handler);
        b6.addActionListener(num_handler);
        b7.addActionListener(num_handler);
        b9.addActionListener(num_handler);
        b10.addActionListener(num_handler);
        b11.addActionListener(num_handler);
        b13.addActionListener(num_handler);
        b14.addActionListener(num_handler);

        //Operation handlers
        b4.addActionListener(opt_handler);
        b8.addActionListener(opt_handler);
        b12.addActionListener(opt_handler);
        b15.addActionListener(opt_handler);
        b16.addActionListener(opt_handler);

        f.addWindowListener(new CloseWidgetHandler());

        f.add(answer, BorderLayout.NORTH);
        f.add(p1, BorderLayout.CENTER);
        f.setSize(200,200);
        f.setVisible(true);
    }

    private class OptButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            char operator = e.getActionCommand().charAt(0);
            String result = "";
            switch (operator) {
                case '+':
                    result = calculator.opAdd(answer.getText());
                    break;
                case '-':
                    result = calculator.opSubtract(answer.getText());
                    break;
                case '*':
                    result = calculator.opMultiply(answer.getText());
                    break;
                case '/':
                    result = calculator.opDivide(answer.getText());
                    break;
                case '=':
                    result = calculator.opEquals(answer.getText());
                    break;
            }
            answer.setText(result);
            readyForNextNumber = true;
        }
    }

    private class NumButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (readyForNextNumber) {
                answer.setText(e.getActionCommand());
                readyForNextNumber = false;
            } else {
                answer.setText(answer.getText()+e.getActionCommand().charAt(0));
            }
        }
    }

    private class CloseWidgetHandler implements WindowListener {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
        public void windowActivated(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}
        public void windowOpened(WindowEvent e) {}
    }

    public static void main(String[] args) {
        CalculatorGUI guiCalc = new CalculatorGUI();
        guiCalc.launchFrame();
    }
}