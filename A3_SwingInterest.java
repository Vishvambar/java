/*
 * ============================================================
 *  ECLIPSE SETUP:
 *  1. Create a Standard Java Project
 *  2. Paste this file → Run As → Java Application
 *  (No external JARs needed — pure Swing)
 * ============================================================
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class A3_SwingInterest extends JFrame implements ActionListener {

    private JTextField tfPrincipal, tfRate, tfTime;
    private JLabel     lblResult;
    private JButton    btnCalculate;

    public A3_SwingInterest() {
        setTitle("Simple Interest Calculator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        // Principal
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Principal (₹):"), gbc);
        gbc.gridx = 1;
        tfPrincipal = new JTextField(12);
        add(tfPrincipal, gbc);

        // Rate
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Rate (%):"), gbc);
        gbc.gridx = 1;
        tfRate = new JTextField(12);
        add(tfRate, gbc);

        // Time
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Time (years):"), gbc);
        gbc.gridx = 1;
        tfTime = new JTextField(12);
        add(tfTime, gbc);

        // Calculate button
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnCalculate = new JButton("Calculate Interest");
        btnCalculate.addActionListener(this);
        add(btnCalculate, gbc);

        // Result label
        gbc.gridy = 4;
        lblResult = new JLabel("Simple Interest: --", SwingConstants.CENTER);
        lblResult.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(lblResult, gbc);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double principal = Double.parseDouble(tfPrincipal.getText().trim());
            double rate      = Double.parseDouble(tfRate.getText().trim());
            double time      = Double.parseDouble(tfTime.getText().trim());

            // Simple Interest = (P × R × T) / 100
            double interest = (principal * rate * time) / 100.0;

            lblResult.setText(String.format("Simple Interest: ₹%.2f", interest));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input! Please enter valid numeric values.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(A3_SwingInterest::new);
    }
}
