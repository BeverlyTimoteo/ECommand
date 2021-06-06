package ecommand.view.cadastro;

import ecommand.dao.cadastro.ProdutoDAO;
import ecommand.model.cadastro.ProdutoVO;
import ecommand.tipo.UnidadeMedida;
import ecommand.tipo.SituacaoCadastro;
import ecommandtools.exception.ExceptionCustom;
import ecommandtools.exception.ExceptionMessage;
import ecommandtools.utils.Formatacao;
import ecommandtools.utils.Mensagens;
import ecommandtools.view.InternalFrameCustom;
import ecommandtools.view.JFrameCustom;
import static ecommandtools.utils.Mensagens.mensagemInfo;

public class ProdutoCadastroUI extends InternalFrameCustom {

    private ProdutoVO oProduto = new ProdutoVO();

    public ProdutoCadastroUI(JFrameCustom formMain) throws Exception {
        super(formMain);
        initComponents();

        setCenterForm();

        cboSituacao.setTabela("situacaocadastro");
        cboSituacao.carregar();

        cboCategoria.setTabela("produtocategoria");
        cboCategoria.carregar();

        cboMedida.setTabela("unidademedida");
        cboMedida.carregar();

        cboSituacao.setId(SituacaoCadastro.ATIVO.getId());

        habilitarTela(false);
    }

    private void habilitarTela(boolean habilitar) throws Exception {
        cboSituacao.setEnabled(habilitar);
    }

    public void salvar() throws Exception {
        if (txtDescricao.getTexto().trim().isEmpty()) {
            throw new ExceptionCustom("Informe uma descrição para o produto!");
        }

        oProduto.id = txtCodigo.getInt();
        oProduto.descricao = txtDescricao.getTexto();
        oProduto.idUnidadeMedida = cboMedida.getId();
        oProduto.custo = txtCusto.getDouble();
        oProduto.margem = txtMargem.getDouble();
        oProduto.precoVenda = txtPrecoVenda.getDouble();
        oProduto.idSituacaoCadastro = cboSituacao.getId();
        oProduto.adicional = chkAdicional.isSelected();
        oProduto.comercializado = chkComercializado.isSelected();
        oProduto.controlaEstoque = chkControlaEstoque.isSelected();
        oProduto.insumo = chkInsumo.isSelected();
        oProduto.idCategoria = cboCategoria.getId();

        oProduto.observacao = txtObs.getText();

        new ProdutoDAO().salvar(oProduto);

        txtCodigo.setText(Formatacao.numberLeft(oProduto.id, 3));

        mensagemInfo(Mensagens.MSG_SALVO_COM_SUCESSO);
    }

    public void novo() throws Exception {
        txtCodigo.setText("");
        txtDescricao.setText("");
        txtCusto.setText("");
        txtMargem.setText("");
        txtPrecoVenda.setText("");
        cboMedida.setId(UnidadeMedida.KG.getId());
        chkAdicional.setSelected(false);
        chkInsumo.setSelected(false);
        chkComercializado.setSelected(false);
        chkControlaEstoque.setSelected(false);
        txtObs.setText("");
        cboSituacao.setId(SituacaoCadastro.ATIVO.getId());

    }

    public void carregar(ProdutoVO p_produto) throws Exception {
        oProduto = new ProdutoDAO().carregar(p_produto.id);

        txtCodigo.setText(Formatacao.numberLeft(oProduto.id, 3));
        txtDescricao.setText(oProduto.descricao);
        txtCusto.setDouble(oProduto.custo);
        txtMargem.setDouble(oProduto.margem);
        txtPrecoVenda.setDouble(oProduto.precoVenda);
        cboMedida.setId(oProduto.idUnidadeMedida);
        cboSituacao.setId(oProduto.idSituacaoCadastro);
        chkInsumo.setSelected(oProduto.insumo);
        chkAdicional.setSelected(oProduto.adicional);
        chkComercializado.setSelected(oProduto.comercializado);
        chkControlaEstoque.setSelected(oProduto.controlaEstoque);
        cboCategoria.setId(oProduto.idCategoria);
        txtObs.setText(oProduto.observacao);

        habilitarTela(oProduto.idSituacaoCadastro == SituacaoCadastro.EXCLUIDO.getId());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new ecommandtools.componentes.campotexto.CampoTexto();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new ecommandtools.componentes.campotexto.CampoTexto();
        lblCusto = new javax.swing.JLabel();
        txtCusto = new ecommandtools.componentes.campotexto.CampoTexto();
        lblMargem = new javax.swing.JLabel();
        txtMargem = new ecommandtools.componentes.campotexto.CampoTexto();
        lblPrecoVenda = new javax.swing.JLabel();
        txtPrecoVenda = new ecommandtools.componentes.campotexto.CampoTexto();
        chkAdicional = new javax.swing.JCheckBox();
        lblSituacao = new javax.swing.JLabel();
        cboSituacao = new ecommandtools.componentes.combo.Combo();
        chkComercializado = new javax.swing.JCheckBox();
        chkInsumo = new javax.swing.JCheckBox();
        chkControlaEstoque = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        cboMedida = new ecommandtools.componentes.combo.Combo();
        lblMedida = new javax.swing.JLabel();
        lblSituacao1 = new javax.swing.JLabel();
        cboCategoria = new ecommandtools.componentes.combo.Combo();
        jToolBarMenu = new javax.swing.JToolBar();
        btnSalvar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cadastro de Produto");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/produto.png"))); // NOI18N
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

        lblCodigo.setText("Código");

        txtCodigo.setEditable(false);
        txtCodigo.setEnabled(false);
        txtCodigo.setTipoformato("Numero");

        lblDescricao.setText("Descrição");

        txtDescricao.setColumns(30);

        lblCusto.setText("Preço de Custo");

        txtCusto.setColumns(10);
        txtCusto.setTipoformato("Decimal2");

        lblMargem.setText("% Margem Bruta");

        txtMargem.setEditable(false);
        txtMargem.setColumns(5);
        txtMargem.setTipoformato("Decimal2");
        txtMargem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMargemFocusLost(evt);
            }
        });

        lblPrecoVenda.setText("Preço de Venda");

        txtPrecoVenda.setColumns(10);
        txtPrecoVenda.setTipoformato("Decimal2");
        txtPrecoVenda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecoVendaFocusLost(evt);
            }
        });

        chkAdicional.setText("Adicional");

        lblSituacao.setText("Situação");

        chkComercializado.setText("Comercializado");

        chkInsumo.setText("Insumo");

        chkControlaEstoque.setText("Controla Estoque");

        jLabel1.setText("Observação");

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane1.setViewportView(txtObs);

        lblMedida.setText("Unidade Medida");

        lblSituacao1.setText("Categoria");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(txtCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblMargem, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(txtMargem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSituacao)
                                    .addComponent(cboSituacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDescricao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblPrecoVenda)
                            .addGap(27, 27, 27)
                            .addComponent(lblCusto))
                        .addComponent(lblCodigo)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSituacao1)
                                .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(chkAdicional)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chkComercializado)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chkInsumo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chkControlaEstoque))
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(lblDescricao)
                    .addComponent(lblMedida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCusto)
                    .addComponent(lblMargem)
                    .addComponent(lblPrecoVenda)
                    .addComponent(lblSituacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMargem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSituacao1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAdicional)
                    .addComponent(chkComercializado)
                    .addComponent(chkInsumo)
                    .addComponent(chkControlaEstoque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
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
                    .addComponent(jToolBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void txtMargemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMargemFocusLost
        try {
            double margem = txtMargem.getDouble();
            double custo = txtCusto.getDouble();
            double precoVenda = (custo * margem) / 100 + custo;

            txtPrecoVenda.setText(Formatacao.getFormatDecimal2(precoVenda));

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());
        }
    }//GEN-LAST:event_txtMargemFocusLost

    private void txtPrecoVendaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecoVendaFocusLost
        try {
            double precoVenda = txtPrecoVenda.getDouble();
            double custo = txtCusto.getDouble();
            double margem = ((precoVenda - custo) / precoVenda) * 100;

            txtMargem.setText(Formatacao.getFormatDecimal2(margem));

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());
        }
    }//GEN-LAST:event_txtPrecoVendaFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSalvar;
    private ecommandtools.componentes.combo.Combo cboCategoria;
    private ecommandtools.componentes.combo.Combo cboMedida;
    private ecommandtools.componentes.combo.Combo cboSituacao;
    private javax.swing.JCheckBox chkAdicional;
    private javax.swing.JCheckBox chkComercializado;
    private javax.swing.JCheckBox chkControlaEstoque;
    private javax.swing.JCheckBox chkInsumo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBarMenu;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCusto;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblMargem;
    private javax.swing.JLabel lblMedida;
    private javax.swing.JLabel lblPrecoVenda;
    private javax.swing.JLabel lblSituacao;
    private javax.swing.JLabel lblSituacao1;
    private javax.swing.JPanel pnlMain;
    private ecommandtools.componentes.campotexto.CampoTexto txtCodigo;
    private ecommandtools.componentes.campotexto.CampoTexto txtCusto;
    private ecommandtools.componentes.campotexto.CampoTexto txtDescricao;
    private ecommandtools.componentes.campotexto.CampoTexto txtMargem;
    private javax.swing.JTextArea txtObs;
    private ecommandtools.componentes.campotexto.CampoTexto txtPrecoVenda;
    // End of variables declaration//GEN-END:variables

}
