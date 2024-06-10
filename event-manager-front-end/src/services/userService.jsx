import React from "react";
import axios from "axios";


const FindAllInstitutions = async (eventData) => {
    try {
        const response = await axios.get(
            `http://localhost:8080/events/findAllInstitutions`, eventData
        );
        if (response.status == 200) {
            return response.data
        }
    } catch (error) {
        throw error;
        console.error('Erro ao criar evento:', error);
    }
};

export default {FindAllInstitutions};