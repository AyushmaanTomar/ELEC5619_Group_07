import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { Button, Stack, TextField, Typography, styled } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../usermanagement/AuthProvider';
import axios from 'axios';


export default function LoginAccount() {
    const navigate = useNavigate();
    const { login } = useAuth();
    const [formError, setFormError] = useState("");

    const StyledTextField = styled(TextField) ({
        "& input": {
            color: "gray"
        },
        "& label" : {
            color: "white"
        },
        "& label.Mui-focused": {
            color: "gray"
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

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        setFormError("");
        event.preventDefault();

        const data = new FormData(event.currentTarget);
        var username = data.get("username")?.toString();
        var password = data.get("password")?.toString();

        if (username == null || password == null) {
            setFormError("Could not load data. Try again.");
            return;
        }

        // Convert FormData to JSON
        const data_json = JSON.stringify(Object.fromEntries(data.entries()));

        try {
            const response = await axios.post('http://localhost:8080/login', data_json, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            console.log(response);
            if (response.status === 200) {
                navigate("/");
            } else {
                setFormError(response.data);
            }
        } catch (error) {
            console.error("Error during login:", error);

            if (axios.isAxiosError(error)) {
                if (error.response) {
                    setFormError(error.response.data);
                } else if (error.request) {
                    setFormError("No response from server. Please try again later.");
                } else {
                    setFormError("Error in sending request.");
                }
            } else {
                setFormError("An unknown error occurred.");
            }
        }

    }

    return (
    <React.Fragment>

        <Container maxWidth="sm">

        <Typography paddingTop="50px" paddingBottom="15px" align='center' variant='h4' color={'common.black'}>Register new account</Typography>

        <Box className="bg-secondary" sx={{height: "auto", border: '1px solid #21262d', borderRadius: '20px', padding: "25px"}}>

            <Stack component="form" onSubmit={handleSubmit} spacing={2} paddingBottom="25px" paddingX="px">
                <StyledTextField required id="username" label="Username" name="username" autoFocus />
                <StyledTextField required id="password" label="Password" name='password' type='password'/>
                <Button type="submit" variant="contained">Login Account</Button>
            </Stack>
            <Typography color="red" align='center'>{formError}</Typography>
        </Box>
      </Container>
    </React.Fragment>
  );
}