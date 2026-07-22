import { useState } from "react";

type EditSceneModalProps = { //in typescript to say: anyone uses this EditSceneModal component must provide these values
    sceneId: string
    initialTitle: string
    initialContent: string
    initialImageUrl: string
    onClose: () => void
    onSaved: () => void
}

function EditSceneModal({
    sceneId,
    initialTitle,
    initialContent,
    initialImageUrl,
    onClose,
    onSaved,
}: EditSceneModalProps) {
    const [title, setTitle] = useState(initialTitle)
    const [content, setContent] = useState(initialContent)
    const [imageUrl, setImageUrl] = useState(initialImageUrl)
    const [isSaving, setIsSaving] = useState(false)

    async function handleSave() {
        setIsSaving(true)
        await fetch(`http://localhost:8080/api/scenes/${sceneId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ title, content, imageUrl }),
        })
        setIsSaving(false)
        onSaved()
        onClose()
    }

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50">
            <div className="w-96 rounded-lg bg-white p-6 shadow-xl">
                <h2 className="mb-4 text-lg font-semibold text-gray-900">Edit Scene</h2>

                <label className="mb-1 block text-sm font-medium text-gray-700">Title</label>
                <input
                    className="mb-3 w-full rounded-md border border-gray-300 px-3 py-2 text-sm"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />

                <label className="mb-1 block text-sm font-medium text-gray-700">Content</label>
                <textarea
                    className="mb-3 w-full rounded-md border border-gray-300 px-3 py-2 text-sm"
                    rows={4}
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                />

                <label className="mb-1 block text-sm font-medium text-gray-700">Image URL</label>
                <input
                    className="mb-4 w-full rounded-md border border-gray-300 px-3 py-2 text-sm"
                    value={imageUrl}
                    onChange={(e) => setImageUrl(e.target.value)}
                />

                <div className="flex justify-end gap-2">
                    <button
                        onClick={onClose}
                        className="rounded-md px-4 py-2 text-sm font-medium text-gray-600 hover:bg-gray-100"
                    >
                        Cancel
                    </button>
                    <button
                        onClick={handleSave}
                        disabled={isSaving}
                        className="rounded-md bg-indigo-600 px-4 py-2 text-sm font-medium text-white hover:bg-indigo-700 disabled:opacity-50"
                    >
                        {isSaving ? "Saving..." : "Save"}
                    </button>
                </div>
            </div>
        </div>
    )
}

export default EditSceneModal