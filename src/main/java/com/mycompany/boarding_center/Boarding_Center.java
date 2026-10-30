package com.mycompany.boarding_center;

/** Legacy entry point retained for NetBeans; launches the same GUI-builder login. */
public class Boarding_Center {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                Database.initialize();
                design login = new design();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Unable to initialize the PawStay database.\n\n" + ex.getMessage(),
                        "Database initialization error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
