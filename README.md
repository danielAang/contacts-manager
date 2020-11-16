# Contacts Importer :leaves: :man:

## Table of Contents

- [About](#about)
- [Getting Started](#getting_started)
- [Usage](#usage)

## About <a name = "about"></a>

Sample project using Spring Batch to ingest and update a custom csv file containing person data. The main goal is to test Spring Batch functionalies. 
The job is started by a rest controller and then, runs two steps which ingest data and then get address information from a api.

## Getting Started <a name = "getting_started"></a>

Just git clone the project and run

```
./gradlew bootRun
```

### Prerequisites

This projects uses a h2 database, so no need to worry about installing databases. 
Just need java 11.

### Installing

First clone the project

```
git clone ...
```

And 
```
./gradlew bootRun
```

To check the ingested data, access [h2-console](http://localhost:8080/h2-console). Note that username and password are "sa";

## Usage <a name = "usage"></a>

To start the job make a request to
```
POST /api/v1/imported-files/import HTTP/1.1
```
Don't forget to insert the multipart form containing the <b>file</b> csv itself.
You can get the file into resources folder.

| nome        | idade           | cpf  |  rg  | data_nasc | sexo | email | cep | endereco | numero | bairro | cidade | estado | telefone_fixo | celular |
|:------------|:---------------:|:----:|:----:|:---------:|:----:|:-----:|:---:|:--------:|:------:|:------:|:------:|:------:|:-------------:|:-------:|
|Isaac Sebastião Rocha | 68 | 45580669615 | 336081418 | 14/01/1952 | Masculino | iisaacsebastiaorocha@apso.org.br | 66630393 | Passagem União | 322 | Bengui | Belém | PA | 9128363282 | 91984581798 |