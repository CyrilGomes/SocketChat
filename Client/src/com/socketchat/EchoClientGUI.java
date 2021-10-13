/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socketchat;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author creep
 */
public class EchoClientGUI extends javax.swing.JFrame {
    String nomUtilisateur;
    Socket clientSocket;
    EmissionThread emissionThread;
    RecieveThread recieveThread;

    GridBagConstraints chatPanelConstraints;
    DefaultListModel modelListeMessages;

    /**
     * Creates new form EchoClientGUI
     */
    public EchoClientGUI() {
        initComponents();
        chatPanelConstraints = new GridBagConstraints();
        chatPanelConstraints.gridy = 0;
        chatPanelConstraints.fill = GridBagConstraints.BOTH;
        modelListeMessages = new DefaultListModel();
        jList1.setModel(modelListeMessages);
        nomUtilisateur = JOptionPane.showInputDialog(this, "Entrez un nom d'utilisateur", "Nom d'utilisateur", JOptionPane.QUESTION_MESSAGE);
        if (nomUtilisateur == null) {
            System.exit(1);
        }
        clientSocket = new Socket();
    }

    synchronized public void addMessage(String author, Date timestamp, String content) {
        java.awt.EventQueue.invokeLater(() -> {
            modelListeMessages.addElement("(" + new Date().toGMTString() + ") Utilisateur #1 - eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            repaint();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 300));
        setPreferredSize(new java.awt.Dimension(250, 382));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Envoyer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 58;
        gridBagConstraints.insets = new java.awt.Insets(6, 5, 0, 6);
        getContentPane().add(jButton1, gridBagConstraints);

        jScrollPane3.setMaximumSize(new java.awt.Dimension(32767, 16));

        jTextArea1.setColumns(20);
        jTextArea1.setMaximumSize(new java.awt.Dimension(2147483647, 10));
        jScrollPane3.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 218;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 0);
        getContentPane().add(jScrollPane3, gridBagConstraints);

        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 218;
        gridBagConstraints.ipady = 126;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jMenu1.setText("Connexion");

        jMenuItem1.setText("Se connecter");
        jMenuItem1.setName("btConnexion"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConnexionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Se déconnecter");
        jMenuItem2.setName("btDeconnexion"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeconnexionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String msg = jTextArea1.getText();
        System.out.println(msg);
        PrintWriter test;
        try {
            test = new PrintWriter(clientSocket.getOutputStream());
            test.write(msg);
            test.flush();
            test.close();
        } catch (IOException ex) {
            Logger.getLogger(EchoClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        addMessage("", new Date(), "");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(EchoClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConnexionActionPerformed
        final JFrame parent = this;
        new Thread(() -> {
            JPanel panelDialog = new JPanel();
            JTextField fieldAdresse = new JTextField(5);
            JTextField fieldPort = new JTextField(5);
            panelDialog.add(new JLabel("Adresse:"));
            panelDialog.add(fieldAdresse);
            panelDialog.add(Box.createHorizontalStrut(15)); // a spacer
            panelDialog.add(new JLabel("Port:"));
            panelDialog.add(fieldPort);

            int res = JOptionPane.showConfirmDialog(parent, panelDialog, "Entrez les informations de connexion", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    clientSocket.connect(new InetSocketAddress(fieldAdresse.getText(), Integer.parseInt(fieldPort.getText())));
                    JOptionPane.showMessageDialog(parent, "Connexion établie", "Réussite", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(parent, "Echec de connexion", "Erreur", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(EchoClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }//GEN-LAST:event_btConnexionActionPerformed

    private void btDeconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeconnexionActionPerformed
        final JFrame parent = this;
        new Thread(() -> {
            if (!clientSocket.isClosed() && clientSocket != null) {
                try {
                    clientSocket.close();
                    JOptionPane.showMessageDialog(parent, "Déconnexion réussie", "Déconnexion", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(parent, "Echec de déconnexion", "Erreur", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(EchoClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(parent, "Vous n'êtes pas connecté...", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }).start();
    }//GEN-LAST:event_btDeconnexionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EchoClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EchoClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EchoClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EchoClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FlatDarkLaf.setup();
                new EchoClientGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
