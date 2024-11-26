import { Link } from "react-router";
import { useConversations } from "../hooks/useConversations";
import { useAuth } from "../contexts/Auth/useAuth";

export function Conversations() {
    const { conversations, loading } = useConversations();
    const { authUser } = useAuth();
    const receiverIds = [...new Set(conversations.map(conv => conv.receiverId))];

    if (loading) {
        return <p>Loading ....</p>
    }

    return (
        <section>
            <h2>Lista de Contatos</h2>
            {conversations.length > 0 ? (
                <ul>
                    {receiverIds.map((rec, index) => {
                        if (rec === authUser?.userId) return;

                        return (
                            <li key={index}>
                                <p>Contato: 
                                    <Link to={`/message/${rec}`}> {rec}</Link>
                                </p>
                            </li>
                        )
                    })}
                </ul>
            ) : (
                <p>Nenhum contato encontrado.</p>
            )}
        </section>
    );
}