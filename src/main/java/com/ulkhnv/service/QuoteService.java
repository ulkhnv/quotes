package com.ulkhnv.service;

import com.ulkhnv.model.Quote;
import com.ulkhnv.model.User;
import com.ulkhnv.repository.QuoteRepository;
import com.ulkhnv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, UserRepository userRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.getAllQuotes();
    }

    public List<Quote> getTop10Quotes() {
        return quoteRepository.getTop10Quotes();
    }

    @Transactional
    public void addQuote(Quote quote, Long userId) {
        final User user = userRepository.findUserById(userId);
        quote.setAuthor(user);
        quoteRepository.addQuote(quote);
    }

    @Transactional
    public boolean updateQuote(Quote quote, Long id) {
        if (quoteRepository.findQuoteById(id) == null) {
            return false;
        }
        quoteRepository.updateQuote(quote);
        return true;
    }

    @Transactional
    public boolean removeQuote(Long id) {
        final Quote quote = quoteRepository.findQuoteById(id);
        if (quote == null) {
            return false;
        }
        quoteRepository.removeQuote(quote);
        return true;
    }

    @Transactional
    public void addLike(Long userId, Long quoteId) {
        final Quote quote = quoteRepository.findQuoteById(quoteId);
        final User user = userRepository.findUserById(userId);

        quote.getDislikedUsers().remove(user);
        quote.getLikedUsers().add(user);
        quote.setScore(quote.getScore() + 1);
        quoteRepository.updateQuote(quote);
    }

    @Transactional
    public void addDislike(Long userId, Long quoteId) {
        final Quote quote = quoteRepository.findQuoteById(quoteId);
        final User user = userRepository.findUserById(userId);

        quote.getLikedUsers().remove(user);
        quote.getDislikedUsers().add(user);
        quote.setScore(quote.getScore() - 1);
        quoteRepository.updateQuote(quote);
    }
}
