import { useState } from "react";
import { useAuth } from "../contexts/Auth/useAuth";

export function useSendMessage() {
    const [loading, setLoading] = useState(false);
    const { authUser } = useAuth();

    const sendMessage = async (receiverId?: string, message?: string) => {
        setLoading(true);
        try {
            const res = await fetch(`/api/messages/${receiverId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': authUser?.token || '',
                },
                body: JSON.stringify({ message: message })
            });
    
            const data = await res.json();
            return data;
        } catch (e) {
            console.log(e);
        } finally {
            setLoading(false);
        }
    }

    return { loading, sendMessage };
}