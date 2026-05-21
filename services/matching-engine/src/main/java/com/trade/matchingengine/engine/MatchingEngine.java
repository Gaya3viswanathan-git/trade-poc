package com.trade.matchingengine.engine;

import com.trade.matchingengine.model.Order;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;

@Component
public class MatchingEngine {

    private final PriorityQueue<Order> buyOrders;
    private final PriorityQueue<Order> sellOrders;

    public MatchingEngine() {

        this.buyOrders = new PriorityQueue<>(new BuyOrderComparator());

        this.sellOrders = new PriorityQueue<>(new SellOrderComparator());
    }

    public void processOrder(Order order) {

        if ("BUY".equalsIgnoreCase(order.getType())) {

            buyOrders.offer(order);

            System.out.println("BUY order added: " + order.getOrderId());

        } else if ("SELL".equalsIgnoreCase(order.getType())) {

            sellOrders.offer(order);

            System.out.println("SELL order added: " + order.getOrderId());

        } else {

            throw new IllegalArgumentException("Invalid order type");
        }

        printOrderBook();
    }

    private void printOrderBook() {

        System.out.println("\n===== BUY ORDERS =====");

        for (Order order : buyOrders) {
            System.out.println(
                    order.getOrderId()
                            + " | Price: " + order.getPrice()
                            + " | Qty: " + order.getQuantity()
            );
        }

        System.out.println("\n===== SELL ORDERS =====");

        for (Order order : sellOrders) {
            System.out.println(
                    order.getOrderId()
                            + " | Price: " + order.getPrice()
                            + " | Qty: " + order.getQuantity()
            );
        }

        System.out.println("\n=========================\n");
    }
}