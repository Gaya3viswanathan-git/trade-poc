package com.trade.matchingengine.controller;

import com.trade.matchingengine.model.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchingController {

    @PostMapping
    public String matchOrder(@RequestBody Order order) {

        return "Received order: " + order.getOrderId();
    }
}