import axios from "axios";
const CLIENT_API_BASE_URL = "http://localhost:8080/manage-client/clients";
class ClientService {
  getClients() {
    return axios.get(CLIENT_API_BASE_URL);
  }

  createClient(client) {
    return axios.post(CLIENT_API_BASE_URL, client);
  }

  getClientById(clientId) {
    return axios.get(CLIENT_API_BASE_URL + '/' + clientId);
  }

  updateClient(client, clientId) {
    return axios.put(CLIENT_API_BASE_URL + '/' + clientId, client);
  }

  deleteClient(clientId) {
    return axios.delete(CLIENT_API_BASE_URL + '/' + clientId);
  }
}
export default new ClientService();
