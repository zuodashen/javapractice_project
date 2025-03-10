import {useState} from "react";
import('./styles.css')// import('./styles.css')


export default function App(){
    const [newItem,setNewItem] = useState("");
    const [todos,setTodos] = useState(()=>{
        const  localValue = localStorage.getItem('ITEM');

    if(!localValue) return [];
    return JSON.parse(localValue);
    });

    const handleSubmit = (e) => {
        e.preventDefault();
        const newId = crypto.randomUUID();
        // 检查是否已经存在相同 ID
        if (todos.some(todo => todo.id === newId)) {
            console.error('Duplicate ID detected:', newId);
            return;
        }
        setTodos((current) => [
            ...current,
            { id: newId, title: newItem, completed: false }
        ]);
        if(newItem === "") return;
        setNewItem(''); // 清空输入框
    };


    function toggleTodo(id,completed){
        setTodos((current) => {
           return current.map(todo => {
               if(todo.id === id) return {...todo,completed:completed}
               return todo;
           });
        });

    }

    function deleteTodo(id){
        setTodos((current) => {
            return current.filter((todo)=>{
                return todo.id !== id;
            })
        })
    }

    return (
        <>
            <form onSubmit={handleSubmit} className="new-item-form">
                <div className="form-row">
                    <label htmlFor=""> New Item</label>
                    <input type="text" id="item" value={newItem} onChange={(e) => setNewItem(e.target.value)}/>
                </div>
                <button className={"btn"}>Add</button>
            </form>
            <h1 className={"header"}>Todo List</h1>

            <ul className={"list"}>
                {todos.map((todo) => (
                        <li key={todo.id}>
                            <label>
                                <input type="checkbox" checked={todo.completed}
                                       onChange={(e) => toggleTodo(todo.id, e.target.checked)}/> {todo.title}
                            </label>
                            <button onClick={() => deleteTodo(todo.id)} className={"btn btn-danger"}>Delete</button>
                        </li>
                    )
                )}

            </ul>


        </>
    )
}