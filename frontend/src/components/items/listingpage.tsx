import React, { useEffect, useState } from 'react';
import { productAPI } from './itemAPI';
import ProductDetail from './itemDetail';
import { Item } from './listings';
import { useParams } from 'react-router-dom';

function ProductPage(props: any) {
  const [loading, setLoading] = useState(false);
  const [product, setProduct] = useState<Item | null>(null);
  const [error, setError] = useState<string | null>(null);
  const params = useParams();
  const id = Number(params.id);

  useEffect(() => {
    setLoading(true);
    productAPI
      .find(id)
      .then((data) => {
        setProduct(data);
        setLoading(false);
      })
      .catch((e) => {
        setError(e);
        setLoading(false);
      });
  }, [id]);

  return (
    <div>
      <>
        <h2 className="text-2xl font-extrabold my-8">Product Detail</h2>

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

        {product && <ProductDetail project={product} />}
      </>
    </div>
  );
}

export default ProductPage;