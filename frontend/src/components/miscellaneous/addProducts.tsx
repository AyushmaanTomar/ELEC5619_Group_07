import React, { useState } from 'react';
import { productAPI } from '../items/itemAPI';
import { useProducts } from '../items/itemHooks';
import ItemList from '../items/itemList';

interface Props {
    onAdd?: (product: any) => void;
}

const AddProducts: React.FC<Props> = ({ onAdd }) => {
    const initialProduct = {
        name: "",
        description: "",
        imageUrl: "",
        seller: "",
        listingDate: new Date().toISOString(),
        price: 0,
        isActive: true
    };

    const [product, setProduct] = useState(initialProduct);

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (onAdd) {
            onAdd(product);
        }
        window.location.href = "/products";
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setProduct(prev => ({ ...prev, [name]: value }));
    };

    const handleCheckboxChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, checked } = e.target;
        setProduct(prev => ({ ...prev, [name]: checked }));
    };

  const formStyle: React.CSSProperties = {
    display: 'flex',
    flexDirection: 'column',
    gap: '20px',
    maxWidth: '500px',  
    margin: '20px auto',
    padding: '20px', 
    backgroundColor: '#231F20',  
    boxShadow: '0px 2px 8px rgba(0, 0, 0, 0.1)',  
    borderRadius: '8px' 
};

const inputStyle: React.CSSProperties = {
    padding: '12px 15px',
    borderRadius: '6px', 
    border: '1px solid #e0e0e0', 
    fontSize: '1rem',
    transition: 'border-color 0.3s ease, box-shadow 0.3s ease', 
};

const whiteTextStyle: React.CSSProperties = {
    color: '#ffffff'
};

  return (
    <form style={formStyle} onSubmit={handleSubmit}>
      <input 
        style={inputStyle}
        name="name" 
        value={product.name} 
        onChange={handleChange} 
        placeholder="Product Name"
        required = {true} 
      />

      <textarea 
        style={inputStyle}
        name="description" 
        value={product.description} 
        onChange={handleChange} 
        placeholder="Description"
        required = {true} 
      ></textarea>

      <input 
        style={inputStyle}
        type="text" 
        name="imageUrl" 
        value={product.imageUrl} 
        onChange={handleChange} 
        placeholder="Image URL"
        required = {true} 
      />

      <input 
        style={inputStyle}
        type="text" 
        name="seller" 
        value={product.seller} 
        onChange={handleChange} 
        placeholder="Seller"
        required = {true} 
      />

      <input 
        style={inputStyle}
        type="number" 
        name="price" 
        value={product.price.toString()} 
        onChange={handleChange} 
        placeholder="Price"
        required = {true} 
      />

      <input 
        style={inputStyle}
        type="date" 
        name="listingDate" 
        value={product.listingDate.split("T")[0]}  // extract YYYY-MM-DD part
        onChange={handleChange} 
        required = {true} 
      />

      <label style={whiteTextStyle}>
        <span>Want to make this product as ongoing sale ??  </span>
        <input id="red-checkbox"
          type="checkbox" 
          name="isActive" 
          checked={product.isActive} 
          onChange={handleCheckboxChange}
          required = {true} 
        />
      </label>

      <button className='bg-primary font-bold' type="submit" style={{ ...inputStyle, cursor: 'pointer' }}>Add Product</button>
    </form>
  );
}

export function AddProductsComponent() {
  const handleAddProduct = async (product: any) => {
      try {
          const newProduct = await productAPI.add(product);
          console.log("Product added successfully:", newProduct);
      } catch (error) {
          console.error("Error adding product:", error);
      }
  };

  const { data } = useProducts();

  return (
      <div>
          <AddProducts onAdd={handleAddProduct} />
          {data && <ItemList projects={data} />}
      </div>
  );
}
