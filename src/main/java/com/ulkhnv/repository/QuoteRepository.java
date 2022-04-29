package com.ulkhnv.repository;

import com.ulkhnv.model.Quote;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuoteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Quote> getAllQuotes() {
        return entityManager.createQuery("SELECT q FROM Quote q", Quote.class).getResultList();
    }

    public void addQuote(Quote quote) {
        entityManager.persist(quote);
    }

    public Quote findQuoteById(Long id) {
        return entityManager.find(Quote.class, id);
    }

    public void updateQuote(Quote quote) {
        entityManager.merge(quote);
    }

    public void removeQuote(Quote quote) {
        entityManager.remove(quote);
    }

    public List<Quote> getTop10Quotes() {
        return entityManager.createQuery("SELECT q FROM Quote q ORDER BY q.score DESC", Quote.class)
                .setMaxResults(10).getResultList();
    }
}
