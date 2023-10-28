import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { Button, Stack, TextField, Typography, styled } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../usermanagement/AuthProvider';
import axios from 'axios';
import ReCAPTCHA from "react-google-recaptcha";
import './LoginPage.css';

export default function LoginAccount() {
    const navigate = useNavigate();
    const { login } = useAuth();
    const [formError, setFormError] = useState("");
    const [captchaValue, setCaptchaValue] = useState<string | null>(null);
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");

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

        if (username == null || password == null) {
            setFormError("Could not load data. Try again.");
            return;
        }

        // Convert FormData to JSON
        const requestData = {
            username: username,
            password: password
        };

        const data_json = JSON.stringify(requestData);

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
                        <StyledTextField
                            required
                            id="username"
                            label="Username"
                            name="username"
                            autoFocus
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                        <StyledTextField
                            required
                            id="password"
                            label="Password"
                            name='password'
                            type='password'
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                            <ReCAPTCHA
                                sitekey="6Lcs59coAAAAAMDbxh2K3Y7-oRiE8IosSxipYvWG"
                                onChange={handleCaptchaChange}
                            />
                        </div>
                        <Button type="submit" variant="contained">Login Account</Button>
                    </Stack>

                    <Typography color="red" align='center'>{formError}</Typography>
                </Box>
            </Container>

        </React.Fragment>
    );
}