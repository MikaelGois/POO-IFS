package br.ifs.tdd.test;

import br.ifs.tdd.exception.EstoqueInsuficienteException;
import br.ifs.tdd.exception.OperacaoInvalidaException;
import br.ifs.tdd.exception.ProdutoDuplicadoException;
import br.ifs.tdd.exception.ProdutoNaoEncontradoException;
import br.ifs.tdd.exception.ValidacaoException;

import br.ifs.tdd.service.Estoque;
import br.ifs.tdd.model.Produto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EstoqueTest {
    private Estoque e;
    private Produto p;

    @BeforeEach
    void setup() {
        e = new Estoque();
        p = new Produto("1234567890123", "Caneta", "Azul", 1.0);
    }

    //
    // CADASTRO DE PRODUTOS
    //

    @Test
    @DisplayName("CT15: Cadastro de produto válido no estoque")
    void ct15() {
        e.cadastrar(p);
        assertEquals(p, e.buscar(p.getIdentificador()));
    }

    @Test
    @DisplayName("CT16: Cadastro de produto nulo")
    void ct16() {
        var ex = assertThrows(ValidacaoException.class, () -> e.cadastrar(null));
        assertEquals("Produto inválido", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT17: Cadastro de produto duplicado")
    void ct17() {
        e.cadastrar(p);
        var ex = assertThrows(ProdutoDuplicadoException.class, () -> e.cadastrar(p));
        assertEquals("Produto já cadastrado", ex.getMessage()); // feito
    }

    //
    // REMOÇÃO DE PRODUTOS
    //

    @Test
    @DisplayName("CT18: Exclusão de produto válido no estoque")
    void ct18() {
        e.cadastrar(p);
        e.remover(p.getIdentificador());
        var ex = assertThrows(ProdutoNaoEncontradoException.class, () -> e.buscar(p.getIdentificador()));
        assertEquals("Produto não encontrado", ex.getMessage());
        // se o produto não for encontrado, o produto foi removido,
        // porém, é necessário fazer a verificação e como consequência, lançar a exceção.
    }

    @Test
    @DisplayName("CT19: Exclusão de produto utilizando identificador nulo")
    void ct19() {
        var ex = assertThrows(ValidacaoException.class, () -> e.remover(null));
        assertEquals("Identificador de produto inválido", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT20: Exclusão de produto não cadastrado")
    void ct20() {
        var ex= assertThrows(ProdutoNaoEncontradoException.class, () -> e.remover(p.getIdentificador()));
        assertEquals("Produto não encontrado", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT21: Exclusão de produto cadastrado com lotes associados")
    void ct21() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 5, LocalDate.now().plusDays(1));
        var ex = assertThrows(OperacaoInvalidaException.class, () -> e.remover(p.getIdentificador()));
        assertEquals("Remova os lotes associados primeiro", ex.getMessage()); // feito
    }

    //
    // BUSCA DE PRODUTOS
    //

    @Test
    @DisplayName("CT22: Busca de produto válido no estoque")
    void ct22() {
        e.cadastrar(p);
        assertEquals(p, e.buscar(p.getIdentificador()));
    }

    @Test
    @DisplayName("CT23: Busca de produto utilizando identificador nulo")
    void ct23() {
        var ex = assertThrows(ValidacaoException.class, () -> e.buscar(null));
        assertEquals("Identificador de produto inválido", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT24: Busca de produto não cadastrado")
    void ct24() {
        var ex = assertThrows(ProdutoNaoEncontradoException.class, () -> e.buscar(p.getIdentificador()));
        assertEquals("Produto não encontrado", ex.getMessage()); // feito
    }

    //
    // ATUALIZAÇÃO DE PRODUTOS
    //

    @Test
    @DisplayName("CT25: Atualização de produto no estoque")
    void ct25() {
        e.cadastrar(p);
        Produto p2 = new Produto(p.getIdentificador(), "Lapiseira", "Grafite", 3.0);
        e.atualizar(p2);
        assertEquals("Lapiseira", e.buscar(p.getIdentificador()).getNome());
    }

    @Test
    @DisplayName("CT26: Atualização de produto utilizando produto nulo")
    void ct26() {
        var ex = assertThrows(ValidacaoException.class, () -> e.atualizar(null));
        assertEquals("Produto inválido", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT27: Atualização de produto não cadastrado")
    void ct27() {
        var ex = assertThrows(ProdutoNaoEncontradoException.class, () -> e.atualizar(p));
        assertEquals("Produto não encontrado", ex.getMessage()); // feito
    }

    //
    // ADIÇÃO DE ITENS DE UM PRODUTO
    //

    @Test
    @DisplayName("CT28: Adição de itens válidos no estoque")
    void ct28() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 30, LocalDate.now().plusDays(5));
        assertEquals(30, e.obterQuantidade(p.getIdentificador()));
    }

    @Test
    @DisplayName("CT29: Adição de itens no estoque utilizando identificador de produto nulo")
    void ct29() {
        var ex = assertThrows(ValidacaoException.class,
                () -> e.adicionar(null, 10, LocalDate.now().plusDays(5)));
        assertEquals("Identificador de produto inválido", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT30: Adição de itens no estoque utilizando identificador não cadastrado")
    void ct30() {
        var ex = assertThrows(ProdutoNaoEncontradoException.class,
                () -> e.adicionar(p.getIdentificador(), 10, LocalDate.now().plusDays(5)));
        assertEquals("Produto não encontrado", ex.getMessage());
    }

    @Test
    @DisplayName("CT31: Adição de itens no estoque utilizando quantidade negativa")
    void ct31() {
        e.cadastrar(p);
        var ex = assertThrows(ValidacaoException.class,
                () -> e.adicionar(p.getIdentificador(), -1, LocalDate.now().plusDays(5)));
        assertEquals("Quantidade inválida", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT32: Adição de itens no estoque utilizando data de validade nula")
    void ct32() {
        e.cadastrar(p);
        var ex = assertThrows(ValidacaoException.class,
                () -> e.adicionar(p.getIdentificador(), 10, null));
        assertEquals("Data de validade inválida", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT33: Adição de itens no estoque utilizando data de validade vencida")
    void ct33() {
        e.cadastrar(p);
        var ex = assertThrows(ValidacaoException.class,
                () -> e.adicionar(p.getIdentificador(), 10, LocalDate.now().minusDays(1)));
        assertEquals("Data de validade vencida", ex.getMessage()); // feito
    }

    //
    // RETIRADA DE ITENS DE UM PRODUTO
    //

    @Test
    @DisplayName("CT34: Retirada de itens válidos no estoque")
    void ct34() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 15, LocalDate.now().plusDays(5));
        e.retirar(p.getIdentificador(), 5);
        assertEquals(10, e.obterQuantidade(p.getIdentificador()));
    }

    @Test
    @DisplayName("CT35: Retirada priorizando lotes com validade mais próxima")
    void ct35() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 10, LocalDate.now().plusDays(1));
        e.adicionar(p.getIdentificador(), 20, LocalDate.now().plusDays(10));
        e.retirar(p.getIdentificador(), 15);

        //br.ifs.tdd.model.Produto@4a05ca7d,    0,      2025-05-14
        //br.ifs.tdd.model.Produto@4a05ca7d,    15,     2025-05-23

        assertEquals(15, e.obterQuantidade(p.getIdentificador()));
    }

    @Test
    @DisplayName("CT36: Retirada de itens utilizando identificador de produto nulo")
    void ct36() {
        var ex = assertThrows(ValidacaoException.class,
                () -> e.retirar(null, 5));
        assertEquals("Identificador de produto inválido", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT37: Retirada de itens de produto não cadastrado")
    void ct37() {
        var ex = assertThrows(ProdutoNaoEncontradoException.class,
                () -> e.retirar(p.getIdentificador(), 5));
        assertEquals("Produto não encontrado", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT38: Retirada de itens utilizando quantidade negativa")
    void ct38() {
        e.cadastrar(p);
        var ex = assertThrows(ValidacaoException.class,
                () -> e.retirar(p.getIdentificador(), -1));
        assertEquals("Quantidade inválida", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT39: Retirada de quantidade maior que a disponível")
    void ct39() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 4, LocalDate.now().plusDays(5));
        var ex = assertThrows(EstoqueInsuficienteException.class,
                () -> e.retirar(p.getIdentificador(), 5));
        assertEquals("Estoque insuficiente", ex.getMessage()); // feito
    }

    //
    // OBTENÇÃO DA QUANTIDADE DE ITENS NO ESTOQUE
    //

    @Test
    @DisplayName("CT40: Obtenção da quantidade total de itens no estoque")
    void ct40() {
        Produto p2 = new Produto("0987654321098", "Borracha", "Branca", 0.50);
        e.cadastrar(p);
        e.cadastrar(p2);
        e.adicionar(p.getIdentificador(), 15, LocalDate.now().plusDays(5));
        e.adicionar(p2.getIdentificador(), 5, LocalDate.now().plusDays(5));
        assertEquals(20, e.obterQuantidade());
    }

    @Test
    @DisplayName("CT41: Obtenção da quantidade de um produto específico")
    void ct41() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 15, LocalDate.now().plusDays(5));
        assertEquals(15, e.obterQuantidade(p.getIdentificador()));
    }

    @Test
    @DisplayName("CT42: Obtenção da quantidade de produto não cadastrado")
    void ct42() {
        var ex = assertThrows(ProdutoNaoEncontradoException.class,
                () -> e.obterQuantidade(p.getIdentificador()));
        assertEquals("Produto não encontrado", ex.getMessage()); // feito
    }

    @Test
    @DisplayName("CT43: Obtenção da quantidade utilizando identificador nulo")
    void ct43() {
        var ex = assertThrows(ValidacaoException.class,
                () -> e.obterQuantidade(null));
        assertEquals("Identificador de produto inválido", ex.getMessage()); // feito
    }

    //
    // IMPRESSÃO DE RELATÓRIOS
    //

    @Test
    @DisplayName("CT44: Impressão de relatório geral em formato textual")
    void ct44() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 2, LocalDate.now().plusDays(2));
        String rel = e.exibirRelatorio();
        assertTrue(rel.contains(p.getIdentificador()));
        System.out.println(rel);
    }

    @Test
    @DisplayName("CT45: Impressão de relatório geral em formato CSV")
    void ct45() throws IOException {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 2, LocalDate.now().plusDays(2));
        String csv = e.exibirRelatorioCSV();
        e.salvarEmArquivo("saida.csv", csv);
        assertTrue(csv.contains(p.getIdentificador()));
        assertTrue(csv.startsWith("id,nome,descricao,preco,quantidade,validade"));
    }

    @Test
    @DisplayName("CT46: Impressão de relatório geral em formato JSON")
    void ct46() throws IOException {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 2, LocalDate.now().plusDays(2));
        String json = e.exibirRelatorioJSON();
        e.salvarEmArquivo("saida.json", json);
        assertTrue(json.contains(p.getIdentificador()));
        assertTrue(json.contains("\"estoque\""));
    }
}
