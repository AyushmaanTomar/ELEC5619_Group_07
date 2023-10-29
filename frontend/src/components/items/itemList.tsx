import React, { useState } from 'react';
import { Item } from './listings';
import ProductCard from './itemCard';
import ProductForm from './itemForm';
import api from 'src/axiosConfig';

interface ProductListProps {
  projects: Item[];
  canDelete: boolean;
}

function ItemList({ projects, canDelete }: ProductListProps) {
  // const [projectBeingEdited, setProjectBeingEdited] = useState({});

  // const handleEdit = (project: Item) => {
  //   setProjectBeingEdited(project);
  // };

  // const cancelEditing = () => {
  //   setProjectBeingEdited({});
  // };

  return (
    <div className="flex flex-wrap gap-6">
      {projects.map((project) => (
        <div key={project.id}>
          {/* {project === projectBeingEdited ? (
            <ProductForm project={project} onCancel={cancelEditing} />
          ) : ( */}
            <ProductCard item ={project} canDelete={canDelete} />
          {/* )} */}
        </div>
      ))}
    </div>
  );
}

export default ItemList;