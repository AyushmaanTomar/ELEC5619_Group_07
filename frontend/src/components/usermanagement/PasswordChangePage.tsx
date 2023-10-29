import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { Button, Stack, TextField, Typography, styled } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../usermanagement/AuthProvider';

export default function ChangePassword() {
    const navigate = useNavigate();
    const { changePassword } = useAuth(); // Assuming you have a changePassword function in useAuth
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

        const data = new FormData(event.currentTarget);
        var currentPwd = data.get("currentPassword")?.toString();
        var newPwd = data.get("newPassword")?.toString();
        var reEnteredPwd = data.get("reEnteredPassword")?.toString();

        if (currentPwd == null || newPwd == null || reEnteredPwd == null) {
            setFormError("Could not load data. Try again.");
            return;
        }

        if (newPwd !== reEnteredPwd) {
            setFormError("New passwords do not match.");
            return;
        }

        try {
            await changePassword(currentPwd, newPwd);
            navigate("/"); // Navigate to a suitable path after password change, maybe a profile page or home
        } catch {
            // Handle error appropriately
            setFormError("Failed to change password. Try again.");
        }
    }


    return (
        <React.Fragment>
            <Container maxWidth="sm">
                <Typography paddingTop="50px" paddingBottom="15px" align='center' variant='h4' color={'common.black'}>Change Password</Typography>
                <Box className="bg-secondary"
                     sx={{height: "auto", border: '1px solid #21262d', borderRadius: '20px', padding: "25px"}}>
                    <Stack component="form" onSubmit={handleSubmit} spacing={2} paddingBottom="25px" paddingX="px">
                        <StyledTextField required id="currentPassword" label="Current Password" name="currentPassword" type="password" autoFocus/>
                        <StyledTextField required id="newPassword" label="New Password" name="newPassword" type="password"/>
                        <StyledTextField required id="reEnteredPassword" label="Re-enter New Password" name="reEnteredPassword" type="password"/>
                        <Button type="submit" variant="contained">Change Password</Button>
                    </Stack>
                    <Typography color="red" align='center'>{formError}</Typography>
                </Box>
            </Container>
        </React.Fragment>
    );
}
