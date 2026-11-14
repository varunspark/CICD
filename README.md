# demo-app

Spring Boot 3 (Java 21) sample app for a Jenkins + AWS (ECR/EKS) CI/CD project.

## Endpoints
| Path | Purpose |
|---|---|
| `GET /api/hello` | Simple message |
| `GET /api/greet/{name}` | Personalised greeting (400 if blank) |
| `GET /actuator/health/liveness` | Kubernetes liveness probe |
| `GET /actuator/health/readiness` | Kubernetes readiness probe |
| `GET /actuator/prometheus` | Metrics for Prometheus |

## Run locally
```bash
mvn test                 # unit tests
mvn spring-boot:run      # run app on :8080
curl localhost:8080/api/hello
```

## Run with Docker
```bash
docker build -t demo-app:local .
docker run --rm -p 8080:8080 demo-app:local
```
