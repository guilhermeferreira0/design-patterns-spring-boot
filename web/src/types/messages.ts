export interface MessagesProtocol {
    messageId?: string,
    message?: string,
    senderId?: string,
    receiverId?: string, 
    creationTimestamp?: Date,
    updateTimestamp?: Date
}