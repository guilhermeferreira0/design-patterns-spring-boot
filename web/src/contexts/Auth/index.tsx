import { createContext, ReactNode, useState } from "react";
import { getUserLocalStorage, removeUserLocalStorage } from "./utils";
import { AuthContextProviderProps, UserProps } from "./types";

export const Context = createContext({} as AuthContextProviderProps);

export function AuthContextProvider({ children }: {children: ReactNode}) {
  const [authUser, setAuthUser] = useState<UserProps | null>(getUserLocalStorage());

  function logoutUser() {
    removeUserLocalStorage();
    setAuthUser(null);
  }

  return (
    <Context.Provider value={{
      authUser,
      setAuthUser,
      logoutUser
    }}>
      {children}
    </Context.Provider>
  );
}