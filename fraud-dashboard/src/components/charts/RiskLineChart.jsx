import { CartesianGrid, Line, LineChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from "recharts";

export default function RiskLineChart({ data }) {
    return (

        <LineChart width={600} height={300} data={data}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="time" />
            <YAxis domain={[0, 100]} />
            <Tooltip />
            <Line
                type="monotone"
                dataKey="risk"
                stroke="#f44336"
                strokeWidth={2}
                dot={{ r: 3 }}
            />

        </LineChart>

    )
}