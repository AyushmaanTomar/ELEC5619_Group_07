import './AdminPage.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {List} from "@mui/material";

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

const AdminPage: React.FC = () => {
    const [selectedUsers, setSelectedUsers] = useState<number[]>([]);
    const [selectedItems, setSelectedItems] = useState<number[]>([]);
    const [users, setUsers] = useState<ListItemType[]>([]);
    const [items, setItems] = useState<ListItemType[]>([]);

    useEffect(() => {
        axios.get('http://localhost:8080/admin/listUsers')
            .then(response => {
                console.log("Fetched users:", response.data);  // Log the fetched users
                setUsers(response.data);
            })
            .catch(error => {
                console.error('Error fetching users:', error);
            });

        axios.get('http://localhost:8080/admin/listItem')
            .then(response => {
                setItems(response.data);
            })
            .catch(error => {
                console.error('Error fetching items:', error);
            });
    }, []);

    const handleDeleteUsers = () => {
        // Delete multiple users at once
        selectedUsers.forEach(userId => {
            axios.delete(`http://localhost:8080/admin/deleteUser/${userId}`)
                .then(response => {
                    console.log('User deleted:', response.data);
                    // Update the local state to remove the deleted users
                    setUsers(prevUsers => prevUsers.filter(user => user.id !== userId));
                })
                .catch(error => {
                    console.error('Error deleting user:', error);
                });
        });
        setSelectedUsers([]);
    };
    const handleDeleteItems = () => {
        // Delete multiple items at once
        selectedItems.forEach(itemId => {
            axios.delete(`http://localhost:8080/admin/deleteItem/${itemId}`)
                .then(response => {
                    console.log('Item deleted:', response.data);
                    // Update the local state to remove the deleted items
                    setItems(prevItems => prevItems.filter(item => item.id !== itemId));
                })
                .catch(error => {
                    console.error('Error deleting item:', error);
                });
        });
        setSelectedItems([]);
    };

    const handleToggle = (list: 'users' | 'items', id: number) => {
        const selectedList = list === 'users' ? selectedUsers : selectedItems;
        const setSelectedList = list === 'users' ? setSelectedUsers : setSelectedItems;

        const newSelection = selectedList.includes(id)
            ? selectedList.filter(itemId => itemId !== id)
            : [...selectedList, id];
        setSelectedList(newSelection);
    };
    const CustomList: React.FC<ListProps> = ({ items, title, handleToggle, handleDelete, selectedItems }) => (
        <div className="section">
            <p>
                {title}
                <button className="delete-btn" onClick={handleDelete}>Delete</button>
            </p>
            <hr />
            {items.length === 0 ? (
                <p>No items to display</p>
            ) : (
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
            )}
        </div>
    );

    return (
        <div className="admin-container">
            <CustomList
                title="User List"
                items={users}
                handleToggle={(id) => handleToggle('users', id)}
                handleDelete={handleDeleteUsers}
                selectedItems={selectedUsers}
            />

            <CustomList
                title="Item List"
                items={items}
                handleToggle={(id) => handleToggle('items', id)}
                handleDelete={handleDeleteItems}
                selectedItems={selectedItems}
            />
        </div>
    );
};

export default AdminPage;
