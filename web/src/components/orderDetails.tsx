import { useState } from "react";
import { OrderProps } from "../types/order";
import { usePayment } from "../hooks/usePayment";
import { Modal } from "./modal";
import { PaymentProps } from "../types/payment";
import { FaRegTrashAlt } from "react-icons/fa";
import { useDeleteOrder } from "../hooks/useDeleteOrder";
import { useNavigate } from "react-router";

export function OrderDetails(order: OrderProps) {
    const initialState = {
        payment: 'credit'
    }
    const [inputs, setInputs] = useState(initialState);
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [paymentData, setPaymentData] = useState<null | PaymentProps>(null);
    const { getPayment } = usePayment();
    const { deleteOrder } = useDeleteOrder();
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const data = await getPayment(inputs.payment);
        if (data) {
            setPaymentData(data);
            setModalIsOpen(true);
            return;
        }
    }

    return (
        <div className="max-w-4xl mx-auto p-6 bg-white rounded-lg shadow-md space-y-6">
            <h2 className="text-2xl font-semibold text-gray-800">Order Details</h2>

            <div className="flex justify-between">
                <div className="space-y-2">
                <p><strong>Order ID:</strong> {order.orderId}</p>
                <p><strong>Creator ID:</strong> {order.creatorId}</p>
                <p><strong>Creation Timestamp:</strong> {new Date(order.creationTimestamp).toLocaleString()}</p>
                </div>

                <div className="space-y-2 text-right">
                <p><strong>Status:</strong> {order.status}</p>
                <p><strong>Payment:</strong> {order.payment}</p>
                <p><strong>Total:</strong> ${order.total.toFixed(2)}</p>
                </div>
            </div>

            <div className="space-y-2">
                <p><strong>Products:</strong></p>
                <ul className="list-disc pl-5 flex flex-col gap-3">
                    {order.products.map((product, index) => (
                        <li key={index} className="flex gap-6">
                            <p>
                                Product ID: {product}
                            </p>
                            <button 
                                className="text-red-300"
                                onClick={async () => console.log('Removendo product') }
                            >
                                <FaRegTrashAlt />
                            </button>
                        </li>
                    ))}
                </ul>
            </div>

            <div className="text-right text-sm text-gray-600">
                <p><strong>Last Updated:</strong> {new Date(order.updateTimestamp).toLocaleString()}</p>
            </div>

            
            <form onSubmit={handleSubmit} className="flex gap-6">
                <div>
                    <label htmlFor="payment">Payment</label>
                    <select 
                        name="payment" 
                        id="payment"
                        onChange={(e) => setInputs(prev => ({...prev, payment: e.target.value}))}    
                        value={inputs.payment}
                    >
                        <option value="credit">Credit</option>
                        <option value="debit">Debit</option>
                        <option value="paypal">Paypal</option>
                        <option value="pix">Pix</option>
                    </select>
                </div>
                <button className="bg-slate-500 rounded-md px-4 py-1 text-stone-50">Buy</button>
            </form>
            <button 
                className="text-red-600 text-lg"
                onClick={async () => {
                    await deleteOrder();
                    navigate(0)
                }}
            >
                <FaRegTrashAlt />
            </button>
            <Modal
                onClose={() => setModalIsOpen(false)}
                open={modalIsOpen}
            >
                <div>
                    <h3 className="text-xl font-semibold">Payment Details</h3>
                    {paymentData ? (
                        <div>
                            <p><strong>Id:</strong> ${paymentData.orderId}</p>
                            <p><strong>Payment Method:</strong> {paymentData.payment}</p>
                            <p><strong>Status:</strong> {paymentData.status}</p>
                            <p><strong>Total:</strong> ${paymentData.total.toFixed(2)}</p>
                            <p><strong>Created:</strong> {paymentData.creationTimestamp}</p>
                        </div>
                    ) : (
                        <p>Loading payment details...</p>
                    )}
                </div>
            </Modal>
        </div>
    );
};