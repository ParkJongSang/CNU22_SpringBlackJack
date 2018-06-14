package kr.ac.cnu.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rokim on 2018. 5. 25..
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String name; //유저 이름
    private long account; //계좌 잔고

}
