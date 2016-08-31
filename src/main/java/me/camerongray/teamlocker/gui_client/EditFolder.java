/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.camerongray.teamlocker.gui_client;
import me.camerongray.teamlocker.core.User;
import me.camerongray.teamlocker.core.Folder;
import me.camerongray.teamlocker.core.Locker;
import me.camerongray.teamlocker.core.FolderPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import me.camerongray.teamlocker.core.CurrentUser;
import me.camerongray.teamlocker.core.LockerRuntimeException;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author camerong
 */
public class EditFolder extends javax.swing.JDialog {
    private MainWindow parent;
    private Locker locker;
    private Folder folder;
    private User user;
    private HashMap<Integer, User> userMap = new HashMap<>();
    private ArrayList<Integer> noPermissionUserIds = new ArrayList<>();
    
    /**
     * Creates new form EditFolder
     */
    public EditFolder(MainWindow parent, boolean modal, Folder folder, FolderPermission[] permissions) throws LockerRuntimeException {
        super(parent, modal);
        this.parent = parent;
        this.user = CurrentUser.getInstance();
        this.locker = Locker.getInstance();
        this.folder = folder;
        initComponents();
        this.panelSavingFolder.setVisible(false);
        this.getRootPane().setDefaultButton(btnSaveFolder);
        populateUi(permissions);
    }
    
    private void populateUi(FolderPermission[] permissions) {
        this.txtFolderName.setText(this.folder.getName());
        DefaultTableModel model = (DefaultTableModel)tblPermissions.getModel();
        for (FolderPermission p : permissions) {
            // Do not add admins to the table as they already have full control
            if (p.getUser().isAdmin()) {
                continue;
            }
            userMap.put(p.getUser().getId(), p.getUser());
            
            model.addRow(new Object[]{
                p.getUser().getId(),
                p.getUser().getFullName(),
                p.getUser().getUsername(),
                p.isRead(),
                p.isWrite()
            });
            if (!p.isRead()) {
                noPermissionUserIds.add(p.getUser().getId());
            }
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

        jLabel1 = new javax.swing.JLabel();
        txtFolderName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPermissions = new javax.swing.JTable();
        btnSaveFolder = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelSavingFolder = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Folder - TeamLocker");
        setLocationByPlatform(true);

        jLabel1.setText("Folder Name");

        tblPermissions.setAutoCreateRowSorter(true);
        tblPermissions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Full Name", "Username", "Read", "Write"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPermissions.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPermissions);

        btnSaveFolder.setText("Save Folder");
        btnSaveFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveFolderActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel2.setText("Permissions:");

        jLabel5.setText("Saving Folder...");

        jProgressBar1.setIndeterminate(true);

        javax.swing.GroupLayout panelSavingFolderLayout = new javax.swing.GroupLayout(panelSavingFolder);
        panelSavingFolder.setLayout(panelSavingFolderLayout);
        panelSavingFolderLayout.setHorizontalGroup(
            panelSavingFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSavingFolderLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSavingFolderLayout.setVerticalGroup(
            panelSavingFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSavingFolderLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.setText("Administrators always have full read/write access to folders and therefore not listed.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFolderName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelSavingFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSaveFolder)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFolderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnCancel)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(panelSavingFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSaveFolder)
                        .addGap(8, 8, 8))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveFolderActionPerformed
        final int COLUMN_ID = 0;
        final int COLUMN_READ = 3;
        final int COLUMN_WRITE = 4;
        
        panelSavingFolder.setVisible(true);
        btnSaveFolder.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel)tblPermissions.getModel();
        ArrayList<FolderPermission> permissionArrayList = new ArrayList<>();
        ArrayList<Integer> readUserIds = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean canRead = (boolean)model.getValueAt(i, COLUMN_READ);
            User user = this.userMap.get((Integer)model.getValueAt(i, COLUMN_ID));
            permissionArrayList.add(new FolderPermission(
                    user,
                    this.folder,
                    canRead,
                    (boolean)model.getValueAt(i, COLUMN_WRITE)
            ));
            if (canRead) {
                readUserIds.add(user.getId());
            }
        }
        FolderPermission[] permissionArray = permissionArrayList.toArray(
                new FolderPermission[permissionArrayList.size()]);
        
        // Get an array of user IDs that have just been granted read permission
        // This is used to determine which users the folder's contents need to be
        // re-encrypted for
        ArrayList<Integer> newReadUsers = new ArrayList<>(readUserIds);
        newReadUsers.retainAll(this.noPermissionUserIds);
                
        this.folder.setName(txtFolderName.getText());
        new SaveFolderTask(this, this.parent, this.locker, this.folder, permissionArray, newReadUsers).execute();
    }//GEN-LAST:event_btnSaveFolderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSaveFolder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelSavingFolder;
    private javax.swing.JTable tblPermissions;
    private javax.swing.JTextField txtFolderName;
    // End of variables declaration//GEN-END:variables

    class SaveFolderTask extends SwingWorker<Void, Object> {
        private EditFolder dialog;
        private ArrayList<Integer> newReadUsers;
        private FolderPermission[] permissions;

        public SaveFolderTask(EditFolder dialog, MainWindow parent, Locker locker, Folder folder, FolderPermission[] permissions, ArrayList<Integer> newReadUsers) {
            this.dialog = dialog;
            this.newReadUsers = newReadUsers;
            this.permissions = permissions;
        }

        @Override
        public Void doInBackground() throws Exception {
            this.dialog.folder.updatePermissionsOnServer(this.permissions, this.newReadUsers);
            this.dialog.folder.updateOnServer();
            return null;
        }

        @Override
        public void done() {
            panelSavingFolder.setVisible(false);
            btnSaveFolder.setEnabled(true);
            try {
                this.get();
                this.dialog.parent.refreshFolderList();
                this.dialog.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this.dialog, e.getCause().getMessage(),
                        "Error Saving Folder", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
