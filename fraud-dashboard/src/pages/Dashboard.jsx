import { Grid, Typography } from "@mui/material";
import KpiCard from "../components/KpiCard";
import TransactionTable from "../components/TransactionTable";
import { useEffect, useState } from "react";
import { getTransactions } from "../api/transactions";
import RiskPieChart from "../components/charts/RiskPieChart";
import RiskLineChart from "../components/charts/RiskLineChart";

export default function Dashboard() {

    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        loadTransactions();
    }, []);

    const loadTransactions = async () => {
        try {

            const data = await getTransactions();
            console.log("TRANSACTIONS: ", data);
            setTransactions(data);
        } catch (error) {
            console.error("Erros loading transactions: ", error);
        }
    };

    const formatRiskTimeSeries = (transactions) => {
        return [...transactions]
            .filter(t => t.timestamp && t.riskScore !== undefined)
            .sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp))
            .map(t => ({
                time: new Date(t.timestamp).toLocaleTimeString(),
                risk: t.riskScore
            }));
    };



    const timeSeriesData = formatRiskTimeSeries(transactions);

    console.log("transactions", transactions);
    console.log("timeSeriesData", timeSeriesData);

    return (
        <>
            <Typography variant="h4" sx={{ mb: 4, fontWeight: "bold" }}>
                Fraud Detection Dashboard
            </Typography>

            <Grid container spacing={3} sx={{ mb: 4 }}>
                <Grid item xs={12} md={4}>
                    <KpiCard
                        title="Total Transactions"
                        value={transactions.length}
                    />
                </Grid>

                <Grid item xs={12} md={4}>
                    <KpiCard
                        title="High Risk Alerts"
                        value={
                            transactions.filter(
                                t => t.riskLevel === "HIGH"
                            ).length
                        }
                    />
                </Grid>

                <Grid item xs={12} md={4}>
                    <KpiCard
                        title="Rejected Transactions"
                        value={
                            transactions.filter(
                                t => t.status === "REJECTED"
                            ).length
                        }
                    />
                </Grid>
            </Grid>

            <Grid container spacing={3} sx={{ mb: 4 }}>
                <Grid item xs={12} md={6}>
                    <RiskPieChart transactions={transactions} />
                </Grid>

                <Grid item xs={12} md={6}>
                    <RiskLineChart data={timeSeriesData} />
                </Grid>
            </Grid>


            <TransactionTable rows={transactions} />
        </>
    );
}