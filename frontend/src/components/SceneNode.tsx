import {useState} from "react";
import {Handle, Position} from "@xyflow/react";
import type {NodeProps} from "@xyflow/react";

type SceneNodeData = {
    label: string
    content: string
}

function SceneNode({data}: NodeProps) {
    const [isHovered, setIsHovered] = useState(false)
    const sceneData = data as SceneNodeData

    return (
        <div
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
            className="relative rounded-lg border border-gray-300 bg-white px-4 py-2 shadow-sm"
        >
            <Handle type="target" position={Position.Left} />
            <div className="text-sm font-medium text-gray-800">{sceneData.label}</div>
            <Handle type="source" position={Position.Right} />

            {isHovered && (
                <div className="absolute left-1/2 top-full z-10 mt-2 w-56 -translate-x-1/2 rounded-md bg-gray-900 p-2 text-xs text-white shadow-lg">
                    {sceneData.content}
                </div>
            )}
        </div>
    )
}

export default SceneNode