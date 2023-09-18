import React, { useState } from 'react';
import { Item } from './listings';
import ProductCard from './itemCard';
import ProductForm from './itemForm';

interface ProductListProps {
  projects: Item[];
}

function ItemList({ projects }: ProductListProps) {
  const [projectBeingEdited, setProjectBeingEdited] = useState({});

  const handleEdit = (project: Item) => {
    setProjectBeingEdited(project);
  };

  const cancelEditing = () => {
    setProjectBeingEdited({});
  };

  return (
    <div className="flex flex-wrap gap-6">
      {projects.map((project) => (
        <div key={project.id}>
          {project === projectBeingEdited ? (
            <ProductForm project={project} onCancel={cancelEditing} />
          ) : (
            <ProductCard item ={project} onEdit={handleEdit} />
          )}
        </div>
      ))}
    </div>
  );
}

export default ItemList;