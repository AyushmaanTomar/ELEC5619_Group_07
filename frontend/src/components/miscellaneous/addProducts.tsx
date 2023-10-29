import React, { useState, useCallback, memo } from 'react';
import { productAPI } from '../items/itemAPI';
import { useProducts } from '../items/itemHooks';
import { Button, Container, Box, TextField, Stack } from '@mui/material';
import  styled  from '@emotion/styled';
import checkForModeration from '../miscellaneous/moderation';
import api from 'src/axiosConfig';
import { useError } from 'src/errorContext';

const handleAddProduct = async (product: any, showError: any) => {
  await api.post("/admin/createItem", product)
    .catch((error) => { showError(error.request.data); });
};

const AddProducts = memo(() => {
  const { showError } = useError();

  console.log('Rendering AddProducts');
    const initialProduct = {
        user: localStorage.getItem("username"),
        name: "",
        price: 0,
        qty: 0,
        likeAmount: 0,
        description: "",
        listingDate: new Date().toISOString().split("T")[0],
        isSold: false,
        imagePath: "",
    };

    const [product, setProduct] = useState(initialProduct);
    const [errorMessage, setErrorMessage] = useState<string | undefined>(undefined);

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
      e.preventDefault();

      const data = new FormData(e.currentTarget);
      let tempName = data.get("name")?.toString();
      let tempPrice = data.get("price")?.toString();
      let tempDescription = data.get("description")?.toString();
      let tempListingDate = data.get("listingDate")?.toString();

      if (tempName == null || tempPrice == null || tempDescription == null || tempListingDate == null) {
        setErrorMessage("Could not load data. Try again.");
        return;
      }

      product.name = tempName;
      product.description = tempDescription;
      product.price = parseInt(tempPrice);
      product.listingDate = tempListingDate;
      
      const isSafe = await checkForModeration(product.description);
      if (!isSafe) {
          setErrorMessage("The description contains inappropriate content.");
          return;
      }

      if (product.price === 0) {
        setErrorMessage("PRICE CANNOT BE 0!");
        return;
      }

      console.log(product.name);
      console.log(product.description);
      console.log(product.price);
      console.log(product.listingDate);
      console.log(imagePreviewUrl)

      handleAddProduct(product, showError);
 
      // window.location.href = "/products";

    };

  //   const handleChange = useCallback((e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
  //     const { name, value } = e.target;
  //     setProduct(prev => {
  //         if (prev[name as keyof typeof prev] !== value) {
  //             return { ...prev, [name]: value };
  //         }
  //         return prev;
  //     });
  // }, []);

    const [imagePreviewUrl, setImagePreviewUrl] = useState<string | undefined>(undefined);

    const handleImageChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        e.preventDefault();

        const reader = new FileReader();
        const file = e.target.files![0];
        let path = e.target.value;
        console.log(file);

        reader.onloadend = () => {
            // setProduct(prev => ({ ...prev, imageUrl: reader.result as string }));
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
            <StyledTextField required id="name" label="Product Name" name="name" />
            <StyledTextField required id="description" label="Description" name="description" multiline />
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
            <StyledTextField required id="price" label="Price" name="price" type="number" />
            <StyledTextField required id="listingDate" label="Listing Date" name="listingDate" type="date" />
            { errorMessage && <p style={{ color: 'red', textAlign: 'center' }}>{errorMessage}</p> }
            <Button type="submit" variant="contained">Add Product</Button>
          </Stack>
        </Box>
      </Container>
    </React.Fragment>
  );
});

export function AddProductsComponent() {

  return (
      <div>
          <AddProducts />
      </div>
  );
}

