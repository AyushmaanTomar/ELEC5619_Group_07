import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';

interface ItemCardProps {
    name: string
    price: number
    imgurl: string
}

export default function ListCard({name, price, imgurl}: ItemCardProps) {
    return (
        <Card sx={{ maxWidth: 345 }}>
        <CardActionArea>
            <CardMedia
            component="img"
            height="140"
            image={imgurl}
            />
            <CardContent>
            <Typography gutterBottom variant="h5" component="div">
                {name} - ${price}
            </Typography>
            </CardContent>
        </CardActionArea>
        </Card>
    );
}
