
import DeleteIcon from '@mui/icons-material/Delete';
import { Alert, Button, Collapse, Container, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Stack, Typography } from '@mui/material';
import { useState } from 'react';

const delay = (ms: number) => new Promise(res => setTimeout(res, ms));

export default function adminUserCard(props: {username: string, reports: number}) {
    const [deleteAlertOpen, setDeleteAlertOpen] = useState(false);
    const [alertMessage, setAlertMessage] = useState(false);

    const handleIconClick = () => { setDeleteAlertOpen(true);}
    const handleIconClose = () => { setDeleteAlertOpen(false);}
    const handleOpenSuccess = async () => {
        setAlertMessage(true);
        await delay(2000);
        setAlertMessage(false);
    }

    const deleteUser = () => {
        // TODO: Should delete user record then update page.
        console.log("User Deleted");
        handleIconClose();
        handleOpenSuccess();
    }

    return (
        <>
            <Collapse in={alertMessage}>
            <Alert severity='success'> User ({props.username}) successfully removed </Alert>
            </Collapse>
            <Dialog 
            open={deleteAlertOpen}
            onClose={handleIconClose}>
                <DialogTitle> Are you sure you want to delete this user? </DialogTitle>
            <DialogContent><DialogContentText>
            Deleting the user ({props.username}) will permanently remove them from the application.
            All relevant data will also be removed. This process is non-reversable!    
            </DialogContentText></DialogContent>
            <DialogActions>
                <Button onClick={handleIconClose} autoFocus> Cancel </Button>
                <Button onClick={deleteUser}> Delete </Button>
            </DialogActions>
            </Dialog>

            <Container>
                <Stack direction="row" spacing="25px">
                <Typography>
                    {props.username} - reported {props.reports}
                </Typography>
                
                <div onClick={handleIconClick}><DeleteIcon /></div>
                </Stack>
            </Container>
        </>
    );
}