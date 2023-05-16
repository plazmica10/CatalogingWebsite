package tim2.cataloging.tim2.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tim2.cataloging.tim2.model.Review;
import tim2.cataloging.tim2.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review findOne(Long id){ return reviewRepository.findById(id).orElse(null); }
    public List<Review> findAll(){ return reviewRepository.findAll(); }
    public Review save(Review review){ return reviewRepository.save(review); }
    public void deleteById(Long id){ reviewRepository.deleteById(id);}
}
