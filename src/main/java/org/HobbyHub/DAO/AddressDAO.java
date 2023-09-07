package org.HobbyHub.DAO;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.entities.Address;

public class AddressDAO {
private static EntityManagerFactory emf;
private static AddressDAO instance;


    public static AddressDAO getInstance(EntityManagerFactory _emf){
        if(instance == null) {
            emf = _emf;
            instance = new AddressDAO();
        }
    return instance;
        }

        public void createAddress(Address address){
        try (var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            }
     }

    public Address getAddressById(int id){
        try (var em = emf.createEntityManager()){
            return em.find(Address.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAddress(Address address) {
            try (var em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.persist(address);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void deleteAddress(Address address){
        try (var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
