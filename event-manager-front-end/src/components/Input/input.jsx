import React from 'react';
import './styles.css';

const Input = ({ id, className, type, placeholder, name, value, onChange, required, onClick }) => {
    return (
        <div className="input-container">
            <input
                id={id}
                className={`input ${className}`}
                type={type}
                placeholder={placeholder}
                name={name}
                value={value}
                onChange={onChange}
                required={required}
                onClick={onClick}
            />
        </div>
    );
};
export default Input;