const PORT = 8081;
export const BASE_ROUTE = `http://localhost:${PORT}`;

export const AxiosAuthConfig = (token: string) => ({
  headers: { Authorization: `Bearer ${token}` }
});
