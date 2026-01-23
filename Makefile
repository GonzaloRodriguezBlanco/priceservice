PROJECT_NAME := Price Service API
CONTAINER_RUNTIME := podman
## Colors
CC := \033[0m
CT := \033[1;37m
CD := \033[1;32m
CG := \033[0;37m

TITLE := printf "$(CT)$(PROJECT_NAME)$(CC) %s\n\n"

.DEFAULT_GOAL=help

help:
	@$(TITLE)
	@echo "command line:"
	@echo "$(CD)build$(CG) $(CC) Build"
	@echo "$(CD)start$(CG) $(CC) Start server"
	@echo "$(CD)stop$(CG) $(CC) Stop server"
	@echo "$(CD)status$(CG) $(CC) Status"
	@echo "$(CD)logs$(CG) [service=application] $(CC) Show service log (default: application)"
	@echo ""
	@echo "$(CD)unit$(CG) $(CC) Execute unit test"
	@echo "$(CD)integration$(CG) $(CC) Execute integration test"
	@echo "$(CD)acceptance$(CG) $(CC) Execute acceptance BDD test"
	@echo "$(CD)e2e$(CG) $(CC) Execute E2E Postman test"



.PHONY: build
build: docker-build

.PHONY: docker-build
docker-build:
	@$(CONTAINER_RUNTIME) compose -f compose.yaml build --no-cache

.PHONY: docker-up
docker-up:
	@$(CONTAINER_RUNTIME) compose -f compose.yaml up -d

.PHONY: status
status:
	@$(CONTAINER_RUNTIME) compose -f compose.yaml ps

.PHONY: start
start: docker-up status

.PHONY: stop
stop:
	@$(CONTAINER_RUNTIME) compose -f compose.yaml down

.PHONY: logs
ifndef service
SERVICE=application
else
SERVICE=$(service)
endif

.PHONY: logs
logs:
	@$(CONTAINER_RUNTIME) compose -f compose.yaml logs -f $(SERVICE)

.PHONY: unit
unit:
	./mvnw clean test -Punit

.PHONY: integration
integration:
	./mvnw clean test -Pintegration

.PHONY: acceptance
acceptance:
	./mvnw clean test -Pacceptance

.PHONY: e2e
e2e:
	$(CONTAINER_RUNTIME) run --network host -v ./src/test/postman:/etc/newman postman/newman run postman_collection.json -d e2e_postman_dataset.json
