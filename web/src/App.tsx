import { Link, Route, Routes, useNavigate } from "react-router"
import { Login } from "./pages/login"
import { useAuth } from "./contexts/Auth/useAuth"
import { Home } from "./pages/home";
import { Conversations } from "./components/conversations";
import { Chat } from "./pages/chat";
import { Order } from "./pages/order";

function App() {
  const { authUser, logoutUser } = useAuth();
  const navigate = useNavigate();
  
  console.log(import.meta.env.VITE_API_URL);
  
  return (
    <>
      <header className="flex w-screen justify-around items-center h-11 bg-slate-500 text-stone-100">
        Welcome to NextStore
        <nav>
          <ul className="flex gap-20">
            {!authUser && (
              <li>
                <a href="/login">Login</a>
              </li>
            )}
            {authUser && (
              <>
                <li>
                  <Link to="/">Home</Link>
                </li>
                <li>
                  <Link to="/conversations">Conversations</Link>
                </li>
                <li>
                  <Link to="/order">Order</Link>
                </li>
                <li>
                  <button onClick={() => {
                    logoutUser();
                    navigate('/login');
                  }}>Logout</button>
                </li>
              </>
            )}
          </ul>
        </nav>
      </header>
      <main className="flex flex-col items-center p-5">
        <Routes>
          <Route path="/login" element={authUser ? <Home /> : <Login />} />
          <Route path="/" element={authUser ? <Home /> : <Login />} />
          <Route path="/message/:id" element={<Chat />} />
          <Route path="/conversations" element={<Conversations />} />
          <Route path="/order" element={<Order />} />
        </Routes>
      </main>
    </>
  )
}

export default App
