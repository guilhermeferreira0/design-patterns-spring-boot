import { useContext } from "react";
import { Context } from ".";

export function useAuth() {
  return useContext(Context);
}