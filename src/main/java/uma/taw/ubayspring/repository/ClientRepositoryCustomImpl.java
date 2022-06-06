package uma.taw.ubayspring.repository;

import org.springframework.stereotype.Repository;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.types.GenderEnum;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author: José Luis Bueno Pachón
 */

@Repository
public class ClientRepositoryCustomImpl implements ClientRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ClientEntity> filterClients(String name, String lastName, GenderEnum gender, String address, String city, Integer id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> query = builder.createQuery(ClientEntity.class);
        Root<ClientEntity> clientTable = query.from(ClientEntity.class);
        query.select(clientTable);
        List<Predicate> predicateList = new ArrayList<>();

        if (id != null) {
            predicateList.add(builder.equal(clientTable.get("id"), id));
        }

        if (gender != null) {
            predicateList.add(builder.equal(clientTable.get("gender"), gender));
        }

        if (address != null) {
            predicateList.add(builder.like(builder.upper(clientTable.get("address")), "%" + address.toUpperCase(Locale.ROOT) + "%"));
        }
        if (city != null) {
            predicateList.add(builder.like(builder.upper(clientTable.get("city")), "%" + city.toUpperCase(Locale.ROOT) + "%"));
        }

        if (name != null) {
            predicateList.add(builder.like(builder.upper(clientTable.get("name")), "%" + name.toUpperCase(Locale.ROOT) + "%"));
        }

        if (lastName != null) {
            predicateList.add(builder.like(builder.upper(clientTable.get("lastName")), "%" + lastName.toUpperCase(Locale.ROOT) + "%"));
        }

        query.select(clientTable)
                .where(predicateList.toArray(new Predicate[0]))
                .orderBy(builder.asc(clientTable.get("id")));
        return em.createQuery(query).getResultList();
    }
}
