import { useEffect, useState } from "react";
import { useAuth } from "../contexts/Auth/useAuth";
import { OrderProps } from "../types/order";

export function useGetOrder() {
    const [loading, setLoading] = useState(false);
    const [order, setOrder] = useState({} as OrderProps[]);
    const { authUser } = useAuth();

    useEffect(() => {
        const getOrder = async () => {
            setLoading(true);
            try {
                const res = await fetch(`/api/payments`, {
                    method: 'get',
                    headers: {
                        'Contenty-Type': 'Application/json',
                        'Authorization': authUser?.token || ''
                    }
                });

                const data = await res.json();

                setOrder(data);
            } catch (e) {
                console.log(e)
            } finally {
                setLoading(false)
            }
        }

        getOrder();
    }, [authUser?.token]);

    return { loading, order }
}