import {useState} from "react";

type CreateChoiceModalPrompts = {
    fromSceneId: string, 
    toSceneId: string, 
    onClose: () => void, 
    onCreated: () => void,
}

function CreateChoiceModal({
    fromSceneId,
    toSceneId,
    onClose,
    onCreated,
}:CreateChoiceModalPrompts) {
    const [text, setText] = useState("")
    const [isSaving, setIsSaving] = useState(false)
    let numToSceneId = Number(toSceneId)

    async function handleSave() {
        setIsSaving(true)
        await fetch(`http://localhost:8080/api/scenes/${fromSceneId}/choices`,
            {
                method: "POST",
                headers: {"Content-Type" : "application/json"},
                body: JSON.stringify({text, toSceneId: numToSceneId})
            })
        setIsSaving(false);
        onCreated()
        onClose()
    }

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50">
            <div className="w-96 rounded-lg bg-white p-6 shadow-xl">
                <h2 className="mb-4 text-lg font-semibold text-gray-900">Create Choice</h2>

                <label className="mb-1 block text-sm font-medium text-gray-700">Text</label>
                <input
                    className="mb-3 w-full rounded-md border border-gray-300 px-3 py-2 text-sm"
                    value={text}
                    onChange={(e) => setText(e.target.value)}
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

export default CreateChoiceModal