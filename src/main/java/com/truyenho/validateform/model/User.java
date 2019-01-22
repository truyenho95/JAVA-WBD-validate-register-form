package com.truyenho.validateform.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class User implements Validator {
  @NotEmpty
  @Size(min = 5, max = 45)
  private String firstName;
  @NotEmpty
  @Size(min = 5, max = 45)
  private String lastName;
  @NotNull
  @Min(18)
  private int age;
  @NotNull
  private String phoneNumber;
  @NotEmpty
  private String email;

  public User() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    User user = (User) target;
    String phoneNumber = user.phoneNumber;
    String email = user.email;
    if (phoneNumber.length() != 10) {
      errors.rejectValue("phoneNumber", "phoneNumber.length");
    }
    if (!phoneNumber.startsWith("0")) {
      errors.rejectValue("phoneNumber", "phoneNumber.startsWith");
    }
    if (!phoneNumber.matches("(^$|[0-9]*$)")) {
      errors.rejectValue("phoneNumber", "phoneNumber.matches");
    }
    if (!email.matches("(^[a-z_.]+@[a-z]+.[a-z]+$)")) {
      errors.rejectValue("email", "email.matches");
    }
  }
}
