# [Altair Bueno](https://github.com/Altair-Bueno/ubay-spring/commits?author=Altair-Bueno)

- JSP
  - [navbar.jsp](src/main/webapp/WEB-INF/components/navbar.jsp)
  - [changePassword.jsp](src/main/webapp/WEB-INF/view/auth/changePassword.jsp)
  - [login.jsp](src/main/webapp/WEB-INF/view/auth/login.jsp)
  - [register.jsp](src/main/webapp/WEB-INF/view/auth/register.jsp)
  - [resetPasswoird.jsp](src/main/webapp/WEB-INF/view/auth/resetPassword.jsp)
  - [404.jsp](src/main/webapp/WEB-INF/view/error/404.jsp)
- Service
  - [AuthService](src/main/java/uma/taw/ubayspring/service/AuthService.java)
  - [MinioWrapperService](src/main/java/uma/taw/ubayspring/wrapper/MinioWrapperService.java)
- DTO
  - [LoginDTO](src/main/java/uma/taw/ubayspring/dto/LoginDTO.java)
  - [ChangePasswordDTO](src/main/java/uma/taw/ubayspring/dto/auth/ChangePasswordDTO.java)
  - [RegisterDTO](src/main/java/uma/taw/ubayspring/dto/auth/RegisterDTO.java)
  - [ResetPasswordDTO](src/main/java/uma/taw/ubayspring/dto/auth/ResetPasswordDTO.java)
- Controller
  - [AuthController](src/main/java/uma/taw/ubayspring/controller/AuthController.java)
  - [ImageController](src/main/java/uma/taw/ubayspring/controller/ImageController.java)
- Docker
  - [docker-compose.yml](docker-compose.yml)
  - [docker-compose-dev.yml](docker-compose-dev.yml)
- Other
  - DB ORM
  - Project setup (README.md,DEV.md)
  - Entity generation
  - Repository creation
  - Solved multiple Hibernate & Spring Boot errors

# [Francisco Javier Hernández Martín](https://github.com/Altair-Bueno/ubay-spring/commits?author=fran1215)

- [Contribution to Spring Configuration](src/main/resources/application.properties)
- Translation of the whole page via Messages bundle
  - [English strings (default)](src/main/resources/messages.properties)
  - [Spanish strings (translation)](src/main/resources/messages_es.properties)
- JSP
  - [Navbar](src/main/webapp/WEB-INF/components/navbar.jsp)
  - [product.jsp (index)](src/main/webapp/WEB-INF/view/product.jsp)
  - [item.jsp](src/main/webapp/WEB-INF/view/product/item.jsp)
  - [new.jsp](src/main/webapp/WEB-INF/view/product/new.jsp)
  - [update.jsp](src/main/webapp/WEB-INF/view/product/update.jsp)
  - [notifications.jsp](src/main/webapp/WEB-INF/view/users/notifications.jsp)
  - [bids.jsp (User bids)](src/main/webapp/WEB-INF/view/users/bids.jsp)
  - [index.jsp (Received bids)](src/main/webapp/WEB-INF/view/vendor/bids/index.jsp)
- Controllers
  - [ProductController](src/main/java/uma/taw/ubayspring/controller/ProductController.java)
  - [VendorController](src/main/java/uma/taw/ubayspring/controller/VendorController.java)
  - [UsersController (Bids related methods)](src/main/java/uma/taw/ubayspring/controller/UsersController.java)
- DTO
  - [ProductCategoryDTO](src/main/java/uma/taw/ubayspring/dto/products/ProductCategoryDTO.java)
  - [ProductClientDTO](src/main/java/uma/taw/ubayspring/dto/products/ProductClientDTO.java)
  - [ProductDTO](src/main/java/uma/taw/ubayspring/dto/products/ProductDTO.java)
  - [ProductFavouritesDTO](src/main/java/uma/taw/ubayspring/dto/products/ProductFavouritesDTO.java)
  - [ProductLoginDTO](src/main/java/uma/taw/ubayspring/dto/products/ProductLoginDTO.java)
  - [ProductsDTO](src/main/java/uma/taw/ubayspring/dto/products/ProductsDTO.java)
  - [ProductBidDTO](src/main/java/uma/taw/ubayspring/dto/products/ProductBidDTO.java)
  - [FavOwnedDTO](src/main/java/uma/taw/ubayspring/dto/products/index/FavOwnedDTO.java)
  - [ListsDTO](src/main/java/uma/taw/ubayspring/dto/products/index/ListsDTO.java)
  - [ParamsDTO](src/main/java/uma/taw/ubayspring/dto/products/index/ParamsDTO.java)
  - [ProductFormParamsDTO](src/main/java/uma/taw/ubayspring/dto/products/ProductForm/ProductFormParamsDTO.java)
  - [BidsParamsDTO](src/main/java/uma/taw/ubayspring/dto/bids/BidsParamsDTO.java)
  - [BidsSortingOptions](src/main/java/uma/taw/ubayspring/dto/bids/BidsSortingOptions.java)
  - [NewBidsDTO](src/main/java/uma/taw/ubayspring/dto/bids/NewBidsDTO.java)
- Repositories
  - [BidRepositoryCustom](src/main/java/uma/taw/ubayspring/repository/BidRepositoryCustom.java)
  - [BidRepositoryCustomImpl](src/main/java/uma/taw/ubayspring/repository/BidRepositoryCustomImpl.java)
  - [ProductFavouritesRepository](src/main/java/uma/taw/ubayspring/repository/ProductFavouritesRepository.java)
  - [ProductFavouritesRepositoryCustom](src/main/java/uma/taw/ubayspring/repository/ProductFavouritesRepositoryCustom.java)
  - [ProductFavouritesRepositoryCustomImpl (Check percentages)](src/main/java/uma/taw/ubayspring/repository/ProductFavouritesRepositoryCustomImpl.java)
  - [ProductRepository](src/main/java/uma/taw/ubayspring/repository/ProductRepository.java)
  - [ProductRepositoryCustom](src/main/java/uma/taw/ubayspring/repository/ProductRepositoryCustom.java)
  - [ProductRepositoryCustomImpl](src/main/java/uma/taw/ubayspring/repository/ProductRepositoryCustomImpl.java)
- Services
  - [ProductService](src/main/java/uma/taw/ubayspring/service/products/ProductService.java)
  - [BidService (Ported and edited)](src/main/java/uma/taw/ubayspring/service/BidService.java)
- Keyfiles
  - [ProductKeys](src/main/java/uma/taw/ubayspring/keys/UsersKeys.java)
- Database
  - Database Schema Design
  - [Database Schema SQL](sql/scheme.sql)
- Others
  - Database design & ORM
  - Minor/medium changes in many other files when needed (check github logs)
  - Refactoring of the whole page (import, errors, etc)
  - Styling set up / fixed all over the website

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