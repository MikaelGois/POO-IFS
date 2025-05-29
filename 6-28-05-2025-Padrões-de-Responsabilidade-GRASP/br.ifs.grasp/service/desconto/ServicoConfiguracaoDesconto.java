package br.ifs.grasp.service.desconto;

import java.util.HashMap;
import java.util.Map;

public class ServicoConfiguracaoDesconto implements IConfiguracaoDesconto {
    private final Map<String, IEstrategiaDesconto> estrategiasPorCupom;

    public ServicoConfiguracaoDesconto() {
        estrategiasPorCupom = new HashMap<>();

        estrategiasPorCupom.put("BLACKFRIDAY", new DescontoBlackFriday());
        estrategiasPorCupom.put("20REAIS", new DescontoVinteReais());
        estrategiasPorCupom.put("SEM_DESCONTO", new SemDesconto());
    }

    @Override
    public IEstrategiaDesconto getEstrategia(String cupomDesconto) {
        if (cupomDesconto == null || cupomDesconto.trim().isEmpty()) {
            System.out.println("Cupom nulo ou vazio, não será aplicado desconto.");
            return new SemDesconto();
        }
        IEstrategiaDesconto estrategia = estrategiasPorCupom.get(cupomDesconto.toUpperCase());
        if (estrategia == null) {
            System.out.println("Cupom '" + cupomDesconto + "' não encontrado, não será aplicado desconto.");
            return new SemDesconto();
        }
        System.out.println("Estratégia encontrada para '" + cupomDesconto + "': " + estrategia.getClass().getSimpleName());
        return estrategia;
    };
}
