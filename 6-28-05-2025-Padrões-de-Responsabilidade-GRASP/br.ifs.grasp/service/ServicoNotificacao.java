package br.ifs.grasp.service;

import br.ifs.grasp.model.Relatorio;

public class ServicoNotificacao implements INotificacao {
    @Override
    public boolean enviarNotificacao(Relatorio relatorio) {
        if (relatorio == null) {
            System.out.println("Erro: dados insuficientes.");
            return false;
        }

        System.out.println("Enviando notificação: \n" + relatorio.getTemp());
        System.out.println("Notificação enviada com sucesso.");
        return true;
    }
}
