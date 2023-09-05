package org.HobbyHub.DAO;


import jakarta.persistence.EntityManagerFactory;

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

        public void saveAddress(AddressDAO addressDAO){
        try (var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(addressDAO);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            }


        }

        public void updateAddress(AddressDAO addressDAO) {
            try (var em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.persist(addressDAO);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void deleteAddress(AddressDAO addressDAO){
            try (var em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.remove(addressDAO);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
