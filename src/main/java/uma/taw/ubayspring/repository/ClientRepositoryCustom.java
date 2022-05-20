package uma.taw.ubayspring.repository;

import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.types.GenderEnum;

import java.util.List;

public interface ClientRepositoryCustom {
    List<ClientEntity> filterClients(String name, String lastName, GenderEnum gender, String address, String city, String id);
}
