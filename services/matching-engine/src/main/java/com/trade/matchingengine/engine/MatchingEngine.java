package com.trade.matchingengine.engine;

import com.trade.matchingengine.model.MatchResult;
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

    public MatchResult processOrder(Order order) {

        int originalQuantity = order.getQuantity();

        if ("BUY".equalsIgnoreCase(order.getType())) {

            processBuyOrder(order);

        } else if ("SELL".equalsIgnoreCase(order.getType())) {

            processSellOrder(order);

        } else {

            throw new IllegalArgumentException("Invalid order type");
        }

        int matchedQuantity =
                originalQuantity - order.getQuantity();

        String status;

        if (matchedQuantity == 0) {

            status = "OPEN";

        } else if (order.getQuantity() == 0) {

            status = "FULL_MATCH";

        } else {

            status = "PARTIAL_MATCH";
        }

        printOrderBook();

        return new MatchResult(
                "Order processed successfully",
                matchedQuantity,
                order.getQuantity(),
                status
        );
    }

    private void processBuyOrder(Order buyOrder) {

        while (!sellOrders.isEmpty()
                && buyOrder.getQuantity() > 0) {

            Order bestSell = sellOrders.peek();

            // Match condition
            if (buyOrder.getPrice() >= bestSell.getPrice()) {

                int matchedQuantity = Math.min(
                        buyOrder.getQuantity(),
                        bestSell.getQuantity()
                );

                System.out.println(
                        "TRADE EXECUTED => BUY: "
                                + buyOrder.getOrderId()
                                + " SELL: "
                                + bestSell.getOrderId()
                                + " QTY: "
                                + matchedQuantity
                );

                buyOrder.setQuantity(
                        buyOrder.getQuantity() - matchedQuantity
                );

                bestSell.setQuantity(
                        bestSell.getQuantity() - matchedQuantity
                );

                // Remove SELL if fully matched
                if (bestSell.getQuantity() == 0) {
                    sellOrders.poll();
                }

            } else {

                break;
            }
        }

        // Remaining BUY quantity goes to heap
        if (buyOrder.getQuantity() > 0) {

            buyOrders.offer(buyOrder);

            System.out.println(
                    "BUY order added to book: "
                            + buyOrder.getOrderId()
            );
        }
    }

    private void processSellOrder(Order sellOrder) {

        while (!buyOrders.isEmpty()
                && sellOrder.getQuantity() > 0) {

            Order bestBuy = buyOrders.peek();

            // Match condition
            if (bestBuy.getPrice() >= sellOrder.getPrice()) {

                int matchedQuantity = Math.min(
                        sellOrder.getQuantity(),
                        bestBuy.getQuantity()
                );

                System.out.println(
                        "TRADE EXECUTED => BUY: "
                                + bestBuy.getOrderId()
                                + " SELL: "
                                + sellOrder.getOrderId()
                                + " QTY: "
                                + matchedQuantity
                );

                sellOrder.setQuantity(
                        sellOrder.getQuantity() - matchedQuantity
                );

                bestBuy.setQuantity(
                        bestBuy.getQuantity() - matchedQuantity
                );

                // Remove BUY if fully matched
                if (bestBuy.getQuantity() == 0) {
                    buyOrders.poll();
                }

            } else {

                break;
            }
        }

        // Remaining SELL quantity goes to heap
        if (sellOrder.getQuantity() > 0) {

            sellOrders.offer(sellOrder);

            System.out.println(
                    "SELL order added to book: "
                            + sellOrder.getOrderId()
            );
        }
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