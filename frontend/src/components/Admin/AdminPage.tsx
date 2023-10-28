import React, { useState } from 'react';
import './AdminPage.css';

type ListItemType = {
    id: number;
    name: string;
};

type ListProps = {
    items: ListItemType[];
    title: string;
    handleToggle: (id: number) => void;
    handleDelete: () => void;
    selectedItems: number[];
};

const List: React.FC<ListProps> = ({ items, title, handleToggle, handleDelete, selectedItems }) => (
    <div className="section">
        <p>{title}</p>
        <button className="delete-btn" onClick={handleDelete}>Delete</button>
        <br />
        <hr />
        <br />
        <ul className="list">
            {items.map(item => (
                <li key={item.id}>
                    {item.name}
                    <input
                        type="checkbox"
                        checked={selectedItems.includes(item.id)}
                        onChange={() => handleToggle(item.id)}
                    />
                </li>
            ))}
        </ul>
    </div>
);

const AdminPage: React.FC = () => {
    const [selectedUsers, setSelectedUsers] = useState<number[]>([]);
    const [selectedItems, setSelectedItems] = useState<number[]>([]);

    const users: ListItemType[] = [
        { id: 1, name: 'User 1' },
        { id: 2, name: 'User 2' }
    ];

    const items: ListItemType[] = [
        { id: 1, name: 'Item 1' },
        { id: 2, name: 'Item 2' }
    ];

    const handleToggle = (list: 'users' | 'items', id: number) => {
        const selectedList = list === 'users' ? selectedUsers : selectedItems;
        const setSelectedList = list === 'users' ? setSelectedUsers : setSelectedItems;

        const newSelection = selectedList.includes(id)
            ? selectedList.filter(itemId => itemId !== id)
            : [...selectedList, id];
        setSelectedList(newSelection);
    };

    return (
        <div className="admin-container">
            <List
                title="User List"
                items={users}
                handleToggle={(id) => handleToggle('users', id)}
                handleDelete={() => {
                    console.log('Delete users with ids:', selectedUsers);
                    setSelectedUsers([]);
                }}
                selectedItems={selectedUsers}
            />

            <List
                title="Item List"
                items={items}
                handleToggle={(id) => handleToggle('items', id)}
                handleDelete={() => {
                    console.log('Delete items with ids:', selectedItems);
                    setSelectedItems([]);
                }}
                selectedItems={selectedItems}
            />
        </div>
    );
};

export default AdminPage;
