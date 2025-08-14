FROM node:20-alpine AS build

WORKDIR /app
COPY frontend/package*.json ./
COPY frontend/package-lock.json ./
RUN npm ci
COPY frontend/ ./


COPY . .

RUN npm run build -- --configuration production

FROM nginx:alpine

COPY --from=build /app/dist/frontend /usr/share/nginx/html
COPY --from=build /app/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
