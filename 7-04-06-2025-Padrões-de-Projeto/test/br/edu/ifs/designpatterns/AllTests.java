package br.edu.ifs.designpatterns;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestAbstractFactory.class, TestAdapter.class, TestBridge.class, TestBuilder.class,
		TestChainResposibility.class, TestCommand.class, TestComposite.class, TestDecorator.class, TesteFlyweight.class,
		TesteStrategy.class, TesteTemplateMethod.class, TestFacade.class, TestFactoryMethod.class,
		TestInterpreter.class, TestIterator.class, TestMediator.class, TestMemento.class, TestObserver.class,
		TestPrototype.class, TestProxy.class, TestSingleton.class, TestState.class, TestVisitor.class })
public class AllTests {

}
