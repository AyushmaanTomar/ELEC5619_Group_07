import * as React from 'react';
import Grid from '@mui/material/Unstable_Grid2';

interface ItemsListProps {
    columns: number
}

export default function UserProfile(profileInfo : {name: string, age: number, } ) {
    //TODO: Implement for loop for a given list of items
    const { name, age, } = profileInfo;
    
    return (
        <div className="profile">
            <h2>User Profile</h2>
            <p>Name: {name}</p>
            <p>Age: {age}</p>
        </div>

    );
}