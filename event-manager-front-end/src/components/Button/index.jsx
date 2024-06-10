import React from 'react'
import "./styles.css"

const Button = ({ label, onClick }) => {
    return (
        <div className="container-button">
            <button
                type="button"
                className="btn"
                onClick={onClick}
            >
                {label}
            </button>
        </div>
    );
}

export default Button