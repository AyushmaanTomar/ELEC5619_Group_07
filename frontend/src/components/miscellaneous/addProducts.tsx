import React, { useState, useCallback, memo } from 'react';
import { productAPI } from '../items/itemAPI';
import { useProducts } from '../items/itemHooks';
import { Button, Container, Box, TextField, Stack } from '@mui/material';
import  styled  from '@emotion/styled';
import checkForModeration from '../miscellaneous/moderation';
import api from 'src/axiosConfig';
import { useError } from 'src/errorContext';
import { useNavigate } from 'react-router-dom';

const handleAddProduct = async (product: any, showError: any) => {
  await api.post("/items/addItem?name=" + product.name + "&description=" + product.description + "&price=" 
    + product.price + "&listingDate=" + product.listingDate + "&userName=" + product.userName + "&imagePath=" + product.imgPath)
    .catch((error) => { showError(error.request.data); });
};

const AddProducts = memo(() => {
  const { showError } = useError();

  console.log('Rendering AddProducts');
    const initialProduct = {
        userName: localStorage.getItem("username"),
        name: "",
        price: "0.0",
        description: "",
        listingDate: new Date().toISOString().split("T")[0],
        imgPath: "",
    };

    const [product, setProduct] = useState(initialProduct);
    const [errorMessage, setErrorMessage] = useState<string | undefined>(undefined);
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
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
      product.price = tempPrice;
      product.listingDate = tempListingDate;
      
      const isSafe = await checkForModeration(product.description);
      if (!isSafe) {
          setErrorMessage("The description contains inappropriate content.");
          return;
      }


      const priceDouble = parseFloat(product.price);
      if (isNaN(priceDouble)) {
        setErrorMessage("Not a valid price!");
        return;
      }

      if (priceDouble == 0.0) {
        setErrorMessage("PRICE CANNOT BE 0!");
        return;
      }

      console.log(product.name);
      console.log(product.description);
      console.log(product.price);
      console.log(product.listingDate);
      console.log(product.imgPath)

      try {
      handleAddProduct(product, showError);
      navigate("/products");
      } catch {}

    };

    const [imagePreviewUrl, setImagePreviewUrl] = useState<string | undefined>(undefined);

    const handleImageChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        e.preventDefault();

        const reader = new FileReader();
        const file = e.target.files![0];
        let path = "/assets/" + file.name;
        product.imgPath = path;

        reader.onloadend = () => {
            setImagePreviewUrl(path);
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

