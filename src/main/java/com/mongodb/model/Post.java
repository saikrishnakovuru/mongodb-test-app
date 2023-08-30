package com.mongodb.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Document(collection = "JobPost")
public class Post {
  private String profile;
  private String desc;
  private int exp;
  private String[] techs;

}
