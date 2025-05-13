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
        assertEquals("Produto não encontrado", ex.getMessage()); // Revisar
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

    @Test
    void CT22_buscarProdutoValido() {
        e.cadastrar(p);
        assertEquals(p, e.buscar(p.getIdentificador()));
    }

    @Test
    void CT23_buscarIdNulo() {
        assertThrows(ValidacaoException.class, () -> e.buscar(null));
    }

    @Test
    void CT24_buscarNaoCadastrado() {
        assertThrows(ProdutoNaoEncontradoException.class, () -> e.buscar(p.getIdentificador()));
    }

    @Test
    void CT25_atualizarProduto() {
        e.cadastrar(p);
        Produto p2 = new Produto(p.getIdentificador(), "Lapiseira", "Grafite", 3.0);
        e.atualizar(p2);
        assertEquals("Lapiseira", e.buscar(p.getIdentificador()).getNome());
    }

    @Test
    void CT26_atualizarNullo() {
        assertThrows(ValidacaoException.class, () -> e.atualizar(null));
    }

    @Test
    void CT27_atualizarNaoCadastrado() {
        assertThrows(ProdutoNaoEncontradoException.class, () -> e.atualizar(p));
    }

    @Test
    void CT28_adicionarItensValidos() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 30, LocalDate.now().plusDays(5));
        assertEquals(30, e.obterQuantidade(p.getIdentificador()));
    }

    @Test
    void CT29_adicionarIdNulo() {
        assertThrows(ValidacaoException.class,
                () -> e.adicionar(null, 10, LocalDate.now().plusDays(5)));
    }

    @Test
    void CT30_adicionarNaoCadastrado() {
        assertThrows(ProdutoNaoEncontradoException.class,
                () -> e.adicionar(p.getIdentificador(), 10, LocalDate.now().plusDays(5)));
    }

    @Test
    void CT31_adicionarQuantidadeNegativa() {
        e.cadastrar(p);
        assertThrows(ValidacaoException.class,
                () -> e.adicionar(p.getIdentificador(), -1, LocalDate.now().plusDays(5)));
    }

    @Test
    void CT32_adicionarValidadeNula() {
        e.cadastrar(p);
        assertThrows(ValidacaoException.class,
                () -> e.adicionar(p.getIdentificador(), 10, null));
    }

    @Test
    void CT33_adicionarValidadeVencida() {
        e.cadastrar(p);
        assertThrows(ValidacaoException.class,
                () -> e.adicionar(p.getIdentificador(), 10, LocalDate.now().minusDays(1)));
    }

    @Test
    void CT34_retirarItensValidos() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 15, LocalDate.now().plusDays(5));
        e.retirar(p.getIdentificador(), 5);
        assertEquals(10, e.obterQuantidade(p.getIdentificador()));
    }

    @Test
    void CT35_retirarPriorizaValidadeProxima() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 10, LocalDate.now().plusDays(1));
        e.adicionar(p.getIdentificador(), 20, LocalDate.now().plusDays(10));
        e.retirar(p.getIdentificador(), 15);
        assertEquals(0, e.obterQuantidade(p.getIdentificador()));
        // lote com validade dist distante deve ter 15
        assertEquals(15, e.obterQuantidade(p.getIdentificador()));
    }

    @Test
    void CT36_retirarIdNulo() {
        assertThrows(ValidacaoException.class,
                () -> e.retirar(null, 5));
    }

    @Test
    void CT37_retirarNaoCadastrado() {
        assertThrows(ProdutoNaoEncontradoException.class,
                () -> e.retirar(p.getIdentificador(), 5));
    }

    @Test
    void CT38_retirarQuantidadeNegativa() {
        e.cadastrar(p);
        assertThrows(ValidacaoException.class,
                () -> e.retirar(p.getIdentificador(), -1));
    }

    @Test
    void CT39_retirarMaisQueDisponivel() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 4, LocalDate.now().plusDays(5));
        assertThrows(EstoqueInsuficienteException.class,
                () -> e.retirar(p.getIdentificador(), 5));
    }

    @Test
    void CT40_obterQuantidadeTotal() {
        Produto p2 = new Produto("0987654321098", "Borracha", "Branca", 0.50);
        e.cadastrar(p);
        e.cadastrar(p2);
        e.adicionar(p.getIdentificador(), 15, LocalDate.now().plusDays(5));
        e.adicionar(p2.getIdentificador(), 5, LocalDate.now().plusDays(5));
        assertEquals(20, e.obterQuantidade());
    }

    @Test
    void CT41_obterQuantidadeProduto() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 15, LocalDate.now().plusDays(5));
        assertEquals(15, e.obterQuantidade(p.getIdentificador()));
    }

    @Test
    void CT42_obterQuantidadeNaoCadastrado() {
        assertThrows(ProdutoNaoEncontradoException.class,
                () -> e.obterQuantidade(p.getIdentificador()));
    }

    @Test
    void CT43_obterQuantidadeIdNulo() {
        assertThrows(ValidacaoException.class,
                () -> e.obterQuantidade(null));
    }

    @Test
    void CT44_relatorioTexto() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 2, LocalDate.now().plusDays(2));
        String rel = e.exibirRelatorio();
        assertTrue(rel.contains(p.getIdentificador()));
    }

    @Test
    void CT45_relatorioCSV() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 2, LocalDate.now().plusDays(2));
        String csv = e.exibirRelatorioCSV();
        assertTrue(csv.startsWith("id,nome,descricao,preco,quantidade,validade"));
    }

    @Test
    void CT46_relatorioJSON() {
        e.cadastrar(p);
        e.adicionar(p.getIdentificador(), 2, LocalDate.now().plusDays(2));
        String json = e.exibirRelatorioJSON();
        assertTrue(json.contains("\"estoque\""));
    }
}