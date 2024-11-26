export interface OrderProps {
    creatorId: string,
    orderId: string,
    payment: string,
    products: string[],
    status: string,
    total: number,
    updateTimestamp: Date,
    creationTimestamp: Date
}