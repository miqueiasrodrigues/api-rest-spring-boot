# API REST com Spring Boot

## Descrição
Esta API REST foi desenvolvida utilizando Spring Boot, Hibernate com JPA para persistência de dados, Flyway para gerenciamento de migrações, e suporta diferentes MediaTypes.

## Funcionalidades

- **Utilização do Hibernate com JPA**
  - Configuração do Hibernate para mapeamento objeto-relacional (ORM) usando JPA.
  - Entidades anotadas com JPA para definir a estrutura do banco de dados.
  - Uso de repositórios Spring Data JPA para operações CRUD.

- **Uso de Versionamento**
  - Controle de versionamento de API usando URL (`/api/v1/user`, `/api/v2/user`).
  - Versionamento de banco de dados com Flyway, garantindo que todas as migrações sejam aplicadas em ordem.

- **Migrações com Flyway**
  - Utilização do Flyway para aplicar e gerenciar migrações de banco de dados.
  - Scripts SQL de migração armazenados no diretório `src/main/resources/db/migration`.
  - Automatização das migrações no início da aplicação.

- **Suporte a Diferentes MediaTypes**
  - Produção e consumo de diferentes tipos de mídia (`application/json`, `application/xml`, `application/x-yaml`).
  - Configuração de controladores para suportar múltiplos MediaTypes usando a anotação `@RequestMapping`.
 
- **Padrão Value Object (VO)**
  - Uso do padrão VO para encapsular dados imutáveis.
  - Garantia de que os dados representados por VOs são consistentes e válidos.
  - Melhoria na clareza e na segurança do código ao evitar mutações inesperadas.

### Exemplo de Endpoint

- `GET /api/v1/user`
  - Descrição: Retorna uma lista de recursos.
  - Produz: `application/json`, `application/xml`

