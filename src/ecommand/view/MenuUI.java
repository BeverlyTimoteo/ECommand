package ecommand.view;

import ecommand.classe.Constantes;
import ecommand.view.cadastro.FornecedorConsultaUI;
import ecommand.view.cadastro.MesaConsultaUI;
import ecommand.view.cadastro.ProdutoCategoriaConsultaUI;
import ecommand.view.cadastro.ProdutoConsultaUI;
import ecommand.view.cadastro.UsuarioConsultaUI;
import ecommandtools.exception.ExceptionMessage;
import javax.swing.JLabel;
import ecommandtools.utils.Mensagens;
import ecommandtools.view.JFrameCustom;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MenuUI extends JFrameCustom {

    public UsuarioConsultaUI formUsuarioConsulta = null;
    public ProdutoConsultaUI formProdutoConsulta = null;
    public ProdutoCategoriaConsultaUI formProdutoCategoriaConsulta = null;
    public MesaConsultaUI formMesaConsulta = null;
    public FornecedorConsultaUI formFornecedorConsulta = null;

    public MenuUI() {
        initComponents();

        setDesktopPane(desktopPane);

        setEstadoForm();

        setBackgroundImage();

        preencherStatusBar();

        this.setIconImage(new ImageIcon(getClass().getResource("/ecommandtools/image/eCommand.png")).getImage());
    }

    private void preencherStatusBar() {
        statusBar.add(new JLabel("  " + Constantes.usuario.nome));
        statusBar.add(new JLabel("  " + Constantes.host + ":" + Constantes.port + "/" + Constantes.name));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        tbAtalho = new javax.swing.JToolBar();
        btnSair = new javax.swing.JButton();
        statusBar = new org.jdesktop.swingx.JXStatusBar();
        mbMain = new javax.swing.JMenuBar();
        mnuCadastro = new javax.swing.JMenu();
        mnuFornecedor = new javax.swing.JMenuItem();
        mnuProduto = new javax.swing.JMenuItem();
        mnuCategoriaProduto = new javax.swing.JMenuItem();
        mnuMesa = new javax.swing.JMenuItem();
        mnuUsuario = new javax.swing.JMenuItem();
        mnuFinanceiro = new javax.swing.JMenu();
        mnuContasPagar = new javax.swing.JMenu();
        mnuContasPagarLancamento = new javax.swing.JMenuItem();
        mnuConstasReceber = new javax.swing.JMenu();
        mnuContasReceberLancamento = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("E-Command");
        setSize(new java.awt.Dimension(1024, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        desktopPane.setBackground(new java.awt.Color(240, 240, 240));
        desktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        desktopPane.setDesktopManager(null);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
        );

        tbAtalho.setFloatable(false);
        tbAtalho.setRollover(true);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/exit24.png"))); // NOI18N
        btnSair.setMnemonic('S');
        btnSair.setToolTipText("Sair");
        btnSair.setBorder(null);
        btnSair.setFocusable(false);
        btnSair.setIconTextGap(0);
        btnSair.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        tbAtalho.add(btnSair);

        mnuCadastro.setText("Cadastro");

        mnuFornecedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        mnuFornecedor.setText("Fornecedor");
        mnuFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFornecedorActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuFornecedor);

        mnuProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        mnuProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/produto.png"))); // NOI18N
        mnuProduto.setText("Produto");
        mnuProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProdutoActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuProduto);

        mnuCategoriaProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        mnuCategoriaProduto.setText("Categoria Produto");
        mnuCategoriaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCategoriaProdutoActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuCategoriaProduto);

        mnuMesa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        mnuMesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/mesa.png"))); // NOI18N
        mnuMesa.setText("Mesa");
        mnuMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMesaActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuMesa);

        mnuUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        mnuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/usuario.png"))); // NOI18N
        mnuUsuario.setText("Usuário");
        mnuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUsuarioActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuUsuario);

        mbMain.add(mnuCadastro);

        mnuFinanceiro.setText("Financeiro");

        mnuContasPagar.setText("Contas a pagar");

        mnuContasPagarLancamento.setText("Lançamento");
        mnuContasPagar.add(mnuContasPagarLancamento);

        mnuFinanceiro.add(mnuContasPagar);

        mnuConstasReceber.setText("Contas a receber");

        mnuContasReceberLancamento.setText("Lançamento");
        mnuConstasReceber.add(mnuContasReceberLancamento);

        mnuFinanceiro.add(mnuConstasReceber);

        mbMain.add(mnuFinanceiro);

        setJMenuBar(mbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
            .addComponent(tbAtalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tbAtalho, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(desktopPane)
                .addGap(0, 0, 0)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            Mensagens.confirmacao("Deseja sair do sistema!", getTitle());
            System.exit(0);

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, this.getTitle());
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        try {
            Mensagens.confirmacao("Deseja sair do sistema!", getTitle());
            System.exit(0);

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, this.getTitle());
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void mnuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUsuarioActionPerformed
        try {
            if (formUsuarioConsulta == null || formUsuarioConsulta.isClosed()) {
                formUsuarioConsulta = new UsuarioConsultaUI(this);
            }

            formUsuarioConsulta.setVisible();

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, this.getTitle());
        }
    }//GEN-LAST:event_mnuUsuarioActionPerformed

    private void mnuProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProdutoActionPerformed
        try {
            if (formProdutoConsulta == null || formProdutoConsulta.isClosed()) {
                formProdutoConsulta = new ProdutoConsultaUI(this);
            }

            formProdutoConsulta.setVisible();

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, this.getTitle());
        }
    }//GEN-LAST:event_mnuProdutoActionPerformed

    private void mnuCategoriaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCategoriaProdutoActionPerformed
        try {
            if (formProdutoCategoriaConsulta == null || formProdutoCategoriaConsulta.isClosed()) {
                formProdutoCategoriaConsulta = new ProdutoCategoriaConsultaUI(this);
            }

            formProdutoCategoriaConsulta.setVisible();

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, this.getTitle());
        }
    }//GEN-LAST:event_mnuCategoriaProdutoActionPerformed

    private void mnuMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMesaActionPerformed
        try {
            if (formMesaConsulta == null || formMesaConsulta.isClosed()) {
                formMesaConsulta = new MesaConsultaUI(this);
            }

            formMesaConsulta.setVisible();

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, this.getTitle());
        }
    }//GEN-LAST:event_mnuMesaActionPerformed

    private void mnuFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFornecedorActionPerformed
        try {
            if (formFornecedorConsulta == null || formFornecedorConsulta.isClosed()) {
                formFornecedorConsulta = new FornecedorConsultaUI(this);
            }

            formFornecedorConsulta.setVisible();

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, this.getTitle());
        }
    }//GEN-LAST:event_mnuFornecedorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSair;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuBar mbMain;
    private javax.swing.JMenu mnuCadastro;
    private javax.swing.JMenuItem mnuCategoriaProduto;
    private javax.swing.JMenu mnuConstasReceber;
    private javax.swing.JMenu mnuContasPagar;
    private javax.swing.JMenuItem mnuContasPagarLancamento;
    private javax.swing.JMenuItem mnuContasReceberLancamento;
    private javax.swing.JMenu mnuFinanceiro;
    private javax.swing.JMenuItem mnuFornecedor;
    private javax.swing.JMenuItem mnuMesa;
    private javax.swing.JMenuItem mnuProduto;
    private javax.swing.JMenuItem mnuUsuario;
    private org.jdesktop.swingx.JXStatusBar statusBar;
    private javax.swing.JToolBar tbAtalho;
    // End of variables declaration//GEN-END:variables

    private void setBackgroundImage() {
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension d = tk.getScreenSize();

            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResource("/ecommandtools/image/eCommandFundo.png")));

            JLabel lbFundo = new JLabel(icon);

            lbFundo.setBounds((d.width - icon.getIconWidth()) / 2, 50, icon.getIconWidth(), icon.getIconHeight());

            desktopPane.add(lbFundo, new Integer(Integer.MIN_VALUE));

        } catch (HeadlessException | IOException e) {
            System.out.println(e);
        }
    }

}
