import { Item } from './listings';
import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from 'src/components/usermanagement/AuthProvider';

function formatDescription(description: string): string {
  return description.substring(0, 60) + '...';
}

function formatTitle(title: string, length: number): string {
  if (title.length <= length) {
    return title;
  } else {
    return title.substring(0, 22) + '...';
  }
}

interface ProductCardProps {
  item: Item;
  // onEdit: (project: Item) => void;
}

function ProductCard(props: ProductCardProps) {
  const { item } = props;
<<<<<<< HEAD
=======
  const { loggedIn } = useAuth();
>>>>>>> a90094e288aa8aa90842dcbec8ec57f6163acb4e

  // const handleEditClick = (projectBeingEdited: Item) => {
  //   onEdit(projectBeingEdited);
  // };

  return (
<<<<<<< HEAD
    <div className="card card-bordered bg-gray-200 w-96">
      <figure>
        <img src={item.imagePath} alt={item.name} />
      </figure>
      <section className="card-body">
        <Link to={'/products/' + item.id}>
          <h6 className="card-title mb-4 text-base">{formatTitle(item.name, 24)}</h6>

          <p className="mb-4 text-gray-500">
=======
      <div className="card card-bordered bg-gray-200 w-96">
        <figure>
          <img src={item.imagePath} alt={item.name} />
        </figure>
        <section className="card-body">
            <Link to={loggedIn ? ('/products/' + item.id) : '#'}>
                <h6 className="card-title mb-4 text-base">{formatTitle(item.name, 24)}</h6>
            </Link>
            <p className="mb-4 text-gray-500">
>>>>>>> a90094e288aa8aa90842dcbec8ec57f6163acb4e
            {formatDescription(item.description)}
          </p>
          <p id="budgetParagraph" className="mb-2 badge badge-outline p-2 text-gray-500 py-4 ">
            Price : $ {item.price.toLocaleString()}
          </p>
<<<<<<< HEAD
        </Link>
        {/* <div className="card-actions justify-start">
=======
          {/* <div className="card-actions justify-start">
>>>>>>> a90094e288aa8aa90842dcbec8ec57f6163acb4e
          <button
            className="btn btn-link text-secondary pl-0 "
            onClick={() => {
              handleEditClick(item);
            }}
          >
            Edit
          </button>
        </div> */}
<<<<<<< HEAD
      </section>
    </div>
=======
        </section>
      </div>
>>>>>>> a90094e288aa8aa90842dcbec8ec57f6163acb4e
  );

}

export default ProductCard;