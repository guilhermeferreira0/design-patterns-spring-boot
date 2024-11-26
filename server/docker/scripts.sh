## Running postgres database only
docker run --rm -d -p 5432:5432 --name postgres-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=secret -e POSTGRES_DB=users postgres:9-alpine