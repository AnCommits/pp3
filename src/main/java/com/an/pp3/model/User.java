package com.an.pp3.model;

import com.an.pp3.constant.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar birthDate;

    @Column(name = "record_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDateTime;

    public User(String firstName, String lastName, Gender gender, String phoneNumber, Calendar birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String birthDateToString() {
        return birthDate != null
                ? new SimpleDateFormat("yyyy-MM-dd").format(birthDate.getTime())
                : "";
    }

    public String recordDateTimeToString() {
        return new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss").format(recordDateTime);
    }
}
