package com.trade.matchingengine.engine;

import com.trade.matchingengine.model.Order;

import java.util.Comparator;

public class SellOrderComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {

        // Lower SELL price gets priority
        if (o1.getPrice() != o2.getPrice()) {
            return Double.compare(o1.getPrice(), o2.getPrice());
        }

        // Older order gets priority
        return Long.compare(o1.getTimestamp(), o2.getTimestamp());
    }
}