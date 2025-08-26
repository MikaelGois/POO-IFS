package br.edu.ifs.designpatterns.visitor;

import br.edu.ifs.designpatterns.visitor.impl.Alimento;
import br.edu.ifs.designpatterns.visitor.impl.Cerveja;
import br.edu.ifs.designpatterns.visitor.impl.Cigarro;
import br.edu.ifs.designpatterns.visitor.impl.Vinho;

public interface Taxador {
  double visitar(Alimento alimento);

  double visitar(Cerveja cerveja);

  double visitar(Cigarro cigarro);

  double visitar(Vinho vinho);
}