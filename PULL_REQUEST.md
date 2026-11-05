## US05 - Cadastro de acréscimo (closes #5)

### O que foi feito
Cadastro e listagem de acréscimos de serviço, organizados em Controller → Service → Repository.

### Decisão arquitetural
Adotei arquitetura em camadas para separar responsabilidades:

- **Controller** só trata HTTP (recebe DTO, valida formato, delega ao Service, devolve status).
- **Service** concentra as regras de negócio (RN01 valor > 0; RN02 nome único). Assim as regras
  podem ser testadas sem subir servidor e reutilizadas por outras histórias (ex.: US03 - Agendamento de serviço).
- **Repository** isola a persistência; trocar o banco não afeta as regras.
- **DTOs** (request/response) evitam expor a entidade JPA na API.
- **GlobalExceptionHandler** traduz `RegraNegocioException` em HTTP 422, mantendo o Controller limpo.

**Alternativa descartada:** colocar a validação no Controller. Isso espalharia regras pela camada web
e dificultaria testes e reuso.

### Como testar
`mvn test` e os exemplos de `curl` do README.

### Checklist
- [x] Nenhuma regra de negócio no Controller
- [x] Diagrama da arquitetura no README
- [x] Testes unitários do Service
