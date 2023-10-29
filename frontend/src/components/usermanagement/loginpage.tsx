import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { Button, Stack, TextField, Typography, styled } from '@mui/material';
import { useNavigate, NavLink } from 'react-router-dom';
import { useAuth } from '../usermanagement/AuthProvider';
import { useError } from "src/errorContext";
import ReCAPTCHA from "react-google-recaptcha";
import './LoginPage.css';

export default function LoginAccount() {
    const navigate = useNavigate();
    const {login} = useAuth();
    const [formError, setFormError] = useState("");
    const [captchaValue, setCaptchaValue] = useState<string | null>(null);
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const { showError } = useError();

    const StyledTextField = styled(TextField)({
        "& input": {
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

    const handleCaptchaChange = (value: string | null) => {
        setCaptchaValue(value);
    }

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        setFormError("");
        event.preventDefault();

        // Check if reCAPTCHA is completed
        if (!captchaValue) {
            setFormError("Please verify that you are not a robot.");
            return;
        }

        const data = new FormData(event.currentTarget);
        var userName = data.get("userName")?.toString();
        var password = data.get("password")?.toString();

        if (userName == null || password == null) {
            setFormError("Could not load data. Try again.");
            return;
        }

        try {
            await login(userName, password);
            navigate("/");
        } catch (error) {
            if ((error as any).response && (error as any).response.data === "Invalid credentials") {
            console.error("Login error:", error);
                throw new Error("username or password is incorrect");
            } else {
                setFormError("username or password is incorrect");
            }
        }
    };


    return (
        <React.Fragment>

            <Container maxWidth="sm">

                <Typography paddingTop="50px" paddingBottom="15px" align='center' variant='h4' color={'common.black'}>Login
                    account</Typography>

                <Box className="bg-secondary"
                     sx={{height: "auto", border: '1px solid #21262d', borderRadius: '20px', padding: "25px"}}>

                    <Stack component="form" onSubmit={handleSubmit} spacing={2} paddingBottom="25px" paddingX="px">
                        <ReCAPTCHA className="recaptcha-container"
                            sitekey="6Lcs59coAAAAAMDbxh2K3Y7-oRiE8IosSxipYvWG"
                            onChange={handleCaptchaChange}
                        />
                        <StyledTextField required id="userName" label="UserName" name="userName" autoFocus/>
                        <StyledTextField required id="password" label="Password" name='password' type='password'/>
                        <Button type="submit" variant="contained">Login Account</Button>
                    </Stack>
                    <NavLink to='/forgotPassword' style={{color:'white', marginLeft:'370px'}}>Forgot Password ?</NavLink>
                    <Typography color="red" align='center'>{formError}</Typography>
                </Box>
            </Container>
        </React.Fragment>
    );
}