package br.ifs.grasp.service;

import br.ifs.grasp.model.Relatorio;

public interface INotificacao {
    boolean enviarNotificacao(Relatorio relatorio);
}
