package br.edu.ifs.designpatterns.facade;

import br.edu.ifs.designpatterns.facade.impl.Cliente;
import br.edu.ifs.designpatterns.facade.impl.GeradorID;
import br.edu.ifs.designpatterns.facade.impl.Pedido;
import br.edu.ifs.designpatterns.facade.impl.Produto;
import br.edu.ifs.designpatterns.facade.impl.Repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Fachada {
  private final Repositorio<Cliente> repositorioClientes = new Repositorio<>();
  private final Repositorio<Produto> repositorioProdutos = new Repositorio<>();
  private final Repositorio<Pedido> repositorioPedidos = new Repositorio<>();
  // Mapa para associar o ID do pedido ao ID do cliente
  private final Map<String, String> pedidoClienteMap = new HashMap<>();

  public String registrarCliente(String nome) {
    String id = "Cli" + GeradorID.gerarClienteID();
    Cliente cliente = new Cliente(id, nome);
    repositorioClientes.criar(id, cliente);
    return id;
  }

  public List<String> listarClientes() {
    return repositorioClientes.recuperar().stream()
        .map(Object::toString)
        .collect(Collectors.toList());
  }

  public String registrarProduto(String nome, double preco) {
    String id = "Pro" + GeradorID.gerarProdutoID();
    Produto produto = new Produto(id, nome, preco);
    repositorioProdutos.criar(id, produto);
    return id;
  }

  public List<String> listarProdutos() {
    return repositorioProdutos.recuperar().stream()
        .map(Object::toString)
        .collect(Collectors.toList());
  }

  public void escolherProduto(String idCliente, String idProduto) {
    Cliente cliente = repositorioClientes.recuperar(idCliente);
    Produto produto = repositorioProdutos.recuperar(idProduto);
    if (cliente != null && produto != null) {
      cliente.getPedido().adicionar(produto);
    }
  }

  public double obterTotal(String idCliente) {
    Cliente cliente = repositorioClientes.recuperar(idCliente);
    if (cliente != null) {
      return cliente.getPedido().valorTotal();
    }
    return 0.0;
  }

  public String fecharCompra(String idCliente) {
    Cliente cliente = repositorioClientes.recuperar(idCliente);
    if (cliente != null && !cliente.getPedido().mostrarPedido().isEmpty()) {
      String idPedido = "Ped" + GeradorID.gerarPedidoID();
      Pedido pedidoAtual = cliente.getPedido();
      pedidoAtual.setId(idPedido);
      repositorioPedidos.criar(idPedido, pedidoAtual);

      // Associa o ID do pedido ao ID do cliente
      pedidoClienteMap.put(idPedido, idCliente);

      // Reseta o pedido do cliente para um novo
      cliente.setPedido(new Pedido());
      return idPedido;
    }
    return null;
  }

  public List<String> listarPedidos(String idCliente) {
    List<String> pedidosFormatados = new ArrayList<>();
    // Itera por todos os pedidos e filtra usando o mapa de associação
    for (Pedido pedido : repositorioPedidos.recuperar()) {
      if (idCliente.equals(pedidoClienteMap.get(pedido.getId()))) {
        // Formata a string de saída para corresponder ao esperado pelo teste
        pedidosFormatados.add(pedido.getId() + ": " + pedido.toString());
      }
    }
    return pedidosFormatados;
  }
}