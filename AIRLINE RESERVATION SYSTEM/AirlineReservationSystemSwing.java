import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirlineReservationSystemSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new ReservationFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

class ReservationFrame extends JFrame {

    private JTextField nameField;
    private JComboBox<String> flightComboBox;
    private JRadioButton economyRadio;
    private JRadioButton businessRadio;

    public ReservationFrame() {
        setTitle("Airline Reservation System");
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameField, gbc);

        JLabel flightLabel = new JLabel("Select Flight:");
        flightComboBox = new JComboBox<>(new String[] { "Flight 101", "Flight 102", "Flight 103" });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(flightLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(flightComboBox, gbc);

        JLabel classLabel = new JLabel("Select Class:");
        ButtonGroup classButtonGroup = new ButtonGroup();
        economyRadio = new JRadioButton("Economy");
        businessRadio = new JRadioButton("Business");
        classButtonGroup.add(economyRadio);
        classButtonGroup.add(businessRadio);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(classLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel classPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        classPanel.add(economyRadio);
        classPanel.add(businessRadio);
        add(classPanel, gbc);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(new ReserveButtonListener());
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(reserveButton, gbc);
    }

    private class ReserveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String flight = (String) flightComboBox.getSelectedItem();
            String travelClass = economyRadio.isSelected() ? "Economy" : "Business";

            // Here you can implement the reservation logic
            // For now, let's just show a message dialog
            String message = "Name: " + name + "\nFlight: " + flight + "\nClass: " + travelClass;
            JOptionPane.showMessageDialog(
                    ReservationFrame.this,
                    message,
                    "Reservation Details",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
