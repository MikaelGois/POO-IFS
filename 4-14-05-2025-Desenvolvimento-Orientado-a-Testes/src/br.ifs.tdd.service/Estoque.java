package br.ifs.tdd.service;

import br.ifs.tdd.model.Lote;
import br.ifs.tdd.model.Produto;
import br.ifs.tdd.exception.EstoqueInsuficienteException;
import br.ifs.tdd.exception.OperacaoInvalidaException;
import br.ifs.tdd.exception.ProdutoDuplicadoException;
import br.ifs.tdd.exception.ProdutoNaoEncontradoException;
import br.ifs.tdd.exception.ValidacaoException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.*;

public class Estoque {
    private final Map<String, Produto> produtos = new HashMap<>();
    private final Map<String, List<Lote>> lotesPorProduto = new HashMap<>();

    public void cadastrar(Produto produto) {
        if (produto == null) {
            throw new ValidacaoException("Produto inválido"); // feito
        }
        String id = produto.getIdentificador();
        if (produtos.containsKey(id)) {
            throw new ProdutoDuplicadoException("Produto já cadastrado"); // feito
        }
        produtos.put(id, produto);
        lotesPorProduto.put(id, new ArrayList<>());
    }

    public void remover(String idProduto) {
        if (idProduto == null || idProduto.isBlank()) {
            throw new ValidacaoException("Identificador de produto inválido"); // feito
        }
        if (!produtos.containsKey(idProduto)) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado"); // feito
        }
        List<Lote> lotes = lotesPorProduto.get(idProduto);
        if (lotes != null && !lotes.isEmpty()) {
            throw new OperacaoInvalidaException("Remova os lotes associados primeiro"); // feito
        }
        produtos.remove(idProduto);
        lotesPorProduto.remove(idProduto);
    }

    public Produto buscar(String idProduto) {
        if (idProduto == null || idProduto.isBlank()) {
            throw new ValidacaoException("Identificador de produto inválido"); // feito
        }
        Produto p = produtos.get(idProduto);
        if (p == null) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado"); // feito
        }
        return p;
    }

    public void atualizar(Produto produto) {
        if (produto == null) {
            throw new ValidacaoException("Produto inválido"); // feito
        }
        String id = produto.getIdentificador();
        if (!produtos.containsKey(id)) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado"); // feito
        }
        produtos.put(id, produto);
    }

    public void adicionar(String idProduto, int quantidade, LocalDate validade) {
        Produto p = buscar(idProduto);
        if (quantidade <= 0) {
            throw new ValidacaoException("Quantidade inválida"); // feito
        }
        if (validade == null) {
            throw new ValidacaoException("Data de validade inválida"); // feito
        }
        if (validade.isBefore(LocalDate.now())) {
            throw new ValidacaoException("Data de validade vencida"); // feito
        }
        Lote lote = new Lote(p, quantidade, validade);
        lotesPorProduto.get(idProduto).add(lote);
    }

    public void retirar(String idProduto, int quantidade) {
        Produto p = buscar(idProduto);
        if (quantidade <= 0) {
            throw new ValidacaoException("Quantidade inválida"); // feito
        }
        List<Lote> lotes = lotesPorProduto.get(idProduto);
        int totalDisponivel = lotes.stream().mapToInt(Lote::getQuantidade).sum();
        if (quantidade > totalDisponivel) {
            throw new EstoqueInsuficienteException("Estoque insuficiente"); // feito
        }
        // ordena por validade ascendente
        List<Lote> ordenados = lotes.stream()
                .sorted(Comparator.comparing(Lote::getDataValidade))
                .collect(Collectors.toList());
        int restante = quantidade;
        for (Lote lote : ordenados) {
            int qtdLote = lote.getQuantidade();
            if (qtdLote <= restante) {
                restante -= qtdLote;
                lote.setQuantidade(0);
            } else {
                lote.setQuantidade(qtdLote - restante);
                restante = 0;
            }
            if (restante == 0) break;
        }
    }

    public int obterQuantidade() {
        return lotesPorProduto.values().stream()
                .flatMap(List::stream)
                .mapToInt(Lote::getQuantidade)
                .sum();
    }

    public int obterQuantidade(String idProduto) {
        Produto p = buscar(idProduto);
        return lotesPorProduto.get(idProduto).stream()
                .mapToInt(Lote::getQuantidade)
                .sum();
    }

    public String exibirRelatorio() {
        StringBuilder sb = new StringBuilder();
        for (String id : produtos.keySet()) {
            Produto p = produtos.get(id);
            sb.append(id).append(",").append(p.getNome()).append(",")
                    .append(p.getDescricao()).append(",").append(p.getPreco()).append("");
            for (Lote l : lotesPorProduto.get(id)) {
                sb.append("  Lote: ").append(l.getQuantidade())
                        .append(" - ").append(l.getDataValidade()).append("");
            }
        }
        return sb.toString();
    }

    public String exibirRelatorioCSV() {
        StringBuilder sb = new StringBuilder("id,nome,descricao,preco,quantidade,validade\n");
        for (String id : produtos.keySet()) {
            Produto p = produtos.get(id);
            for (Lote l : lotesPorProduto.get(id)) {
                sb.append(id).append(",")
                        .append(p.getNome()).append(",")
                        .append(p.getDescricao()).append(",")
                        .append(p.getPreco()).append(",")
                        .append(l.getQuantidade()).append(",")
                        .append(l.getDataValidade());
            }
        }
        return sb.toString();
    }

    public String exibirRelatorioJSON() {
        // implementação simples sem biblioteca externa
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"estoque\": [");
        boolean firstProd = true;
        for (String id : produtos.keySet()) {
            if (!firstProd) sb.append(",");
            firstProd = false;
            Produto p = produtos.get(id);
            sb.append("{\"id\":\"").append(id).append("\",")
                    .append("\"nome\":\"").append(p.getNome()).append("\",")
                    .append("\"descricao\":\"").append(p.getDescricao()).append("\",")
                    .append("\"preco\":").append(p.getPreco()).append(",")
                    .append("\"lotes\":[");
            boolean firstLote = true;
            for (Lote l : lotesPorProduto.get(id)) {
                if (!firstLote) sb.append(",");
                firstLote = false;
                sb.append("{\"quantidade\":").append(l.getQuantidade())
                        .append(",\"validade\":\"").append(l.getDataValidade()).append("\"}");
            }
            sb.append("]}");
        }
        sb.append("]}");
        return sb.toString();
    }

    public void salvarEmArquivo(String nomeArquivo, String conteudo) throws IOException {
        if (nomeArquivo == null || nomeArquivo.isBlank()) {
            throw new ValidacaoException("Nome de arquivo inválido"); // feito
        } else if (conteudo == null || conteudo.isBlank()) {
            throw new ValidacaoException("Conteúdo inválido"); // feito
        } else if (nomeArquivo.endsWith(".csv") || nomeArquivo.endsWith(".json")) {
            try {
                Path path = Paths.get("src/br.ifs.tdd.test/" + nomeArquivo);
                Files.createDirectories(path.getParent());
                Files.writeString(path, conteudo);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage());
            }
        }
    }
}