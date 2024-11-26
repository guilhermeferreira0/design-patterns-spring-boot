import { useState } from "react";
import { useAuth } from "../contexts/Auth/useAuth";

export function useDeleteOrder() {
    const [loading, setLoading] = useState(false);
    const { authUser } = useAuth();

    const deleteOrder = async () => {
        setLoading(true);
        try {
            const res = await fetch(`/api/payments`, {
                method: 'DELETE',
                headers: {
                    'Contenty-Type': 'Application/json',
                    'Authorization': authUser?.token || ''
                }
            });
            const data = await res.json();
            console.log(data);

            return data;
        } catch (e) {
            console.log(e);
        } finally {
            setLoading(false);
        }
    }

    return { loading, deleteOrder };
}