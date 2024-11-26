import { useState } from "react";
import { useParams } from "react-router";
import { useGetMessages } from "../hooks/useGetMessages";
import { useSendMessage } from "../hooks/useSendMessage";
import { useAuth } from "../contexts/Auth/useAuth";

export interface MessagesTypes {
    text: string,
    sender: string,
    receiver?: string
}

export function Chat() {
    const [input, setInput] = useState('');
    const { id } = useParams();
    const  { loading, messages, setMessages } = useGetMessages(id);
    const { sendMessage } = useSendMessage();
    const { authUser } = useAuth();

    if (loading) {
        return <p>Loading ....</p>
    }

    const handleSendMessage = async (e: React.FormEvent<HTMLFormElement>) => {
      e.preventDefault();
        const data = await sendMessage(id, input);
        if (data.message) {
            setMessages([...messages, { 
                message: input,
                receiverId: id,
                senderId:  authUser?.userId
            }]);
        }
        setInput('');
    };
  
    return (
    <div className="w-full max-w-md bg-white rounded-lg shadow-lg">
        <div className="bg-blue-500 text-white text-center py-4 rounded-t-lg">
        <h1 className="text-2xl font-semibold">Chat App</h1>
        </div>

        <div className="p-4 h-80 overflow-y-auto flex flex-col gap-4">
        <div className="flex flex-col gap-2">
            {messages.map((message, index) => (
                <div
                    key={index}
                    className={`flex ${
                    message.receiverId === id ? 'justify-end' : 'justify-start'
                    }`}
                >
                    <div
                    className={`max-w-xs rounded-lg p-3 text-white ${
                        message.receiverId === id ? 'bg-blue-500' : 'bg-gray-500'
                    }`}
                    >
                    {message.message}
                    </div>
                </div>
            ))}
        </div>
        </div>

        <form
            className="p-4 bg-gray-200 flex items-center"
            onSubmit={handleSendMessage}
        >
        <input
            type="text"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="Type a message..."
            className="flex-1 p-3 rounded-l-lg border border-gray-300"
        />
        <button
            type="submit"
            className="bg-blue-500 text-white px-4 py-2 rounded-r-lg hover:bg-blue-600 transition-all"
        >
            Send
        </button>
        </form>
    </div>
    );
  }