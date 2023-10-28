import React, { useState, useCallback, memo } from 'react';
import { productAPI } from '../items/itemAPI';
import { useProducts } from '../items/itemHooks';
import { Button, Container, Box, TextField, Stack } from '@mui/material';
import  styled  from '@emotion/styled';
import checkForModeration from '../miscellaneous/moderation';

interface Props {
    onAdd?: (product: any) => void;
}

const AddProducts: React.FC<Props> = memo(({ onAdd }) => {

  console.log('Rendering AddProducts');
    const initialProduct = {
        name: "",
        description: "",
        imageUrl: "",
        seller: "",
        listingDate: new Date().toISOString().split("T")[0],
        price: 0,
        isActive: true
    };

    const [product, setProduct] = useState(initialProduct);
    const [errorMessage, setErrorMessage] = useState<string | undefined>(undefined);

    const handleSubmit = useCallback(async (e: React.FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      
      const isSafe = await checkForModeration(product.description);
      if (!isSafe) {
          setErrorMessage("The description contains inappropriate content.");
          return;
      }

      if (product.price === 0) {
        setErrorMessage("PRICE CANNOT BE 0!");
        return;
      }

      if (onAdd) {
          onAdd(product);
      }
      window.location.href = "/products";

    }, [product]);

    const handleChange = useCallback((e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
      const { name, value } = e.target;
      setProduct(prev => {
          if (prev[name as keyof typeof prev] !== value) {
              return { ...prev, [name]: value };
          }
          return prev;
      });
  }, []);

    const handleCheckboxChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, checked } = e.target;
        setProduct(prev => ({ ...prev, [name]: checked }));
    }, []);

    const [imagePreviewUrl, setImagePreviewUrl] = useState<string | undefined>(undefined);

    const handleImageChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        e.preventDefault();

        const reader = new FileReader();
        const file = e.target.files![0];

        reader.onloadend = () => {
            setProduct(prev => ({ ...prev, imageUrl: reader.result as string }));
            setImagePreviewUrl(reader.result as string);
        };

        if (file) {
            reader.readAsDataURL(file);
        }
    }, []);

  const StyledTextField = styled(TextField)({
    "& input, & textarea": {
      color: "white"
    },
    "& label": {
        color: "white"
    },
    "& label.Mui-focused": {
        color: "white"
    },
    "& .MuiOutlinedInput-root": {
      "& fieldset": {
        borderColor: "white"
      },
      "&:hover fieldset": {
        borderColor: "white",
        borderWidth: 2
      },
      "&.Mui-focused fieldset": {
        borderColor: "white"
      }
    }
  });

  return (
    <React.Fragment>
      <h2 className="text-2xl font-extrabold my-8">Add Products</h2>
      <Container maxWidth="sm" sx={{height: "auto", borderRadius: '20px', padding: "30px"}}>
        <Box className="bg-secondary" sx={{height: "auto", borderRadius: '20px', padding: "30px"}}>
          <Stack component="form" onSubmit={handleSubmit} spacing={2} paddingBottom="25px">
            <StyledTextField required id="name" label="Product Name" name="name" value={product.name} onChange={handleChange} />
            <StyledTextField required id="description" label="Description" name="description" value={product.description} onChange={handleChange} multiline />
            <input 
                style={{ display: 'none' }}
                type="file" 
                id="fileInput"
                name="imageUrl" 
                accept="image/*"
                onChange={handleImageChange} 
            />

            <label htmlFor="fileInput" style={{ 
                display: 'flex', 
                alignItems: 'center', 
                justifyContent: 'center', 
                cursor: 'pointer',
                borderRadius: '5px',
                border: '1px solid #ffffff',
                background: '#231f21',
                color: '#ffffff',
                height: '40px',
            }}>
                {imagePreviewUrl ? "Change Image" : "Upload Image"}
            </label>

              {imagePreviewUrl && <img src={imagePreviewUrl} alt="Selected Preview" style={{ maxWidth: '100%', margin: '20px 0' }} />}
            <StyledTextField required id="seller" label="Seller" name="seller" value={product.seller} onChange={handleChange} />
            <StyledTextField required id="price" label="Price" name="price" value={product.price.toString()} onChange={handleChange} type="number" />
            <StyledTextField required id="listingDate" label="Listing Date" name="listingDate" value={product.listingDate} InputProps={{ readOnly: true }} type="date" />
            <label style={{ color: 'white' }}>
              <span>Want to make this product as ongoing sale? </span>
              <input id="isActive" type="checkbox" name="isActive" checked={product.isActive} onChange={handleCheckboxChange} style={{ margin: '0 10px' }} />
            </label>
            { errorMessage && <p style={{ color: 'red', textAlign: 'center' }}>{errorMessage}</p> }
            <Button type="submit" variant="contained">Add Product</Button>
          </Stack>
        </Box>
      </Container>
    </React.Fragment>
  );
});

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
      </div>
  );
}

