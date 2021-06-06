package ecommand.view.cadastro;

import ecommand.classe.Constantes;
import ecommand.dao.cadastro.UsuarioDAO;
import ecommand.model.cadastro.UsuarioVO;
import ecommand.tipo.SituacaoCadastro;
import ecommandtools.exception.ExceptionCustom;
import ecommandtools.exception.ExceptionMessage;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import ecommandtools.view.InternalFrameCustom;
import ecommandtools.view.JFrameCustom;
import org.openide.awt.DropDownButtonFactory;
import ecommandtools.utils.Formatacao;
import ecommandtools.utils.Mensagens;
import ecommandtools.view.Biometria;
import static ecommandtools.utils.Mensagens.mensagemInfo;

public class UsuarioCadastroUI extends InternalFrameCustom {

    private UsuarioVO usuario = null;
    private JButton botaoBiometria = null;

    public UsuarioCadastroUI(JFrameCustom formMain) throws Exception {
        super(formMain);
        initComponents();

        setCenterForm();
        
        cboSituacao.setTabela("situacaocadastro");
        cboSituacao.carregar();

        configurarBotaoDigital();
    }

    private void configurarBotaoDigital() throws Exception {
        botaoBiometria = DropDownButtonFactory.createDropDownButton(new ImageIcon(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB)), menuDigital);
        botaoBiometria.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        botaoBiometria.setIcon(new ImageIcon(getClass().getResource("/ecommandtools/image/biometria.png")));
        botaoBiometria.setToolTipText("Cadastrar biometria");
        toolbarDigital.add(botaoBiometria);
    }

    public void salvar() throws Exception {
        if (txtUsuario.getTexto().trim().isEmpty()) {
            throw new ExceptionCustom("Informe um nome para o usuário!");
        }

        if (String.valueOf(txtSenha.getPassword()).trim().isEmpty()) {
            throw new ExceptionCustom("Informe uma senha para o usuário!");
        }

        usuario.id = txtCodigo.getInt();
        usuario.nome = txtUsuario.getTexto();

        if (txtSenha.isEnabled()) {
            usuario.senha = String.valueOf(txtSenha.getPassword()).trim();
        }

        usuario.idSituacaoCadastro = cboSituacao.getId();

        new UsuarioDAO().salvar(usuario);

        txtSenha.setEnabled(false);

        txtCodigo.setText(Formatacao.numberLeft(usuario.id, 3));

        mensagemInfo(Mensagens.MSG_SALVO_COM_SUCESSO);
    }

    public void novo() throws Exception {
        usuario = new UsuarioVO();

        txtCodigo.setText("");
        txtUsuario.setText("");

        txtSenha.setEnabled(true);
        txtSenha.setText("");

        cboSituacao.setId(SituacaoCadastro.ATIVO.getId());

        habilitar();
    }

    public void habilitar() throws Exception {
        if (cboSituacao.getId() == SituacaoCadastro.EXCLUIDO.getId()) {
            cboSituacao.setEnabled(true);
        } else {
            cboSituacao.setEnabled(false);
        }

        if (usuario.digital.isEmpty()) {
            mnuExcluirDigital.setEnabled(false && Constantes.utlizaBiometria);
            mnuVerificarDigital.setEnabled(false && Constantes.utlizaBiometria);
            mnuCadastrarDigital.setEnabled(true && Constantes.utlizaBiometria);

        } else {
            mnuExcluirDigital.setEnabled(true && Constantes.utlizaBiometria);
            mnuVerificarDigital.setEnabled(true && Constantes.utlizaBiometria);
            mnuCadastrarDigital.setEnabled(false && Constantes.utlizaBiometria);
        }
    }

    public void carregar(UsuarioVO usuario) throws Exception {
        this.usuario = new UsuarioDAO().carregar(usuario.id);

        txtCodigo.setText(Formatacao.numberLeft(this.usuario.id, 3));
        txtUsuario.setText(this.usuario.nome);
        txtSenha.setText(this.usuario.senha);
        cboSituacao.setId(this.usuario.idSituacaoCadastro);

        habilitar();
    }

    private void carregarLeitorBiometrico() throws Exception {
        Biometria.criarLeituraCadastro(null);

        try {
            Biometria.encerrarBiometria();

        } catch (Exception ex) {
            Logger.getLogger(UsuarioCadastroUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        usuario.digital = Biometria.getBiometriaCapturada();

        habilitar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuDigital = new javax.swing.JPopupMenu();
        mnuCadastrarDigital = new javax.swing.JMenuItem();
        mnuVerificarDigital = new javax.swing.JMenuItem();
        mnuExcluirDigital = new javax.swing.JMenuItem();
        pnlMain = new javax.swing.JPanel();
        lbUsuario = new javax.swing.JLabel();
        txtUsuario = new ecommandtools.componentes.campotexto.CampoTexto();
        txtCodigo = new ecommandtools.componentes.campotexto.CampoTexto();
        lbCodigo = new javax.swing.JLabel();
        lbSenha = new ecommandtools.componentes.rotulo.Rotulo();
        btnTrocarSenha = new ecommandtools.componentes.radiobotao.RadioBotao();
        txtSenha = new javax.swing.JPasswordField();
        lbSituacao = new javax.swing.JLabel();
        toolbarDigital = new javax.swing.JToolBar();
        cboSituacao = new ecommandtools.componentes.combo.Combo();
        jToolBarMenu = new javax.swing.JToolBar();
        btnSalvar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        mnuCadastrarDigital.setText("Cadastrar");
        mnuCadastrarDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCadastrarDigitalActionPerformed(evt);
            }
        });
        menuDigital.add(mnuCadastrarDigital);

        mnuVerificarDigital.setText("Verificar");
        mnuVerificarDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVerificarDigitalActionPerformed(evt);
            }
        });
        menuDigital.add(mnuVerificarDigital);

        mnuExcluirDigital.setText("Excluir");
        mnuExcluirDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExcluirDigitalActionPerformed(evt);
            }
        });
        menuDigital.add(mnuExcluirDigital);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cadastro de Usuário");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/usuario.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        pnlMain.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbUsuario.setText("Usuário");

        txtUsuario.setColumns(30);

        txtCodigo.setEditable(false);
        txtCodigo.setEnabled(false);
        txtCodigo.setTipoformato("Numero");

        lbCodigo.setText("Código");

        lbSenha.setText("Senha");

        btnTrocarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/lock.png"))); // NOI18N
        btnTrocarSenha.setToolTipText("Trocar senha");
        btnTrocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocarSenhaActionPerformed(evt);
            }
        });

        txtSenha.setColumns(10);
        txtSenha.setEnabled(false);

        lbSituacao.setText("Situação");

        toolbarDigital.setFloatable(false);
        toolbarDigital.setRollover(true);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCodigo)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSenha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(btnTrocarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSituacao)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(toolbarDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lbUsuario))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(lbUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lbSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTrocarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtSenha)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lbSituacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toolbarDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jToolBarMenu.setFloatable(false);
        jToolBarMenu.setRollover(true);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/salvar.png"))); // NOI18N
        btnSalvar.setFocusable(false);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jToolBarMenu.add(btnSalvar);

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/novo.png"))); // NOI18N
        btnIncluir.setFocusable(false);
        btnIncluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIncluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        jToolBarMenu.add(btnIncluir);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/cancelar.png"))); // NOI18N
        btnCancelar.setFocusable(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jToolBarMenu.add(btnCancelar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBarMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        try {
            startWaitCursor();
            sair();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());
        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            startWaitCursor();
            salvar();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());
        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            startWaitCursor();
            sair();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());
        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        try {
            startWaitCursor();
            novo();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());

        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrocarSenhaActionPerformed
        try {
            startWaitCursor();
            txtSenha.setEnabled(true);
            txtSenha.setText("");

            if (usuario != null) {
                usuario.senha = "";
            }

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());

        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_btnTrocarSenhaActionPerformed

    private void mnuCadastrarDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCadastrarDigitalActionPerformed
        try {
            startWaitCursor();
            carregarLeitorBiometrico();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());

        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_mnuCadastrarDigitalActionPerformed

    private void mnuVerificarDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVerificarDigitalActionPerformed
        try {
            Biometria.criarLeitura(null);

            try {
                Biometria.encerrarBiometria();

            } catch (Exception ex) {
                Logger.getLogger(UsuarioCadastroUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            String biometriaCapturada = Biometria.getBiometriaCapturada();

            if (!biometriaCapturada.isEmpty()) {
                if (Biometria.compararBiometrias(biometriaCapturada, usuario.digital)) {
                    mensagemInfo("Digitais conferem!");
                } else {
                    mensagemInfo("Digitais não conferem!");
                }
            }
        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());

        }
    }//GEN-LAST:event_mnuVerificarDigitalActionPerformed

    private void mnuExcluirDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExcluirDigitalActionPerformed
        try {
            startWaitCursor();
            usuario.digital = "";

            habilitar();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());

        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_mnuExcluirDigitalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSalvar;
    private ecommandtools.componentes.radiobotao.RadioBotao btnTrocarSenha;
    private ecommandtools.componentes.combo.Combo cboSituacao;
    private javax.swing.JToolBar jToolBarMenu;
    private javax.swing.JLabel lbCodigo;
    private ecommandtools.componentes.rotulo.Rotulo lbSenha;
    private javax.swing.JLabel lbSituacao;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JPopupMenu menuDigital;
    private javax.swing.JMenuItem mnuCadastrarDigital;
    private javax.swing.JMenuItem mnuExcluirDigital;
    private javax.swing.JMenuItem mnuVerificarDigital;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JToolBar toolbarDigital;
    private ecommandtools.componentes.campotexto.CampoTexto txtCodigo;
    private javax.swing.JPasswordField txtSenha;
    private ecommandtools.componentes.campotexto.CampoTexto txtUsuario;
    // End of variables declaration//GEN-END:variables

}
