import { useState } from "react";
import { Products } from "../components/products";
import { Modal } from "../components/modal";
import { FormNewProduct } from "../components/formNewProduct";

export function Home() {
    const [modalIsOpen, setModalIsOpen] = useState(false);

    return (
        <section className="w-full flex flex-col gap-5">
            <h1 className="text-center">Home Products</h1>
            <Products />
            <button 
                className="bg-blue-100 w-32 rounded-md px-4 py-1 hover:bg-blue-300 transition-all"
                onClick={() => setModalIsOpen(prev => !prev)}    
            >
                Add new Product
            </button>
            <Modal
                onClose={() => setModalIsOpen(false)}
                open={modalIsOpen}
            >
                <FormNewProduct />
            </Modal>
        </section>
    );
}