import axios from "axios";
const PROPERTY_API_BASE_URL = "http://localhost:8080/manage-properties/properties";
class PropertyService {
  getProperties() {
    return axios.get(PROPERTY_API_BASE_URL);
  }
  createProperty(property) {
    return axios.post(PROPERTY_API_BASE_URL, property);
  }
  getPropertyById(propertyId) {
    return axios.get(PROPERTY_API_BASE_URL + "/" + propertyId);
  }
  updateProperty(property, propertyId) {
    return axios.put(PROPERTY_API_BASE_URL + "/" + propertyId, property);
  }
  deleteProperty(propertyId) {
    return axios.delete(PROPERTY_API_BASE_URL + "/" + propertyId);
  }
}
export default new PropertyService();
