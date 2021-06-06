package ecommand.view.cadastro;

import ecommand.dao.cadastro.ProdutoCategoriaDAO;
import ecommand.model.cadastro.ProdutoCategoriaVO;
import ecommandtools.exception.ExceptionCustom;
import ecommandtools.exception.ExceptionMessage;
import ecommandtools.utils.ExtensionFile;
import ecommandtools.utils.FileChoose;
import ecommandtools.view.InternalFrameCustom;
import ecommandtools.view.JFrameCustom;
import ecommandtools.utils.Formatacao;
import ecommandtools.utils.Imagem;
import ecommandtools.utils.Mensagens;
import static ecommandtools.utils.Mensagens.mensagemInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;
import javax.swing.ImageIcon;

public class ProdutoCategoriaCadastroUI extends InternalFrameCustom {

    private ProdutoCategoriaVO categoria = null;

    public ProdutoCategoriaCadastroUI(JFrameCustom formMain) throws Exception {
        super(formMain);
        initComponents();

        setCenterForm();
    }

    public void salvar() throws Exception {
        if (txtDescricao.getTexto().trim().isEmpty()) {
            throw new ExceptionCustom("Informe uma descrição!");
        }

        categoria.id = txtCodigo.getInt();
        categoria.descricao = txtDescricao.getTexto();

        new ProdutoCategoriaDAO().salvar(categoria);

        txtCodigo.setText(Formatacao.numberLeft(categoria.id, 3));

        mensagemInfo(Mensagens.MSG_SALVO_COM_SUCESSO);
    }

    public void novo() throws Exception {
        categoria = new ProdutoCategoriaVO();

        txtCodigo.setText("");
        txtDescricao.setText("");
        lbImagem.setIcon(null);
    }

    public void carregar(ProdutoCategoriaVO categoria) throws Exception {
        this.categoria = new ProdutoCategoriaDAO().carregar(categoria.id);

        txtCodigo.setText(Formatacao.numberLeft(this.categoria.id, 3));
        txtDescricao.setText(this.categoria.descricao);

        if (!this.categoria.imagem.isEmpty()) {
            lbImagem.setIcon(new ImageIcon(new Imagem().base64ToBufferedImage(this.categoria.imagem)));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        txtCodigo = new ecommandtools.componentes.campotexto.CampoTexto();
        lbDescricao = new javax.swing.JLabel();
        txtDescricao = new ecommandtools.componentes.campotexto.CampoTexto();
        lbImagem = new ecommandtools.componentes.rotulo.Rotulo();
        btnChooseImage = new ecommandtools.componentes.botao.Botao();
        jToolBarMenu = new javax.swing.JToolBar();
        btnSalvar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cadastro de Categoria Produto");
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

        lbCodigo.setText("Código");

        txtCodigo.setEditable(false);
        txtCodigo.setEnabled(false);
        txtCodigo.setTipoformato("Numero");

        lbDescricao.setText("Descrição");

        txtDescricao.setColumns(30);

        lbImagem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnChooseImage.setText("Carregar Imagem");
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDescricao)
                    .addComponent(btnChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(lbDescricao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBarMenu.setFloatable(false);
        jToolBarMenu.setRollover(true);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/salvar.png"))); // NOI18N
        btnSalvar.setToolTipText("Salvar");
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
        btnIncluir.setToolTipText("Novo");
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
        btnCancelar.setToolTipText("Cancelar");
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
                    .addComponent(jToolBarMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
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

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        try {
            startWaitCursor();
            File f = new FileChoose(new String[]{"jpeg", "jpg"}).openDialog(lbImagem);

            if (Objects.isNull(f)) {
                categoria.imagem = "";
                lbImagem.setIcon(null);

                return;
            }

            BufferedImage imagem = new Imagem().getImage(f);

            lbImagem.setIcon(new ImageIcon(imagem));

            categoria.imagem = new Imagem().bufferedImageToBase64(imagem, new ExtensionFile().getExtension(f));

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());

        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_btnChooseImageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private ecommandtools.componentes.botao.Botao btnChooseImage;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JToolBar jToolBarMenu;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDescricao;
    private ecommandtools.componentes.rotulo.Rotulo lbImagem;
    private javax.swing.JPanel pnlMain;
    private ecommandtools.componentes.campotexto.CampoTexto txtCodigo;
    private ecommandtools.componentes.campotexto.CampoTexto txtDescricao;
    // End of variables declaration//GEN-END:variables

}
