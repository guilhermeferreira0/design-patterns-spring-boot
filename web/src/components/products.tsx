import { Link } from "react-router";
import { useProucts } from "../hooks/useProducts";
import { useAuth } from "../contexts/Auth/useAuth";
import { ProductProtocol } from "../types/product";
import { useAddProductToCart } from "../hooks/useAddProductToCart";

export function Products() {
    const { loading, products } = useProucts();
    const { authUser } = useAuth();
    const { addProduct } = useAddProductToCart();

    if (loading) {
        return <p>Products...</p>
    }

    if (products.length <= 0) {
        return  <p>Products Empty..</p>
    }

    const buttonChat = (product: ProductProtocol) => {
        if (authUser?.userId === product.creatorId) return;

        return (
            <Link to={`/message/${product.creatorId}`} className="bg-blue-300 rounded-md hover:scale-105 transition-all text-center">Chat</Link>
        );
    }

    return (
        <section className="grid grid-cols-[repeat(auto-fit,minmax(250px,1fr))] gap-4 p-4">
            {products.map((product, index) => (
                <div
                    className="bg-slate-400 w-72 rounded-md p-7 flex flex-col gap-2"
                    key={index}
                >
                    <h3>Name: {product.name}</h3>
                    <p>Description: {product.description}</p>
                    <p className="price">Price: R$ {product.price.toFixed(2)}</p>
                    <p>Tipo: {product.type}</p>
                    <p>Criado em: {new Date(product.creationTimestamp).toLocaleDateString()}</p>
                    <p>Última atualização: {new Date(product.updateTimestamp).toLocaleDateString()}</p>
                    <div className="">
                        Produto {product.guarantee ? 'Com Garantia' : 'Sem Garantia'}
                    </div>
                    <div className="flex flex-col gap-2">
                        {buttonChat(product)}
                        <button 
                            className="bg-blue-300 rounded-md hover:scale-105 transition-all"
                            onClick={async () => await addProduct(product.productId)}
                        >
                            Add to cart
                        </button>
                    </div>
                </div>
            ))}
        </section>
    );
}