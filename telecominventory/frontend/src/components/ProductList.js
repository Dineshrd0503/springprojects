import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import ProductService from '../services/ProductService';

const ProductList = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        loadProducts();
    }, []);

    const loadProducts = () => {
        ProductService.getAllProducts()
            .then(response => {
                setProducts(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the products!", error);
            });
    };

    const handleDelete = (id) => {
        ProductService.deleteProduct(id)
            .then(() => {
                // Refresh the list after deleting
                loadProducts();
            })
            .catch(error => {
                console.error("There was an error deleting the product!", error);
            });
    };

    return (
        <div className="container mt-4">
            <div className="d-flex justify-content-between align-items-center mb-3">
                <h2>Product Inventory</h2>
                <Link to="/add" className="btn btn-primary">Add Product</Link>
            </div>
            <table className="table table-striped table-bordered">
                <thead className="thead-dark">
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Quantity</th>
                        <th>Location</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map(product => (
                        <tr key={product.id}>
                            <td>{product.name}</td>
                            <td>{product.type}</td>
                            <td>{product.quantity}</td>
                            <td>{product.location}</td>
                            <td>{product.status}</td>
                            <td>
                                <Link to={`/edit/${product.id}`} className="btn btn-sm btn-outline-primary me-2">Edit</Link>
                                <button onClick={() => handleDelete(product.id)} className="btn btn-sm btn-outline-danger">Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ProductList;