import {
    createOrder,
    getAllOrders,
    getOrderById
} from "./order-service.mjs";

const buildResponse = (statusCode, body) => {
    return {
        statusCode,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    };
};

export const createOrderHandler = async (event) => {

    try {

        if (!event.body) {
            return buildResponse(400, {
                message: "Request body is required"
            });
        }

        const body = JSON.parse(event.body);

        const result = await createOrder(body);

        return buildResponse(201, result);

    } catch (err) {

        console.error("Create order error:", err);

        if (err.message === "INVALID_ORDER_TYPE") {
            return buildResponse(400, {
                message: "Order type must be BUY or SELL"
            });
        }

        if (err.message === "INVALID_PRICE") {
            return buildResponse(400, {
                message: "Price must be greater than 0"
            });
        }

        if (err.message === "INVALID_QUANTITY") {
            return buildResponse(400, {
                message: "Quantity must be greater than 0"
            });
        }

        return buildResponse(500, {
            message: "Internal server error"
        });
    }
};

export const getOrdersHandler = async () => {

    try {

        const orders = await getAllOrders();

        return buildResponse(200, orders);

    } catch (err) {

        console.error("Get orders error:", err);

        return buildResponse(500, {
            message: "Internal server error"
        });
    }
};

export const getOrderByIdHandler = async (event) => {

    try {

        const orderId = event.pathParameters?.id;

        if (!orderId) {
            return buildResponse(400, {
                message: "Order id is required"
            });
        }

        const order = await getOrderById(orderId);

        if (!order) {
            return buildResponse(404, {
                message: "Order not found"
            });
        }

        return buildResponse(200, order);

    } catch (err) {

        console.error("Get order by id error:", err);

        return buildResponse(500, {
            message: "Internal server error"
        });
    }
};