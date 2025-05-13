package br.ifs.tdd.test;

import br.ifs.tdd.model.Lote;
import br.ifs.tdd.model.Produto;
import br.ifs.tdd.exception.ValidacaoException;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoteTest {

    private Produto criarProdutoValido() {
        return new Produto("1234567890123", "Caneta", "Azul", 1.50);
    }

    @Test
    @DisplayName("CT10: Construção de lote válido")
    void ct10() {
        Produto produto = criarProdutoValido();
        var l = new Lote(produto, 20, LocalDate.now().plusDays(10));
        assertEquals(produto, l.getProduto());
        assertEquals(20, l.getQuantidade());
        assertEquals(LocalDate.now().plusDays(10), l.getDataValidade());

    }

    @Test
    @DisplayName("CT11: Produto nulo")
    void ct11() {
        var ex = assertThrows(ValidacaoException.class,
                () -> new Lote(null, 20, LocalDate.now().plusDays(10)));
        assertEquals("Produto inválido", ex.getMessage());
    }

    @Test
    @DisplayName("CT12: Quantidade negativa")
    void ct12() {
        Produto produto = criarProdutoValido();
        var ex = assertThrows(ValidacaoException.class,
                () -> new Lote(produto, -20, LocalDate.now().plusDays(10)));
        assertEquals("Quantidade inválida (deve ser positiva)", ex.getMessage());
    }

    @Test
    @DisplayName("CT13: Validade nula")
    void ct13() {
        Produto produto = criarProdutoValido();
        var ex = assertThrows(ValidacaoException.class,
                () -> new Lote(produto, 20, null));
        assertEquals("Data de validade obrigatória", ex.getMessage());
    }

    @Test
    @DisplayName("CT14: Validade nula")
    void ct14() {
        Produto produto = criarProdutoValido();
        var ex = assertThrows(ValidacaoException.class,
                () -> new Lote(produto, 20, LocalDate.now().minusDays(1)));
        assertEquals("Data de validade vencida", ex.getMessage());
    }
}
