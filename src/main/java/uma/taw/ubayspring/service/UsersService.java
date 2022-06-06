package uma.taw.ubayspring.service;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.dto.notifications.BidsDTO;
import uma.taw.ubayspring.dto.products.ProductClientDTO;
import uma.taw.ubayspring.dto.users.ClientDTO;
import uma.taw.ubayspring.dto.users.FilterUsersDTO;
import uma.taw.ubayspring.dto.users.PasswordChangeDTO;
import uma.taw.ubayspring.dto.users.ProductDTO;
import uma.taw.ubayspring.entity.*;
import uma.taw.ubayspring.repository.*;
import uma.taw.ubayspring.types.GenderEnum;
import uma.taw.ubayspring.types.KindEnum;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    ProductRepository productRepository;

    @Autowired
    LoginCredentialsRepository loginRepository;

    @Autowired
    LoginCredentialsRepositoryCustom loginRepositoryCustom;

    @Autowired
    AuthService authService;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    BidRepositoryCustom bidRepositoryCustom;

    @Autowired
    PasswordResetRepository passwordResetRepository;

    @Autowired
    FavouritesRepositoryCustom favouritesRepositoryCustom;

    @Autowired
    ProductFavouritesRepositoryCustom productFavouritesRepositoryCustom;

    @Autowired
    ProductFavouritesRepository productFavouritesRepository;


    /**
     * @author: José Luis Bueno Pachón
     */

    public void addFavProduct(String productID, String clientID) {
        ProductEntity product = productRepository.findById(Integer.parseInt(productID)).get();
        ClientEntity client = clientRepository.findById(Integer.parseInt(clientID)).get();

        ProductFavouritesEntity fav = ProductFavouritesEntity
                .builder()
                .product(product)
                .client(client)
                .build();

        favouritesRepository.save(fav);
    }


    /**
     * @author: José Luis Bueno Pachón
     */

    public void deleteUser(String id) {
        if(clientRepository.findById(Integer.parseInt(id)).isPresent()){
            clientRepository.delete(clientRepository.findById(Integer.parseInt(id)).get());
        }
    }


    /**
     * @author: José Luis Bueno Pachón
     */

    public void deleteFavProduct(String productID, String clientID) {
        ProductEntity product = productRepository.findById(Integer.parseInt(productID)).get();
        ClientEntity client = clientRepository.findById(Integer.parseInt(clientID)).get();

        ProductFavouritesEntity fav = productFavouritesRepositoryCustom.getTuple(client, product);
        productFavouritesRepository.delete(fav);
    }
    /**
     * @author: José Luis Bueno Pachón
     */

    public void modifyUser(ClientDTO clientDTO) {
        ClientEntity client = clientRepository.findById(clientDTO.getId()).get();
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setGender(clientDTO.getGender());
        client.setAddress(clientDTO.getAddress());
        client.setCity(clientDTO.getCity());
        client.setBirthDate(clientDTO.getBirthDate());
        clientRepository.save(client);
    }


    /**
     * @author: José Luis Bueno Pachón
     */

    public List<ProductDTO> products(User client) {
        ClientEntity user = authService.getCredentialsEntity(client).getClient();

        List<ProductDTO> favouriteProducts = productFavouritesRepositoryCustom.getClientFavouriteProducts(user).stream().map(this::productEntityToDTO).collect(Collectors.toList());
        return favouriteProducts;
    }


    /**
     * @author: José Luis Bueno Pachón
     */

    private ProductDTO productEntityToDTO(ProductEntity productEntity) {
        return new ProductDTO(productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getDescription(),
                productEntity.getImage(),
                productEntity.getCloseDate());
    }


    /**
     * @author: José Luis Bueno Pachón
     */

    public int getClientID(User user) {
        return authService.getCredentialsEntity(user).getClient().getId();
    }


    /**
     * @author: José Luis Bueno Pachón
     */

    public List<ClientDTO> users(FilterUsersDTO filterUsersDTO) {
        List<ClientEntity> filtrados = clientRepositoryCustom.filterClients(
                filterUsersDTO.getName(),
                filterUsersDTO.getLastName(),
                filterUsersDTO.getGender(),
                filterUsersDTO.getAddress(),
                filterUsersDTO.getCity(),
                filterUsersDTO.getId());
        return filtrados.stream().map(this::clientEntityToDTO).collect(Collectors.toList());
    }


    /**
     * @author: José Luis Bueno Pachón
     */

    private ClientDTO clientEntityToDTO(ClientEntity client) {
        return new ClientDTO(client.getId(), client.getName(), client.getLastName(), client.getGender(), client.getAddress(), client.getCity(), client.getBirthDate());
    }

    /**
     * @author: Altair Bueno
     */
    private String generateRandomString(int size, Random random) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * @author: José Luis Bueno Pachón
     */

    @NotNull
    public PasswordChangeDTO passwordChange(String id) {
        String passwordChangeID = generateRandomString(20, new Random());

        if(clientRepository.findById(Integer.parseInt(id)).isPresent()){
            ClientEntity client = clientRepository.findById(Integer.parseInt(id)).get();
            LoginCredentialsEntity loginCredentialsEntity = loginRepositoryCustom.searchClientLoginByClient(client);


            PasswordResetEntity passwordResetEntity = new PasswordResetEntity();
            passwordResetEntity.setLoginCredentials(loginCredentialsEntity);
            passwordResetEntity.setRequestId(passwordChangeID);
            passwordResetRepository.save(passwordResetEntity);

            return new PasswordChangeDTO(loginCredentialsEntity.getId(), passwordChangeID);
        }
        return null;
    }

    /**
     *
     * @author Francisco Javier Hernández Martín
     */
    private uma.taw.ubayspring.dto.notifications.ProductDTO notificationsProductEntityToDTO(ProductEntity productEntity) {
        return new uma.taw.ubayspring.dto.notifications.ProductDTO(productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getDescription(),
                productEntity.getImage(),
                productEntity.getCloseDate());
    }

    /**
     *
     * @author Francisco Javier Hernández Martín
     */
    private BidsDTO bidEntityToDto(BidEntity bidEntity) {
        return new BidsDTO(bidEntity.getPublishDate(),
                bidEntity.getAmount(),
                notificationsProductEntityToDTO(bidEntity.getProduct())
        );
    }

    /**
     *
     * @author Francisco Javier Hernández Martín
     */
    public HashMap<BidsDTO, Boolean> getNotifications(int id) {
        var user = clientRepository.findById(id).get();
        HashMap<BidsDTO, Boolean> notifications = new LinkedHashMap(); // Key: Bid; Value: Is the client the bid winner
        List<BidEntity> closedBidsByClient = bidRepositoryCustom.productsBiddedClosedProducts(user);

        for (BidEntity b : closedBidsByClient) {
            BidsDTO bidDto = bidEntityToDto(b);
            notifications.put(bidDto, bidRepositoryCustom.isWinnerBid(user, b));
        }

        return notifications;
    }
}
