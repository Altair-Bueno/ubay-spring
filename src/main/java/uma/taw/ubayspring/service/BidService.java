package uma.taw.ubayspring.service;

import io.micrometer.core.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.dto.bids.*;
import uma.taw.ubayspring.entity.BidEntity;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.repository.BidRepository;
import uma.taw.ubayspring.repository.BidRepositoryCustom;
import uma.taw.ubayspring.repository.LoginCredentialsRepository;
import uma.taw.ubayspring.repository.ProductRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BidService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    BidRepositoryCustom bidRepositoryCustom;

    @Autowired
    AuthService authService;

    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;

    private SentBidsDTO entityBidToSentBid(BidEntity bidEntity) {
        ProductEntity product = bidEntity.getProduct();
        return new SentBidsDTO(
                bidEntity.getPublishDate(),
                bidEntity.getAmount(),
                new ProductDTO(
                        product.getId(),
                        product.getTitle(),
                        new VendorDTO(product.getVendedor().getName())
                )
        );
    }

    private ReceivedBidsDTO entityBidToReceivedBid(BidEntity bidEntity) {
        var product = bidEntity.getProduct();
        var vendor = product.getVendedor();
        return new ReceivedBidsDTO(
                bidEntity.getPublishDate(),
                bidEntity.getAmount(),
                new ProductDTO(product.getId(), product.getTitle(), new VendorDTO(vendor.getName())),
                new UserDTO(bidEntity.getClient().getName())
        );
    }

    public List<ReceivedBidsDTO> getReceivedBids(@NonNull LoginDTO loginDTO, String startDateParameter, String endDateParameter, String productTitleParameter, String clientNameParameter, String pageParameter, String orderByParameter, String ascParameter) {
        Date startDate = "".equals(startDateParameter) || startDateParameter == null ? null : Date.valueOf(startDateParameter);
        Date endDate = "".equals(endDateParameter) || endDateParameter == null ? null : Date.valueOf(endDateParameter);
        String productTitle = "".equals(productTitleParameter) ? null : productTitleParameter;
        String clientName = "".equals(clientNameParameter) ? null : clientNameParameter;
        var orderBy = orderByParameter == null || orderByParameter.equals("") ? "publishDate" : orderByParameter;
        var asc = ascParameter != null;
        int page = "".equals(pageParameter) || pageParameter == null ? 0 : Integer.parseInt(pageParameter);
        if (page < 0) throw new IllegalArgumentException("Negative page index");

        var vendor = loginCredentialsRepository.findLoginCredentialsEntityByUsername(loginDTO.getUsername()).getClient();
        Stream<BidEntity> bidEntityStream = bidRepositoryCustom.getFilteredBidsFromVendor(vendor, page, startDate, endDate, productTitle, clientName, orderBy, asc);

        return bidEntityStream.map(this::entityBidToReceivedBid).collect(Collectors.toList());
    }

    public List<SentBidsDTO> getSentBids(@NonNull LoginDTO loginDTO, String startDateParameter, String endDateParameter, String productTitleParameter, String vendorNameParameter, String pageParameter, String orderByParameter, String ascParameter) {
        Date startDate = "".equals(startDateParameter) || startDateParameter == null ? null : Date.valueOf(startDateParameter);
        Date endDate = "".equals(endDateParameter) || endDateParameter == null ? null : Date.valueOf(endDateParameter);
        String productTitle = "".equals(productTitleParameter) ? null : productTitleParameter;
        String vendorName = "".equals(vendorNameParameter) ? null : vendorNameParameter;
        var orderBy = orderByParameter == null || orderByParameter.equals("") ? "publishDate" : orderByParameter;
        var asc = ascParameter != null;
        int page = "".equals(pageParameter) || pageParameter == null ? 0 : Integer.parseInt(pageParameter);
        if (page < 0) throw new IllegalArgumentException("Negative page index");

        var vendor = loginCredentialsRepository.findLoginCredentialsEntityByUsername(loginDTO.getUsername()).getClient();
        Stream<BidEntity> bidEntityStream = bidRepositoryCustom.getFilteredBidsFromUser(vendor, page, startDate, endDate, productTitle, vendorName, orderBy, asc);
        return bidEntityStream.map(this::entityBidToSentBid).collect(Collectors.toList());
    }

    public void createBid(@NonNull LoginDTO loginDTO, @NonNull String amountParameter, @NonNull String productIDParameter) {
        var vendor = loginCredentialsRepository.findLoginCredentialsEntityByUsername(loginDTO.getUsername()).getClient();
        var timestamp = new Timestamp(new java.util.Date().getTime());

        var amount = Double.parseDouble(amountParameter);
        var productId = Integer.parseInt(productIDParameter);
        var product = productRepository.findById(productId).get();

        if (product == null) throw new IllegalArgumentException("The given product ID doesn't exist");
        if (product.getCloseDate() != null)
            throw new IllegalArgumentException("The given product is no longer available");
        if (product.getOutPrice() > amount)
            throw new IllegalArgumentException("The received amount is lower than the starting bid");

        var highestBid = bidRepositoryCustom.getHighestBidByProduct(product);
        if (highestBid != null && highestBid.getAmount() >= amount) {
            throw new IllegalArgumentException("A higher bid exist. Current bid amount: " + highestBid.getAmount());
        }


        BidEntity bid = BidEntity
                .builder()
                .publishDate(timestamp)
                .amount(amount)
                .client(vendor)
                .product(product)
                .build();
        bidRepository.save(bid);
    }
}
