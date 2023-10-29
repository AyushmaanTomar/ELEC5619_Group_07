import React, { useEffect, useState } from 'react';
import { productAPI } from './itemAPI';
import ProductDetail from './itemDetail';
import { Item } from './listings';
import { useParams } from 'react-router-dom';
import { GoogleMap, LoadScript } from '@react-google-maps/api';
import api from 'src/axiosConfig';
import { useError } from 'src/errorContext';
import { useProfile } from '../usermanagement/profileHooks';

function ProductPage(props: any) {
  const [loading, setLoading] = useState(false);
  const [product, setProduct] = useState<Item | null>(null);
  const [error, setError] = useState<string | null>(null);
  const params = useParams();
  const id = Number(params.id);
  const { showError } = useError();

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
    // productAPI
    //   .find(id)
    //   .then((data) => {
    //     setProduct(data);
    //     setLoading(false);
    //   })
    //   .catch((e) => {
    //     setError(e);
    //     setLoading(false);
    //   });
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
        <div style={{ paddingBottom: '10px'}}>
            <strong>Seller Phone Number:</strong> {data.phoneNumber}
        </div>
      ) : null }
      </>
    </div>
  );
}

export default ProductPage;