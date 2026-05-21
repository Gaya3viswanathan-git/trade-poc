import {
    DynamoDBClient
} from "@aws-sdk/client-dynamodb";

import {
    DynamoDBDocumentClient,
    PutCommand,
    ScanCommand,
    GetCommand
} from "@aws-sdk/lib-dynamodb";

const client = new DynamoDBClient({});

const ddbDocClient = DynamoDBDocumentClient.from(client);

const tableName = process.env.ORDER_TABLE;

export const saveOrder = async (order) => {

    try {

        await ddbDocClient.send(
            new PutCommand({
                TableName: tableName,
                Item: order
            })
        );

    } catch (err) {

        console.error("DynamoDB save failed:", err);

        throw new Error("DB_SAVE_FAILED");
    }
};

export const fetchAllOrders = async () => {

    try {

        const response = await ddbDocClient.send(
            new ScanCommand({
                TableName: tableName
            })
        );

        return response.Items || [];

    } catch (err) {

        console.error("DynamoDB fetch all failed:", err);

        throw new Error("DB_FETCH_FAILED");
    }
};

export const fetchOrderById = async (orderId) => {

    try {

        const response = await ddbDocClient.send(
            new GetCommand({
                TableName: tableName,
                Key: {
                    orderId
                }
            })
        );

        return response.Item;

    } catch (err) {

        console.error("DynamoDB fetch by id failed:", err);

        throw new Error("DB_FETCH_BY_ID_FAILED");
    }
};