import * as React from 'react';
import { useProfile } from '../usermanagement/profileHooks';
import { User } from '../usermanagement/userClass';

interface ItemsListProps {
    columns: number
}

export default function UserProfile() {
    //TODO: Implement for loop for a given list of items
    const {
        data,
        isLoading,
        error,
        isError,
        isFetching,
        page,
        setPage,
        isPreviousData,
      } = useProfile();
    return (
        <>
        
  
        {data ? (
            <div className="user-profile">
                <div className="profile-picture">
    <img src={data.imageUrl} alt="Profile Picture" style={{ width: '150px' }}/>
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
            </div>
        ) : isLoading ? (
          <div className="center-page">
            <span className="spinner primary"></span>
            <p>LOADING...</p>
          </div>
        )  : null}
      </>
    );
}