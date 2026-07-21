import { useState } from 'react'
import './App.css'
import StoryGraph from "./components/StoryGraph.tsx";

function App() {
  const [] = useState(0)

  return (
      <StoryGraph />
  )
}

export default App
