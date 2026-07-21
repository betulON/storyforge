import { useState, useEffect } from 'react'
import {type NodeTypes, ReactFlow} from '@xyflow/react'
import type { Node, Edge } from '@xyflow/react'
import '@xyflow/react/dist/style.css'
import SceneNode from "./SceneNode.tsx";

const nodeTypes: NodeTypes = {scene: SceneNode}

type SceneResponse = {
    id: number
    title: string
    content: string
    storyId: number
}

type ChoiceResponse = {
    id: number
    text: string
    fromSceneId: number
    toSceneId: number | null
}

function StoryGraph() {
    const storyId = 1
    const [nodes, setNodes] = useState<Node[]>([])
    const [edges, setEdges] = useState<Edge[]>([])

    useEffect(() => {
        async function loadGraph() {
            const scenesRes = await fetch(`http://localhost:8080/api/stories/${storyId}/scenes`)
            const scenes: SceneResponse[] = await scenesRes.json()

            const choicesRes = await fetch(`http://localhost:8080/api/stories/${storyId}/choices`)
            const choices: ChoiceResponse[] = await choicesRes.json()

            const graphNodes: Node[] = scenes.map((scene, index) => ({
                id: String(scene.id),
                type: 'scene',
                position: { x: index * 200, y: 0 },
                data: { label: scene.title, content: scene.content },
            }))

            const graphEdges: Edge[] = choices
                .filter((choice) => choice.toSceneId !== null)
                .map((choice) => ({
                    id: String(choice.id),
                    source: String(choice.fromSceneId),
                    target: String(choice.toSceneId),
                }))

            setNodes(graphNodes)
            setEdges(graphEdges)
        }

        loadGraph()
    }, [])

    return (
        <div style={{ height: '100vh', width: '100%' }}>
            <ReactFlow nodes={nodes} edges={edges} nodeTypes={nodeTypes} />
        </div>
    )
}

export default StoryGraph