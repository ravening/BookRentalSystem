package com.booksrental.bookrental.model.goodreads;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "work")
@XmlAccessorType(XmlAccessType.FIELD)
public class Work {
    @XmlElement(name = "id")
    int id;

    @XmlElement(name = "books_count")
    int booksCount;

    @XmlElement(name = "ratings_count")
    int ratingsCount;

    @XmlElement(name = "text_reviews_count")
    int textReviewCount;

    @XmlElement(name = "original_publication_year")
    int originalPublicationYear;

    @XmlElement(name = "original_publication_month")
    int originalPublicationMonth;

    @XmlElement(name = "original_publication_day")
    int origianlPublicationDay;

    @XmlElement(name = "average_rating")
    int averageRating;

    @XmlElement(name = "best_book")
    BestBook bestBook;
}
