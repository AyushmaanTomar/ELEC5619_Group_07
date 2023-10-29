import React, { useEffect, useState } from 'react';
import { useProducts } from './itemHooks';
import ItemList from './itemList';
import SearchBarFilter from "../miscellaneous/searchBar"
import { useError } from 'src/errorContext';
import api from 'src/axiosConfig';
import { Item } from './listings';
import { Button } from '@mui/material';

const getProducts = async (setData: any, showError: any) => {
  await api.get("/admin/listItem")
    .then((res) => {;
      setData(sortAlphabeticallyByName(res.data));
    })
    .catch((error) => {
      showError(error.request.data);
  });
 };

 const searchProducts = async (key: string, setData: any, showError: any) => {
  await api.post("/items/search?key=" + key)
    .then((res) => {;
      if (res.status == 204) {
        console.log("No results found");
      }
      setData(sortAlphabeticallyByName(res.data));
    })
    .catch((error) => {
      showError(error.request.data);
  });
 };

 const sortAlphabeticallyByName = (productList: any) => {
  return productList.sort((a: Item, b: Item) => a.name.localeCompare(b.name));
};

function ProductsPage() {

  const [searchTerm, setSearchTerm] = useState<string>('');
  const [data, setData] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const { showError } = useError();

  const submitSearch = async () => {
    setLoading(true);
    await searchProducts(searchTerm, setData, showError);
    setLoading(false);
  }

  useEffect( () => {
    getProducts(setData, showError);
    setLoading(false);
  }, [setData]);

  return (
    <>
      <h2 className="text-2xl font-extrabold my-8">
        Products
        <div className="mt-2">
        <SearchBarFilter value={searchTerm} onChange={setSearchTerm} />
        <Button onClick={submitSearch}> Search </Button>
        </div> 
      </h2>
      {data == null || isLoading ? (
        <div className="center-page">
          <span className="spinner primary"></span>
          <p>LOADING...</p>
        </div>
      ) : data == 0 ? (
        <div className="center-page">
          <span className="spinner primary"></span>
          <p>No results found</p>
        </div>
      ) :
      (
        <>
          {/* {isFetching && (
            <div className="toast toast-center toast-top ">
              <div className="alert bg-gray-200">
                <span>REFRESHING...</span>
              </div>
            </div>
          )} */}
          <ItemList projects={data} />
          {/* <div>
            <div>Current page: {page + 1}</div>
            <div>
              <div className="btn-group">
                <button
                  className="btn "
                  onClick={() => setPage((oldPage) => oldPage - 1)}
                  disabled={page === 0}
                >
                  Previous
                </button>
                <button
                  className="btn"
                  onClick={() => {
                    if (!isPreviousData) {
                      setPage((oldPage) => oldPage + 1);
                    }
                  }}
                  disabled={filteredProducts.length !== 12}
                >
                  Next
                </button>
              </div>
            </div>
          </div> */}
        </>
      )}
    </>
  );
}

export default ProductsPage;