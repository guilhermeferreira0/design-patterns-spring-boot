import { useState } from "react";
import { useAuth } from "../contexts/Auth/useAuth";

export function useAddProductToCart() {
    const [loading, setLoading] = useState(false);
    const { authUser } = useAuth();

    const addProduct = async (productId: string) => {
        setLoading(true);
        try {
            const res = await fetch(`/api/payments/${productId}`, {
                method: 'GET',
                headers: {
                    'Contenty-Type': 'Application/json',
                    'Authorization': authUser?.token || ''
                }
            });
            const data = await res.json();
            return data;
        } catch (e) {
            console.log(e)
        } finally {
            setLoading(false);
        }
    }


    return { loading, addProduct }
}