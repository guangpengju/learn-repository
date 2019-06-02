package com.gpj.design.pattern.strategy;

import org.junit.Test;

public class StrategyTest {
    @Test
    public void test(){
        Context contextA = new Context(new ConcreteStrategyA());
        contextA.contextAlgorithm();

        Context contextB = new Context(new ConcreteStrategyB());
        contextB.contextAlgorithm();

        Context contextC = new Context(new ConcreteStrategyC());
        contextC.contextAlgorithm();
    }
}
