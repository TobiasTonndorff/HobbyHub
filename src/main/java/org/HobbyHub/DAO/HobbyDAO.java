package org.HobbyHub.DAO;

import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.dto.HobbyUserCountDTO;
import org.HobbyHub.entities.Hobby;

import java.util.List;

public class HobbyDAO {

    private static EntityManagerFactory emf;
    private static HobbyDAO instance;

    public static HobbyDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyDAO();

        }
        return instance;
    }


    public void createHobby(Hobby hobby){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        }

    }

    public Hobby getHobbyById(int id){

        try(var em = emf.createEntityManager()){
            return em.find(Hobby.class, id);
        }
    }
    
    public Hobby updateHobby(Hobby hobby){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
            return hobby;
        }
    }

    public void deleteHobby(int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Hobby hobby = em.find(Hobby.class, id);
            em.remove(hobby);
            em.getTransaction().commit();
        }
    }

    public HobbyUserCountDTO getHobbyUserCount(int id){
        try(var em = emf.createEntityManager()){
            return em.createNamedQuery("Hobby.HobbyUserCountDTO", HobbyUserCountDTO.class).setParameter("id", id).getSingleResult();
        }
    }

    public List<HobbyUserCountDTO> getHobbiesUserCount(){
        try(var em = emf.createEntityManager()){
            return em.createNamedQuery("Hobby.HobbiesUserCountDTO", HobbyUserCountDTO.class).getResultList();
        }
    }


}
