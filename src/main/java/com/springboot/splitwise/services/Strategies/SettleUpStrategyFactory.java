package com.springboot.splitwise.services.Strategies;

public class SettleUpStrategyFactory {
    public static SettleUpStrategy getSettleUpStrategy(SettleUpStrategies strategyName){
        return new HeapBasedSettleUpStrategy();
    }
}
