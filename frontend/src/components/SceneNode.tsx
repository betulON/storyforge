import { useState } from "react";
import { Handle, Position } from "@xyflow/react";
import type { NodeProps } from "@xyflow/react";

type SceneNodeData = {
    label: string
    content: string
    imageUrl: string
}

function SceneNode({ data }: NodeProps) {
    const [isHovered, setIsHovered] = useState(false)
    const sceneData = data as SceneNodeData

    return (
        <div
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
            className="relative flex h-16 w-16 cursor-pointer items-center justify-center rounded-full border-2 border-white bg-indigo-500 shadow-md"
        >
            <Handle type="target" position={Position.Left} />

            {sceneData.imageUrl ? (
                <img
                    src={sceneData.imageUrl}
                    alt={sceneData.label}
                    className="h-full w-full rounded-full object-cover"
                />
            ) : (
                <span className="text-lg font-semibold text-white">
                    {sceneData.label.charAt(0).toUpperCase()}
                </span>
            )}

            <Handle type="source" position={Position.Right} />

            {isHovered && (
                <div className="absolute left-1/2 top-full z-10 mt-2 w-56 -translate-x-1/2 rounded-md bg-gray-900 p-2 text-xs text-white shadow-lg">
                    <div className="mb-1 font-semibold">{sceneData.label}</div>
                    <div>{sceneData.content}</div>
                </div>
            )}
        </div>
    )
}

export default SceneNode