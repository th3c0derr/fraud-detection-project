import { Chip } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";

const getRiskColor = (risk) => {
    switch (risk) {
        case "HIGH":
            return "error";

        case "MEDIUM":
            return "warning";

        default:
            return "success";
    }
};

export default function TransactionTable({ rows }) {

    const columns = [
        {
            field: "id",
            headerName: "ID",
            flex: 1
        },
        {
            field: "amount",
            headerName: "Amount",
            flex: 1
        },
        {
            field: "country",
            headerName: "Country",
            flex: 1
        },
        {
            field: "riskLevel",
            headerName: "Risk",
            flex: 1,
            renderCell: (params) => (
                <Chip
                    label={params.value}
                    color={getRiskColor(params.value)}
                />
            )
        },
        {
            field: "status",
            headerName: "Status",
            flex: 1
        }
    ];

    return (
        <div style={{ height: 500, width: "100%" }}>
            <DataGrid
                rows={rows}
                columns={columns}
                getRowId={(row) => row.id}
                pageSizeOptions={[5, 10]}
            />
        </div>
    );
}
