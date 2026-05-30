import axios from "axios";
import api from "./client"

export const getTransactions = async () => {
    const response = await api.get("/transactions");
    return response.data;
};