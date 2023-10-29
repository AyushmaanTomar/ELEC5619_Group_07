import * as React from 'react';
import { useProfile } from '../usermanagement/profileHooks';
import { NavLink } from 'react-router-dom'; 

interface UserProfileProps {
    columns?: number;
}

const UserProfile: React.FC<UserProfileProps> = () => {
    const [selectedImage, setSelectedImage] = React.useState<string | null>(null);
    const fileInputRef = React.useRef<HTMLInputElement>(null);

    const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                setSelectedImage(reader.result as string);
            }
            reader.readAsDataURL(file);
        }
    };

    const handleButtonClick = () => {
        fileInputRef.current?.click();
    };

    const {
        data,
        isLoading,
    } = useProfile();

    return (
        <>
            {data ? (
                <div className="user-profile">
                    <div className="profile-picture">
                        <img src={selectedImage || data.imageUrl} alt="Profile Picture" style={{ width: '150px' }} />

                        {/* File Input */}
                        <input 
                            ref={fileInputRef}
                            type="file" 
                            accept="image/*" 
                            onChange={handleImageChange}
                            style={{ display: 'none' }} 
                        />

                        {/* Button */}
                        <button className="upload-button" onClick={handleButtonClick}>
                            Change Profile Image
                        </button>
                    </div>
                    <h2 className="text-2xl font-extrabold my-8">{data.username}</h2>
                    <div className="profile-info">
                        <div>
                            <strong>Email:</strong> {data.email}
                        </div>
                        <div>
                            <strong>Phone Number:</strong> {data.phoneNumber}
                        </div>
                    </div>
                    <NavLink to="/like" className="goto-like-button" style={{ textDecoration: 'none', color: 'inherit', display: 'inline-block', padding: '10px 15px', border: '1px solid #333', borderRadius: '5px' }}>
                        Go to Like Page
                    </NavLink>
                </div>
            ) : isLoading ? (
                <div className="center-page">
                    <span className="spinner primary"></span>
                    <p>LOADING...</p>
                </div>
            ) : null}
        </>
    );
}

export default UserProfile;
