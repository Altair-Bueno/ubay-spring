package uma.taw.ubayspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.taw.ubayspring.dto.users.ClientDTO;
import uma.taw.ubayspring.dto.users.PasswordChangeDTO;
import uma.taw.ubayspring.dto.users.ProductDTO;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.entity.ProductFavouritesEntity;
import uma.taw.ubayspring.repository.*;
import uma.taw.ubayspring.types.GenderEnum;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UsersService {
    @Autowired
    ProductFavouritesRepository favouritesRepository;

    @Autowired
    ClientRepositoryCustom clientRepositoryCustom;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    LoginCredentialsRepository loginRepository;

    //@Autowired
    //AuthService authService;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    PasswordResetRepository passwordResetRepository;

    public void addFavProduct(String productID, String clientID) {
        ProductEntity product = productRepository.findById(Integer.parseInt(productID)).get();
        ClientEntity client = clientRepository.findById(Integer.parseInt(clientID)).get();

        ProductFavouritesEntity fav = new ProductFavouritesEntity();
        fav.setProduct(product);
        fav.setClient(client);
        favouritesRepository.save(fav);
    }


    public void deleteUser(String id) {
        ClientEntity client = clientRepository.findById(Integer.parseInt(id)).isPresent() ? clientRepository.findById(Integer.parseInt(id)).get() : null;
        clientRepository.delete(client);
    }


    /* TODO: No se como enganchar el getTuple...
    public void deleteFavProduct(String productID, String clientID) {
        ProductEntity product = productRepository.findById(Integer.parseInt(productID)).get();
        ClientEntity client = clientRepository.findById(Integer.parseInt(clientID)).get();

        ProductFavouritesEntity fav = favouritesRepository.getTuple(client, product);
        favouritesRepository.delete(fav);
    }

     */

    public void modifyUser(String id, String name, String lastName, GenderEnum gender, String address, String city, Date birthDate) {
        ClientEntity client = clientRepository.findById(Integer.parseInt(id)).get();
        client.setName(name);
        client.setLastName(lastName);
        client.setGender(gender);
        client.setAddress(address);
        client.setCity(city);
        client.setBirthDate(birthDate);
        clientRepository.save(client);
    }


    /*
    public List<ProductDTO> products(LoginDTO client) {
        ClientEntity user = authService.getCredentialsEntity(client).getUser();

        List<ProductDTO> favouriteProducts = favouritesRepository.getClientFavouriteProducts(user).stream().map(this::productEntityToDTO).collect(Collectors.toList());
        return favouriteProducts;
    }

    */

    private ProductDTO productEntityToDTO(ProductEntity productEntity) {
        return new ProductDTO(productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getDescription(),
                productEntity.getImage(),
                productEntity.getCloseDate());
    }

    /*
    private uma.taw.ubay.dto.notifications.ProductDTO notificationsProductEntityToDTO(ProductEntity productEntity) {
        return new uma.taw.ubay.dto.notifications.ProductDTO(productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getDescription(),
                productEntity.getImages(),
                productEntity.getCloseDate());
    }

     */

    /*
    public int getClientID(LoginDTO login) {
        return authService.getCredentialsEntity(login).getUser().getId();
    }
    */


    /*
    private BidsDTO bidEntityToDto(BidEntity bidEntity) {
        return new BidsDTO(bidEntity.getPublishDate(),
                bidEntity.getAmount(),
                notificationsProductEntityToDTO(bidEntity.getProduct())
        );
    }

    public HashMap<BidsDTO, Boolean> getNotifications(LoginDTO login) {
        var user = authService.getCredentialsEntity(login).getUser();
        HashMap<BidsDTO, Boolean> notifications = new LinkedHashMap(); // Key: Bid; Value: Is the client the bid winner
        List<BidEntity> closedBidsByClient = bidRepository.productsBiddedClosedProducts(user);

        for (BidEntity b : closedBidsByClient) {
            BidsDTO bidDto = bidEntityToDto(b);
            notifications.put(bidDto, bidRepository.isWinnerBid(user, b));
        }

        return notifications;
    }

    */


    public List<ClientDTO> users(String id, String name, String lastName, String address, String city, String genderString) {
        GenderEnum gender = null;
        if (genderString != null && !"".equals(genderString) && !genderString.equals("--")) {
            gender = GenderEnum.valueOf(genderString);
        }

        List<ClientEntity> clientEntityList = (List<ClientEntity>) clientRepository.findAll();
        List<ClientEntity> filtrados = clientRepositoryCustom.filterClients(name, lastName, gender, address, city, id);
        if(!name.equals("") || !lastName.equals("") || gender != null|| !address.equals("") || !city.equals("") || !id.equals("")){
            return filtrados.stream().map(this::clientEntityToDTO).collect(Collectors.toList());
        } else {
            return clientEntityList.stream().map(this::clientEntityToDTO).collect(Collectors.toList());
        }
    }


    private ClientDTO clientEntityToDTO(ClientEntity client) {
        return new ClientDTO(client.getId(), client.getName(), client.getLastName(), client.getGender(), client.getAddress(), client.getCity(), client.getBirthDate());
    }

    private String generateRandomString(int size, Random random) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
    /*
    @NotNull
    public PasswordChangeDTO passwordChange(String id) {
        String passwordChangeID = generateRandomString(20, new Random());
        ClientEntity client = clientRepository.findById(Integer.parseInt(id)).get();
        LoginCredentialsEntity loginCredentialsEntity = loginRepository.searchClientLoginByClient(client);

        PasswordResetEntity passwordResetEntity = new PasswordResetEntity();
        passwordResetEntity.setUser(loginCredentialsEntity);
        passwordResetEntity.setRequestId(passwordChangeID);
        passwordResetRepository.create(passwordResetEntity);

        return new PasswordChangeDTO(passwordChangeID, loginCredentialsEntity.getUsername());
    }

     */
}
