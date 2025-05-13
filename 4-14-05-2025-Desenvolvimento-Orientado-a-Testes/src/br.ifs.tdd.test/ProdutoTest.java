package br.ifs.tdd.test;

import br.ifs.tdd.exception.ValidacaoException;
import br.ifs.tdd.model.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {
    @Test
    @DisplayName("CT01: Construção de produto válido")
    void ct01() {
        var p = new Produto("1234567890123", "Caneta", "Esferográfica", 2.5);
        assertEquals("1234567890123", p.getIdentificador());
        assertEquals("Caneta", p.getNome());
        assertEquals("Esferográfica", p.getDescricao());
        assertEquals(2.5, p.getPreco());
    }

    @Test
    @DisplayName("CT02: Identificador vazio ou nulo")
    void ct02() {
        var ex = assertThrows(ValidacaoException.class,
                () -> new Produto("", "Caneta", "desc", 2.5));
        assertEquals("Identificador obrigatório", ex.getMessage());
    }

    @Test
    @DisplayName("CT03: Identificador com comprimento inválido")
    void ct03() {
        var ex = assertThrows(ValidacaoException.class,
                () -> new Produto("123456789012", "Caneta", "desc", 2.5));
        assertEquals("Tamanho do identificador inválido (13 caracteres)", ex.getMessage());
    }

    @Test
    @DisplayName("CT04: Identificador com caracteres não numéricos")
    void ct04() {
        var ex = assertThrows(ValidacaoException.class,
                () -> new Produto("12345ABC90123", "Caneta", "desc", 2.5));
        assertEquals("Formato do identificador inválido (apenas números)", ex.getMessage());
    }

    @Test
    @DisplayName("CT05: Nome vazio ou nulo")
    void ct05() {
        var ex = assertThrows(ValidacaoException.class,
                () -> new Produto("1234567890123", "", "desc", 2.5));
        assertEquals("Nome obrigatório", ex.getMessage());
    }

    @Test
    @DisplayName("CT06: Nome muito longo (>100 caracteres)")
    void ct06() {
        var ex = assertThrows(ValidacaoException.class,
                () -> new Produto("1234567890123", "Lorem ipsum dolor sit amet officia ut consectetur quis aute in non et labore et sint veniam est ut ut sunt id ad aliquip commodo esse qui non elit quis.",
                        "desc", 2.5));
        assertEquals("Nome excede 100 caracteres", ex.getMessage());
    }

    @Test
    @DisplayName("CT07: Descrição vazia ou nula")
    void ct07() {
        var ex = assertThrows(ValidacaoException.class,
                () -> new Produto("1234567890123", "Caneta", "", 2.5));
        assertEquals("Descrição obrigatória", ex.getMessage());
    }

    @Test
    @DisplayName("CT08: Descrição muito longa (>500 caracteres)")
    void ct08() {
        var ex = assertThrows(ValidacaoException.class,
                () -> new Produto("1234567890123", "Caneta", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eget tortor risus. Curabitur aliquet quam id dui posuere blandit. Nulla porttitor accumsan tincidunt. Pellentesque in ipsum id orci porta dapibus. Vivamus suscipit tortor eget felis porttitor volutpat. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eget tortor risus. Curabitur aliquet quam id dui posuere blandit. Nulla porttitor accumsan tincidunt. Pellentesque in ipsum id orci porta dapibus. Vivamus suscipit tortor eget felis porttitor volutpat.",
                        2.5));
        assertEquals("Descrição excede 500 caracteres", ex.getMessage());
    }

    @Test
    @DisplayName("CT09: Preço negativo")
    void ct09() {
        var ex = assertThrows(ValidacaoException.class,
                () -> new Produto("1234567890123", "Caneta", "desc", -2.5));
        assertEquals("Preço inválido (deve ser positivo)", ex.getMessage());
    }
}