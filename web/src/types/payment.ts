export interface PaymentProps {
    creationTimestamp: string,
    creatorId: string,
    orderId: string,
    payment: string,
    products: string[],
    status: string,
    total: number,
    updateTimestamp: string
}