package ecommand.view.cadastro;

import ecommand.dao.cadastro.UsuarioDAO;
import ecommand.model.cadastro.UsuarioFiltroConsultaVO;
import ecommand.model.cadastro.UsuarioVO;
import ecommand.tipo.SituacaoCadastro;
import ecommandtools.exception.ExceptionCustom;
import ecommandtools.exception.ExceptionMessage;
import ecommandtools.model.ColunaTabelaVO;
import java.util.List;
import ecommandtools.model.ItemListaComboVO;
import ecommandtools.utils.Formatacao;
import ecommandtools.utils.Mensagens;
import static ecommandtools.utils.Mensagens.MSG_SELECIONAR_ITENS_TABELA;
import ecommandtools.view.InternalFrameCustom;
import ecommandtools.view.JFrameCustom;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import static ecommandtools.utils.Mensagens.mensagemInfo;

public class UsuarioConsultaUI extends InternalFrameCustom {

    private List<UsuarioVO> listaUsuarios = null;
    private UsuarioCadastroUI formCadastroUsuario = null;

    public UsuarioConsultaUI(JFrameCustom formMain) throws Exception {
        super(formMain);
        initComponents();

        setCenterForm();

        cboSituacao.addItemLista(new ItemListaComboVO(SituacaoCadastro.ATIVO.getId(), SituacaoCadastro.ATIVO.getDescricao()));
        cboSituacao.addItemLista(new ItemListaComboVO(SituacaoCadastro.EXCLUIDO.getId(), SituacaoCadastro.EXCLUIDO.getDescricao()));
        cboSituacao.addItemLista(new ItemListaComboVO(SituacaoCadastro.TODOS.getId(), SituacaoCadastro.TODOS.getDescricao()));
        cboSituacao.setId(SituacaoCadastro.ATIVO.getId());

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

        if (formCadastroUsuario == null || formCadastroUsuario.isClosed()) {
            formCadastroUsuario = new UsuarioCadastroUI(this.getMainForm());
        }

        formCadastroUsuario.carregar(listaUsuarios.get(tbMain.getLinhaSelecionada()));
        formCadastroUsuario.setVisible();
    }

    public void inserir() throws Exception {
        if (formCadastroUsuario == null || formCadastroUsuario.isClosed()) {
            formCadastroUsuario = new UsuarioCadastroUI(this.getMainForm());
        }

        formCadastroUsuario.novo();
        formCadastroUsuario.setVisible();
    }

    public void excluir() throws Exception {
        if (tbMain.getLinhaSelecionada() == -1) {
            throw new ExceptionCustom(MSG_SELECIONAR_ITENS_TABELA);
        }

        Mensagens.confirmacao("Remover informações?", getTitle());

        UsuarioVO usuario = listaUsuarios.get(tbMain.getLinhaSelecionada());

        new UsuarioDAO().deletar(usuario);

        mensagemInfo(Mensagens.MSG_REMOVIDO_COM_SUCESSO);

        consultar();
    }

    public void consultar() throws Exception {
        UsuarioFiltroConsultaVO oFiltro = new UsuarioFiltroConsultaVO();

        oFiltro.id = txtCodigo.getInt();
        oFiltro.nome = txtUsuario.getText();
        oFiltro.idSituacao = cboSituacao.getId();

        listaUsuarios = new UsuarioDAO().consultar(oFiltro);

        exibirConsulta();
    }

    public void exibirConsulta() throws Exception {
        Object[][] dados = new Object[listaUsuarios.size()][tbMain.getColunas().size()];

        int i = 0;
        for (UsuarioVO usuario : listaUsuarios) {
            dados[i][0] = Formatacao.numberLeft(usuario.id, 3);
            dados[i][1] = usuario.nome;
            dados[i][2] = usuario.situacaocadastro;

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
        lbUsuario = new javax.swing.JLabel();
        txtUsuario = new ecommandtools.componentes.campotexto.CampoTexto();
        lbSituacao = new javax.swing.JLabel();
        cboSituacao = new ecommandtools.componentes.combo.Combo();
        menu = new javax.swing.JToolBar();
        btnConsultar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Consulta de Usuários");
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
                .addComponent(spTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlOperacoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbCodigo.setText("Código");

        txtCodigo.setColumns(3);
        txtCodigo.setTipoformato("Numero");

        lbUsuario.setText("Usuário");

        txtUsuario.setColumns(30);

        lbSituacao.setText("Situação");

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
                    .addComponent(lbUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSituacao)
                    .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOperacoesLayout.setVerticalGroup(
            pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlOperacoesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbSituacao)
                        .addGap(26, 26, 26))
                    .addGroup(pnlOperacoesLayout.createSequentialGroup()
                        .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCodigo)
                            .addComponent(lbUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
    private ecommandtools.componentes.combo.Combo cboSituacao;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbSituacao;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JToolBar menu;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlOperacoes;
    private javax.swing.JScrollPane spTabela;
    private ecommandtools.componentes.tabela.Tabela tbMain;
    private ecommandtools.componentes.campotexto.CampoTexto txtCodigo;
    private ecommandtools.componentes.campotexto.CampoTexto txtUsuario;
    // End of variables declaration//GEN-END:variables

    enum ColunasTabela {

        CODIGO(new ColunaTabelaVO("Código", SwingConstants.LEFT, true, false)),
        USUARIO(new ColunaTabelaVO("Usuário", SwingConstants.LEFT, true, false)),
        SITUACAO(new ColunaTabelaVO("Situação", SwingConstants.LEFT, true, false));

        private final ColunaTabelaVO coluna;

        private ColunasTabela(ColunaTabelaVO coluna) {
            this.coluna = coluna;
        }

        public ColunaTabelaVO getColuna() {
            return coluna;
        }

    }

}
