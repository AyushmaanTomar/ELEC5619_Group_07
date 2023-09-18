import React, { SyntheticEvent, useState } from 'react';
import { Item } from './listings';
import { useSaveProduct } from './itemHooks';

interface ProjectFormProps {
  project: Item;
  onCancel: () => void;
}

function ProductForm({ project: initialProject, onCancel }: ProjectFormProps) {
  const [project, setProject] = useState(initialProject);
  const [errors, setErrors] = useState({
    name: '',
    description: '',
    price: '',
  });

  const { mutate: saveProduct, isLoading } = useSaveProduct();
  const handleSubmit = (event: SyntheticEvent) => {
    event.preventDefault();
    if (!isValid()) return;
    saveProduct(project);
  };

  const handleChange = (event: any) => {
    const { type, name, value, checked } = event.target;
    // if input type is checkbox use checked
    // otherwise it's type is text, number etc. so use value
    let updatedValue = type === 'checkbox' ? checked : value;

    //if input type is number convert the updatedValue string to a number
    if (type === 'number') {
      updatedValue = Number(updatedValue);
    }
    const change = {
      [name]: updatedValue,
    };

    let updatedProject: Item;
    // need to do functional update b/c
    // the new project state is based on the previous project state
    // so we can keep the project properties that aren't being edited like project.id
    // the spread operator (...) is used to
    // spread the previous project properties and the new change
    setProject((p) => {
      updatedProject = new Item({ ...p, ...change });
      return updatedProject;
    });
    setErrors(() => validate(updatedProject));
  };

  function validate(project: Item) {
    let errors: any = { name: '', description: '', budget: '' };
    if (project.name.length === 0) {
      errors.name = 'Name is required';
    }
    if (project.name.length > 0 && project.name.length < 3) {
      errors.name = 'Name needs to be at least 3 characters.';
    }
    if (project.description.length === 0) {
      errors.description = 'Description is required.';
    }
    if (project.price === 0) {
      errors.budget = 'Price must be more than $0.';
    }
    return errors;
  }

  function isValid() {
    return (
      errors.name.length === 0 &&
      errors.description.length === 0 &&
      errors.price.length === 0
    );
  }

  return (
    <div className="card w-96 bg-gray-200 card-bordered glass  min-h-full">
      {isLoading && (
        <div className="toast toast-right toast-bottom ">
          <div className="alert bg-white">
            <span>SAVING...</span>
          </div>
        </div>
      )}
      <section className="card-body">
        <form className="  " onSubmit={handleSubmit}>
          {/* {isLoading && <span className="toast">Saving...</span>} */}

          <div className="form-control mb-2">
            <label className="label" htmlFor="name">
              <span className="label-text">Products Name</span>
            </label>
            <input
              className="form-control input input-bordered"
              type="text"
              name="name"
              placeholder="enter name"
              value={project.name}
              onChange={handleChange}
            />
            {errors.name.length > 0 && (
              <div className="card error">
                <p>{errors.name}</p>
              </div>
            )}
          </div>

          <div className="form-control mb-2">
            <label className="label" htmlFor="description">
              <span className="label-text">Product Description</span>
            </label>
            <textarea
              className="textarea textarea-bordered"
              name="description"
              placeholder="enter description"
              value={project.description}
              onChange={handleChange}
            />
            {errors.description.length > 0 && (
              <div className="card error">
                <p>{errors.description}</p>
              </div>
            )}
          </div>
          <div className="form-control mb-2">
            <label className="label" htmlFor="budget">
              <span className="label-text">Product Price</span>
            </label>
            <input
              className="input input-bordered"
              type="number"
              name="prince"
              placeholder="enter price"
              value={project.price}
              onChange={handleChange}
            />
            {errors.price.length > 0 && (
              <div className="card error">
                <p>{errors.price}</p>
              </div>
            )}
          </div>
          <div className="form-control mb-4">
            <label className="label " htmlFor="isActive">
              <span className="label-text">Active?</span>
            </label>
            <input
              className="checkbox ml-1"
              type="checkbox"
              name="isActive"
              checked={project.isActive}
              onChange={handleChange}
            />
          </div>
          {/* <div className="form-control mb-4">
            <label className="label" htmlFor="contractType">
              Contract Type
            </label>
            <select className="form-control select" name="contractType">
              <option value="">Select...</option>
              <option value="">Fixed Bid</option>
              <option value="">Time & Materials</option>
              <option value="">Value Proposition</option>
            </select>
          </div> */}

          <div className="card-actions justify-end">
            <button className="btn btn-primary">Save</button>
            <button
              type="button"
              className="btn btn-link text-accent"
              onClick={onCancel}
            >
              cancel
            </button>
          </div>
        </form>
      </section>
    </div>
  );
}

export default ProductForm;