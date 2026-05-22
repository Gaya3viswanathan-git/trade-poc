package com.trade.matchingengine.model;

public class MatchResult {

    private String message;
    private int matchedQuantity;
    private int remainingQuantity;
    private String status;

    public MatchResult() {
    }

    public MatchResult(
            String message,
            int matchedQuantity,
            int remainingQuantity,
            String status
    ) {
        this.message = message;
        this.matchedQuantity = matchedQuantity;
        this.remainingQuantity = remainingQuantity;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMatchedQuantity() {
        return matchedQuantity;
    }

    public void setMatchedQuantity(int matchedQuantity) {
        this.matchedQuantity = matchedQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}