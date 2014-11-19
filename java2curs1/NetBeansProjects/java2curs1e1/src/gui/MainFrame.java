/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import controllers.MainController;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import model.Autor;
import model.Carte;
import obspattern.AutorListener;
import obspattern.CartiListener;

/**
 *
 * @author student
 */
public class MainFrame extends javax.swing.JFrame {
    private DefaultListModel model1 = new DefaultListModel();
    private DefaultListModel model2 = new DefaultListModel();

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        setLocationRelativeTo(null);
        
        jList1.setModel(model1);
        jList2.setModel(model2);
        
        MainController.getInstance().addCartiListener(
                new CartiListener() {

            @Override
            public void listaCartiModificata() {
                afisareCarti();
            }
        });
        
        MainController.getInstance().addAutorListener(
                new AutorListener() {

            @Override
            public void listaAutoriModificata() {
                afisareAutori();
            }
        });
        
    }
    
    private void afisareCarti(){
        model1.clear();
        ArrayList<Carte> carti = MainController.getInstance().getCarti();
        for(Carte c: carti){
            model1.addElement(c);
        }
    }
    private void afisareAutori(){
        model2.clear();
        ArrayList<Autor> autori = MainController.getInstance().getAutori();
        for(Autor c: autori){
            model2.addElement(c);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem4.setText("Modifica");
        jPopupMenu1.add(jMenuItem4);

        jMenuItem5.setText("Sterge");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem5);

        jMenuItem6.setText("Adauga carte");
        jPopupMenu2.add(jMenuItem6);

        jMenuItem7.setText("Vizualizare carti");
        jPopupMenu2.add(jMenuItem7);

        jMenuItem8.setText("Sterge");
        jMenuItem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem8MouseClicked(evt);
            }
        });
        jPopupMenu2.add(jMenuItem8);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(200);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jSplitPane1.setRightComponent(jScrollPane2);

        jMenu1.setText("Optiuni");

        jMenuItem1.setText("Adauga carte");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Adauga autor");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Inchide");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new AdaugaCarte().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new AdaugaAutor().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        if(evt.getButton() == MouseEvent.BUTTON3 &&
                jList1.getSelectedIndex() != -1){
            jPopupMenu1.show(jList1, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        if(evt.getButton() == MouseEvent.BUTTON3 &&
                jList2.getSelectedIndex() != -1){
            jPopupMenu2.show(jList2, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jList2MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Carte c = (Carte) jList1.getSelectedValue();
        MainController.getInstance().stergeCarte(c);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MouseClicked
        Autor a = (Autor) jList2.getSelectedValue();
        MainController.getInstance().stergeAutor(a);
    }//GEN-LAST:event_jMenuItem8MouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
