import { Typography } from "@mui/material";

export default function adminPage() {

    return (
        <>
        <Typography variant="h2" className="pb-8"> Admin Panel </Typography>

        <div id="usersList">
            <Typography variant="h4"> Users List </Typography>
        </div>

        <div id="itemsList">
            <Typography variant="h4"> Items List </Typography>
        </div>
        </>
    );
}