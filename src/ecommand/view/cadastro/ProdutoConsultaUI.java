package ecommand.view.cadastro;

import ecommand.dao.cadastro.ProdutoDAO;
import ecommand.model.cadastro.ProdutoFiltroConsultaVO;
import ecommand.model.cadastro.ProdutoVO;
import ecommand.tipo.SituacaoCadastro;
import ecommandtools.exception.ExceptionCustom;
import ecommandtools.exception.ExceptionMessage;
import ecommandtools.model.ColunaTabelaVO;
import java.util.ArrayList;
import java.util.List;
import ecommandtools.utils.Formatacao;
import ecommandtools.model.ItemListaComboVO;
import ecommandtools.utils.Mensagens;
import static ecommandtools.utils.Mensagens.MSG_SELECIONAR_ITENS_TABELA;
import ecommandtools.view.InternalFrameCustom;
import ecommandtools.view.JFrameCustom;
import javax.swing.SwingConstants;
import static ecommandtools.utils.Mensagens.mensagemInfo;

public class ProdutoConsultaUI extends InternalFrameCustom {

    List<ProdutoVO> vProduto = null;
    ProdutoCadastroUI formCadastroProduto = null;

    public ProdutoConsultaUI(JFrameCustom formMain) throws Exception {
        super(formMain);
        initComponents();

        setCenterForm();

        btnExportar.setVisible(false);

        cboSituacao.addItemLista(new ItemListaComboVO(SituacaoCadastro.ATIVO.getId(), SituacaoCadastro.ATIVO.getDescricao()));
        cboSituacao.addItemLista(new ItemListaComboVO(SituacaoCadastro.EXCLUIDO.getId(), SituacaoCadastro.EXCLUIDO.getDescricao()));
        cboSituacao.addItemLista(new ItemListaComboVO(SituacaoCadastro.TODOS.getId(), SituacaoCadastro.TODOS.getDescricao()));
        cboSituacao.setId(SituacaoCadastro.ATIVO.getId());

        configurarColunas();

    }

    public void setCarregar() throws Exception {
        btnExportar.setVisible(true);

    }

    private void configurarColunas() throws Exception {
        List<ColunaTabelaVO> colunas = new ArrayList<>();

        for (ColunasTabela col : ColunasTabela.values()) {
            colunas.add(col.getColuna());
        }

        tblProduto.setColunas(colunas);
    }

    public void alterar() throws Exception {
        if (tblProduto.getSelectedRow() == -1) {
            throw new ExceptionCustom(MSG_SELECIONAR_ITENS_TABELA);
        }

        if (formCadastroProduto == null || formCadastroProduto.isClosed()) {
            formCadastroProduto = new ProdutoCadastroUI(this.getMainForm());
        }

        formCadastroProduto.carregar(vProduto.get(tblProduto.getLinhaSelecionada()));
        formCadastroProduto.setVisible();
    }

    public void inserir() throws Exception {
        if (formCadastroProduto == null || formCadastroProduto.isClosed()) {
            formCadastroProduto = new ProdutoCadastroUI(this.getMainForm());
        }

        formCadastroProduto.novo();
        formCadastroProduto.setVisible();
    }

    public void excluir() throws Exception {
        if (tblProduto.getSelectedRow() == -1) {
            throw new ExceptionCustom(MSG_SELECIONAR_ITENS_TABELA);
        }

        Mensagens.confirmacao("Remover informações?", getTitle());

        ProdutoVO produto = vProduto.get(tblProduto.getLinhaSelecionada());

        new ProdutoDAO().deletar(produto);

        mensagemInfo(Mensagens.MSG_REMOVIDO_COM_SUCESSO);

        consultar();
    }

    public void consultar() throws Exception {
        ProdutoFiltroConsultaVO oFiltro = new ProdutoFiltroConsultaVO();

        oFiltro.id = txtCodigo.getInt();
        oFiltro.descricao = txtDescricao.getText();
        oFiltro.idSituacaoCadastro = cboSituacao.getId();

        vProduto = new ProdutoDAO().consultar(oFiltro);

        exibirConsulta();
    }

    public void exibirConsulta() throws Exception {
        Object[][] dados = new Object[vProduto.size()][tblProduto.getColunas().size()];

        int i = 0;

        for (ProdutoVO produto : vProduto) {
            dados[i][ColunaOrdenacao.CODIGO.getId()] = Formatacao.numberLeft(produto.id, 3);
            dados[i][ColunaOrdenacao.DESCRICAO.getId()] = produto.descricao;
            dados[i][ColunaOrdenacao.CUSTO.getId()] = Formatacao.getFormatDecimal2(produto.custo);
            dados[i][ColunaOrdenacao.MARGEM.getId()] = Formatacao.getFormatDecimal2(produto.margem);
            dados[i][ColunaOrdenacao.PRECOVENDA.getId()] = Formatacao.getFormatDecimal2(produto.precoVenda);
            dados[i][ColunaOrdenacao.ADICIONAL.getId()] = produto.adicional;
            dados[i][ColunaOrdenacao.COMERCIALIZADO.getId()] = produto.comercializado;
            dados[i][ColunaOrdenacao.CONTROLAESTOQUE.getId()] = produto.controlaEstoque;
            dados[i][ColunaOrdenacao.INSUMO.getId()] = produto.insumo;
            dados[i][ColunaOrdenacao.CATEGORIA.getId()] = produto.categoria;
            dados[i][ColunaOrdenacao.SITUACAOCADASTRO.getId()] = produto.situacaoCadastro;

            i++;
        }

        tblProduto.setModel(dados);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new ecommandtools.componentes.tabela.Tabela();
        pnlOperacoes = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        txtCodigo = new ecommandtools.componentes.campotexto.CampoTexto();
        txtDescricao = new ecommandtools.componentes.campotexto.CampoTexto();
        lblSituacao = new javax.swing.JLabel();
        cboSituacao = new ecommandtools.componentes.combo.Combo();
        menu = new javax.swing.JToolBar();
        btnConsultar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnExportar = new ecommandtools.componentes.radiobotao.RadioBotao();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Consulta de Produtos");
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

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlOperacoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCodigo.setText("Código");

        lblDescricao.setText("Descrição");

        txtCodigo.setColumns(3);
        txtCodigo.setTipoformato("Numero");

        txtDescricao.setColumns(30);

        lblSituacao.setText("Situação");

        javax.swing.GroupLayout pnlOperacoesLayout = new javax.swing.GroupLayout(pnlOperacoes);
        pnlOperacoes.setLayout(pnlOperacoesLayout);
        pnlOperacoesLayout.setHorizontalGroup(
            pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSituacao))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        pnlOperacoesLayout.setVerticalGroup(
            pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(lblDescricao)
                    .addComponent(lblSituacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        menu.setFloatable(false);
        menu.setRollover(true);

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/pesquisar.png"))); // NOI18N
        btnConsultar.setFocusable(false);
        btnConsultar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        menu.add(btnConsultar);

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/novo.png"))); // NOI18N
        btnIncluir.setFocusable(false);
        btnIncluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIncluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        menu.add(btnIncluir);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/editar.png"))); // NOI18N
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        menu.add(btnEditar);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/excluir.png"))); // NOI18N
        btnExcluir.setFocusable(false);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        menu.add(btnExcluir);

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/exportar.png"))); // NOI18N
        btnExportar.setFocusable(false);
        btnExportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menu.add(btnExportar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOperacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            startWaitCursor();
            consultar();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());
        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            startWaitCursor();
            alterar();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());
        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        try {
            startWaitCursor();
            inserir();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());

        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            startWaitCursor();
            excluir();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());
        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        try {
            startWaitCursor();
            if (evt.getClickCount() == 2) {
                alterar();
            }

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, getTitle());
        } finally {
            stopWaitCursor();
        }
    }//GEN-LAST:event_tblProdutoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private ecommandtools.componentes.radiobotao.RadioBotao btnExportar;
    private javax.swing.JButton btnIncluir;
    private ecommandtools.componentes.combo.Combo cboSituacao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblSituacao;
    private javax.swing.JToolBar menu;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlOperacoes;
    private ecommandtools.componentes.tabela.Tabela tblProduto;
    private ecommandtools.componentes.campotexto.CampoTexto txtCodigo;
    private ecommandtools.componentes.campotexto.CampoTexto txtDescricao;
    // End of variables declaration//GEN-END:variables

    enum ColunasTabela {

        CODIGO(new ColunaTabelaVO("Código", SwingConstants.LEFT, true, false)),
        DESCRICAO(new ColunaTabelaVO("Descrição", SwingConstants.LEFT, true, false)),
        CUSTO(new ColunaTabelaVO("Custo", SwingConstants.LEFT, true, false)),
        MARGEM(new ColunaTabelaVO("Margem", SwingConstants.LEFT, true, false)),
        PRECOVENDA(new ColunaTabelaVO("Preço Venda", SwingConstants.LEFT, true, false)),
        ADICIONAL(new ColunaTabelaVO("Adicional", SwingConstants.CENTER, true, false)),
        COMERCIALIZADO(new ColunaTabelaVO("Comercializado", SwingConstants.CENTER, true, false)),
        CONTROLAESTOQUE(new ColunaTabelaVO("Controla Estoque", SwingConstants.CENTER, true, false)),
        INSUMO(new ColunaTabelaVO("Insumo", SwingConstants.CENTER, true, false)),
        CATEGORIA(new ColunaTabelaVO("Categoria", SwingConstants.LEFT, true, false)),
        SITUACAOCADASTRO(new ColunaTabelaVO("Situação Cadastro", SwingConstants.LEFT, true, false));

        private final ColunaTabelaVO coluna;

        private ColunasTabela(ColunaTabelaVO coluna) {
            this.coluna = coluna;
        }

        public ColunaTabelaVO getColuna() {
            return coluna;
        }
    }

    enum ColunaOrdenacao {

        CODIGO(0),
        DESCRICAO(1),
        CUSTO(2),
        MARGEM(3),
        PRECOVENDA(4),
        ADICIONAL(5),
        COMERCIALIZADO(6),
        CONTROLAESTOQUE(7),
        INSUMO(8),
        CATEGORIA(9),
        SITUACAOCADASTRO(10);

        private final int id;

        private ColunaOrdenacao(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
