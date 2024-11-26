import { OrderDetails } from "../components/orderDetails";
import { useGetOrder } from "../hooks/useGetOrder";

export function Order() {
    const { order } = useGetOrder();
    const existingOrder = order[0];

    return (
        <section>
            <h1>Order</h1>
            {existingOrder ? (
                <OrderDetails 
                    creationTimestamp={existingOrder.creationTimestamp}
                    creatorId={existingOrder.creatorId}
                    orderId={existingOrder.orderId}
                    payment={existingOrder.payment}
                    products={existingOrder.products}
                    status={existingOrder.status}
                    total={existingOrder.total}
                    updateTimestamp={existingOrder.updateTimestamp}
                    key={existingOrder.orderId}
                />
            ) : (
                <p>Order Empty.....</p>
            )}
        </section>
    );
}