import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { Button, Stack, TextField, Typography, styled } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function ForgotPassword() {
    const navigate = useNavigate();
    const [formError, setFormError] = useState<string>("");
    const [formMessage, setFormMessage] = useState<string>("");
    const [email, setEmail] = useState<string>("");

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


    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        const data = new FormData(event.currentTarget);
        var enteredEmail = data.get("email")?.toString();

        if (!enteredEmail) {
            setFormError("Please enter a valid email.");
            setFormMessage(""); // Clear out formMessage
            return;
        }

        try {
            const response = await fetch('/users/checkEmail', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email: enteredEmail })
            });

            const result = await response.json();

            if (response.status === 200 && result.exists) {
                setFormMessage("Sent email for resetting password.");
                setFormError(""); // Clear out formError
            } else if (response.status === 200 && !result.exists) {
                setFormError("Email does not exist.");
                setFormMessage(""); // Clear out formMessage
            } else {
                setFormError(result.error || "There was an error checking the email. Please try again later.");
                setFormMessage(""); // Clear out formMessage
            }
        } catch (error) {
            setFormError("There was an error checking the email. Please try again later.");
            setFormMessage(""); // Clear out formMessage
        }

    }

    return (
        <React.Fragment>
            <Container maxWidth="sm">
                <Typography paddingTop="50px" paddingBottom="15px" align='center' variant='h4' color={'common.black'}>Forgot Password</Typography>
                <Box className="bg-secondary"
                     sx={{height: "auto", border: '1px solid #21262d', borderRadius: '20px', padding: "25px"}}>
                    <Stack component="form" onSubmit={handleSubmit} spacing={2} paddingBottom="25px" paddingX="px">
                        <StyledTextField required id="email" label="Email" name="email" autoFocus/>
                        <Button type="submit" variant="contained">Submit</Button>
                    </Stack>
                    <Typography color="red" align='center'>{formError}</Typography>
                    <Typography color="green" align='center'>{formMessage}</Typography>
                </Box>
            </Container>
        </React.Fragment>
    );
}
