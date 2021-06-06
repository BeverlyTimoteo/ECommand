package ecommand.view.cadastro;

import ecommand.dao.cadastro.ProdutoCategoriaDAO;
import ecommand.model.cadastro.ProdutoCategoriaFiltroConsultaVO;
import ecommand.model.cadastro.ProdutoCategoriaVO;
import ecommandtools.exception.ExceptionCustom;
import ecommandtools.exception.ExceptionMessage;
import ecommandtools.model.ColunaTabelaVO;
import java.util.List;
import ecommandtools.utils.Formatacao;
import ecommandtools.utils.Mensagens;
import static ecommandtools.utils.Mensagens.MSG_SELECIONAR_ITENS_TABELA;
import ecommandtools.view.InternalFrameCustom;
import ecommandtools.view.JFrameCustom;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import static ecommandtools.utils.Mensagens.mensagemInfo;

public class ProdutoCategoriaConsultaUI extends InternalFrameCustom {

    private List<ProdutoCategoriaVO> vCategoria = null;
    private ProdutoCategoriaCadastroUI formProdutoCategoria = null;

    public ProdutoCategoriaConsultaUI(JFrameCustom formMain) throws Exception {
        super(formMain);
        initComponents();

        setCenterForm();

        configurarColunas();
    }

    private void configurarColunas() throws Exception {
        List<ColunaTabelaVO> colunas = new ArrayList<>();

        for (ColunasTabela col : ColunasTabela.values()) {
            colunas.add(col.getColuna());
        }

        tbMain.setColunas(colunas);
    }

    public void alterar() throws Exception {
        if (tbMain.getLinhaSelecionada() == -1) {
            throw new ExceptionCustom(MSG_SELECIONAR_ITENS_TABELA);
        }

        if (formProdutoCategoria == null || formProdutoCategoria.isClosed()) {
            formProdutoCategoria = new ProdutoCategoriaCadastroUI(this.getMainForm());
        }

        formProdutoCategoria.carregar(vCategoria.get(tbMain.getLinhaSelecionada()));
        formProdutoCategoria.setVisible();
    }

    public void inserir() throws Exception {
        if (formProdutoCategoria == null || formProdutoCategoria.isClosed()) {
            formProdutoCategoria = new ProdutoCategoriaCadastroUI(this.getMainForm());
        }

        formProdutoCategoria.novo();
        formProdutoCategoria.setVisible();
    }

    public void excluir() throws Exception {
        if (tbMain.getLinhaSelecionada() == -1) {
            throw new ExceptionCustom(MSG_SELECIONAR_ITENS_TABELA);
        }

        Mensagens.confirmacao("Remover informações?", getTitle());

        ProdutoCategoriaVO categoria = vCategoria.get(tbMain.getLinhaSelecionada());

        new ProdutoCategoriaDAO().deletar(categoria);

        mensagemInfo(Mensagens.MSG_REMOVIDO_COM_SUCESSO);

        consultar();
    }

    public void consultar() throws Exception {
        ProdutoCategoriaFiltroConsultaVO oFiltro = new ProdutoCategoriaFiltroConsultaVO();

        oFiltro.id = txtCodigo.getInt();
        oFiltro.descricao = txtDescricao.getText();

        vCategoria = new ProdutoCategoriaDAO().consultar(oFiltro);

        exibirConsulta();
    }

    public void exibirConsulta() throws Exception {
        Object[][] dados = new Object[vCategoria.size()][tbMain.getColunas().size()];

        int i = 0;
        for (ProdutoCategoriaVO categoria : vCategoria) {
            dados[i][0] = Formatacao.numberLeft(categoria.id, 3);
            dados[i][1] = categoria.descricao;

            i++;
        }

        tbMain.setModel(dados);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        spTabela = new javax.swing.JScrollPane();
        tbMain = new ecommandtools.componentes.tabela.Tabela();
        pnlOperacoes = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        txtCodigo = new ecommandtools.componentes.campotexto.CampoTexto();
        lbDescricao = new javax.swing.JLabel();
        txtDescricao = new ecommandtools.componentes.campotexto.CampoTexto();
        menu = new javax.swing.JToolBar();
        btnConsultar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Consulta de Categoria Produto");
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

        tbMain.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMainMouseClicked(evt);
            }
        });
        spTabela.setViewportView(tbMain);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlOperacoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbCodigo.setText("Código");

        txtCodigo.setColumns(3);
        txtCodigo.setTipoformato("Numero");

        lbDescricao.setText("Descrição");

        txtDescricao.setColumns(30);

        javax.swing.GroupLayout pnlOperacoesLayout = new javax.swing.GroupLayout(pnlOperacoes);
        pnlOperacoes.setLayout(pnlOperacoesLayout);
        pnlOperacoesLayout.setHorizontalGroup(
            pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOperacoesLayout.setVerticalGroup(
            pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(lbDescricao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        menu.setFloatable(false);
        menu.setRollover(true);

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/pesquisar.png"))); // NOI18N
        btnConsultar.setToolTipText("Consultar");
        btnConsultar.setFocusable(false);
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        menu.add(btnConsultar);

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/novo.png"))); // NOI18N
        btnIncluir.setToolTipText("Incluir");
        btnIncluir.setFocusable(false);
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        menu.add(btnIncluir);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/editar.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setFocusable(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        menu.add(btnEditar);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecommandtools/image/excluir.png"))); // NOI18N
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setFocusable(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        menu.add(btnExcluir);

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

    private void tbMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMainMouseClicked
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
    }//GEN-LAST:event_tbMainMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDescricao;
    private javax.swing.JToolBar menu;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlOperacoes;
    private javax.swing.JScrollPane spTabela;
    private ecommandtools.componentes.tabela.Tabela tbMain;
    private ecommandtools.componentes.campotexto.CampoTexto txtCodigo;
    private ecommandtools.componentes.campotexto.CampoTexto txtDescricao;
    // End of variables declaration//GEN-END:variables

    enum ColunasTabela {

        CODIGO(new ColunaTabelaVO("Código", SwingConstants.LEFT, true, false)),
        DESCRICAO(new ColunaTabelaVO("Descrição", SwingConstants.LEFT, true, false));

        private final ColunaTabelaVO coluna;

        private ColunasTabela(ColunaTabelaVO coluna) {
            this.coluna = coluna;
        }

        public ColunaTabelaVO getColuna() {
            return coluna;
        }

    }

}
