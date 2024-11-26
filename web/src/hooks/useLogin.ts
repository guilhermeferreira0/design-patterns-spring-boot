import { useState } from "react";
import { InputsProps } from "../pages/login";
import { useAuth } from "../contexts/Auth/useAuth";
import { setUserLocalStorage } from "../contexts/Auth/utils";

export default function useLogin() {
    const [loading, setLoading] = useState(false);
    const { setAuthUser } = useAuth();

    const login = async (inputs: InputsProps) => {
      const success = handleInputErrors(inputs);
      if (!success) return;
  
      setLoading(true);
      try {
        const res = await fetch('/api/auth/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({email: inputs.email, password: inputs.password}),
        });
        
        const data = await res.json();
        if (data.error) throw new Error(data.error);

        const userAuth = {
          email: data.email,
          userId: data.userId,
          token: data.token
        }

        setUserLocalStorage(userAuth);
        setAuthUser(userAuth);

      } catch (e) {
        console.log(e);
      } finally {
        setLoading(false);
      }
    }
  
    return { loading, login };
  }
  
  function handleInputErrors(inputs: InputsProps) {
    if (
      !inputs.email ||
      !inputs.password
    ) {
      return false;
    }
  
    return true;
  }