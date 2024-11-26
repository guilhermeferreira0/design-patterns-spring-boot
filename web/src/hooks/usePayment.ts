import { useState } from "react";
import { useAuth } from "../contexts/Auth/useAuth";
import { PaymentProps } from "../types/payment";

export function usePayment() {
    const [loading, setLoading] = useState(false);
    const { authUser } = useAuth();

    const getPayment = async (payment: string) => {
        setLoading(true);
        try {
            const res = await fetch('/api/payments', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': authUser?.token || ''
                },
                body: JSON.stringify({ payment })
            });

            const data = await res.json();

            return data as PaymentProps;    
        } catch (e) {
            console.log(e)
        } finally {
            setLoading(false);
        }
    }

    return { getPayment, loading }
}