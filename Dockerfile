FROM node:22.0.0-alpine

WORKDIR /app

COPY server.js .
COPY index.html .
COPY images ./images
COPY package.json .

RUN npm install

EXPOSE 4000

CMD ["node", "server.js"]
