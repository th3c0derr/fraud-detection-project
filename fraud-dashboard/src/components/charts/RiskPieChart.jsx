import { Cell, Legend, Pie, PieChart, Tooltip } from "recharts";

const colors = {
    LOW: "#4caf50",
    MEDIUM: "#ff9800",
    HIGH: "#f44336",
};

export default function RiskPieChart({ transactions }) {

    const data = [
        {
            name: "LOW",
            value: transactions.filter(t => t.riskLevel === "LOW").length,
        },
        {
            name: "MEDIUM",
            value: transactions.filter(t => t.riskLevel === "MEDIUM").length,
        },
        {
            name: "HIGH",
            value: transactions.filter(t => t.riskLevel === "HIGH").length,
        },
    ];

    return (
        <PieChart width={400} height={300}>
            <Pie
                data={data}
                dataKey="value"
                nameKey="name"
                outerRadius={120}
                label
            >
                {data.map((entry, index) => (
                    <Cell key={index} fill={colors[entry.name]} />
                ))}
            </Pie>

            <Tooltip/>
            <Legend />
        </PieChart>
    )
}