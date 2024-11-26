import { useState } from "react";
import useNewProduct from "../hooks/useNewProduct";
import { useNavigate } from "react-router";

export interface InputsProduct {
    name: string,
    description: string,
    price: number,
    type: string,
    guarantee: boolean,
}

const initialState: InputsProduct = {
    guarantee: false,
    type: 'regular',
    name: '',
    description: '',
    price: 0,
}

export function FormNewProduct() {
    const [inputs, setInputs] = useState(initialState);
    const { loading, newProduct } = useNewProduct();
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        await newProduct(inputs);
        navigate(0);
    }


    return (
        <form onSubmit={handleSubmit} className="h-max flex flex-col gap-9">
            <div>
                <label htmlFor="name">Name: </label>
                <input type="text" name="name" id="name" onChange={(e) => setInputs(prev => ({...prev, name: e.target.value}))}/>
            </div>

            <div className="flex">
                <label htmlFor="description">Description: </label>
                <textarea 
                    name="description" 
                    id="description" 
                    className="w-full p-2"
                    onChange={(e) => setInputs(prev => ({...prev, description: e.target.value}))}
                ></textarea>
            </div>

            <div>
                <label htmlFor="price">Price: </label>
                <input 
                    type="number" 
                    name="price" 
                    id="price" 
                    onChange={(e) => setInputs(prev => ({...prev, price: Number(e.target.value)}))}
                />
            </div>

            <div>
                <label htmlFor="type">Type: </label>
                <select 
                    name="type" 
                    id="type" 
                    onChange={(e) => setInputs(prev => ({...prev, type: e.target.value}))}    
                    value={inputs.type}
                >
                    <option value="regular">Regular</option>
                    <option value="premium">Premium</option>
                    <option value="limitedEdition">LimitedEdition</option>
                </select>
            </div>

            <div>
                <label htmlFor="guarantee">Guarantee? </label>
                <input 
                    type="checkbox" 
                    name="guarantee" 
                    id="guarantee" 
                    checked={inputs.guarantee}
                    onChange={(e) => setInputs(prev => ({...prev, guarantee: e.target.checked}))}
                />
            </div>

            <button 
                className="bg-slate-200 rounded-md py-1 hover:bg-slate-500 transition-all"
                disabled={loading}
            >
                {loading ? 'Loading...' : 'Add'}
            </button>
        </form>
    );
}