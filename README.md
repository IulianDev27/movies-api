# movies-api

API REST de películas construida con Spring Boot 4 y JPA, con base de datos H2 en memoria.

> Ejercicio del bootcamp de Factoría F5 (P5 Digital Academy).

## Stack

- Java 21
- Spring Boot 4.1.1 (Web MVC, Data JPA, Validation)
- H2 (en memoria)
- Maven

## Modelo de datos

Cuatro tablas principales — `movies`, `genres`, `actors` y `years` — más dos tablas intermedias
para resolver las relaciones N:M.

| Relación | Cardinalidad | Cómo se implementa |
|---|---|---|
| Película → Año | N:1 | FK `year_id` en la tabla `movies` |
| Película ↔ Género | N:M | tabla intermedia `movies_genres` |
| Película ↔ Actor | N:M | tabla intermedia `movies_actors` |

Una película se estrena en un único año, pero en un año se estrenan muchas películas, así que la FK
vive en el lado N. Una película puede ser de varios géneros y un género agrupa muchas películas, y lo
mismo ocurre con los actores: ahí no hay sitio para una FK y hace falta una tabla intermedia.

### Diagrama entidad-relación (Chen)

![Diagrama de Chen](docs/diagrama-chen.svg)

### Diagrama de patas de gallo (crow's foot)

```mermaid
erDiagram
    YEARS {
        bigint id_year PK
        int year_value
    }
    MOVIES {
        bigint id_movie PK
        varchar title
        varchar synopsis
        int duration
        bigint year_id FK
    }
    GENRES {
        bigint id_genre PK
        varchar name
    }
    ACTORS {
        bigint id_actor PK
        varchar name
    }
    MOVIES_GENRES {
        bigint movie_id FK
        bigint genre_id FK
    }
    MOVIES_ACTORS {
        bigint movie_id FK
        bigint actor_id FK
    }

    YEARS        ||--o{ MOVIES        : "estrena"
    MOVIES       ||--o{ MOVIES_GENRES : "tiene"
    GENRES       ||--o{ MOVIES_GENRES : "clasifica"
    MOVIES       ||--o{ MOVIES_ACTORS : "reparto"
    ACTORS       ||--o{ MOVIES_ACTORS : "actúa en"
```

## Instalación

_(pendiente)_

## Endpoints

_(pendiente)_
