# Prueba Técnica [PS2024] Proyecto de Gestión de Segmentos 

Este proyecto fue desarrollado como parte de la prueba tecnica [PS2024]. El sistema tiene como objetivo la administración de la malla vial 
en la ciudad de Medellín, gestionando "segmentos" de vías y sus respectivos "bordillos" y "calzadas". Está desarrollado utilizando 
Play Framework con Java y simula una conexión con una base de datos utilizando una base de datos en memoria.

# Descripción
El backend permite realizar operaciones CRUD sobre los siguientes conceptos:

Segmentos: Porciones de la vía que pueden tener uno o varios bordillos o calzadas.
Bordillos: Lados de la vía o bordes de los segmentos.
Calzadas: Parte transitable de la vía dentro de un segmento.

# Limitación Técnica

Aunque se configuró la mayor parte del proyecto para conectarse con una base de datos PostgreSQL, no se logró establecer la conexión correctamente. 
Por lo tanto, se decidió continuar el desarrollo utilizando una base de datos en memoria, emulando la interacción con los datos a través de listas estáticas. 
Este enfoque permitió seguir trabajando en la lógica de negocio sin depender de la base de datos real.

# Tecnologías Utilizadas

- Play Framework: Framework web para el backend en Java.

- Java 21: Lenguaje de programación usado para desarrollar la lógica del backend.

- SBT: Herramienta para la construcción del proyecto y gestión de dependencias.

- JPA/Hibernate: Configuración de ORM para bases de datos.

- Base de datos en memoria: Utilizada para simular la persistencia de datos.

# Instalación y Configuración

## Requisitos: 

Java 8+

SBT (Scala Build Tool)

PostgreSQL

## Pasos de Configuración

Clonar el repositorio:

```bash
git clone https://github.com/CristianGiVi/backend-malla-vial.git
cd backend-malla-vial
```

Compilar y ejecutar el proyecto:

```bash
sbt run
```




