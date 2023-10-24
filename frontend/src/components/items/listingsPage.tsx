import React, { useEffect, useState } from 'react';
import { useProducts } from './itemHooks';
import ItemList from './itemList';
import SearchBarFilter from "../miscellaneous/searchBar"


function ProductsPage() {
  const {
    data,
    isLoading,
    error,
    isError,
    isFetching,
    page,
    setPage,
    isPreviousData,
  } = useProducts();

  const handleMoreClick = () => {
    setPage((currentPage) => currentPage + 1);
  };

  return (
    <>
      <h2 className="text-2xl font-extrabold my-8">Products  <SearchBarFilter /> </h2>
      {data ? (
        <>
          {isFetching && (
            <div className="toast toast-center toast-top ">
              <div className="alert bg-gray-200">
                <span>REFRESHING...</span>
              </div>
            </div>
          )}
          <ItemList projects={data} />
          <div>
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
                  disabled={data.length != 12}
                >
                  Next
                </button>
              </div>
            </div>
          </div>
        </>
      ) : isLoading ? (
        <div className="center-page">
          <span className="spinner primary"></span>
          <p>LOADING...</p>
        </div>
      ) : isError && error instanceof Error ? (
        <div className="row">
          <div className="card large error">
            <section>
              <p>
                <span className="icon-alert inverse "></span>
                {error.message}
              </p>
            </section>
          </div>
        </div>
      ) : null}
    </>
  );
}

export default ProductsPage;