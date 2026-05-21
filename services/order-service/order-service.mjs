import crypto from "crypto";

import {
    saveOrder,
    fetchAllOrders,
    fetchOrderById
} from "./db.mjs";

export const createOrder = async (body) => {

    validateOrder(body);

    const order = {
        orderId: crypto.randomUUID(),
        type: body.type,
        price: Number(body.price),
        quantity: Number(body.quantity),
        userId: body.userId,
        status: "OPEN",
        createdAt: new Date().toISOString()
    };

    await saveOrder(order);

    return {
        message: "Order created successfully",
        order
    };
};

export const getAllOrders = async () => {

    return await fetchAllOrders();
};

export const getOrderById = async (orderId) => {

    return await fetchOrderById(orderId);
};

const validateOrder = (body) => {

    if (!body.type || !["BUY", "SELL"].includes(body.type)) {
        throw new Error("INVALID_ORDER_TYPE");
    }

    if (!body.price || Number(body.price) <= 0) {
        throw new Error("INVALID_PRICE");
    }

    if (!body.quantity || Number(body.quantity) <= 0) {
        throw new Error("INVALID_QUANTITY");
    }

    if (!body.userId) {
        throw new Error("INVALID_USER");
    }
};