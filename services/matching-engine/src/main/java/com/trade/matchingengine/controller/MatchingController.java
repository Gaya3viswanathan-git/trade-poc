package com.trade.matchingengine.controller;

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
    public String matchOrder(@RequestBody Order order) {

        matchingService.processOrder(order);

        return "Order processed successfully";
    }
}