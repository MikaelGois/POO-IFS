package br.ifs.grasp.service;

public class ServicoPagamento {
    public boolean processarPagamento(double total) {
        System.out.println("Processando pagamento de R$ " + total);
        if (total <= 0) {
            System.out.println("Pagamento reprovado");
            return false;
        }
        System.out.println("Pagamento aprovado.");
        return true;
    }
}
