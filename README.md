### This is a project accompanying Nginx Crash Course

### Commands used in the tutorial

#### Run with Composer
`docker compose -f docker-compose.yaml up -d`
##### start nginx
`nginx`

##### get options 
`nginx -h`

##### restart nginx
`nginx -s reload`

##### stop nginx
`nginx -s stop`  

##### print logs
`tail -f /usr/local/var/log/nginx/access.log`

##### restart nginx
`nginx -s reload`

##### create folder for nginx certificates
`mkdir ~/nginx-certs`
`cd ~/nginx-certs`

##### create self-signed certificate
`openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout nginx-selfsigned.key -out nginx-selfsigned.crt`


cat /etc/nginx/nginx.conf

code /etc/nginx/nginx.conf



## Create Custome SSL Certificate
- mkdir nginx-certs
- cd nginx-certs
- openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout nginx-selfsigned.key -out nginx-selfsigned.crt
 - will be creae 2 file 
    - nginx-selfsigned.crt  nginx-selfsigned.key
    - private key: nginx-selfsigned.key
- add listern port: 443 ssl;
- ssl_certificate .crt file location // as public key
- ssl_certificate_key .key file location // as private key
- nginx -s reload

