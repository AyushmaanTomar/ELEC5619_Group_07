import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { Button, Grid, Link, Stack, TextField, Typography, colors, styled } from '@mui/material';
import { text } from 'stream/consumers';
import { alignProperty } from '@mui/material/styles/cssUtils';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../usermanagement/AuthProvider';

export default function RegisterAccount() {
    const navigate = useNavigate();
    const { loggedIn, login, logout, register } = useAuth();
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

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        setFormError("");
        event.preventDefault();

        const data = new FormData(event.currentTarget);
        var username = data.get("username")?.toString();
        var email = data.get("email")?.toString();
        var password = data.get("password")?.toString();
        var cpassword = data.get("cpassword")?.toString();
        
        if (username == null || email == null || password == null || cpassword == null) {
            setFormError("Could not load data. Try again.");
            return;
        }
        if (password != cpassword) {
            setFormError("Password and Confirm Password do not match!");
            return;
        }
        console.log(username);
        console.log(loggedIn);
        register({username, email, password});
        var a = login(username, password);
        console.log("FUNC", a);
        console.log(loggedIn);
        navigate("/login");
    }

    return (
    <React.Fragment>

        <Container maxWidth="sm">

        <Typography paddingTop="50px" paddingBottom="15px" align='center' variant='h4' color={'common.black'}>Register new account</Typography>

        <Box className="bg-secondary" sx={{height: "auto", border: '1px solid #21262d', borderRadius: '20px', padding: "25px"}}>

            <Stack component="form" onSubmit={handleSubmit} spacing={2} paddingBottom="25px" paddingX="px">
                <StyledTextField required id="username" label="Username" name="username" autoFocus />
                <StyledTextField required id="email" label="Email" name="email" type="email"  autoComplete="email" />
                <StyledTextField required id="password" label="Password" name='password' type='password'/>
                <StyledTextField required id="cpassword" label="Confirm Password" name='cpassword' type='password'/>
                <Button type="submit" variant="contained">Register Account</Button>
            </Stack>
            <Typography color="red" align='center'>{formError}</Typography>
        </Box>
      </Container>
    </React.Fragment>
  );
}