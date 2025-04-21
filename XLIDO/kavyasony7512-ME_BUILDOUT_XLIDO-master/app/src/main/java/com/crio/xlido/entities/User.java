package com.crio.xlido.entities;

public class User {
   private final Long id;
   private final String email;
   private  String password;

  
   public User(String email, String password) {
       this.id = null; // ID will be set by the repository
       this.email = email;
       this.password = password;
   }

   public User(Long id, User other) {
       this.id = id;
       this.email = other.email;
       this.password = other.password;
   }

   
   public Long getId() {
       return id;
   }
   public String getEmail() {
       return email;
   }
   public String getPassword() {
       return password;
   }

   public void setPassword(String password) {
       this.password = password;
   }

   @Override
   public String toString() {
       return "User [id=" + id + "]";
   }

   
}