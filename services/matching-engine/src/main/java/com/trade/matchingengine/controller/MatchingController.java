package com.trade.matchingengine.controller;

import com.trade.matchingengine.model.MatchResult;
import com.trade.matchingengine.model.Order;
import com.trade.matchingengine.service.MatchingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @PostMapping
    public MatchResult matchOrder(
            @RequestBody Order order
    ) {

        return matchingService.processOrder(order);
    }
}