import { useState } from "react";
import useLogin from "../hooks/useLogin";
import useRegister from "../hooks/useRegister";

export interface InputsProps {
    username?: string;
    email: string;
    password: string;
}

export function Login() {
    const [loginForm, setLoginForm] = useState(true);
    const [inputs, setInputs] = useState({} as InputsProps);
    const { login } = useLogin();
    const { register } = useRegister();

    const handleSubmitLogin = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        await login(inputs);
    }

    const handleSubmitRegister = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        await register(inputs);
    }

    return (
        <section className="bg-blue-200 p-4 w-[60%] rounded-md">
            <h1>Forms</h1>
            {loginForm ? (
                <form className="mt-4 flex flex-col gap-3" method="post" onSubmit={handleSubmitLogin}>
                    <h2>Login</h2>
                    <div className="flex w-full">
                        <label htmlFor="login-email">Email</label>
                        <input 
                            type="email" 
                            id="login-email" 
                            name="login-email" 
                            required placeholder="Digite seu email" 
                            className="w-full rounded-sm p-1 ml-8" 
                            onChange={(e) => setInputs(prev => ({ ...prev,  email: e.target.value}))}
                        />
                    </div>
                    <div className="flex w-full">
                        <label htmlFor="login-password">Senha</label>
                        <input 
                            type="password" 
                            id="login-password" 
                            name="login-password" 
                            required placeholder="Digite sua senha" 
                            className="w-full rounded-sm p-1 ml-7" 
                            onChange={(e) => setInputs(prev => ({ ...prev,  password: e.target.value}))}
                        />
                    </div>
                    <button type="submit" className="btn">Entrar</button>

                    <p>Não tem uma conta? <button onClick={() => setLoginForm(false)}>Registre-se aqui</button></p>
                </form>
            ) : (
                <form className="mt-4 flex flex-col gap-3" method="post" onSubmit={handleSubmitRegister}>
                    <h2>Register</h2>
                    <div className="flex w-full">
                        <label htmlFor="register-username">Username</label>
                        <input 
                            type="text" 
                            id="register-username" 
                            name="register-username" 
                            required placeholder="Digite seu username" 
                            className="w-full rounded-sm p-1 ml-8" 
                            onChange={(e) => setInputs(prev => ({ ...prev,  username: e.target.value}))}
                        />
                    </div>
                    <div className="flex w-full">
                        <label htmlFor="register-email">Email</label>
                        <input 
                            type="email" 
                            id="register-email" 
                            name="register-email" 
                            required placeholder="Digite seu email" 
                            className="w-full rounded-sm p-1 ml-8" 
                            onChange={(e) => setInputs(prev => ({ ...prev,  email: e.target.value}))}
                        />
                    </div>
                    <div className="flex w-full">
                        <label htmlFor="register-password">Senha</label>
                        <input 
                            type="password" 
                            id="register-password" 
                            name="register-password" 
                            required placeholder="Digite sua senha" 
                            className="w-full rounded-sm p-1 ml-8" 
                            onChange={(e) => setInputs(prev => ({ ...prev,  password: e.target.value}))}
                        />
                    </div>
                    <button type="submit" className="btn">Registrar</button>

                    <p>Já tem uma conta? <button onClick={() => setLoginForm(true)}>Entre aqui</button></p>
                </form>
            )}
        </section>
    );
}