package br.ifs.grasp.service.desconto;

import br.ifs.grasp.model.Moeda;
import br.ifs.grasp.model.Pedido;

import java.math.BigDecimal;

public class DescontoBlackFriday implements IEstrategiaDesconto {
    private static final BigDecimal PERCETUAL_DESCONTO = new BigDecimal("0.20");

    @Override
    public Moeda aplicarDesconto(Moeda totalBruto, Pedido pedido) {
        System.out.println("Aplicando desconto de Black Friday de " + PERCETUAL_DESCONTO.multiply(new BigDecimal(100)) + "%" );
        BigDecimal valorDescontado = totalBruto.getValor().multiply(BigDecimal.ONE.subtract(PERCETUAL_DESCONTO));
        return new Moeda(valorDescontado, totalBruto.getCodigoISO());
    }
}
