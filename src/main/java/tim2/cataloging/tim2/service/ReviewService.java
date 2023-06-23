package tim2.cataloging.tim2.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tim2.cataloging.tim2.model.Book;
import tim2.cataloging.tim2.model.Review;
import tim2.cataloging.tim2.model.ShelfItem;
import tim2.cataloging.tim2.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ShelfItemService shelfItemService;

    @Autowired
    private BookService bookService;

    @PersistenceContext
    private EntityManager entityManager;

    public Review findOne(Long id){ return reviewRepository.findById(id).orElse(null); }
    public List<Review> findAll(){ return reviewRepository.findAll(); }
    public Review save(Review review){ return reviewRepository.save(review); }
    public void deleteById(Long id){
        List<ShelfItem> shelfItems= shelfItemService.findAll();

        reviewRepository.deleteById(id);

        for (ShelfItem shelfItem : shelfItems) {
            double rating = 0;
            for (Review r : shelfItem.getReviews()) {
                rating += r.getRating();
            }
            rating /= shelfItem.getReviews().size();
            Book book = bookService.findOne(shelfItem.getId());
            book.setRating((int) rating);
            bookService.save(book);
            shelfItem.setBook(book);
            shelfItemService.save(shelfItem);
        }


        reviewRepository.deleteById(id);
    }

}
