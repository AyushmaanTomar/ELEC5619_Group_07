import React from 'react';
import { Item } from './listings';

interface ProductDetailProps {
  project: Item;
}
export default function ProductDetail({ project }: ProductDetailProps) {
  return (
    <div className="">
      <div className="">
        <div className="card card-bordered bg-gray-200 w-96 relative">
          <figure>
            <img
              className=""
              src={project.imagePath}
              alt={project.name}
              width={500}
              height={300}
            />
          </figure>
          <section className="card-body">
            <h3 className="card-title mb-4 text-base">
              <strong>{project.name}</strong>
            </h3>
            <p className=" text-gray-500 mb-4">{project.description}</p>
            <p className=" text-gray-500">
              <span className="text-gray-800 font-semibold">Price: </span>$
              {project.price.toLocaleString()}
            </p>

            <p className=" text-gray-500">
              <span className="text-gray-800 font-semibold">Seller: </span>{' '}
<<<<<<< HEAD
              {project.user.username} 
=======
              {project.user.userName} 
>>>>>>> a90094e288aa8aa90842dcbec8ec57f6163acb4e
            </p>
            <p className=" text-gray-500">
              <span className="text-gray-800 font-semibold">Active: </span>{' '}
              {project.sold ? 'Sold' : 'For-Sale'}
            </p>
          </section>
        </div>
      </div>
    </div>
  );
}