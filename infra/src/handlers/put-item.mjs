import { DynamoDBClient } from "@aws-sdk/client-dynamodb";
import { DynamoDBDocumentClient, PutCommand } from "@aws-sdk/lib-dynamodb";

const client = new DynamoDBClient({});
const ddbDocClient = DynamoDBDocumentClient.from(client);

const tableName = process.env.ORDER_TABLE;

export const putItemHandler = async (event) => {
    const body = JSON.parse(event.body);

    const order = {
        orderId: Date.now().toString(),
        type: body.type,
        price: body.price,
        quantity: body.quantity,
        userId: body.userId
    };

    try {
        await ddbDocClient.send(new PutCommand({
            TableName: tableName,
            Item: order
        }));

        return {
            statusCode: 200,
            body: JSON.stringify({
                message: "Order created",
                order: order
            })
        };
    } catch (err) {
        console.log("Error:", err);
        return {
            statusCode: 500,
            body: JSON.stringify({ error: "Failed to create order" })
        };
    }
};