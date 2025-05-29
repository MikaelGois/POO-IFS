package br.ifs.grasp.service.cambio;

import br.ifs.grasp.model.Moeda;

public interface IConversorMoeda {
    Moeda converter(Moeda valorOrigem, String codigoMoedaDestino) throws Exception;
}
