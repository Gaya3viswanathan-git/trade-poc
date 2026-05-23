export async function sendOrderToMatchingEngine(order) {
    const matchingEngineUrl = process.env.MATCHING_ENGINE_URL;

    try {
        const response = await fetch(`${matchingEngineUrl}/match`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(order)
        });

        if (!response.ok) {
            throw new Error(`Matching engine failed with status ${response.status}`);
        }

        return await response.json();

    } catch (error) {
        console.error("Matching engine communication error:", error);

        return {
            success: false,
            message: "Matching engine unavailable"
        };
    }
}