import { useEffect, useState } from 'react';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Button from '../components/Button';
import axios from "axios";


import Input from '../components/Input/input';
import userService from '../services/userService';
import './EventForm.css';

const EventForm = () => {
    const [values, setValues] = useState({
        name: '',
        institutionId: '',
    });

    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [institutions, setInstitutions] = useState([]);
    const [errors, setErrors] = useState({});

    useEffect(() => {
        // Função para buscar todas as instituições
        const fetchInstitutions = async () => {
            try {
                const data = await userService.FindAllInstitutions();
                setInstitutions(data);
            } catch (error) {
                console.error('Erro ao buscar instituições', error);
            }
        };

        fetchInstitutions();
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setValues({
            ...values,
            [name]: value,
        });
    };


    const validate = () => {
        const newErrors = {};

        if (!values.name) newErrors.name = '*Nome do evento é obrigatório';
        if (!values.institutionId) newErrors.institutionId = '*Instituição é obrigatória';
        if (!startDate) newErrors.startDate = '*Data inicial é obrigatória';
        if (!endDate) newErrors.endDate = '*Data final é obrigatória';

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = async () => {
        if (!validate()) return;

        const eventData = {
            name: values.name,
            startDate: startDate ? startDate.toISOString().split('T')[0] : null,
            endDate: endDate ? endDate.toISOString().split('T')[0] : null,
        }

        try {
            const result = await axios.post('http://localhost:8080/events/createEvent', eventData);
            alert('Evento criado com sucesso.');
        } catch (error) {
            alert('Erro ao cadastrar evento, verifique as datas.');
            console.error('Erro ao cadastrar evento', error);
        }
    };

    const handleStartDateChange = (date) => {
        setStartDate(date);

        if (endDate && date > endDate) {
            alert('Data inválida');
            setEndDate(null);
        }
    };

    const handleEndDateChange = (date) => {
        setEndDate(date);

        if (startDate && date < startDate) {
            alert('Data inválida');
            setStartDate(null);
        }
    };

    return (
        <div className="container">
            <div className="form-container-cadastro">
                <h1>Criar evento</h1>
                <p>Preencha os campos abaixo</p>

                <div className="input-container">
                    <label htmlFor="name" style={{ marginLeft: '50px' }}>Nome do Evento</label>
                    {errors.name && <p className="error-text-1">{errors.name}</p>}
                    <Input
                        onChange={handleInputChange}
                        id="name"
                        className="input"
                        type="text"
                        placeholder="Nome do Evento"
                        name="name"
                        value={values.name}
                    />
                </div>

                <div className="input-container">
                    <label htmlFor="institutionId">Instituição</label>
                    {errors.institutionId && <p className="error-text">{errors.institutionId}</p>}
                    <select
                        id="institutionId"
                        name="institutionId"
                        className="input"
                        value={values.institutionId}
                        onChange={handleInputChange}
                    >
                        <option value="">Selecione uma instituição</option>
                        {institutions.map((institution) => (
                            <option key={institution.id} value={institution.id}>
                                {institution.name}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="input-container datepicker-container-1">
                    <label htmlFor="startDate">Data inicial</label>
                    {errors.startDate && <p className="error-text">{errors.startDate}</p>}
                    <div className="custom-datepicker">
                        <DatePicker
                            selected={startDate}
                            onChange={handleStartDateChange}
                            placeholderText="Data inicial"
                            dateFormat="dd/MM/yyyy"
                            customInput={
                                <Input
                                    id="startDate"
                                    className="input-datepicker"
                                    type="text"
                                    placeholder="Data inicial"
                                    name="startDate"
                                    value={startDate ? startDate.toLocaleDateString('pt-BR') : ''}
                                    onChange={() => { }}
                                    readOnly
                                />
                            }
                        />
                    </div>
                </div>

                <div className="input-container datepicker-container-1">
                    <label htmlFor="endDate">Data final</label>
                    {errors.endDate && <p className="error-text">{errors.endDate}</p>}
                    <div className="custom-datepicker">
                        <DatePicker
                            selected={endDate}
                            onChange={handleEndDateChange}
                            placeholderText="Data final"
                            dateFormat="dd/MM/yyyy"
                            customInput={
                                <Input
                                    id="endDate"
                                    className="input-datepicker"
                                    type="text"
                                    placeholder="Data final"
                                    name="endDate"
                                    value={endDate ? endDate.toLocaleDateString('pt-BR') : ''}
                                    onChange={() => { }}
                                    readOnly
                                />
                            }
                        />
                    </div>
                </div>
                <Button label="Salvar evento" onClick={handleSubmit} />
            </div>
        </div>
    );
};

export default EventForm;