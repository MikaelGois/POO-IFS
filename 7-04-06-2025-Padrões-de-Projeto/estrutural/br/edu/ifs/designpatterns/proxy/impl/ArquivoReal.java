package br.edu.ifs.designpatterns.proxy.impl;

import br.edu.ifs.designpatterns.proxy.Arquivo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class ArquivoReal implements Arquivo {
  private final Path path;

  public ArquivoReal(String nome) {
    this.path = Path.of(nome);
  }

  @Override
  public void criar() {
    try {
      if (!Files.exists(path.getParent())) {
        Files.createDirectories(path.getParent());
      }
      if (!Files.exists(path)) {
        Files.createFile(path);
      }
    } catch (IOException e) {
      throw new RuntimeException("Falha ao criar arquivo", e);
    }
  }

  @Override
  public String recuperar() {
    try {
      return Files.lines(path).collect(Collectors.joining("\n"));
    } catch (IOException e) {
      return "";
    }
  }

  @Override
  public void escrever(String texto) {
    try {
      String conteudo = recuperar();
      if (!conteudo.isEmpty()) {
        texto = conteudo + "\n" + texto;
      }
      Files.writeString(path, texto, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    } catch (IOException e) {
      throw new RuntimeException("Falha ao escrever no arquivo", e);
    }
  }

  @Override
  public void remover() {
    try {
      Files.deleteIfExists(path);
    } catch (IOException e) {
      throw new RuntimeException("Falha ao remover arquivo", e);
    }
  }
}