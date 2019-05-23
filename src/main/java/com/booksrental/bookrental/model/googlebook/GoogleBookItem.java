package com.booksrental.bookrental.model.googlebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoogleBookItem {
    @Id
    String id;
    String selfLink;
    GoogleBookVolumeInfo volumeInfo;
}
