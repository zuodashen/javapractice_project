# React

## 一小时快速入门

**npm create vite@latest      创建react 项目**

npm run dev 

```markdown
index.html:
这是网页的入口文件。
它定义了网页的基本结构，包括HTML标签如 <html>, <head>, 和 <body>。
在这个文件中，你可以引入外部资源，比如CSS和JavaScript文件。
对于使用React等框架的应用，通常会在<body>标签内定义一个特定的元素（例如<div id="root"></div>），作为JSX渲染的目标容器。

main.jsx:
这是一个使用JSX语法编写的JavaScript文件，主要用于构建用户界面。
在React项目中，它可能是应用的主要组件或入口点之一，负责渲染根组件到DOM中指定的容器（例如上面提到的#root）。
文件中会包含React组件定义、状态管理逻辑以及事件处理程序等。

App.jsx 文件是 React 应用的核心组件文件，它定义了一个简单的用户界面，包括两个 logo、一个标题、一个带有计数器功能的按钮以及一些提示文本。通过使用 useState 钩子，组件能够维护和更新其内部状态（即 count），从而实现交互性。

两者的关系通常是：index.html 提供了一个静态的HTML页面框架，并指定了一个挂载点；而 main.jsx 则通过React等库动态地在这个挂载点上生成和更新UI内容。
```



```react
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
```

新增NewTodoform 把这个页面中的内容传入到之前的页面，把之前的jsx分成四个jsx  相当于把一个个组件分开来

app.jsx

```react
import {useState} from "react";
import NewTodoform from "./NewTodoform.jsx";
import('./styles.css')// import('./styles.css')
export default function App(){
    const [todos,setTodos] = useState([])
    function addTodos(title) {
        const newId = crypto.randomUUID();
        // 检查是否已经存在相同 ID
        if (todos.some(todo => todo.id === newId)) {
            console.error('Duplicate ID detected:', newId);
            return;
        }
        setTodos((current) => [
            ...current,
            { id: newId, title, completed: false }
        ]);
    }
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
            <NewTodoform addTodos={addTodos} />

            <h1 className={"header"}>Todo List</h1>
            {todos.length === 0 && <div>No todos</div>}

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
```

NewTodoform.jsx

```react
import React, {useState} from "react";
export default function NewTodoform({addTodos}) {
    const [newItem,setNewItem] = useState("");
    const handleSubmit = (e) => {
        e.preventDefault();
        addTodos(newItem);
        setNewItem(''); // 清空输入框
    };
    return (
        <form onSubmit={handleSubmit} className="new-item-form">
            <div className="form-row">
                <label htmlFor=""> New Item</label>
                <input type="text" id="item" value={newItem} onChange={(e) => setNewItem(e.target.value)}/>
            </div>
            <button className={"btn"}>Add</button>
        </form>
    );
}
```

### 1. `onChange` 用法

`onChange` 是 React 中用于处理表单元素（如 `<input>`、`<select>`、`<textarea>` 等）值变化事件的一个属性。当表单元素的值发生改变时，会触发 `onChange` 所绑定的函数。

在你的代码示例中，用于处理复选框状态变化：

```jsx
<input type="checkbox" checked={todo.completed}
       onChange={(e) => toggleTodo(todo.id, e.target.checked)}/>
```

这里，当复选框的状态改变时，会调用 `toggleTodo` 函数，同时将当前复选框的 `checked` 状态（`e.target.checked`）作为参数传递给 `toggleTodo` 函数。

### 2. `useState` 用法

`useState` 是 React 中的一个钩子，用于在函数组件中添加状态管理。它返回一个数组，数组的第一个元素是当前状态的值，第二个元素是用于更新该状态的函数。

在你的代码中：

收起

```jsx
const [todos, setTodos] = useState([]);
```

- `todos` 是状态变量，初始值为一个空数组。
- `setTodos` 是用于更新 `todos` 状态的函数。

更新状态有两种常见方式：

- **直接赋值**：当新状态不依赖于旧状态时可以使用。



```jsx
setTodos([{ id: 1, title: 'New Todo', completed: false }]);
```

- **函数式更新**：当新状态依赖于旧状态时，推荐使用这种方式，它能确保在并发更新时不会出现问题。



```jsx
setTodos((current) => [
    ...current,
    { id: newId, title, completed: false }
]);
```

### 3. `onSubmit` 用法

`onSubmit` 通常用于表单元素（如 `<form>`），当表单提交时会触发 `onSubmit` 所绑定的函数。

例如，在 `NewTodoform.jsx` 组件中可能会有类似的用法：

收起



```jsx
// NewTodoform.jsx
import React from 'react';

const NewTodoform = ({ addTodos }) => {
    const [inputValue, setInputValue] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (inputValue.trim()) {
            addTodos(inputValue);
            setInputValue('');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={inputValue}
                onChange={(e) => setInputValue(e.target.value)}
            />
            <button type="submit">Add Todo</button>
        </form>
    );
};

export default NewTodoform;
```

这里，当用户点击表单的提交按钮时，会触发 `handleSubmit` 函数。

### 4. `preventDefault` 用法

`preventDefault` 是事件对象（如 `e`）的一个方法，用于阻止事件的默认行为。在表单提交的场景中，默认行为是页面刷新，使用 `preventDefault` 可以避免这种情况。

在上面的 `handleSubmit` 函数中：

收起

```jsx
const handleSubmit = (e) => {
    e.preventDefault();
    // 其他逻辑
};
```

调用 `e.preventDefault()` 后，表单提交时不会刷新页面，而是执行后续的逻辑。

### 5. `setTodos` 的两种写法

- **直接赋值**：适用于新状态不依赖于旧状态的情况。



```jsx
const newTodos = [...todos, { id: newId, title, completed: false }];
setTodos(newTodos);
```

- **函数式更新**：适用于新状态依赖于旧状态的情况，推荐使用这种方式，能避免并发更新时的问题。



```jsx
setTodos((current) => [
    ...current,
    { id: newId, title, completed: false }
]);
```

### 6. 多根与单根需要用到 `<></>`

在 React 中，组件的返回值必须是单个根元素。如果需要返回多个元素而不想添加额外的 DOM 节点，可以使用 React 片段 `<></>` 或 `<React.Fragment></React.Fragment>`。

在你的代码中：

收起

```jsx
return (
    <>
        <NewTodoform addTodos={addTodos} />
        <h1 className={"header"}>Todo List</h1>
        {/* 其他元素 */}
    </>
);
```

这里使用 `<></>` 包裹了多个元素，避免了添加额外的父元素。

### 7. 把代办事项添加到代办列表中

在你的代码中，通过 `addTodos` 函数将新的待办事项添加到 `todos` 列表中：

```jsx
function addTodos(title) {
    const newId = crypto.randomUUID();
    if (todos.some(todo => todo.id === newId)) {
        console.error('Duplicate ID detected:', newId);
        return;
    }
    setTodos((current) => [
        ...current,
        { id: newId, title, completed: false }
    ]);
}
```

当调用 `addTodos` 函数并传入待办事项的标题时，会生成一个唯一的 ID，然后将新的待办事项添加到 `todos` 数组的末尾。

### 8. 关于省略 `return`

在箭头函数中，如果函数体只有一行代码，可以省略 `return` 关键字。

例如，在 `toggleTodo` 函数中：

```jsx
setTodos((current) => {
    return current.map(todo => {
        if (todo.id === id) return {...todo, completed: completed };
        return todo;
    });
});
```

可以简化为：

收起

```jsx
setTodos((current) =>
    current.map(todo => (todo.id === id ? {...todo, completed: completed } : todo))
);
```

### 9. 钩子：`useEffect` 和 `useState`

- **`useState`**：前面已经详细介绍过，用于在函数组件中添加状态管理。
- **`useEffect`**：用于处理副作用操作，如数据获取、订阅、手动修改 DOM 等。它接受两个参数：一个回调函数和一个依赖项数组。

例如，在组件挂载时打印一条消息：

```jsx
import React, { useState, useEffect } from 'react';

export default function App() {
    const [todos, setTodos] = useState([]);

    useEffect(() => {
        console.log('Component mounted');
        return () => {
            console.log('Component unmounted');
        };
    }, []);

    // 其他代码

    return (
        // JSX
    );
}
```

- 回调函数：在组件挂载后执行，并且在依赖项数组中的值发生变化时也会执行。
- 依赖项数组：如果为空，回调函数只会在组件挂载和卸载时执行；如果包含某些值，当这些值发生变化时，回调函数会重新执行。

通过 `useEffect` 的返回函数，可以进行清理操作，如取消订阅、清除定时器等。
