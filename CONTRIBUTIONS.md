# [Altair Bueno](https://github.com/Altair-Bueno/ubay-spring/commits?author=Altair-Bueno)

- Docker
  - [docker-compose.yml](docker-compose.yml)
  - [docker-compose-dev.yml](docker-compose-dev.yml)
- Otros
  - DB ORM
  - Project setup (README.md,DEV.md)
  - Entity generation.
  - Repository creation.

# [Francisco Javier Hernández Martín](https://github.com/Altair-Bueno/ubay-spring/commits?author=fran1215)

- Spring Configuration
  - Website Localization (Language Translation)
    - [Localization](src/main/java/uma/taw/ubayspring/springconfig/Localization.java)
    - [MessageConfig](src/main/java/uma/taw/ubayspring/springconfig/MessageConfig.java)
    - [SecurityConfig](src/main/java/uma/taw/ubayspring/springconfig/SecurityConfig.java)
- Translation of the whole page via Messages bundle
  - [English strings (default)](src/main/resources/messages.properties)
  - [Spanish strings (translation)](src/main/resources/messages_es.properties)

# [José Luis Bueno](https://github.com/Altair-Bueno/ubay-spring/commits?author=jxtaaa)
- Admin functions on user page
  - [/users/delete](src/main/java/uma/taw/ubayspring/controller/UsersController.java)
  - [/users/modify](src/main/java/uma/taw/ubayspring/controller/UsersController.java)
  - [/users/modify.jsp](src/main/webapp/WEB-INF/view/users/modify.jsp)
  - [/users](src/main/java/uma/taw/ubayspring/controller/UsersController.java)
  - [/users/users.jsp](src/main/webapp/WEB-INF/view/users/users.jsp)
- Admin functions on categories page
  - [/categories/add](src/main/java/uma/taw/ubayspring/controller/CategoriesController.java)
  - [/categories/add.jsp](src/main/webapp/WEB-INF/view/categories/add.jsp)
  - [/categories/delete](src/main/java/uma/taw/ubayspring/controller/CategoriesController.java)
  - [/categories/modify](src/main/java/uma/taw/ubayspring/controller/CategoriesController.java)
  - [/categories/modify.jsp](src/main/webapp/WEB-INF/view/categories/modify.jsp)
- Categories functions
  - [/categories](src/main/java/uma/taw/ubayspring/controller/CategoriesController.java)
  - [/categories/categories.jsp](src/main/webapp/WEB-INF/view/categories/categories.jsp)
  - [/categories/addFavourite](src/main/java/uma/taw/ubayspring/controller/CategoriesController.java)
  - [/categories/deleteFavourite](src/main/java/uma/taw/ubayspring/controller/CategoriesController.java)
- User functions
  - [/users/addFavourite](src/main/java/uma/taw/ubayspring/controller/UsersController.java)
  - [/users/deleteFavourite](src/main/java/uma/taw/ubayspring/controller/UsersController.java)
  - [/users/products](src/main/java/uma/taw/ubayspring/controller/UsersController.java)
  - [/users/products.jsp](src/main/webapp/WEB-INF/view/users/products.jsp)
- Repositories
  - [ClientRepository](src/main/java/uma/taw/ubayspring/repository/ClientRepository.java)
  - [ClientRepositoryCustom](src/main/java/uma/taw/ubayspring/repository/ClientRepositoryCustom.java)
  - [CategoryRepository](src/main/java/uma/taw/ubayspring/repository/CategoryRepository.java)
  - [FavouritesRepositoryCustom](src/main/java/uma/taw/ubayspring/repository/FavouritesRepositoryCustom.java)
- Services
  - [CategoriesService](src/main/java/uma/taw/ubayspring/service/CategoriesService.java)
  - [UsersService](src/main/java/uma/taw/ubayspring/service/UsersService.java)
- DTO
  - [CategoriesDTO](src/main/java/uma/taw/ubayspring/dto/categories/CategoriesDTO.java)
  - [CategoryDTO](src/main/java/uma/taw/ubayspring/dto/categories/CategoryDTO.java)
  - [ClientDTO](src/main/java/uma/taw/ubayspring/dto/users/ClientDTO.java)
  - [PasswordChangeDTO](src/main/java/uma/taw/ubayspring/dto/users/PasswordChangeDTO.java)
  - [ProductDTO](src/main/java/uma/taw/ubayspring/dto/users/ProductDTO.java)
- Others
  - Database schema design.
  - Navbar dropdown / buttons change for admins.
  - Some checks/testing on Hibernate entities.
  - Implement language customization to my pages.