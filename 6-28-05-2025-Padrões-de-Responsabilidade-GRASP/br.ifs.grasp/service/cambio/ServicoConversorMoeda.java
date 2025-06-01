package br.ifs.grasp.service.cambio;

import br.ifs.grasp.model.Moeda;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ServicoConversorMoeda implements IConversorMoeda {
    private static final Map<String, BigDecimal> taxasParaBRL = new HashMap<>();

    static {
        taxasParaBRL.put("USD", new BigDecimal("5.70"));
        taxasParaBRL.put("EUR", new BigDecimal("6.40"));
        taxasParaBRL.put("BRL", BigDecimal.ONE);
    }

    @Override
    public Moeda converter(Moeda valorOrigem, String codigoMoedaDestino) throws Exception {
        String origemISO = valorOrigem.getCodigoISO().toUpperCase();
        String destinoISO = codigoMoedaDestino.toUpperCase();

        if (origemISO.equals(destinoISO)) {
            return valorOrigem;
        }

        BigDecimal taxaOrigemParaBRL = taxasParaBRL.get(origemISO);
        BigDecimal taxaDestinoParaBRL = taxasParaBRL.get(destinoISO);

        if (taxaOrigemParaBRL == null) {
            throw new Exception("Taxa de conversão não encontrada para a moeda de origem: " + origemISO);
        }
        if (taxaDestinoParaBRL == null) {
            throw new Exception("Taxa de conversão não encontrada para a moeda de destino: " + destinoISO);
        }

        BigDecimal valorEmBRL = valorOrigem.getValor().multiply(taxaOrigemParaBRL);
        BigDecimal valorConvertido = valorEmBRL.divide(taxaDestinoParaBRL, 2, BigDecimal.ROUND_HALF_UP);

        System.out.println("Resultado da conversão: " + new Moeda(valorConvertido, destinoISO));
        return new Moeda(valorConvertido, destinoISO);
    }
}
