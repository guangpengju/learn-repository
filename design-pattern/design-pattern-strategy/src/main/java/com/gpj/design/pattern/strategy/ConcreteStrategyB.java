package com.gpj.design.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteStrategyB implements Strategy {
    @Override
    public void algorithm() {
        log.info("{}---algorithm",this.getClass());
    }
}
