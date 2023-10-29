import React, { useEffect, useState } from 'react';
import { productAPI } from './itemAPI';
import ProductDetail from './itemDetail';
import { Item } from './listings';
import { useParams } from 'react-router-dom';
import { GoogleMap, LoadScript } from '@react-google-maps/api';
import api from 'src/axiosConfig';
import { useError } from 'src/errorContext';
import { useProfile } from '../usermanagement/profileHooks';
import './Like.css'

function ProductPage(props: any) {
  const [loading, setLoading] = useState(false);
  const [product, setProduct] = useState<Item | null>(null);
  const [error, setError] = useState<string | null>(null);
  const { id } = useParams();
  const { showError } = useError();
  const [liked, setLiked] = useState(false);
  const [likeCount, setLikeCount] = useState(0);  // Initialize this with the actual count from the server if needed
  const [likes, setLikes] = useState<number>(0);

  // useEffect(() => {
  //   async function fetchLikes() {
  //     if (id) { // Check if id is defined
  //       const numericId = Number(id);
  //       if (!isNaN(numericId)) { // Ensure that id is a valid number
  //         const totalLikes = await productAPI.getTotalLikesForItem(numericId);
  //         setLikes(totalLikes);
  //       }
  //     }
  //   }
  //   fetchLikes();
  // }, [id]);


  const handleLikeClick = () => {
    if (liked) {
      setLikes(likes - 1);
    } else {
      setLikes(likes + 1);
    }
    setLiked(!liked);
  };

  const mapStyles = {
    height: "450px",
    width: "80%"
  };
  
  const defaultCenter = {
    lat: -33.8540 , // Adjust this to your desired coordinates
    lng: 151.0507
  }

  const {
    data,
    isLoading,
} = useProfile();

  useEffect(() => {
    setLoading(true);
    api.get("/items/" + id)
      .then((data) => {
        setProduct(data.data);
        setLoading(false);
      }).catch((error) => {
        showError(error.results.data);
      })
  }, [id]);

  return (
      <div>
        <>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            <div style={{ flex: 1, marginRight: '20px' }}>
              <h2 className="text-2xl font-extrabold my-8">Product Detail</h2>
              <button className="like-button" onClick={handleLikeClick}>
                {liked ? 'Unlike' : 'Like'}
              </button>
              <span className="like-total">{likes}</span> {/* Notice the change from likeCount to likes */}
            </div>

        <div style={{ flex: 1, marginRight: '20px' }}>
          <h2 className="text-xl font-extrabold my-8">Seller's Nearby Location</h2>
        </div>
      </div>

        {loading && (
          <div className="center-page">
            <span className="spinner primary"></span>
            <p>Loading...</p>
          </div>
        )}

        {error && (
          <div className="">
            <div className="">
              <section>
                <p>
                  <span className="icon-alert inverse "></span> {error}
                </p>
              </section>
            </div>
          </div>
        )}

      <div style={{ display: 'flex', justifyContent: 'space-between' }}>
        <div style={{ flex: 1, marginRight: '20px' }}>
          {product && <ProductDetail project={product} />}
        </div>

        <div style={{ flex: 1 }}>
          <LoadScript googleMapsApiKey="AIzaSyA99t4ghcD2-QR5wXTQg8aHOSu0admetDY">
            <GoogleMap
              mapContainerStyle={mapStyles}
              zoom={13}
              center={defaultCenter}
            >
            </GoogleMap>
          </LoadScript>
        </div>
      </div>
      {data ? (
        <div style={{ paddingBottom: '15px'}}>
            <strong>Seller Phone Number:</strong> {data.phoneNumber}
        </div>
      ) : null }
      </>
    </div>
  );
}

export default ProductPage;