import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { Button, Stack, TextField, Typography, styled } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../usermanagement/AuthProvider';
import { useError } from 'src/errorContext';

export default function LoginAccount() {
    const navigate = useNavigate();
    const { login } = useAuth();
    const [formError, setFormError] = useState("");
    const {showError} = useError();

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

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        setFormError("");
        event.preventDefault();

        const data = new FormData(event.currentTarget);
        var userName = data.get("userName")?.toString();
        var password = data.get("password")?.toString();
        
        if (userName == null || password == null ) {
            setFormError("Could not load data. Try again.");
            return;
        }
    
        try {
          await login(userName, password);
          navigate("/");
        } catch {}
    }

    return (
    <React.Fragment>

        <Container maxWidth="sm">

        <Typography paddingTop="50px" paddingBottom="15px" align='center' variant='h4' color={'common.black'}>Login account</Typography>

        <Box className="bg-secondary" sx={{height: "auto", border: '1px solid #21262d', borderRadius: '20px', padding: "25px"}}>

            <Stack component="form" onSubmit={handleSubmit} spacing={2} paddingBottom="25px" paddingX="px">
                <StyledTextField required id="userName" label="UserName" name="userName" autoFocus />
                <StyledTextField required id="password" label="Password" name='password' type='password'/>
                <Button type="submit" variant="contained">Login Account</Button>
            </Stack>
            <Typography color="red" align='center'>{formError}</Typography>
        </Box>
      </Container>
    </React.Fragment>
  );
}