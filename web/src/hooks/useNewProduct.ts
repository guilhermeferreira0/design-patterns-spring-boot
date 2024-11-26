import { useState } from "react";
import { InputsProduct } from "../components/formNewProduct";
import { useAuth } from "../contexts/Auth/useAuth";

export default function useNewProduct() {
    const [loading, setLoading] = useState(false);
    const { authUser } = useAuth();

    const newProduct = async (newProduct: InputsProduct) => {
        setLoading(true);

        try {
            const res = await fetch('/api/products', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': authUser?.token || '',
                },
                body: JSON.stringify(newProduct),
            });
            
            const data = await res.json();
            if (data) throw new Error('Not found');
            return data;
        } catch (e) {
            console.log(e);
        } finally {
            setLoading(false);
        }
    }

    return { loading, newProduct };
}