import { useEffect, useState } from "react";
import { ProductProtocol } from "../types/product";

export function useProucts() {
    const [loading, setLoading] = useState(false);
    const [products, setProducts] = useState([] as ProductProtocol[]);

    useEffect(() => {
        const fetchApi = async () => {
            setLoading(true);
            try {
                const res = await fetch('/api/products', {
                    method: 'GET',
                    headers: { 'Content-Type': 'application/json' },
                    mode: 'cors'
                });
    
                const data = await res.json();
                setProducts(data);
            } catch(e) {
                console.log(e);
            } finally {
                setLoading(false);
            }
        }

        fetchApi();
    }, []);


    return { loading, products };
}