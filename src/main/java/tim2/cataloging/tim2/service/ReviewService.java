package tim2.cataloging.tim2.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tim2.cataloging.tim2.model.Review;
import tim2.cataloging.tim2.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Review findOne(Long id){ return reviewRepository.findById(id).orElse(null); }
    public List<Review> findAll(){ return reviewRepository.findAll(); }
    public Review save(Review review){ return reviewRepository.save(review); }
    public void deleteById(Long id){ reviewRepository.deleteById(id);}

    public List<Review> findByUserId(Long id){ return reviewRepository.findByUserId(id); }

    @Transactional
    public void deleteAllByUserId(Long userId) {
        entityManager.createQuery("DELETE FROM Review r WHERE r.user.id = :userId")
            .setParameter("userId", userId)
            .executeUpdate();
    }
}
