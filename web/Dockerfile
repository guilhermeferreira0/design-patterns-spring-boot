FROM node:20-alpine

WORKDIR /app/web

COPY ./web/package*.json /app/web/

RUN npm install

COPY ./web /app/web

EXPOSE 5173

CMD [ "npm", "run", "dev" ]