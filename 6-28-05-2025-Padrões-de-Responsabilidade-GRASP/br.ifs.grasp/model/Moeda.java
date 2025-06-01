package br.ifs.grasp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Moeda {
    private final BigDecimal valor;
    private final String codigoISO;

    public Moeda(BigDecimal valor, String codigoISO) {
        if (codigoISO == null || codigoISO.trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo ISO da moeda não pode estar vazio");
        }
        this.valor = valor;
        this.codigoISO = codigoISO;
    }
    public Moeda(double valor, String codigoISO) {
        this(BigDecimal.valueOf(valor), codigoISO);
    }

    public Moeda soma(Moeda outra) {
        if (!this.codigoISO.equals(outra.codigoISO)) {
            throw new IllegalArgumentException("Não é possível somar valores de moedas diferentes diretamente: " +
                    this.codigoISO + " e " + outra.codigoISO);
        }
        return new Moeda(this.valor.add(outra.valor), this.codigoISO);
    }

    public Moeda multiplica(BigDecimal multiplicador) {
        return new Moeda(this.valor.multiply(multiplicador).setScale(2, RoundingMode.HALF_UP), this.codigoISO);
    }

    public Moeda subtrai(Moeda outra) {
        if (!this.codigoISO.equals(outra.codigoISO)) {
            throw new IllegalArgumentException("Não é possível subtrair valores de moedas diferentes diretamente: "
                    + this.codigoISO + " e " + outra.codigoISO);
        }
        return new Moeda(this.valor.subtract(outra.valor), this.codigoISO);
    }

    // Getters
    public BigDecimal getValor() { return valor; }
    public String getCodigoISO() { return codigoISO; }

    @Override
    public String toString() {
        return String.format("%s %.2f", codigoISO, valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, codigoISO);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Moeda moeda = (Moeda) obj;
        return valor.compareTo(moeda.valor) == 0 && codigoISO.equals(moeda.codigoISO);
    }
}
