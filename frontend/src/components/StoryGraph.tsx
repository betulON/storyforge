import {useState, useEffect, useCallback} from 'react'
import {type NodeTypes, ReactFlow} from '@xyflow/react'
import type { Node, Edge } from '@xyflow/react'
import '@xyflow/react/dist/style.css'
import SceneNode from "./SceneNode.tsx";
import EditSceneModal from "./EditSceneModal.tsx";

const nodeTypes: NodeTypes = {scene: SceneNode}

type SceneNodeData = {
    label: string
    content: string
    imageUrl: string
}

type SceneResponse = {
    id: number
    title: string
    content: string
    storyId: number
    imageUrl: string | null
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
    const [editingSceneId, setEditingSceneId] = useState<string | null>(null)

    const loadGraph = useCallback(async () => {
        const scenesRes = await fetch(`http://localhost:8080/api/stories/${storyId}/scenes`)
        const scenes: SceneResponse[] = await scenesRes.json()

        const choicesRes = await fetch(`http://localhost:8080/api/stories/${storyId}/choices`)
        const choices: ChoiceResponse[] = await choicesRes.json()

        const graphNodes: Node[] = scenes.map((scene, index) => ({
            id: String(scene.id),
            type: 'scene',
            position: { x: index * 200, y: 0 },
            data: { label: scene.title, content: scene.content, imageUrl: scene.imageUrl ?? ''},
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

    }, [storyId]) // load graph changes only when storyId changes

    // useEffect means: after you've rendered this component, run this code !!! not while rendering but AFTER !!!
    // after every state changes react renders the component again
    // so if we don't use useEffect the function calls every time react renders and change things everytime it is called
    // this will become a loop
    // that's why the functions which update states aren't called directly in the component body
    useEffect(() => {
         loadGraph();
    }, [loadGraph]) // run this effect when loadGraph changes

    // first render -> react finishes rendering -> useEffect runs -> graph loads
    // user edits a scene -> no storyId change -> no loadgraph change -> no useEffect run

    function handleNodeClick(_event : React.MouseEvent, node: Node){
        setEditingSceneId(node.id)
    }

    const editingNode = nodes.find((node) => editingSceneId === node.id)
    const editingData = editingNode?.data as SceneNodeData | undefined


    return (
        <div style={{ height: '100vh', width: '100%' }}>
            <ReactFlow nodes={nodes} edges={edges} nodeTypes={nodeTypes} onNodeClick={handleNodeClick} />

            {editingNode && editingData && (
                <EditSceneModal
                    sceneId={editingNode.id}
                    initialTitle={editingData.label}
                    initialContent={editingData.content}
                    initialImageUrl={editingData.imageUrl}
                    onClose={() => setEditingSceneId(null)}
                    onSaved={loadGraph}
                />
            )}
        </div>
    )
}

export default StoryGraph