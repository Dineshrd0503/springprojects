import axios from 'axios';

// The base URL of your Spring Boot application
const API_BASE_URL = "http://localhost:8080/api";

class ProductService {

    getAllProducts() {
        // Note the endpoint is /allProducts as per your controller
        return axios.get(API_BASE_URL + "/allProducts");
    }

    getProductById(productId) {
        return axios.get(API_BASE_URL + "/" + productId);
    }

    createProduct(product) {
        return axios.post(API_BASE_URL, product);
    }

    updateProduct(productId, product) {
        return axios.put(API_BASE_URL + "/" + productId, product);
    }

    deleteProduct(productId) {
        return axios.delete(API_BASE_URL + "/" + productId);
    }
}

// Export an instance of the class
export default new ProductService();