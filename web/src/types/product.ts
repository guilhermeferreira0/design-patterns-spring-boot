export interface ProductProtocol {
    productId: string,
    name: string,
    description: string,
    price: number,
    creationTimestamp: Date,
    updateTimestamp: Date,
    guarantee: boolean, 
    type: string,
    creatorId: string,
}