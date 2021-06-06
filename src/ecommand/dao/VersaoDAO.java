package ecommand.dao;

import ecommandtools.connection.Conexao;
import ecommandtools.connection.Database;
import ecommandtools.connection.Sql;
import ecommandtools.utils.Texto;
import org.apache.commons.io.IOUtils;

public class VersaoDAO {

    public void atualizar() throws Exception {
        Sql sql;

        try {
            Conexao.begin();

            if (!Database.tabelaExiste("versao")) {
                sql = new Sql("CREATE TABLE versao (");
                sql.add("id SERIAL,");
                sql.add("versao VARCHAR (10),");
                sql.add("datacompilacao DATE,");
                sql.add("CONSTRAINT pk_versao PRIMARY KEY (id)");
                sql.add(");");

                sql.add("INSERT INTO versao (versao, datacompilacao) VALUES ('1.0.0', '2019-11-04');");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("tiporegimetributario")) {
                sql = new Sql("CREATE TABLE tiporegimetributario (");
                sql.add("id INTEGER NOT NULL,");
                sql.add("descricao VARCHAR (20) NOT NULL,");
                sql.add("CONSTRAINT pk_tiporegimetributario PRIMARY KEY (id)");
                sql.add(");");

                sql.add("INSERT INTO tiporegimetributario (id, descricao) VALUES (1, 'SIMPLES NACIONAL');");
                sql.add("INSERT INTO tiporegimetributario (id, descricao) VALUES (3, 'REGIME NORMAL');");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("tiporegimeespecialtributacaoissqn")) {
                sql = new Sql("CREATE TABLE tiporegimeespecialtributacaoissqn (");
                sql.add("id INTEGER NOT NULL,");
                sql.add("descricao VARCHAR (35) NOT NULL,");
                sql.add("CONSTRAINT pk_tiporegimeespecialtributacaoissqn PRIMARY KEY (id)");
                sql.add(");");

                sql.add("INSERT INTO tiporegimeespecialtributacaoissqn (id, descricao) VALUES (1, 'MICROEMPRESA MUNICIPAL');");
                sql.add("INSERT INTO tiporegimeespecialtributacaoissqn (id, descricao) VALUES (2, 'ESTIMATIVA');");
                sql.add("INSERT INTO tiporegimeespecialtributacaoissqn (id, descricao) VALUES (3, 'SOCIEDADE DE PROFISSIONAIS');");
                sql.add("INSERT INTO tiporegimeespecialtributacaoissqn (id, descricao) VALUES (4, 'COOPERATIVA');");
                sql.add("INSERT INTO tiporegimeespecialtributacaoissqn (id, descricao) VALUES (5, 'MICROEMPRESARIO INDIVIDUAL (MEI)');");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("tiposimnao")) {
                sql = new Sql("CREATE TABLE tiposimnao (");
                sql.add("id INTEGER NOT NULL,");
                sql.add("descricao VARCHAR (3) NOT NULL,");
                sql.add("CONSTRAINT pk_tiposimnao PRIMARY KEY (id)");
                sql.add(");");

                sql.add("INSERT INTO tiposimnao (id, descricao) VALUES (1, 'SIM');");
                sql.add("INSERT INTO tiposimnao (id, descricao) VALUES (2, 'NAO');");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("bairro")) {
                sql = new Sql("CREATE TABLE bairro (");
                sql.add("id SERIAL NOT NULL,");
                sql.add("descricao VARCHAR (60) NOT NULL,");
                sql.add("CONSTRAINT pk_bairro PRIMARY KEY (id)");
                sql.add(");");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("estado")) {
                sql = new Sql("CREATE TABLE estado (");
                sql.add("id INTEGER NOT NULL,");
                sql.add("descricao VARCHAR (60) NOT NULL,");
                sql.add("uf VARCHAR(2) NOT NULL,");
                sql.add("CONSTRAINT pk_estado PRIMARY KEY (id)");
                sql.add(");");

                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (11, 'RONDONIA', 'RO');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (12, 'ACRE', 'AC');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (13, 'AMAZONAS', 'AM');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (14, 'RORAIMA', 'RR');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (15, 'PARA', 'PA');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (16, 'AMAPA', 'AP');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (17, 'TOCANTINS', 'TO');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (21, 'MARANHAO', 'MA');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (22, 'PIAUI', 'PI');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (23, 'CEARA', 'CE');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (24, 'RIO GRANDE DO NORTE', 'RN');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (25, 'PARAIBA', 'PB');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (26, 'PERNAMBUCO', 'PE');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (27, 'ALAGOAS', 'AL');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (28, 'SERGIPE', 'SE');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (29, 'BAHIA', 'BA');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (31, 'MINAS GERAIS', 'MG');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (32, 'ESPIRITO SANTO', 'ES');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (33, 'RIO DE JANEIRO', 'RJ');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (35, 'SAO PAULO', 'SP');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (41, 'PARANA', 'PR');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (42, 'SANTA CATARINA', 'SC');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (43, 'RIO GRANDE DO SUL', 'RS');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (50, 'MATO GROSSO DO SUL', 'MS');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (51, 'MATO GROSSO', 'MT');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (52, 'GOIAS', 'GO');");
                sql.add("INSERT INTO estado(id, descricao, uf) VALUES (53, 'DISTRITO FEDERAL', 'DF');");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("municipio")) {
                sql = new Sql("CREATE TABLE municipio (");
                sql.add("id SERIAL NOT NULL,");
                sql.add("descricao VARCHAR (60) NOT NULL,");
                sql.add("id_estado INTEGER NOT NULL,");
                sql.add("ibge INTEGER NOT NULL,");
                sql.add("CONSTRAINT pk_municipio PRIMARY KEY (id),");
                sql.add("CONSTRAINT fk_id_estado FOREIGN KEY (id_estado) REFERENCES estado (id)");
                sql.add(");");

                sql.add(Texto.removerCaracteresInvalidos(IOUtils.toString(getClass().getResourceAsStream("/ecommand/script/municipios.sql"), "UTF-8")));

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("tipopessoa")) {
                sql = new Sql("CREATE TABLE tipopessoa (");
                sql.add("id SERIAL NOT NULL,");
                sql.add("descricao VARCHAR (15) NOT NULL,");
                sql.add("CONSTRAINT pk_tipopessoa PRIMARY KEY (id)");
                sql.add(");");

                sql.add("INSERT INTO tipopessoa (id, descricao) VALUES (1, 'FISICA');");
                sql.add("INSERT INTO tipopessoa (id, descricao) VALUES (2, 'JURIDICA');");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("fornecedor")) {
                sql = new Sql("CREATE TABLE fornecedor (");
                sql.add("id SERIAL NOT NULL,");
                sql.add("razao VARCHAR(60) NOT NULL,");
                sql.add("fantasia VARCHAR(60) NOT NULL,");
                sql.add("inscricaoestadual VARCHAR(14),");
                sql.add("inscricaomunicipal VARCHAR(15),");
                sql.add("cnpj NUMERIC(14,0) NOT NULL,");
                sql.add("logradouro VARCHAR(60) NOT NULL,");
                sql.add("numero VARCHAR(60) NOT NULL,");
                sql.add("complemento VARCHAR(60),");
                sql.add("id_bairro INTEGER NOT NULL,");
                sql.add("id_municipio INTEGER NOT NULL,");
                sql.add("cep VARCHAR(8) NOT NULL,");
                sql.add("id_regimetributario INTEGER,");
                sql.add("id_regimeespecialtributacaoissqn INTEGER,");
                sql.add("id_indicadorrateiodescontoissqn INTEGER,");
                sql.add("id_tipopessoa INTEGER NOT NULL,");
                sql.add("CONSTRAINT pk_fornecedor PRIMARY KEY (id),");
                sql.add("CONSTRAINT fk_id_bairro FOREIGN KEY (id_bairro) REFERENCES bairro(id),");
                sql.add("CONSTRAINT fk_id_municipio FOREIGN KEY (id_municipio) REFERENCES municipio(id),");
                sql.add("CONSTRAINT fk_id_regimetributario FOREIGN KEY (id_regimetributario) REFERENCES tiporegimetributario(id),");
                sql.add("CONSTRAINT fk_id_regimeespecialtributacaoissqn FOREIGN KEY (id_regimeespecialtributacaoissqn) REFERENCES tiporegimeespecialtributacaoissqn(id),");
                sql.add("CONSTRAINT fk_id_indicadorrateiodescontoissqn FOREIGN KEY (id_indicadorrateiodescontoissqn) REFERENCES tiposimnao(id),");
                sql.add("CONSTRAINT fk_id_tipopessoa FOREIGN KEY (id_tipopessoa) REFERENCES tipopessoa(id)");
                sql.add(");");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("loja")) {
                sql = new Sql("CREATE TABLE loja (");
                sql.add("id INTEGER NOT NULL,");
                sql.add("descricao VARCHAR(30),");
                sql.add("id_fornecedor INTEGER NOT NULL,");
                sql.add("CONSTRAINT pk_loja PRIMARY KEY (id),");
                sql.add("CONSTRAINT fk_id_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id)");
                sql.add(");");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("situacaocadastro")) {
                sql = new Sql("CREATE TABLE situacaocadastro (");
                sql.add("id SERIAL,");
                sql.add("descricao VARCHAR(15),");
                sql.add("CONSTRAINT pk_situacaocadastro PRIMARY KEY (id)");
                sql.add(");");

                sql.add("INSERT INTO situacaocadastro(id, descricao) VALUES (1, 'ATIVO');");
                sql.add("INSERT INTO situacaocadastro(id, descricao) VALUES (2, 'EXCLUIDO');");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("usuario")) {
                sql = new Sql("CREATE TABLE usuario (");
                sql.add("id SERIAL,");
                sql.add("nome VARCHAR(30),");
                sql.add("login VARCHAR(20),");
                sql.add("senha VARCHAR(50),");
                sql.add("digital TEXT,");
                sql.add("id_situacaocadastro INTEGER NOT NULL,");
                sql.add("CONSTRAINT pk_usuario PRIMARY KEY (id),");
                sql.add("CONSTRAINT fk_id_situacaocadastro FOREIGN KEY (id_situacaocadastro) REFERENCES situacaocadastro (id)");
                sql.add(");");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("unidademedida")) {
                sql = new Sql("CREATE TABLE unidademedida (");
                sql.add("id INTEGER,");
                sql.add("descricao VARCHAR (2),");
                sql.add("CONSTRAINT pk_unidademedida PRIMARY KEY (id));");

                Database.execute(sql);

                sql = new Sql("INSERT INTO unidademedida (id, descricao) VALUES (1, 'KG');");
                sql.add("INSERT INTO unidademedida (id, descricao) VALUES (2, 'PC');");
                sql.add("INSERT INTO unidademedida (id, descricao) VALUES (3, 'UN');");
                sql.add("INSERT INTO unidademedida (id, descricao) VALUES (4, 'LT');");
                sql.add("INSERT INTO unidademedida (id, descricao) VALUES (5, 'ML');");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("produtocategoria")) {
                sql = new Sql("CREATE TABLE produtocategoria (");
                sql.add("id SERIAL,");
                sql.add("descricao VARCHAR (30),");
                sql.add("imagem TEXT,");
                sql.add("CONSTRAINT pk_produtocategoria PRIMARY KEY (id));");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("produto")) {
                sql = new Sql("CREATE TABLE produto (");
                sql.add("id SERIAL,");
                sql.add("descricao VARCHAR(50),");
                sql.add("custo NUMERIC(10, 2),");
                sql.add("margem NUMERIC(10, 2),");
                sql.add("precovenda NUMERIC(10, 2),");
                sql.add("adicional BOOLEAN,");
                sql.add("comercializado BOOLEAN,");
                sql.add("controlaestoque BOOLEAN,");
                sql.add("insumo BOOLEAN,");
                sql.add("observacao TEXT,");
                sql.add("id_situacaocadastro INTEGER,");
                sql.add("id_unidademedida INTEGER,");
                sql.add("id_categoria INTEGER,");
                sql.add("CONSTRAINT pk_produto PRIMARY KEY (id),");
                sql.add("CONSTRAINT fk_id_situacaocadastro FOREIGN KEY (id_situacaocadastro) REFERENCES situacaocadastro (id),");
                sql.add("CONSTRAINT fk_id_produtocategoria FOREIGN KEY (id_categoria) REFERENCES produtocategoria (id),");
                sql.add("CONSTRAINT fk_id_unidademedida FOREIGN KEY (id_unidademedida) REFERENCES unidademedida (id));");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("mesa")) {
                sql = new Sql("CREATE TABLE mesa (");
                sql.add("id SERIAL,");
                sql.add("descricao VARCHAR (30),");
                sql.add("observacao TEXT,");
                sql.add("identificacao VARCHAR(5),");
                sql.add("CONSTRAINT pk_mesa PRIMARY KEY (id));");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("receita")) {
                sql = new Sql("CREATE TABLE receita (");
                sql.add("id SERIAL,");
                sql.add("id_produto INTEGER NOT NULL,");
                sql.add("CONSTRAINT pk_receita PRIMARY KEY (id),");
                sql.add("CONSTRAINT fk_id_produto FOREIGN KEY (id_produto) REFERENCES produto (id)");
                sql.add(");");

                Database.execute(sql);
            }

            if (!Database.tabelaExiste("receitaproduto")) {
                sql = new Sql("CREATE TABLE receitaproduto (");
                sql.add("id SERIAL,");
                sql.add("id_receita INTEGER NOT NULL,");
                sql.add("id_produto INTEGER NOT NULL,");
                sql.add("quantidade NUMERIC(10,3) NOT NULL,");
                sql.add("CONSTRAINT pk_receitaproduto PRIMARY KEY (id),");
                sql.add("CONSTRAINT fk_id_produto FOREIGN KEY (id_produto) REFERENCES produto (id),");
                sql.add("CONSTRAINT fk_id_receita FOREIGN KEY (id_receita) REFERENCES receita (id)");
                sql.add(");");

                Database.execute(sql);
            }

            Conexao.commit();

        } catch (Exception e) {
            Conexao.rollback();
            throw e;
        }
    }
}
