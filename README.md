# Calculator EJB (Simple)

- Stateless EJB only (server)
- 4 sync + 4 async (Future)
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
