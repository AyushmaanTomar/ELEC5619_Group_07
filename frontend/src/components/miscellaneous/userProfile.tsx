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
                <div className="user-profile" style={{ display: 'flex', justifyContent: 'space-between' }}>
                    <div className="profile-picture" style={{ flex: 1, marginRight: '20px' }}>
                        <img src={selectedImage || data.imageUrl} alt="Profile Picture" style={{ width: '450px' }} />

                        {/* File Input */}
                        <input 
                            ref={fileInputRef}
                            type="file" 
                            accept="image/*" 
                            onChange={handleImageChange}
                            style={{ display: 'none' }} 
                        />

                        {/* Button */}
                        <button className="upload-button" onClick={handleButtonClick} style={{marginLeft : '130px'}}>
                            Change Profile Image
                        </button>
                        <a href="/changePassword">
                        <button className="upload-button" style={{marginLeft : '130px'}}>
                            Change Password
                        </button>
                        </a>
                        
                    </div>
                    <div style={{ flex: 1, paddingTop: '20px' }}>
                        <h2 className="text-2xl font-extrabold my-8">{data.username}</h2>
                        <div className="profile-info">
                            <div style={{ paddingBottom: '3px'}}>
                                <strong>Email:</strong> {data.email}
                            </div>
                            <div style={{ paddingBottom: '10px'}}>
                                <strong>Phone Number:</strong> {data.phoneNumber}
                            </div>
                        </div>
                        <NavLink to="/like" className="goto-like-button" style={{backgroundColor: '#ef4444', display: 'inline-block', padding: '10px 15px', borderRadius: '5px' }}>
                            Go to Like Page
                        </NavLink>
                        <NavLink to="/changePassword" className="bg-blue-700 goto-like-button" style={{display: 'inline-block', padding: '10px 15px', borderRadius: '5px', margin:'20px' }}>
                            Change Password
                        </NavLink>
                    </div>
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
