import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { Button, Stack, TextField, Typography, styled } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../usermanagement/AuthProvider';

export default function ChangePassword() {
    const { loggedInEmail } = useAuth();
    const navigate = useNavigate();
    const [formError, setFormError] = useState<string>("");

    const [currentPassword, setCurrentPassword] = useState<string>("");
    const [reEnteredPassword, setReEnteredPassword] = useState<string>("");
    const [newPassword, setNewPassword] = useState<string>("");

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
        setFormError("");
        event.preventDefault();


        if (newPassword !== reEnteredPassword) {
            setFormError("New passwords do not match.");
            return;
        }

        if (currentPassword === newPassword) {
            setFormError("Incorrect current password.");
            return;
        }

        const requestData = {
            user: loggedInEmail,
            newPassword: newPassword,
        };

        try {
            const response = await fetch('http://localhost:8080/users/changePassword', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestData),
            });

            if (response.ok) {
                navigate("/"); 
            } else {
                throw new Error('Failed to change password');
            }
        } catch (error : any) {
            // Handle error appropriately
            setFormError(error.message);
        }
    };

    return (
        <React.Fragment>
            <Container maxWidth="sm">
                <Typography paddingTop="50px" paddingBottom="15px" align='center' variant='h4' color={'common.black'}>Change Password</Typography>
                <Box className="bg-secondary" sx={{ height: "auto", border: '1px solid #21262d', borderRadius: '20px', padding: "25px" }}>
                    <Stack component="form" onSubmit={handleSubmit} spacing={2} paddingBottom="25px" paddingX="px">
                        <StyledTextField required id="currentPassword" label="Current Password" name="currentPassword" type="password" value={currentPassword} onChange={(e) => setCurrentPassword(e.target.value)} />
                        <StyledTextField required id="newPassword" label="New Password" name="newPassword" type="password" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} />
                        <StyledTextField required id="reEnteredPassword" label="Re-enter New Password" name="reEnteredPassword" type="password" value={reEnteredPassword} onChange={(e) => setReEnteredPassword(e.target.value)} />
                        <Button type="submit" variant="contained">Change Password</Button>
                    </Stack>
                    <Typography color="red" align='center'>{formError}</Typography>
                </Box>
            </Container>
        </React.Fragment>
    );
}
