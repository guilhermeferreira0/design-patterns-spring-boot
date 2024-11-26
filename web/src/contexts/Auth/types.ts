export interface UserProps {
  username?: string,
  email: string,
  token: string,
  userId: string;
}
  
export interface AuthContextProviderProps {
  authUser: UserProps | null;
  logoutUser: () => void;
  setAuthUser: (user: UserProps) => void;
}