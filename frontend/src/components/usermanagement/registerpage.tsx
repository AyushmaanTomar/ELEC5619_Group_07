import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { Button, Stack, TextField, Typography, styled } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../usermanagement/AuthProvider';

export default function RegisterAccount() {
    const navigate = useNavigate();
    const { login, register } = useAuth();
    const [formError, setFormError] = useState("");

    const StyledTextField = styled(TextField) ({
        "& input": {
            color: "white"
        },
        "& label" : {
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

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        setFormError("");
        event.preventDefault();

        const data = new FormData(event.currentTarget);
        var name = data.get("name")?.toString();
        var email = data.get("email")?.toString();
        var phone = data.get("phone")?.toString();
        var password = data.get("password")?.toString();
        var cpassword = data.get("cpassword")?.toString();
        
        if (name == null || email == null || password == null || cpassword == null || phone == null) {
            setFormError("Could not load data. Try again.");
            return;
        }
        if (password != cpassword) {
            setFormError("Password and Confirm Password do not match!");
            return;
        }
    
        register({name, email, password, phone});
        var result: boolean = login(name, password);
        if (!result) {
            setFormError("Account registered but failed to log in!");
            return;
        }
        navigate("/");
    }

    return (
    <React.Fragment>

        <Container maxWidth="sm">

        <Typography paddingTop="50px" paddingBottom="15px" align='center' variant='h4' color={'common.black'}>Register new account</Typography>

        <Box className="bg-secondary" sx={{height: "auto", border: '1px solid #21262d', borderRadius: '20px', padding: "25px"}}>

            <Stack component="form" onSubmit={handleSubmit} spacing={2} paddingBottom="25px" paddingX="px">
                <StyledTextField required id="name" label="name" name="name" autoFocus />
                <StyledTextField required id="email" label="Email" name="email" type="email"  autoComplete="email" />
                <StyledTextField required id="phone" label="Phone" name="phone" type="tel" />
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