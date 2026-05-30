import { Card, CardContent, Typography } from "@mui/material";

export default function KpiCard({ title, value }) {
    return (
        <Card sx={{ borderRadius: 3 }}>
            <CardContent>
                <Typography variant="h6">
                    {title}
                </Typography>

                <Typography variant="h4" sx={{ fontWeight: "bold", mt: 2 }}>
                    {value}
                </Typography>

            </CardContent>
        </Card>
    );
}