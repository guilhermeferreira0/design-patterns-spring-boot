import { UserProps } from "./types";

export function getUserLocalStorage(): null | UserProps {
  const user = localStorage.getItem('user-details');
  if (!user) return null;

  return JSON.parse(user);
}

export function setUserLocalStorage(user: UserProps) {
  localStorage.setItem('user-details', JSON.stringify(user));
}

export function removeUserLocalStorage() {
  localStorage.removeItem('user-details');
}