package ecommand.view;

import ecommand.classe.Constantes;
import ecommand.dao.cadastro.UsuarioDAO;
import ecommandtools.exception.ExceptionCustom;
import ecommandtools.exception.ExceptionMessage;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;

public class LoginUI extends JDialog {

    private LoginUI dialogoLogin = null;

    public LoginUI(Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();

        lbVersao.setText(Constantes.VERSAO);
    }

    public static void criarLogin(Object parent) throws Exception {
        LoginUI dlg = new LoginUI((Frame) parent, true);
        dlg.exibirDialogo(dlg);
    }

    private void exibirDialogo(LoginUI dlgParent) throws Exception {
        dialogoLogin = dlgParent;
        dialogoLogin.pack();
        dialogoLogin.setLocationRelativeTo(null);
        dialogoLogin.toFront();
        dialogoLogin.setVisible(true);
    }

    private void verificarLogin() throws Exception {
        if (txtUsuario.getTexto().trim().isEmpty() || txtSenha.getPassword().length == 0) {
            throw new ExceptionCustom("Informe usuário/senha!");
        }

        Constantes.usuario = new UsuarioDAO().verificarLogin(txtUsuario.getTexto(), txtSenha.getPassword());

        if (Constantes.usuario != null) {
            dialogoLogin.setVisible(false);
            dialogoLogin.dispose();

        } else {
            throw new ExceptionCustom("Usuário não encontrado!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnFundo = new javax.swing.JPanel();
        lbUsuario = new ecommandtools.componentes.rotulo.Rotulo();
        txtUsuario = new ecommandtools.componentes.campotexto.CampoTexto();
        lbSenha = new ecommandtools.componentes.rotulo.Rotulo();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new ecommandtools.componentes.radiobotao.RadioBotao();
        btnSair = new ecommandtools.componentes.radiobotao.RadioBotao();
        lbVersao = new ecommandtools.componentes.rotulo.Rotulo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");
        setModal(true);
        setName("dlgLogin"); // NOI18N
        setUndecorated(true);

        pnFundo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lbUsuario.setText("Usuário");

        lbSenha.setText("Senha");

        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        btnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/login.png"))); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.setPreferredSize(new java.awt.Dimension(91, 25));
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/exit.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        lbVersao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbVersao.setText("versao 1.0");
        lbVersao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbVersao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnFundoLayout = new javax.swing.GroupLayout(pnFundo);
        pnFundo.setLayout(pnFundoLayout);
        pnFundoLayout.setHorizontalGroup(
            pnFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFundoLayout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addGroup(pnFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbVersao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnFundoLayout.createSequentialGroup()
                        .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnFundoLayout.setVerticalGroup(
            pnFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFundoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(lbVersao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnFundo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        try {
            dialogoLogin.setVisible(false);
            dialogoLogin.dispose();

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, this.getTitle());
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        try {
            verificarLogin();

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, this.getTitle());
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                btnEntrar.requestFocus();
            }
        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, getTitle());
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ecommandtools.componentes.radiobotao.RadioBotao btnEntrar;
    private ecommandtools.componentes.radiobotao.RadioBotao btnSair;
    private ecommandtools.componentes.rotulo.Rotulo lbSenha;
    private ecommandtools.componentes.rotulo.Rotulo lbUsuario;
    private ecommandtools.componentes.rotulo.Rotulo lbVersao;
    private javax.swing.JPanel pnFundo;
    private javax.swing.JPasswordField txtSenha;
    private ecommandtools.componentes.campotexto.CampoTexto txtUsuario;
    // End of variables declaration//GEN-END:variables
}
