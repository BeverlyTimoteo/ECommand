package ecommand;

import ecommand.classe.Constantes;
import ecommand.dao.cadastro.UsuarioDAO;
import ecommand.dao.VersaoDAO;
import ecommand.model.cadastro.UsuarioVO;
import ecommand.view.LoginUI;
import ecommand.view.MenuUI;
import ecommandtools.connection.Conexao;
import ecommandtools.enumeration.SistemasOperacionais;
import ecommandtools.exception.ExceptionCustom;
import ecommandtools.exception.ExceptionMessage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import ecommandtools.utils.LookAndFeelCustom;
import ecommandtools.utils.Mensagens;
import ecommandtools.view.Biometria;

public class Main {

    public static void main(String[] args) {
        try {
            LookAndFeelCustom.set();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Properties prop = getProp();

            Constantes.user = prop.getProperty("database.user");
            Constantes.password = prop.getProperty("database.password");
            Constantes.host = prop.getProperty("database.host");
            Constantes.port = prop.getProperty("database.port");
            Constantes.name = prop.getProperty("database.name");

            Constantes.utlizaBiometria = Boolean.valueOf(prop.getProperty("sistema.utilizabiometria"));

            Conexao.conectar(Constantes.user, Constantes.password, Constantes.host + ":" + Constantes.port + "/" + Constantes.name);

            criarTabelas();

        } catch (Exception ex) {
            ExceptionMessage.exibirMensagemException(ex, "Não foi possível conectar!");
            System.exit(0);
        }

        boolean login = true;

        for (String parametros : args) {
            if (parametros.equals("nologin")) {
                login = false;
            }
        }

        if (login) {
            iniciarLogin();

        } else {
            Constantes.usuario = new UsuarioVO();
            Constantes.usuario.nome = "ADMIN";
        }

        if (Constantes.usuario != null) {
            java.awt.EventQueue.invokeLater(() -> {
                new MenuUI().setVisible(true);
            });
        } else {
            System.exit(0);
        }
    }

    public static void criarTabelas() {
        try {
            if (Constantes.VERSAO.equals("1.0.0")) {
                new VersaoDAO().atualizar();
            }

        } catch (Exception e) {
            ExceptionMessage.exibirMensagemException(e, "Não foi possível atualizar banco de dados!");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

            System.exit(0);
        }
    }

    public static void iniciarLogin() {
        try {
            if (Constantes.utlizaBiometria) {
                try {
                    Biometria.criarLeituraLogin(null, new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                Biometria.fecharDialogo();

                            } catch (Exception ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                } catch (Exception ex) {
                    Mensagens.mensagemInfo("Problemas com o dispositivo: " + ex.getMessage());
                }

                try {
                    Biometria.encerrarBiometria();

                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String digital = Biometria.getBiometriaCapturada();

            if (!digital.isEmpty()) {
                Constantes.usuario = new UsuarioDAO().validarBiometriaUsuario(digital);

                if (Constantes.usuario == null) {
                    Mensagens.mensagemInfo("Usuário não encontrado!");
                    iniciarLogin();
                }

            } else if (Constantes.usuario == null) {
                LoginUI.criarLogin(null);
            }
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static Properties getProp() throws Exception {
        File f;

        if (SistemasOperacionais.get() == SistemasOperacionais.WINDOWS.getId()) {
            f = new File("C:/ecommand/ecommand.properties");

        } else {
            f = new File("/ecommand/ecommand.properties");
        }

        if (!f.exists()) {
            throw new ExceptionCustom("Arquivo de configuração não encontrado!");
        }

        Properties props = new Properties();
        props.load(new FileInputStream(f));

        return props;
    }
}
