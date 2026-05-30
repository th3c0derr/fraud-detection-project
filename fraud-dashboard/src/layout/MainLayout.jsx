import { Container } from "@mui/material";

export default function MainLayout({ children }) {
    return (
        <Container maxWidth="xl" sx={{ mt: 4}}>
            {children}
        </Container>
    );
}