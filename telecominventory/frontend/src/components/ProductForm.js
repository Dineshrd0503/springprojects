import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ProductService from '../services/ProductService';

const ProductForm = () => {
    const [product, setProduct] = useState({ name: '', type: '', quantity: 0, location: '', status: '' });
    const navigate = useNavigate();
    const { id } = useParams(); // Gets the 'id' from the URL, if it exists

    useEffect(() => {
        if (id) {
            // If an ID is present, we are editing, so fetch the product data
            ProductService.getProductById(id)
                .then(response => {
                    setProduct(response.data);
                })
                .catch(error => {
                    console.error("Error fetching product data!", error);
                });
        }
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProduct(prevState => ({ ...prevState, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (id) {
            // If ID exists, update the product
            ProductService.updateProduct(id, product)
                .then(() => navigate('/'))
                .catch(error => console.error("Error updating product!", error));
        } else {
            // Otherwise, create a new product
            ProductService.createProduct(product)
                .then(() => navigate('/'))
                .catch(error => console.error("Error creating product!", error));
        }
    };

    return (
        <div className="container mt-4">
            <h2>{id ? 'Edit Product' : 'Add New Product'}</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Name</label>
                    <input type="text" className="form-control" id="name" name="name" value={product.name} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label htmlFor="type" className="form-label">Type</label>
                    <input type="text" className="form-control" id="type" name="type" value={product.type} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label htmlFor="quantity" className="form-label">Quantity</label>
                    <input type="number" className="form-control" id="quantity" name="quantity" value={product.quantity} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label htmlFor="location" className="form-label">Location</label>
                    <input type="text" className="form-control" id="location" name="location" value={product.location} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label htmlFor="status" className="form-label">Status</label>
                    <input type="text" className="form-control" id="status" name="status" value={product.status} onChange={handleChange} required />
                </div>
                <button type="submit" className="btn btn-success me-2">Save</button>
                <button type="button" className="btn btn-secondary" onClick={() => navigate('/')}>Cancel</button>
            </form>
        </div>
    );
};

export default ProductForm;