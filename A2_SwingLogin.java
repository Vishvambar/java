import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class A2_SwingLogin extends JFrame implements ActionListener {

    private static final String URL  = "jdbc:mysql://localhost:3306/example_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    /*
     * Required table:
     *   CREATE TABLE users (
     *       username VARCHAR(50) PRIMARY KEY,
     *       password VARCHAR(50) NOT NULL
     *   );
     *   INSERT INTO users VALUES ('admin', 'admin123');
     */

    private JTextField     tfUsername;
    private JPasswordField pfPassword;
    private JButton        btnLogin;

    public A2_SwingLogin() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center on screen
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        // Username label + field
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        tfUsername = new JTextField(15);
        add(tfUsername, gbc);

        // Password label + field
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        pfPassword = new JPasswordField(15);
        add(pfPassword, gbc);

        // Login button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        add(btnLogin, gbc);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = tfUsername.getText().trim();
        String password = new String(pfPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both fields.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful! Welcome, " + username,
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(A2_SwingLogin::new);
    }
}
