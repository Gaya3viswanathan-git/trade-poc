package com.trade.matchingengine.service;

import com.trade.matchingengine.engine.MatchingEngine;
import com.trade.matchingengine.model.MatchResult;
import com.trade.matchingengine.model.Order;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {

    private final MatchingEngine matchingEngine;

    public MatchingService(MatchingEngine matchingEngine) {
        this.matchingEngine = matchingEngine;
    }

    public MatchResult processOrder(Order order) {

        return matchingEngine.processOrder(order);
    }
}