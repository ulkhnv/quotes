package com.ulkhnv.controller;

import com.ulkhnv.model.Quote;
import com.ulkhnv.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public ResponseEntity<List<Quote>> getAllQuotes() {
        final List<Quote> quotes = quoteService.getAllQuotes();
        return quotes != null && !quotes.isEmpty()
                ? new ResponseEntity<>(quotes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/top10Quotes")
    public ResponseEntity<List<Quote>> getTop10Quotes() {
        final List<Quote> quotes = quoteService.getTop10Quotes();
        return quotes != null && !quotes.isEmpty()
                ? new ResponseEntity<>(quotes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addQuote(@RequestBody Quote quote, @RequestParam("userId") Long userId) {
        quoteService.addQuote(quote, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuote(@PathVariable(name = "id") Long id, @RequestBody Quote quote) {
        final boolean updated = quoteService.updateQuote(quote, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeQuote(@PathVariable(name = "id") Long id) {
        final boolean deleted = quoteService.removeQuote(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/addLike")
    public ResponseEntity<?> addLike(@RequestParam("userId") Long userId, @RequestParam("quoteId") Long quoteId) {
        quoteService.addLike(userId, quoteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addDislike")
    public ResponseEntity<?> addDislike(@RequestParam("userId") Long userId, @RequestParam("quoteId") Long quoteId) {
        quoteService.addDislike(userId, quoteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
