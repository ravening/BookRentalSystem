package com.booksrental.bookrental.model.goodreads;

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
@XmlRootElement(name = "best_book")
@XmlAccessorType(XmlAccessType.FIELD)
public class BestBook {
    @XmlElement(name = "id")
    int id;

    @XmlElement(name = "title")
    String title;

    @XmlElement(name = "author")
    Author author;

    @XmlElement(name = "image_url")
    String imageUrl;

    @XmlElement(name = "small_image_url")
    String smallImageUrl;
}
