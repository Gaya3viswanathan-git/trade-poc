package com.trade.matchingengine.engine;

import com.trade.matchingengine.model.Order;

import java.util.Comparator;

public class BuyOrderComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {

        // Higher BUY price gets priority
        if (o1.getPrice() != o2.getPrice()) {
            return Double.compare(o2.getPrice(), o1.getPrice());
        }

        // Older order gets priority
        return Long.compare(o1.getTimestamp(), o2.getTimestamp());
    }
}