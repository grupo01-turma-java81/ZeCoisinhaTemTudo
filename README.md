# 🛒 Zé Coisinha Tem Tudo

![Zé Coisinha mascote](https://i.postimg.cc/jSxdHD4c/mascote.gif)

**Zé Coisinha Tem Tudo** é uma API RESTful desenvolvida com **Spring Boot**, que simula um **sistema de CRM** voltado para vendedores de comércios de variedades. O objetivo do projeto é facilitar o gerenciamento de **clientes** e **pedidos**, oferecendo ferramentas para identificar **oportunidades de fidelização**, melhorar o relacionamento com o cliente e impulsionar os resultados do negócio. O sistema também conta com **cadastro e autenticação de usuários**, utilizando **JWT** para segurança.

> 🔧 Projeto funcional, com **backend hospedado no Render ([acessar backend](https://zecoisinhatemtudo.onrender.com/)) e frontend disponível no Vercel**, permitindo acesso completo à aplicação em ambiente online. </br>
> 💻 O frontend da aplicação foi desenvolvido em **React + TypeScript** e pode ser acessado clicando [aqui](https://ze-coisinha-tem-tudo-frontend.vercel.app/). </br>
> 📂 O código-fonte do frontend está disponível neste [repositório](https://github.com/grupo01-turma-java81/ZeCoisinhaTemTudo-frontend).

## ✨ Funcionalidades
* 🔐 Cadastro e login de usuários com autenticação via JWT
* 🧾 CRUD completo para:
  * Clientes
  * Pedidos
* 📋 Consulta de clientes por nome
* 🚚 Filtro de pedidos por status de entrega
* 🔍 Listagem de clientes com **feedback positivo**
* 🧼 Validações e tratamento de exceções
* 🔒 Filtros de segurança com Spring Security

## 🧠 Entidades e Relacionamentos

🧑 **Cliente** </br>
Representa o cliente final do comércio, contendo informações essenciais para o contato, localização e análise de comportamento de compra.
| Atributo       | Tipo          | Descrição                                                |
| -------------- | ------------- | -------------------------------------------------------- |
| `id`           | Long          | Identificador único do cliente (gerado automaticamente). |
| `cpf`          | String        | Cadastro de Pessoa Física (limitado a 16 caracteres).    |
| `nome`         | String        | Nome completo do cliente.                                |
| `telefone`     | String        | Telefone de contato (com DDD).                           |
| `endereco`     | String        | Endereço completo do cliente.                            |
| `dataCadastro` | LocalDate     | Data de cadastro do cliente no sistema.                  |
| `pedido`       | List\<Pedido> | Lista de pedidos feitos por este cliente.                |

🔁 **Relacionamento:** </br>
Um cliente pode ter vários pedidos associados (**1:N** com `Pedido`).

---

📦 **Pedido** </br>
Representa uma compra ou serviço realizado por um cliente. Pode conter comentários, status de entrega e feedbacks que ajudam na análise de satisfação e recorrência.
| Atributo        | Tipo       | Descrição                                              |
| --------------- | ---------- | ------------------------------------------------------ |
| `id`            | Long       | Identificador do pedido (gerado automaticamente).      |
| `dataPedido`    | LocalDate  | Data em que o pedido foi efetuado.                     |
| `statusEntrega` | String     | Situação da entrega (ex: Pendente, Enviado, Entregue). |
| `valorTotal`    | BigDecimal | Valor total do pedido.                                 |
| `positivo`      | Boolean    | Indica se o cliente teve uma experiência positiva.     |
| `nota`          | Integer    | Avaliação do pedido (de 1 a 5).                        |
| `comentario`    | String     | Comentário textual deixado pelo cliente (opcional).    |
| `cliente`       | Cliente    | Cliente que realizou o pedido.                         |
| `usuario`       | Usuario    | Usuário (vendedor) responsável por cadastrar o pedido. |

🔁 **Relacionamentos:**
* Cada pedido está vinculado a **um cliente e um usuário (vendedor)**.
* Um cliente ou usuário pode ter **vários pedidos associados (N:1)**.

---

👤 **Usuário** </br>
Representa o usuário do sistema (normalmente o vendedor ou atendente da loja). Possui acesso autenticado com senha criptografada e pode ser o responsável por cadastrar pedidos.
| Atributo  | Tipo          | Descrição                             |
| --------- | ------------- | ------------------------------------- |
| `id`      | Long          | Identificador                         |
| `nome`    | String        | Nome completo                         |
| `usuario` | String        | E-mail usado como login               |
| `senha`   | String        | Senha criptografada                   |
| `foto`    | String        | URL da foto do perfil (opcional)      |
| `pedido`  | List\<Pedido> | Lista de pedidos criados pelo usuário |

🔁 **Relacionamento:** </br>
Um usuário pode registrar vários pedidos (**1:N** com `Pedido`).

## 🗂️ Estrutura do Projeto
```bash
├── controller/
│   ├── ClienteController.java
│   ├── PedidoController.java
│   └── UsuarioController.java
│
├── model/
│   ├── Cliente.java
│   ├── Pedido.java
│   └── Usuario.java
│
├── repository/
│   ├── ClienteRepository.java
│   ├── PedidoRepository.java
│   └── UsuarioRepository.java
│
├── security/
│   ├── BasicSecurityConfig.java
│   ├── JwtService.java
│   ├── JwtAuthFilter.java
│   ├── UserDetailsImpl.java
│   └── UserDetailsServiceImpl.java
│
├── service/
│   ├── ClienteService.java
│   ├── PedidoService.java
│   └── UsuarioService.java
```

## 🔗 Principais Endpoints
> Supondo a aplicação rodando em `http://localhost:8080`

👥 **Clientes** `/clientes`

| Método | Endpoint         | Descrição                            |
| ------ | ---------------- | ------------------------------------ |
| GET    | `/`              | Lista todos os clientes              |
| GET    | `/{id}`          | Busca cliente por ID                 |
| GET    | `/nome/{nome}`   | Busca clientes por nome              |
| GET    | `/oportunidades` | Lista clientes com pedidos positivos |
| POST   | `/`              | Cadastra novo cliente                |
| PUT    | `/`              | Atualiza dados do cliente            |
| DELETE | `/{id}`          | Deleta cliente por ID                |

</br>

📦 **Pedidos** `/pedidos`
| Método | Endpoint                  | Descrição                 |
| ------ | ------------------------- | ------------------------- |
| GET    | `/`                       | Lista todos os pedidos    |
| GET    | `/{id}`                   | Busca pedido por ID       |
| GET    | `/status/{statusEntrega}` | Filtra pedidos por status |
| POST   | `/`                       | Cadastra novo pedido      |
| PUT    | `/`                       | Atualiza pedido           |
| DELETE | `/{id}`                   | Deleta pedido             |

</br>

🔐 **Usuários** `/usuarios`
| Método | Endpoint     | Descrição                    |
| ------ | ------------ | ---------------------------- |
| GET    | `/all`       | Lista todos os usuários      |
| GET    | `/{id}`      | Busca usuário por ID         |
| POST   | `/logar`     | Login e retorno de token JWT |
| POST   | `/cadastrar` | Cadastro de novo usuário     |
| PUT    | `/atualizar` | Atualiza dados do usuário    |

## 🚀 Tecnologias Utilizadas

* [**Java 17+**](https://www.java.com/pt-BR/) – Linguagem principal do backend.  
* [**Spring Boot**](https://spring.io/projects/spring-boot) – Framework para construção da API RESTful.  
* [**Spring Security**](https://spring.io/projects/spring-security) – Autenticação e autorização.  
* [**JWT (JSON Web Token)**](https://jwt.io/) – Controle de sessões e autenticação.  
* **[MySQL](https://www.mysql.com/) / [PostgreSQL](https://www.postman.com/)** – Banco de dados, com alternância por perfil (dev/prod).  
* [**Swagger / OpenAPI**](https://swagger.io/tools/swagger-ui/) – Documentação interativa da API.  
* [**Render**](https://render.com/) – Deploy do backend.  
* [**Postman**](https://www.postman.com/) – Testes de endpoints da API.  </br>
![zecoisinhatemtudo Postman](https://i.postimg.cc/gjt4gX6h/zepostman.png)


## 🚀 Como Rodar o Projeto Localmente
```bash
# 1. Clone o repositório
git clone https://github.com/GiulioArantes/ZeCoisinhaTemTudo.git

# 2. Navegue até o diretório do projeto
cd ZeCoisinhaTemTudo

# 3. Configure o arquivo application.properties em:
src/main/resources/application.properties

# 4. Defina suas credenciais e informações do banco de dados MySQL

# 5. Execute a aplicação Spring Boot
```

## 🤝 Contribuição
Contribuições são sempre bem-vindas! Você pode colaborar com sugestões, feedbacks ou melhorias no código. Sinta-se à vontade para abrir issues ou enviar pull requests.

> 💙 Este projeto foi desenvolvido de forma colaborativa por um grupo comprometido com o aprendizado e a prática de desenvolvimento.

👤 **Colaboradores do Projeto**
<table> <tr> <td align="center"> <a href="https://github.com/ViniCristhian"> <img src="https://github.com/ViniCristhian.png" width="100px;" alt="Vinícius Cristhian"/> <br /> <sub><b>Vinícius Cristhian</b></sub> </a><br /> <a href="https://www.linkedin.com/in/vinicristhian/">🔗 LinkedIn</a> </td> <td align="center"> <a href="https://github.com/IagoWiliian"> <img src="https://github.com/IagoWiliian.png" width="100px;" alt="Iago Willian"/> <br /> <sub><b>Iago Willian</b></sub> </a><br /> <a href="https://www.linkedin.com/in/iago-willian-/">🔗 LinkedIn</a> </td> <td align="center"> <a href="https://github.com/GiulioArantes"> <img src="https://github.com/GiulioArantes.png" width="100px;" alt="Giulio Gabriel"/> <br /> <sub><b>Giulio Gabriel</b></sub> </a><br /> <a href="https://www.linkedin.com/in/giulio-arantes/">🔗 LinkedIn</a> </td> <td align="center"> <a href="https://github.com/Yasmimruescas"> <img src="https://github.com/Yasmimruescas.png" width="100px;" alt="Yasmim Ruescas"/> <br /> <sub><b>Yasmim Ruescas</b></sub> </a><br /> <a href="https://www.linkedin.com/in/yasmim-ruescas/">🔗 LinkedIn</a> </td> <td align="center"> <a href="https://github.com/Vitoriacmlly"> <img src="https://github.com/Vitoriacmlly.png" width="100px;" alt="Vitoria Camilly"/> <br /> <sub><b>Vitoria Camilly</b></sub> </a><br /> <a href="https://www.linkedin.com/in/vitoria-camilly/">🔗 LinkedIn</a> </td> <td align="center"> <a href="https://github.com/BrunoDaniel13"> <img src="https://github.com/BrunoDaniel13.png" width="100px;" alt="Bruno Daniel"/> <br /> <sub><b>Bruno Daniel</b></sub> </a><br /> <a href="https://www.linkedin.com/in/bruno-daniel-ferreira-leite/">🔗 LinkedIn</a> </td> </tr> </table>
