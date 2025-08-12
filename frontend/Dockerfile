# Estágio de build
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .

# Argumento para receber a URL da API
ARG NG_APP_API_URL

# Substitui o placeholder no arquivo de ambiente pelo valor real da URL
RUN sed -i "s|__NG_APP_API_URL__|${NG_APP_API_URL}|g" src/environments/environment.prod.ts

# Constrói a aplicação
RUN npm run build -- --configuration production

# Estágio de produção
FROM nginx:alpine
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80