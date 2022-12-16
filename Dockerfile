
FROM mariadb:10.5

ENV MYSQL_DATABASE lbn
ENV MYSQL_USER admin
ENV MYSQL_PASSWORD admin
ENV MYSQL_ROOT_PASSWORD root

ADD schema.sql /docker-entrypoint-initdb.d/

