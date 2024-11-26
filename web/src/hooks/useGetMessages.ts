import { useEffect, useState } from "react";
import { MessagesProtocol } from "../types/messages";
import { useAuth } from "../contexts/Auth/useAuth";

export function useGetMessages(receiverId?: string) {
    const [loading, setLoading] = useState(false);
    const [messages, setMessages] = useState([] as MessagesProtocol[]);
    const { authUser } = useAuth();

    useEffect(() => {
        const getMessages = async () => {
            setLoading(true);
            try {

                const res = await fetch(`/api/messages/${receiverId}`, {
                    method: 'get',
                    headers: {
                        'Contenty-Type': 'Application/json',
                        'Authorization': authUser?.token || ''
                    }
                })

                const data = await res.json();
                setMessages(data);
            } catch (e) {
                console.log(e);
            } finally {
                setLoading(false);
            }
        }

        getMessages();
    }, [receiverId, authUser?.token]);

    return { loading, messages, setMessages };
}