import { Item } from './listings';
import React from 'react';
import { Link } from 'react-router-dom';
import api from 'src/axiosConfig';
import { useAuth } from 'src/components/usermanagement/AuthProvider';
import { useError } from 'src/errorContext';

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


const deleteItem = async (id: number, showError: any) => {
  await api.delete("/items/delete?itemId=" + id)
    .catch((error) => {
      showError(error.response.data);
      return;
    });
}

interface ProductCardProps {
  item: Item;
  canDelete: boolean;
  // onEdit: (project: Item) => void;
}

function ProductCard(props: ProductCardProps) {
  const { item } = props;
  const { loggedIn } = useAuth();
  const { showError } = useError();
  const canDelete = props.canDelete;

  // const handleEditClick = (projectBeingEdited: Item) => {
  //   onEdit(projectBeingEdited);
  // };

  const handleDeleteClick = async (id: number) => {
    await deleteItem(id, showError);
    window.location.reload();
  }

  return (
      <div className="card card-bordered bg-gray-200 w-96">
        {!canDelete ? (<></>) : (
          <button
            className="absolute top-0 right-0 p-2 text-gray-500 hover:text-red-500"
            onClick={() => handleDeleteClick(item.id)}
          >
              X
          </button>
        )}
        
        <figure>
          <img src={item.imagePath} alt={item.name} />
        </figure>
        <section className="card-body">
            <Link to={loggedIn ? ('/products/' + item.id) : '#'}>
                <h6 className="card-title mb-4 text-base">{formatTitle(item.name, 24)}</h6>
            </Link>
            <p className="mb-4 text-gray-500">
            {formatDescription(item.description)}
          </p>
          <p id="budgetParagraph" className="mb-2 badge badge-outline p-2 text-gray-500 py-4 ">
            Price : $ {item.price.toLocaleString()}
          </p>
          {/* <div className="card-actions justify-start">
          <button
            className="btn btn-link text-secondary pl-0 "
            onClick={() => {
              handleEditClick(item);
            }}
          >
            Edit
          </button>
        </div> */}
        </section>
      </div>
  );

}

export default ProductCard;