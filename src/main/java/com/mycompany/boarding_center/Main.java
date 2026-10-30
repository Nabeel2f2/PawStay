package com.mycompany.boarding_center;

import javax.swing.JOptionPane;

/** Application entry point. Uses the NetBeans GUI Builder screens in this project. */
public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                Database.initialize();
                design login = new design();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Unable to initialize the PawStay database.\n\n"
                        + "Make sure MySQL is running and the credentials in Database.java are correct.\n\n"
                        + ex.getMessage(),
                        "Database initialization error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
