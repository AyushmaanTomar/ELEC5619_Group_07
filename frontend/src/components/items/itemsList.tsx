import * as React from 'react';
import Grid from '@mui/material/Unstable_Grid2';
import ListCard from './listCard';

interface ItemsListProps {
    columns: number
}

export default function itemsList({columns}: ItemsListProps) {
    let cols = 12/columns; 
    
    return (
        <Grid container rowSpacing={3} columnSpacing={{ xs: 3, sm: 3, md: 3 }}>
            <Grid xs={cols}>
                <ListCard 
                name={"Lizard"}
                price={5}
                imgurl={"a/b/c.png"}
                />
            </Grid>
            <Grid xs={cols}>
                <ListCard 
                    name={"Lizard"}
                    price={5}
                    imgurl={"a/b/c.png"}
                    />
                </Grid>
            <Grid xs={cols}>
                <ListCard 
                    name={"Lizard"}
                    price={5}
                    imgurl={"a/b/c.png"}
                    />
            </Grid>
            <Grid xs={cols}>
                <ListCard 
                    name={"Lizard"}
                    price={5}
                    imgurl={"a/b/c.png"}
                    />
            </Grid>
            <Grid xs={cols}>
                <ListCard 
                    name={"Lizard"}
                    price={5}
                    imgurl={"a/b/c.png"}
                    />
            </Grid>
        </Grid>
    );
}