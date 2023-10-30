import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalButton;
    private JButton clearButton, deleteButton, dotButton;
    private JButton sinButton, cosButton, tanButton, sqrtButton, powButton;

    private double num1, num2, result;
    private char operator;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 5));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(this);
        }

        functionButtons = new JButton[5];
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalButton = new JButton("=");
        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = equalButton;

        for (JButton button : functionButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
        }

        clearButton = new JButton("C");
        deleteButton = new JButton("DEL");
        dotButton = new JButton(".");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        sqrtButton = new JButton("sqrt");
        powButton = new JButton("^");

        JButton[] otherButtons = { clearButton, deleteButton, dotButton, sinButton, cosButton, tanButton, sqrtButton,
                powButton };
        for (JButton button : otherButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
        }

        buttonPanel.add(sinButton);
        buttonPanel.add(cosButton);
        buttonPanel.add(tanButton);
        buttonPanel.add(sqrtButton);
        buttonPanel.add(powButton);
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(divideButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(subtractButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(addButton);
        buttonPanel.add(dotButton);
        buttonPanel.add(equalButton);
        buttonPanel.setLayout(new GridLayout(5, 4));

        add(inputField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String text = source.getText();

        if (text.matches("[0-9]")) {
            inputField.setText(inputField.getText() + text);
        } else if (text.equals(".")) {
            if (!inputField.getText().contains(".")) {
                inputField.setText(inputField.getText() + ".");
            }
        } else if (text.equals("C")) {
            inputField.setText("");
        } else if (text.equals("DEL")) {
            String currentText = inputField.getText();
            if (!currentText.isEmpty()) {
                inputField.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")) {
            num1 = Double.parseDouble(inputField.getText());
            operator = text.charAt(0);
            inputField.setText("");
        } else if (text.equals("=")) {
            num2 = Double.parseDouble(inputField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero.");
                        inputField.setText("");
                        return;
                    }
                    break;
            }
            inputField.setText(String.valueOf(result));
        } else if (text.equals("sin") || text.equals("cos") || text.equals("tan") || text.equals("sqrt")
                || text.equals("^")) {
            double value = Double.parseDouble(inputField.getText());
            switch (text) {
                case "sin":
                    result = Math.sin(value);
                    break;
                case "cos":
                    result = Math.cos(value);
                    break;
                case "tan":
                    result = Math.tan(value);
                    break;
                case "sqrt":
                    if (value >= 0) {
                        result = Math.sqrt(value);
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot compute square root of a negative number.");
                        inputField.setText("");
                        return;
                    }
                    break;
                case "^":
                    result = Math.pow(num1, num2);
                    break;
            }
            inputField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScientificCalculator());
    }
}
