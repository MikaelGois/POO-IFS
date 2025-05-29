package br.ifs.grasp.service.desconto;

public interface IConfiguracaoDesconto {
    IEstrategiaDesconto getEstrategia(String cupomDesconto);
}
