// LikePage.tsx
import * as React from 'react';
import { NavLink } from 'react-router-dom'; // To allow navigating back to the UserProfile page

const LikePage: React.FC = () => {
    return (
        <div style={{ padding: '20px' }}>
            <h2>This is the Like Page</h2>
            <p>
                Here you might have a list of things the user has liked, 
                or any other relevant content you'd like to showcase.
            </p>

            {/* Button to navigate back to the UserProfile Page */}
            <NavLink 
                to="/profile" 
                style={{ textDecoration: 'none', color: 'inherit', display: 'inline-block', padding: '10px 15px', border: '1px solid #333', borderRadius: '5px', marginTop: '20px' }}
            >
                Back to Profile
            </NavLink>
        </div>
    );
}

export default LikePage;
