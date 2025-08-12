# Estágio de build
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
ARG NG_APP_API_URL
ENV NG_APP_API_URL=$NG_APP_API_URL
RUN npm run build -- --configuration production

# Estágio de produção
FROM nginx:alpine
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80