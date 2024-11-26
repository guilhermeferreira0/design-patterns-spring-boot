import { useState } from "react";
import { InputsProps } from "../pages/login";
import { setUserLocalStorage } from "../contexts/Auth/utils";
import { useAuth } from "../contexts/Auth/useAuth";

export default function useRegister() {
    const [loading, setLoading] = useState(false);
    const { setAuthUser } = useAuth();
  
    const register = async (inputs: InputsProps) => {
      const success = handleInputErrors(inputs);
      if (!success) return;
  
      try {
        setLoading(true);
        const res = await fetch('/api/auth/register', {
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify(inputs)
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
      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }
    }
    
    return { loading, register }
  }
  
  function handleInputErrors(inputs: InputsProps) {
      if (
        !inputs.username ||
        !inputs.password ||
        !inputs.email
      ) {
        return false;
      }
  
      return true;
  }