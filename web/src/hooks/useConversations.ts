import { useEffect, useState } from "react";
import { MessagesProtocol } from "../types/messages";

export function useConversations() {
    const [loading, setLoading] = useState(false);
    const [conversations, setConversations] = useState([] as MessagesProtocol[]);

    useEffect(() => {
        const getConversations = async () => {
            setLoading(true);
            try {
                const res = await fetch('/api/messages', {
                    method: 'get',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
    
                const data = await res.json();
    
                setConversations(data);
            } catch (e) {
                console.log(e);
            } finally {
                setLoading(false);
            }
        }

        getConversations();
    }, []);

    return { loading, conversations }
}