package com.gpj.design.pattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Context {
    private Strategy strategy;

    public void contextAlgorithm(){
        strategy.algorithm();
    }
}
