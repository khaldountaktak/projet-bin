# Calculator EJB (Simple)

- Stateless EJB only (server)
- 10 sync + 10 async (Future)
- Explicit JNDI: calculator/CalculatorRemote
- Docker image autodeploys EJB; client runs on host

## Run (Docker)
```
./scripts/docker-run.sh
```

## Stop
```
./scripts/docker-down.sh
```
