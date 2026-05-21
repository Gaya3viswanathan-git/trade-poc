package com.trade.matchingengine.service;

import com.trade.matchingengine.engine.MatchingEngine;
import com.trade.matchingengine.model.Order;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {

    private final MatchingEngine matchingEngine;

    public MatchingService() {
        this.matchingEngine = new MatchingEngine();
    }

    public void processOrder(Order order) {

        matchingEngine.processOrder(order);
    }
}