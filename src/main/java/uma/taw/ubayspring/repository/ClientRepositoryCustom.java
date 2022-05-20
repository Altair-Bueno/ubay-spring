package uma.taw.ubayspring.repository;

import org.springframework.stereotype.Repository;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.types.GenderEnum;

import java.util.List;

public interface ClientRepositoryCustom {
    public List<ClientEntity> filterClients(String name, String lastName, GenderEnum gender, String address, String city, String id);
}
